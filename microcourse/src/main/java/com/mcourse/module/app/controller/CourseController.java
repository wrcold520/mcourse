package com.mcourse.module.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcourse.frame.base.controller.BaseController;
import com.mcourse.frame.db.base.query.OrderBy;
import com.mcourse.frame.db.base.query.SQLJsonKey;
import com.mcourse.frame.db.base.query.Where;
import com.mcourse.frame.utils.json.JsonUtils;
import com.mcourse.frame.utils.result.Result;
import com.mcourse.frame.utils.result.ResultUtils;
import com.mcourse.module.course.model.McCourse;
import com.mcourse.module.course.service.ICourseService;

@Controller
@RequestMapping("/app/course")
public class CourseController extends BaseController {

	@Resource
	private ICourseService courseService;

	@RequestMapping("/getHotCourse")
	@ResponseBody
	public Result getHotCourse() {
		try {
			String hotTags = JsonUtils.stringify(new Integer[] { 1 });
			Where where = new Where.Builder().EQ("categoryId", 1L)// 分类
					.JSON(SQLJsonKey.JSON_CONTAINS, "tagIds", hotTags)// tag
					.OrderBy("createdTime", OrderBy.DESC)// orderby
					.build();
			List<McCourse> courselist = courseService.findByWhere(where);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("courselist", courselist);
			return ResultUtils.buildSuccess(params);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.buildError();
		}
	}

	@RequestMapping("/getNewCourse")
	@ResponseBody
	public Result getNewCourse() {
		try {
			String newTags = JsonUtils.stringify(new Integer[] { 2 });
			Where where = new Where.Builder().EQ("categoryId", 1L)// 分类
					.JSON(SQLJsonKey.JSON_CONTAINS, "tagIds", newTags)// tag
					.OrderBy("createdTime", OrderBy.DESC)// orderby
					.build();
			List<McCourse> courselist = courseService.findByWhere(where);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("courselist", courselist);
			return ResultUtils.buildSuccess(params);
		} catch (Exception e) {
			return ResultUtils.buildError();
		}
	}
}
