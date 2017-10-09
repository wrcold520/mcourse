package com.mcourse.module.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcourse.frame.base.controller.BaseController;
import com.mcourse.module.app.service.IAppIndexService;

/**
 * 前台App首页IndexController
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月5日下午3:14:40
 */
@Controller
@RequestMapping("/app")
public class AppIndexController extends BaseController {

	@Resource
	private IAppIndexService indexService;

	/**
	 * 到达首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		indexService.fillIndexData(model);

		return "/app/index";
	}

	/**
	 * 到达登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "/app/login";
	}

	/**
	 * 到达注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String toRegister() {
		return "/app/register";
	}

}
