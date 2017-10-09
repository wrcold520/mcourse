package com.mcourse.module.course.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.module.course.model.McCategory;

/**
 * CategoryServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<McCategory> implements ICategoryService {

	@Resource(name = "categoryDao")
	@Override
	public void setBaseDao(IBaseDao<McCategory> baseDao) {
		super.setBaseDao(baseDao);
	}

}