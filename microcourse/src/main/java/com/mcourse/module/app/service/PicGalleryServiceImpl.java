package com.mcourse.module.app.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.module.app.model.McPicGallery;

/**
 * PicGalleryServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("picgalleryService")
public class PicGalleryServiceImpl extends BaseServiceImpl<McPicGallery> implements IPicGalleryService {

	@Resource(name = "picgalleryDao")
	@Override
	public void setBaseDao(IBaseDao<McPicGallery> baseDao) {
		super.setBaseDao(baseDao);
	}

}
