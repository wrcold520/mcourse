package com.mcourse.module.course.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.module.course.model.McVideo;

/**
 * VideoServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("videoService")
public class VideoServiceImpl extends BaseServiceImpl<McVideo> implements IVideoService {

	@Resource(name = "videoDao")
	@Override
	public void setBaseDao(IBaseDao<McVideo> baseDao) {
		super.setBaseDao(baseDao);
	}

}
