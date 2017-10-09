package com.mcourse.frame.base.model;

import java.io.Serializable;
import java.util.Date;

import com.mcourse.frame.constants.SysConstants;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.IDPlus;
import com.mcourse.frame.db.base.anno.Notes;

/**
 * @Title 实体基类
 * @Description
 * @Created Assassin
 * @DateTime 2017年9月11日上午9:50:57
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Notes("自增主键ID，这里命名为" + SysConstants.DB_PRIMARY_KEY)
	@IDPlus(name = SysConstants.DB_PRIMARY_KEY)
	@ColumnPlus
	protected Long id;

	@Notes("创建者")
	@ColumnPlus(name = "created_by")
	protected String createdBy;

	@Notes("创建时间")
	@ColumnPlus(name = "created_time")
	protected Date createdTime;

	@Notes("更新者 ")
	@ColumnPlus(name = "updated_by")
	protected String updatedBy;

	@Notes("更新时间")
	@ColumnPlus(name = "updated_time")
	protected Date updatedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
