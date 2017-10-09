package com.mcourse.module.course.model;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.anno.TablePlus;

/**
 * 课程分类Model
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月4日下午7:34:51
 */
@Notes("课程分类")
@TablePlus(name = "mc_category")
public class McCategory extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Notes("分类名称")
	@ColumnPlus
	private String name;

	@Notes("图片url")
	@ColumnPlus
	private String imgurl;

	@Notes("分类描述")
	@ColumnPlus
	private String description;

	@Notes("排序字段")
	@ColumnPlus
	private Integer sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
