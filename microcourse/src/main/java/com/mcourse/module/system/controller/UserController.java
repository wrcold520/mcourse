package com.mcourse.module.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcourse.frame.base.controller.BaseController;
import com.mcourse.frame.utils.result.Result;
import com.mcourse.frame.utils.result.ResultUtils;
import com.mcourse.module.system.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;

	/**
	 * 登陆请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return userService.login(username, password);
	}

	@RequestMapping("/register")
	@ResponseBody
	public Result register(HttpServletRequest request) {

		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (StringUtils.isBlank(username)) {
			return ResultUtils.buildFailed("用户名不能为空");
		}
		if (StringUtils.isBlank(phone)) {
			return ResultUtils.buildFailed("手机号不能为空");
		}
		if (StringUtils.isBlank(email)) {
			return ResultUtils.buildFailed("邮箱不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return ResultUtils.buildFailed("密码不能为空");
		}

		return userService.register(username, phone, email, password);

	}
}
