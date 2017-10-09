package com.mcourse.module.app.service;

import org.springframework.ui.Model;

import com.mcourse.frame.base.service.IBaseService;
import com.mcourse.module.system.model.McUser;

public interface IAppIndexService extends IBaseService<McUser> {

	/**
	 * 填充首页数据
	 * 
	 * @param model
	 */
	void fillIndexData(Model model);

}
