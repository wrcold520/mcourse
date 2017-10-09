package com.mcourse.module.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.frame.db.base.query.OrderBy;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.frame.exception.ServiceException;
import com.mcourse.module.app.dao.IPicGalleryDao;
import com.mcourse.module.app.model.McPicGallery;
import com.mcourse.module.system.model.McUser;

@Service
public class AppIndexServiceImpl extends BaseServiceImpl<McUser> implements IAppIndexService {

	@Resource
	private IPicGalleryDao picgalleryDao;

	@Override
	public void fillIndexData(Model model) {
		try {
			List<McPicGallery> picList = new ArrayList<McPicGallery>();
			// 查询轮播记录
			Where where = new Where.Builder().EQ("groupId", 1).OrderBy("sort", OrderBy.ASC).build();
			picList = picgalleryDao.findByWhere(where);
			model.addAttribute("picList", picList);
		} catch (Exception e) {
			throw new ServiceException("获取首页数据时发生异常", e);
		}
	}
}
