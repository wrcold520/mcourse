package com.mcourse.frame.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Title
 * @Description
 * 
 * @Created Assassin
 * @DateTime 2017/06/18 21:25:22
 */
public class SpringCtxUtils implements ApplicationContextAware {

	public static final Logger logger = LoggerFactory.getLogger(SpringCtxUtils.class);

	@Autowired
	public static ApplicationContext appctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringCtxUtils.appctx = applicationContext;
	}

	public static Object getBean(String beanname) {
		return appctx.getBean(beanname);
	}

	public static <T> Object getBean(Class<T> beanclazz) {
		return appctx.getBean(beanclazz);
	}

}
