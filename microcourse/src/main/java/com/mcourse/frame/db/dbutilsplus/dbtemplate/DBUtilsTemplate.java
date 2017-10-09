package com.mcourse.frame.db.dbutilsplus.dbtemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.mapping.SqlParamPairs;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.frame.db.dbutilsplus.DBPlus;
import com.mcourse.frame.db.dbutilsplus.annoparse.DbutilsPlusAnnoParse;
import com.mcourse.frame.db.dbutilsplus.handler.AnnoRowProcessor;
import com.mcourse.frame.db.dbutilsplus.sqlconvert.MySqlConvert;
import com.mcourse.frame.utils.Assert;
import com.mcourse.frame.utils.SysPropUtils;
import com.mcourse.frame.utils.json.JsonUtils;

/**
 * 
 * DBUtils模板
 * 
 * @Title
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/06/19 21:30:21
 */
public class DBUtilsTemplate {

	private static final Logger logger = LoggerFactory.getLogger(DBUtilsTemplate.class);

	// 向DBUtilsTemplate中注入dataSource
	@Resource(name = "dataSource")
	private DataSource dataSource;

	private QueryRunner queryRunner;

	private static DBPlus plus = new DBPlus();

	static {
		plus.registerSqlConvert(MySqlConvert.getInstance());
		plus.registerDbAnnoParse(DbutilsPlusAnnoParse.getInstance());
	}

	/**
	 * 插入数据操作
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public int insert(BaseModel model) throws SQLException {
		SqlParamPairs pairs = plus.buildInsertPairs(model);
		logger.info(pairs.toString());

		int affectedRows = getQueryRunner().update(pairs.getSql(), pairs.getValues());
		return affectedRows;

	}

	/**
	 * 插入数据操作返回主键ID
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public Long insertAndGetPk(BaseModel model) throws SQLException {
		SqlParamPairs pairs = plus.buildInsertPairs(model);
		logger.info(pairs.toString());

		Long pk = getQueryRunner().insert(pairs.getSql(), new ScalarHandler<Long>(), pairs.getValues());
		return pk;

	}

	/**
	 * 更新操作
	 * 
	 * @param setMap
	 *            要更新的值
	 * @param where
	 *            条件
	 * @return
	 * @throws SQLException
	 */
	public int update(Class<? extends BaseModel> modelClass, Map<String, Object> setMap, Where where) throws SQLException {
		SqlParamPairs pairs = plus.buildUpdatePairs(modelClass, setMap, where);
		logger.info(pairs.toString());

		int affectedRows = getQueryRunner().update(pairs.getSql(), pairs.getValues());
		return affectedRows;
	}

	/**
	 * 删除操作
	 * 
	 * @param modelClass
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public <T extends BaseModel> int delete(Class<T> modelClass, Where where) throws SQLException {
		SqlParamPairs pairs = plus.buildDeletePairs(modelClass, where);
		logger.info(pairs.toString());

		int affectedRows = getQueryRunner().update(pairs.getSql(), pairs.getValues());
		return affectedRows;
	}

	/**
	 * 查询
	 * 
	 * @param modelClass
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public <T extends BaseModel> List<T> query(Class<T> modelClass, Where where) throws SQLException {

		SqlParamPairs pairs = plus.buildQueryPairs(modelClass, where);
		List<T> modelList = getQueryRunner().query(pairs.getSql(), new BeanListHandler<T>(modelClass, new AnnoRowProcessor()), pairs.getValues());

		StringBuffer sql = new StringBuffer();
		sql.append(SysPropUtils.LINE_SEPARATOR + "[***** DBUTILS.sql    *****]: " + pairs.getSql());
		sql.append(SysPropUtils.LINE_SEPARATOR + "[***** DBUTILS.values *****]: " + JsonUtils.stringify(pairs.getValues()));
		sql.append(SysPropUtils.LINE_SEPARATOR + "[***** DBUTILS.result *****]: " + modelList.size());
		logger.info(sql.toString());

		return modelList;
	}

	/**
	 * 计数
	 * 
	 * @param clazz
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public <T extends BaseModel> int count(Class<T> modelClass, Where where) throws SQLException {
		SqlParamPairs pairs = plus.buildCountPairs(modelClass, where);
		logger.info(pairs.toString());

		return getQueryRunner().query(pairs.getSql(), new ScalarHandler<Long>(1), pairs.getValues()).intValue();
	}

	public DataSource getDatasource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 从DataSource中获取QueryRunner
	 * 
	 * @return
	 */
	public QueryRunner getQueryRunner() {
		Assert.notNull(dataSource, "The datasoure must not be null...");
		if (queryRunner == null) {
			queryRunner = new QueryRunner(dataSource);
		}
		return queryRunner;
	}

}
