package com.mcourse.frame.db.generator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.InfomationSchema;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.IDPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.exception.DBException;
import com.mcourse.frame.db.base.mapping.ModelMapping;
import com.mcourse.frame.db.dbutilsplus.DBPlus;
import com.mcourse.frame.db.dbutilsplus.annoparse.DbutilsPlusAnnoParse;
import com.mcourse.frame.db.dbutilsplus.sqlconvert.MySqlConvert;
import com.mcourse.frame.utils.BeanUtils;
import com.mcourse.frame.utils.FileUtils;
import com.mcourse.frame.utils.FreemarkerUtils;
import com.mcourse.frame.utils.PropUtils;
import com.mcourse.frame.utils.SysPropUtils;
import com.mcourse.frame.utils.date.DatePattern;
import com.mcourse.frame.utils.date.DateUtils;
import com.mcourse.frame.utils.json.JsonUtils;

/**
 * DBHelper辅助帮助类
 * 
 * @author Administrator
 *
 */
public class DBHelper {

	public static final Logger logger = LoggerFactory.getLogger(DBHelper.class);

	/** 换行符 **/
	private static final String NEXTLINE = SysPropUtils.LINE_SEPARATOR;
	/** 文件路径分隔符 **/
	private static final String FILE_SEPARATOR = SysPropUtils.FILE_SEPARATOR;

	private static final DBPlus dbPlus = new DBPlus();

	static {
		dbPlus.registerSqlConvert(MySqlConvert.getInstance());
		dbPlus.registerDbAnnoParse(DbutilsPlusAnnoParse.getInstance());
	}

	/** loadJDBC()加载jdbc属性文件的时候创建DataSource **/
	private BasicDataSource dataSource;

	/** loadJDBC()加载jdbc属性文件的时候创建queryRunner **/
	private QueryRunner queryRunner;

	// freemarker模板所在目录
	private String fmTemplateDir;

	/**
	 * 加载jdbc属性文件
	 * 
	 * @param jdbcPropFile
	 */
	public void loadJDBC(String jdbcPropFile) {

		// 读取属性文件
		PropUtils.use(jdbcPropFile, "jdbc", true);

		String jdbcDriver = PropUtils.getString("jdbc", "jdbc.driver");
		String jdbcUrl = PropUtils.getString("jdbc", "jdbc.url");
		String jdbcUsername = PropUtils.getString("jdbc", "jdbc.username");
		String jdbcPassword = PropUtils.getString("jdbc", "jdbc.password");

		// 创建数据源
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);

		// 创建queryRunner
		queryRunner = new QueryRunner(dataSource);
	}

	/**
	 * 生成数据库表结构
	 * 
	 * @param modelClass
	 *            实体类
	 * @param dropTableIfExist
	 *            如果存在是否删除
	 */
	public void generateTable(Class<? extends BaseModel> modelClass, boolean dropTableIfExist) {

		ModelMapping modelMapping = dbPlus.getMapping(modelClass);
		String tablename = modelMapping.getTableName();

		logger.info("[ Ready ] Table: " + tablename + ", Class: " + modelClass);

		// 查找对应的数据库表名称
		String queryTableSql = buildQueryTableSql(tablename);
		// 删除对应的数据库表
		String dropTableSql = buildDropTableSql(modelMapping);
		// 创建对应的数据库表
		String createTableSql = buildCreateTableSql(modelMapping);

		Connection connection = null;
		try {
			connection = queryRunner.getDataSource().getConnection();
			connection.setAutoCommit(false);

			logger.info("[ Query ]: " + queryTableSql);
			List<InfomationSchema> dbTables = queryRunner.query(queryTableSql, new BeanListHandler<InfomationSchema>(InfomationSchema.class),
					new Object[] {});
			logger.info("[ Query Result ]: " + JsonUtils.format(JsonUtils.stringify(dbTables)));
			if (!(!dbTables.isEmpty() && !dropTableIfExist)) {
				// 有表，先删除表
				logger.info("[ Drop ] table " + tablename + " is not exist, countinue next step...");
				logger.info("DropTableSql: " + dropTableSql);
				queryRunner.update(dropTableSql);
				logger.info("[ Drop ] drop table " + tablename + " successfully!");

				// 然后创建
				logger.info("CreateTableSql: " + createTableSql);
				queryRunner.update(createTableSql);

				connection.commit();
				logger.info("[ Create ] create table " + tablename + " successfully!");
			}

		} catch (Exception e) {
			logger.error("[ Create ] create table " + tablename + " failed! Exception: " + e.toString());
			if (connection != null) {
				try {
					connection.rollback();
					connection.close();
					logger.info("[ Rollback ] generate for " + tablename + " successfully !");
				} catch (SQLException sqlEx) {
					throw new DBException("[ Rollback ] generate for " + tablename + " failed !", sqlEx);
				}
			}
		}
	}

	/**
	 * 生成IDao daoImpl
	 * 
	 * @param modelClass
	 *            model
	 * @param distDirectory
	 *            生成文件的所在目录
	 * @param deleteIfExist
	 *            如果存在是否删除重新生成
	 * @throws IOException
	 */
	public void generateDao(Class<? extends BaseModel> modelClass, String distDirectory, boolean deleteIfExist) throws IOException {
		String modelName = modelClass.getSimpleName().replaceAll("(?i)mc", "");// User
		String modelNameLowercase = StringUtils.lowerCase(modelName);// user
		String daoFileName = "I" + modelName + "Dao.java";
		String daoImplFileName = modelName + "DaoImpl.java";
		String idaoPath = distDirectory + FILE_SEPARATOR + daoFileName;// IUserDao.java
		String daoImplPath = distDirectory + FILE_SEPARATOR + daoImplFileName;// UserDaoImpl.java

		Map<String, Object> dataModel = new LinkedHashMap<String, Object>();
		// datetime
		dataModel.put("datetime", DateUtils.format(new Date(), DatePattern.DATETIME_SLASH));
		// model
		dataModel.put("modelClass", modelClass.getName().toString());
		dataModel.put("modelClassName", modelClass.getSimpleName());
		dataModel.put("modelName", modelName);
		dataModel.put("modelNameLowercase", modelNameLowercase);
		// dao
		dataModel.put("daoPackageName", idaoPath.substring(idaoPath.indexOf("com")).replace(SysPropUtils.FILE_SEPARATOR + daoFileName, "")
				.replace(SysPropUtils.FILE_SEPARATOR, "."));
		dataModel.put("daoFileName", daoFileName);
		dataModel.put("daoClassSimpleName", daoFileName.replace(".java", ""));

		// daoimpl
		dataModel.put("daoImplPackageName", daoImplPath.substring(daoImplPath.indexOf("com"))
				.replace(SysPropUtils.FILE_SEPARATOR + daoImplFileName, "").replace(SysPropUtils.FILE_SEPARATOR, "."));
		dataModel.put("daoImplFileName", daoImplFileName);
		dataModel.put("daoImplClassSimpleName", daoImplFileName.replace(".java", ""));

		if (!(FileUtils.isExist(idaoPath) && !deleteIfExist)) {
			FreemarkerUtils.renderFile(fmTemplateDir, "idao.ftl", dataModel, idaoPath);
		}
		if (!(FileUtils.isExist(daoImplPath) && !deleteIfExist)) {
			FreemarkerUtils.renderFile(fmTemplateDir, "daoImpl.ftl", dataModel, daoImplPath);
		}
	}

	/**
	 * 生成IService serviceImpl
	 * 
	 * @param modelClass
	 *            model
	 * @param distDirectory
	 *            生成文件的所在目录
	 * @param deleteIfExist
	 *            如果存在是否删除重新生成
	 * @throws IOException
	 */

	public void generateService(Class<? extends BaseModel> modelClass, String distDirectory, boolean deleteIfExist) throws IOException {
		String modelName = modelClass.getSimpleName().replaceAll("(?i)mc", "");// User
		String modelNameLowercase = StringUtils.lowerCase(modelName);// user
		String serviceFileName = "I" + modelName + "Service.java";
		String serviceImplFileName = modelName + "ServiceImpl.java";
		String iservicePath = distDirectory + FILE_SEPARATOR + serviceFileName;// IUserServce.java
		String serviceImplPath = distDirectory + FILE_SEPARATOR + serviceImplFileName;// UserServiceImpl.java

		Map<String, Object> dataModel = new LinkedHashMap<String, Object>();
		// datetime
		dataModel.put("datetime", DateUtils.format(new Date(), DatePattern.DATETIME_SLASH));
		// model
		dataModel.put("modelClass", modelClass.getName().toString());
		dataModel.put("modelClassName", modelClass.getSimpleName());
		dataModel.put("modelName", modelName);
		dataModel.put("modelNameLowercase", modelNameLowercase);
		// service
		dataModel.put("servicePackageName", iservicePath.substring(iservicePath.indexOf("com"))
				.replace(SysPropUtils.FILE_SEPARATOR + serviceFileName, "").replace(SysPropUtils.FILE_SEPARATOR, "."));
		dataModel.put("serviceFileName", serviceFileName);
		dataModel.put("serviceClassSimpleName", serviceFileName.replace(".java", ""));

		// serviceImpl
		dataModel.put("serviceImplPackageName", serviceImplPath.substring(serviceImplPath.indexOf("com"))
				.replace(SysPropUtils.FILE_SEPARATOR + serviceImplFileName, "").replace(SysPropUtils.FILE_SEPARATOR, "."));
		dataModel.put("serviceImplFileName", serviceImplFileName);
		dataModel.put("serviceImplClassSimpleName", serviceImplFileName.replace(".java", ""));

		if (!(FileUtils.isExist(iservicePath) && !deleteIfExist)) {
			FreemarkerUtils.renderFile(fmTemplateDir, "iservice.ftl", dataModel, iservicePath);
		}
		if (!(FileUtils.isExist(serviceImplPath) && !deleteIfExist)) {
			FreemarkerUtils.renderFile(fmTemplateDir, "serviceImpl.ftl", dataModel, serviceImplPath);
		}
	}

	/**
	 * 创建当前数据库查询对应数据库表的sql语句
	 * 
	 * @param tablename
	 * @return
	 */
	private String buildQueryTableSql(String tablename) {
		// 数据库概要
		String tableschema = PropUtils.getString("jdbc", "jdbc.tableschema");

		String queryTableSql = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + tableschema + "' AND TABLE_NAME = '" + tablename
				+ "';";
		return queryTableSql;
	}

	/**
	 * 生成删除数据库表的sql语句
	 * 
	 * @param modelMapping
	 * @return
	 */
	private String buildDropTableSql(ModelMapping modelMapping) {
		String tablename = modelMapping.getTableName();
		String dropTableSql = "DROP TABLE IF EXISTS `" + tablename + "`;";
		return dropTableSql;
	}

	/**
	 * 生成创建数据库表的sql语句
	 * 
	 * @param modelMapping
	 * @return
	 */
	private String buildCreateTableSql(ModelMapping modelMapping) {
		/** sql **/
		StringBuffer createSqlBuffer = new StringBuffer();

		/** tablename **/
		String tablename = modelMapping.getTableName();

		createSqlBuffer.append("CREATE TABLE `" + tablename + "` (" + NEXTLINE);

		/** pkcolumn **/
		Field pkField = modelMapping.getPkField();
		String pkColumnName = modelMapping.getPkColumnName();
		// pk annotation
		IDPlus pkAnno = (IDPlus) pkField.getAnnotation(modelMapping.getAnnoClassPk());
		// autoincrement
		boolean pkAutoIncrement = pkAnno.autoIncrement();

		String pkComment = "";
		// field comment annotation
		Notes pkCommentAnno = (Notes) BeanUtils.getAnnotation(pkField, Notes.class);
		if (pkCommentAnno != null && StringUtils.isNotBlank(pkCommentAnno.value())) {
			pkComment = " COMMENT '" + pkCommentAnno.value() + "'";
		}

		createSqlBuffer.append("  `" + pkColumnName + "` BIGINT (20) NOT NULL ");
		createSqlBuffer.append(pkAutoIncrement ? "AUTO_INCREMENT" : "");
		createSqlBuffer.append(StringUtils.isBlank(pkComment) ? "" : pkComment);
		createSqlBuffer.append("," + NEXTLINE);

		/** columns **/
		Field[] filedataSource = modelMapping.getFieldsWithoutPk();
		for (Field field : filedataSource) {
			// column annotation
			ColumnPlus columnAnno = (ColumnPlus) BeanUtils.getAnnotation(field, ColumnPlus.class);
			String columnName = StringUtils.isBlank(columnAnno.name()) ? field.getName() : columnAnno.name();
			boolean unique = columnAnno.unique();
			boolean nullable = columnAnno.nullable();

			String fieldSqlTypeAndLength = SqlTypeUtils.getSqlTypeAndLength(field);
			createSqlBuffer.append("  `" + columnName + "` " + fieldSqlTypeAndLength);
			if (unique) {
				createSqlBuffer.append(" UNIQUE");
			}
			if (!nullable) {
				createSqlBuffer.append(" NOT NULL");
			}
			// field comment annotation
			Notes fieldCommentAnno = (Notes) BeanUtils.getAnnotation(field, Notes.class);
			if (fieldCommentAnno != null && StringUtils.isNotBlank(fieldCommentAnno.value())) {
				createSqlBuffer.append(" COMMENT '" + fieldCommentAnno.value() + "'");
			}
			createSqlBuffer.append("," + NEXTLINE);
		}

		// table comment annotation
		String tableComment = "";
		Notes tableCommentAnno = (Notes) BeanUtils.getAnnotation(modelMapping.getModelClass(), Notes.class);
		if (tableCommentAnno != null && StringUtils.isNotBlank(tableCommentAnno.value())) {
			tableComment = " COMMENT '" + tableCommentAnno.value() + "'";
		}

		createSqlBuffer.append("  PRIMARY KEY (`" + pkColumnName + "`)," + NEXTLINE);
		createSqlBuffer.append("  KEY `index_primary_key` (`" + pkColumnName + "`) USING BTREE" + NEXTLINE);
		createSqlBuffer.append(") ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4");
		createSqlBuffer.append(StringUtils.isBlank(tableComment) ? "" : tableComment);
		createSqlBuffer.append(";");

		return createSqlBuffer.toString();
	}

	/**
	 * 创建DBHelper的单例
	 * 
	 * @return
	 */
	public static DBHelper getInstance() {
		return DBHelperHolder.DBHELPER_INSTANCE;
	}

	public static class DBHelperHolder {
		private static final DBHelper DBHELPER_INSTANCE = new DBHelper();
	}

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public QueryRunner getQueryRunner() {
		return queryRunner;
	}

	public String getFmTemplateDir() {
		return fmTemplateDir;
	}

	public void setFmTemplateDir(String fmTemplateDir) {
		this.fmTemplateDir = fmTemplateDir;
	}

}
