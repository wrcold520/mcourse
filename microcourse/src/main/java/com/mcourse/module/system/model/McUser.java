package com.mcourse.module.system.model;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.anno.TablePlus;

@Notes("用户表")
@TablePlus(name = "mc_user")
public class McUser extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Notes("头像url")
	@ColumnPlus
	private String imgurl;

	@Notes("用户名")
	@ColumnPlus
	private String username;

	@Notes("密码")
	@ColumnPlus
	private String password;

	@Notes("手机号")
	@ColumnPlus
	private String phone;

	@Notes("邮箱")
	@ColumnPlus
	private String email;

	@Notes("qq")
	@ColumnPlus
	private String qq;

	@Notes("微信")
	@ColumnPlus
	private String weixin;

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

}
