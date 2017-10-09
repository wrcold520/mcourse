package com.mcourse.module.system.model;

import com.mcourse.frame.base.model.BaseModel;
import com.mcourse.frame.db.base.anno.ColumnPlus;
import com.mcourse.frame.db.base.anno.Notes;
import com.mcourse.frame.db.base.anno.TablePlus;

@Notes("数据字典")
@TablePlus(name = "mc_dictionary")
public class McDictionary extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Notes("类型id")
	@ColumnPlus
	private Integer typeid;

	@Notes("类型名称")
	@ColumnPlus
	private String typename;

	@Notes("不同类型字典id")
	@ColumnPlus
	private Integer dictid;

	@Notes("不同类型字典名称")
	@ColumnPlus
	private String dictname;

	@Notes("图片url")
	@ColumnPlus
	private String imgurl;

	@Notes("不同类型排序字段")
	@ColumnPlus
	private int sort;

	@Notes("是否启用")
	@ColumnPlus
	private boolean enable = true;

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getDictid() {
		return dictid;
	}

	public void setDictid(Integer dictid) {
		this.dictid = dictid;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
