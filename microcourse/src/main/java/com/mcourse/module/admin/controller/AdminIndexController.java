package com.mcourse.module.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcourse.frame.base.controller.BaseController;
import com.mcourse.module.system.service.IUserService;

/**
 * 后台管理系统首页
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月7日上午12:13:47
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

	@Resource
	private IUserService userService;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {

		return "/admin/index";
	}

	/**
	 * 到达登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "/admin/login";
	}
}
