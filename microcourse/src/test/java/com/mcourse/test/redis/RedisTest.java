package com.mcourse.test.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.mcourse.test.dbgenerator.DBPlusGenerator;

public class RedisTest {

	public static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

	// spring appctx
	private ConfigurableApplicationContext appctx;

	// redistemplate
	public RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings("unchecked")
	@Before
	public void before() {
		DBPlusGenerator.loadLog4j(null);

		String[] resources = new String[] { "classpath:config/spring/spring.xml" };
		appctx = new ClassPathXmlApplicationContext(resources);
		redisTemplate = appctx.getBean(RedisTemplate.class);

		// 启用事务
		redisTemplate.setEnableTransactionSupport(true);
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	public void redisTest() {
		ListOperations<String, Object> listOps = redisTemplate.opsForList();
		listOps.rightPush("listOps_int", 10);
		listOps.rightPush("listOps_int", 11);
		listOps.rightPush("listOps_int", 12);
	}

	@After
	public void afert() {
		appctx.close();
	}
}
