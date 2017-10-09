package com.mcourse.module.system.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcourse.frame.base.dao.IBaseDao;
import com.mcourse.frame.base.service.BaseServiceImpl;
import com.mcourse.frame.constants.SysConstants;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.frame.db.base.query.WhereUtils;
import com.mcourse.frame.exception.ServiceException;
import com.mcourse.frame.spring.SpringHttpUtils;
import com.mcourse.frame.utils.result.Result;
import com.mcourse.frame.utils.result.ResultUtils;
import com.mcourse.module.system.model.McUser;

/**
 * UserServiceImpl.java
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin_DBGenerator
 * @DateTime 2017/10/01 00:00:00
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<McUser> implements IUserService {

	@Resource(name = "userDao")
	@Override
	public void setBaseDao(IBaseDao<McUser> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Result login(String username, String password) {
		Result result = null;

		Where[] orWhere = new Where[] { //
				WhereUtils.EQ("username", username), // 用户名
				WhereUtils.EQ("phone", username), // 手机
				WhereUtils.EQ("email", username) // 邮箱
		};

		Where loginWhere = new Where.Builder().EQ("password", password).And(orWhere).build();

		try {
			McUser user = getBaseDao().findUniqueByWhere(loginWhere);
			if (user == null) {
				result = ResultUtils.buildFailed("抱歉，用户名/手机号/邮箱或者密码错误！");
			} else {
				SpringHttpUtils.getCurrentSession().setAttribute(SysConstants.SESSION_KEY_ACTIVEUSER, user);
				result = ResultUtils.buildSuccess();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = ResultUtils.buildError();
		}
		return result;
	}

	@Override
	public Result register(String username, String phone, String email, String password) {
		try {
			// 校验注册信息是否异常
			int count = getBaseDao().countByWhere(WhereUtils.EQ("username", username));
			if (count > 0) {
				return ResultUtils.buildFailed("用户名太抢手了，再重新挑选一个呗！");
			}
			count = getBaseDao().countByWhere(WhereUtils.EQ("phone", phone));
			if (count > 0) {
				return ResultUtils.buildFailed("手机号码已被注册，请确认下您的手机号码！");
			}
			count = getBaseDao().countByWhere(WhereUtils.EQ("email", email));
			if (count > 0) {
				return ResultUtils.buildFailed("电子邮箱已被注册，换个姿势再来一次！");
			}
			// 注册用户
			McUser user = new McUser();
			user.setUsername(username);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);
			int affectrows = getBaseDao().insert(user);
			if (affectrows > 0) {
				return ResultUtils.buildSuccess();
			} else {
				return ResultUtils.buildFailed();
			}
		} catch (SQLException e) {
			throw new ServiceException("用户注册时发生异常！", e);
		}
	}
}