package com.mcourse.module.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcourse.frame.base.controller.BaseController;
import com.mcourse.frame.utils.result.Result;
import com.mcourse.frame.utils.result.ResultUtils;
import com.mcourse.module.course.model.McVideo;
import com.mcourse.module.course.service.IVideoService;

@Controller
@RequestMapping("/app/video")
public class VideoController extends BaseController {

	@Resource
	private IVideoService videoService;

	@RequestMapping("/showvideo")
	public String toShowVideo(HttpServletRequest request, Model model) {
		String videoid = request.getParameter("videoid");
		model.addAttribute("videoid", videoid);
		return "/app/video";
	}

	@RequestMapping("/getbyid")
	@ResponseBody
	public Result getVideoById(HttpServletRequest request) {
		try {
			String videoid = request.getParameter("videoid");
			McVideo video = videoService.findBySid(Long.valueOf(videoid));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("video", video);
			return ResultUtils.buildSuccess(params);
		} catch (Exception e) {
			return ResultUtils.buildError("获取视频数据发生异常！");
		}
	}

}
