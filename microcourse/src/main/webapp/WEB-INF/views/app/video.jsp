<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/base/base_taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/base/base_meta.jsp"%>
<%@ include file="/WEB-INF/views/base/base_static.jsp"%>
<title>mcourse.cc</title>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/app/common/header.jsp"%>
	<input id="videoid" value="${videoid }">
	<video id="course-video" class="video-js" controls preload="auto" poster="${ctx }${video.posterurl}" data-setup="{}">
		<source class="mp4source" src="${ctx }${video.videourl}" type='video/mp4'>
		<p class="vjs-no-js">
			To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
		</p>
	</video>
</body>
<script>
	$().ready(function() {
		initVideo();
	})

	//初始化video
	function initVideo() {
		//url
		var getbyidUrl = "${ctx}/app/video/getbyid?videoid="
				+ $("#videoid").val();

		//ajax
		mc.postAjax(getbyidUrl, {}, function(result) {
			var video = result.params.video;
			if (!video) {
				mc.alert("提示", "抱歉，找不到对应的资源！");
			} else {
				$("#course-video").attr("poster", "${ctx}" + video.posterurl);
				$("#course-video>.mp4source").attr("src",
						"${ctx}" + video.videourl);

				//延迟加载js
				mc.loadjs($("html"), "http://vjs.zencdn.net/6.2.7/video.js");
			}
		});
	}
</script>
</html>
