package com.mcourse.module.course.model;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.anno.TablePlus;

/**
 * 课程Model
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月4日下午7:36:28
 */
@Notes("课程")
@TablePlus(name = "mc_course")
public class McCourse extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Notes("所属分类")
	@ColumnPlus(name = "category_id")
	private Long categoryId;

	@Notes("所属分类名称")
	@ColumnPlus(name = "category_name")
	private String categoryName;

	@Notes("课程名称")
	@ColumnPlus(nullable = false)
	private String name;

	@Notes("详情")
	@ColumnPlus
	private String description;

	@Notes("视频id")
	@ColumnPlus
	private Long videoid;

	@Notes("标签ids，json数组存储")
	@ColumnPlus(name = "tag_ids")
	private String tagIds;

	@Notes("标签names，json数组存储")
	@ColumnPlus(name = "tag_names")
	private String tagNames;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVideoid() {
		return videoid;
	}

	public void setVideoid(Long videoid) {
		this.videoid = videoid;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

}
