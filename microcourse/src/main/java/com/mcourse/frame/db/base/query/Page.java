package com.mcourse.frame.db.base.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * 
 * @Title
 * @Description
 * 
 * @CreatedBy Assassin
 * @DateTime 2017年10月3日上午10:38:15
 */
public class Page<T> {

	public static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 15;
	public static final int DEFAULT_TOTAL_PAGE = 1;
	public static final int DEFAULT_TOTAL_ROWS = 0;

	private int pageNo = DEFAULT_PAGE_NO;// 当前页码
	private int pageSize = DEFAULT_PAGE_SIZE;// 每页显示记录数
	private int totalPage = DEFAULT_TOTAL_PAGE;// 总页数
	private int totalRows = DEFAULT_TOTAL_ROWS;// 总记录数
	private List<T> result = new ArrayList<T>();// 结果集

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
