package com.mcourse.module.course.model;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.anno.TablePlus;

@Notes("流媒体资源")
@TablePlus(name = "mc_video")
public class McVideo extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Notes("资源名称")
	@ColumnPlus
	private String name;

	@Notes("资源详情")
	@ColumnPlus
	private String description;

	@Notes("封面图片地址")
	@ColumnPlus
	private String posterurl;

	@Notes("视频地址")
	@ColumnPlus
	private String videourl;

	@Notes("是否第三放视频")
	@ColumnPlus(name = "is_third_resource")
	private boolean isThirdResource = false;

	@Notes("组别id")
	private Integer groupId;

	@Notes("组别名称")
	private String groupName;

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

	public String getPosterurl() {
		return posterurl;
	}

	public void setPosterurl(String posterurl) {
		this.posterurl = posterurl;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public boolean isThirdResource() {
		return isThirdResource;
	}

	public void setThirdResource(boolean isThirdResource) {
		this.isThirdResource = isThirdResource;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
