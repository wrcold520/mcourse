package com.mcourse.module.course.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.module.course.model.McCourse;

/**
 * CourseServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<McCourse> implements ICourseService {

	@Resource(name = "courseDao")
	@Override
	public void setBaseDao(IBaseDao<McCourse> baseDao) {
		super.setBaseDao(baseDao);
	}

}
