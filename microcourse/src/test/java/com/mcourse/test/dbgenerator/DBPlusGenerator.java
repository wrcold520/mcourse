package com.mcourse.test.dbgenerator;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.generator.DBHelper;
import com.mcourse.frame.utils.SysPropUtils;
import com.mcourse.module.app.model.McPicGallery;
import com.mcourse.module.course.model.McCategory;
import com.mcourse.module.course.model.McCourse;
import com.mcourse.module.course.model.McVideo;
import com.mcourse.module.system.model.McDictionary;
import com.mcourse.module.system.model.McUser;

/**
 * 目前只支持mysql
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月8日下午7:47:05
 */
public class DBPlusGenerator {

	public static final String BASEPATH = SysPropUtils.USER_DIR + SysPropUtils.FILE_SEPARATOR;

	/**
	 * 加载Log4j配置文件，filepath为空，传递默认的log4j配置
	 * 
	 * @param filepath
	 */
	public static void loadLog4j(String filepath) {
		// 默认路径
		if (StringUtils.isBlank(filepath)) {
			filepath = BASEPATH + "src\\main\\resources\\config\\log\\log4j.properties";
		}
		PropertyConfigurator.configure(filepath);
	}

	/**
	 * 配置基本参数
	 * 
	 * @return
	 */
	public static DBHelper configureDBHelper() {
		// jdbc配置文件路径
		String jdbcPropFilepath = BASEPATH + "src\\main\\resources\\config\\database\\jdbc.properties";
		// 指定freemarker的模板目录
		String fmTemplateDirpath = BASEPATH + "src\\main\\java\\com\\mcourse\\frame\\db\\generator\\template";
		// log4j配置文件路径
		String log4jFilepath = BASEPATH + "src\\main\\resources\\config\\log\\log4j.properties";

		// 加载Log4j配置文件
		loadLog4j(log4jFilepath);

		// 获取DBHelper实例
		DBHelper dbHelper = DBHelper.getInstance();
		dbHelper.loadJDBC(jdbcPropFilepath);
		dbHelper.setFmTemplateDir(fmTemplateDirpath);
		return dbHelper;

	}

	/**
	 * 生成代码
	 * 
	 * @param modelClass
	 *            实体class
	 * @param dropTableIfExist
	 *            如果
	 * @param modelDirPath
	 *            模块dao和service所在的dirpath
	 * 
	 * @throws IOException
	 */
	public static void generate(Class<? extends BaseModel> modelClass, String modelDirPath, boolean dropTableIfExist, boolean deleteDaoIfExist,
			boolean deleteServiceIfExist) throws IOException {

		DBHelper dbHelper = configureDBHelper();

		dbHelper.generateTable(modelClass, dropTableIfExist);
		dbHelper.generateDao(modelClass, modelDirPath + SysPropUtils.FILE_SEPARATOR + "dao", deleteDaoIfExist);
		dbHelper.generateService(modelClass, modelDirPath + SysPropUtils.FILE_SEPARATOR + "service", deleteServiceIfExist);

	}

	/**
	 * 根据实体类的注解生成Table，生成Dao、DaoImpl，生成Service、ServiceImpl
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		generate(McUser.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\system", true, false, false);
		generate(McCategory.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\course", true, false, false);
		generate(McCourse.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\course", true, false, false);
		generate(McPicGallery.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\app", true, false, false);
		generate(McVideo.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\course", true, false, false);
		generate(McDictionary.class, BASEPATH + "src\\main\\java\\com\\mcourse\\module\\system", true, false, false);
	}

}
