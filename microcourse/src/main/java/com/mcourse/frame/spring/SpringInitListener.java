package com.mcourse.frame.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Title Spring初始化完成后调用的方法
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/05/21 12:06:25
 */
public class SpringInitListener implements ApplicationListener<ContextRefreshedEvent> {

	public static final Logger logger = LoggerFactory.getLogger(SpringInitListener.class);

	/**
	 * @Title
	 * @Description
	 * 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			logger.debug("Spring 初始化完成...");
			ApplicationContext appctx = event.getApplicationContext();
			String[] beanNames = appctx.getBeanDefinitionNames();
			logger.debug("初始化的Bean列表：");
			for (String beanName : beanNames) {
				logger.debug("beanName --> " + beanName);
			}
		}
	}

}
