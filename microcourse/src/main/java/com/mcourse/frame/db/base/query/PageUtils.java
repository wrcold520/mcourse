package com.mcourse.frame.db.base.query;

import java.util.List;

import com.mcourse.frame.base.model.BaseModel;

/**
 * Page工具类
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月1日下午3:55:27
 */
public class PageUtils {

	/***
	 * 构建一个Page
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param totalRows
	 * @param result
	 * @return
	 */
	public static <T extends BaseModel> Page<T> buildPage(int pageNo, int pageSize, int totalRows, List<T> result) {
		Page<T> page = new Page<T>();

		page.setPageNo(pageNo);// 当前页数
		page.setPageSize(pageSize);// 每页记录数
		page.setResult(result);// 当前页结果集

		page.setTotalRows(totalRows);// 总计数个数
		int totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
		page.setTotalPage(totalPage);// 总页数
		return page;
	}

}
