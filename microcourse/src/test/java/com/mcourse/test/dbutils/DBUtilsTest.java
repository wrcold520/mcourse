package com.mcourse.test.dbutils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcourse.frame.constants.SysConstants;
import com.mcourse.frame.db.base.query.OrderBy;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.frame.db.base.query.WhereUtils;
import com.mcourse.frame.db.dbutilsplus.DBPlus;
import com.mcourse.frame.db.dbutilsplus.annoparse.DbutilsPlusAnnoParse;
import com.mcourse.frame.db.dbutilsplus.dbtemplate.DBUtilsTemplate;
import com.mcourse.frame.db.dbutilsplus.sqlconvert.MySqlConvert;
import com.mcourse.frame.utils.BeanUtils;
import com.mcourse.frame.utils.date.DateUtils;
import com.mcourse.frame.utils.json.JsonUtils;
import com.mcourse.module.course.model.McCategory;
import com.mcourse.test.dbgenerator.DBPlusGenerator;

/**
 * @Title
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/06/17 11:02:21
 */
public class DBUtilsTest {

	private static final DBPlus plus = new DBPlus();

	static {
		plus.registerSqlConvert(MySqlConvert.getInstance());
		plus.registerDbAnnoParse(DbutilsPlusAnnoParse.getInstance());
	}

	private ConfigurableApplicationContext appctx;

	private DBUtilsTemplate dbUtilsTemplate;

	@Before
	public void before() {
		DBPlusGenerator.loadLog4j(null);

		String[] resources = new String[] { "classpath:config/spring/spring.xml" };
		appctx = new ClassPathXmlApplicationContext(resources);
		dbUtilsTemplate = appctx.getBean(DBUtilsTemplate.class);
	}

	@Test
	public void dbutilsTest() throws SQLException {
		// testInsert();// 测试新增
		// testUpdate();// 测试更新
		// testDelete();// 测试删除
		// testQuery();// 测试查询
		// testCount();// 测试计数
	}

	@After
	public void after() {
		appctx.close();
	}

	public void testInsert() throws SQLException {
		McCategory category = new McCategory();
		// category.setSid(1L);
		category.setName("fenlei5");
		category.setDescription("felei5 xx");

		int affectrows = dbUtilsTemplate.insert(category);
		System.out.println("affectrows: " + affectrows);
	}

	public void testUpdate() throws SQLException {

		McCategory category = new McCategory();
		// category.setName("fenleixxx");
		category.setSort(5);
		// category.setDescription("fenleixxx desc");
		// category.setCreatedBy("wangzf");
		// category.setCreatedTime(new Date());
		// category.setUpdatedBy("wangzfff");
		category.setUpdatedTime(DateUtils.offsetDayOfMonth(new Date(), 1));

		Map<String, Object> setMap = BeanUtils.bean2map(category, false);

		int affectrows = dbUtilsTemplate.update(category.getClass(), setMap,
				new Where.Builder().Like("createdBy", "%admin%").build());

		System.out.println("affectrows: " + affectrows);
	}

	public void testDelete() throws SQLException {

		int affectrows = dbUtilsTemplate.delete(McCategory.class, WhereUtils.EQ(SysConstants.DB_PRIMARY_KEY, 1L));
		System.out.println("affectrows: " + affectrows);
	}

	public void testQuery() throws SQLException {

		int pageNo = 2;
		int pageSize = 5;
		Where where = new Where.Builder().EQ("createdBy", "admin").Page(pageNo, pageSize)
				.OrderBy(SysConstants.DB_PRIMARY_KEY, OrderBy.ASC).build();
		List<McCategory> categories = dbUtilsTemplate.query(McCategory.class, where);
		for (McCategory category : categories) {
			String json = JsonUtils.stringify(category);
			System.out.println(json);
		}
	}

	public void testCount() throws SQLException {

		Where where = new Where.Builder().EQ("createdBy", "admin").OrderBy(SysConstants.DB_PRIMARY_KEY, OrderBy.ASC)
				.build();
		int count = dbUtilsTemplate.count(McCategory.class, where);

		System.out.println("count: " + count);
	}

}
