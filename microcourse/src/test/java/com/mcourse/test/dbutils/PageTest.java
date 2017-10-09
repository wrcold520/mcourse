package com.mcourse.test.dbutils;

import java.util.ArrayList;
import java.util.List;

import com.mcourse.frame.db.base.query.Page;
import com.mcourse.frame.db.base.query.PageUtils;
import com.mcourse.frame.utils.json.JsonUtils;
import com.mcourse.module.course.model.McCategory;

public class PageTest {
	public static void main(String[] args) {
		List<McCategory> result = new ArrayList<McCategory>();
		for (int i = 0; i < 3; i++) {
			result.add(new McCategory());
		}
		Page<McCategory> page = PageUtils.buildPage(1, 5, 3, result);
		System.out.println("--------------" + JsonUtils.stringify(page));
	}
}
