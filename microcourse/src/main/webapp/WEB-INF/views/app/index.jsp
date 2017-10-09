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

	<!-- iscroll -->
	<!-- 	<div class="iscrollwrapper"> -->
	<!-- 		<div class="scroller"> -->
	<!-- content -->
	<div>
		<!-- 轮播 -->
		<div data-am-widget="slider" class="am-slider am-slider-c2" data-am-slider='{&quot;directionNav&quot;:false}'>
			<ul class="am-slides">
				<c:forEach items="${picList }" var="pic">
					<li><img class="lazy" height="200px" src="${ctx }${pic.picurl}" alt="${pic.description }">
						<div class="am-slider-desc">${pic.title }</div></li>
				</c:forEach>
			</ul>
		</div>
		<!-- 热门课程 -->
		<div>
			<div class="am-panel am-panel-primary">
				<div class="am-panel-hd">
					<h3 class="am-panel-title">热门</h3>
				</div>
				<div id="hotcourse" class="am-g am-cf">
					<!-- template #hotcourse-tpl -->

				</div>
			</div>
		</div>
	</div>
	<!-- 		</div> -->
	<!-- 	</div> -->
	<script type="text/x-handlebars-template" id="hotcourse-tpl">
		{{#each courselist}}
			<div class="am-u-sm-6" onclick="showvideo('{{this.videoid}}');">
				<div class="am-thumbnail">
					<img class="lazyload" src="${ctx}/static/img/icon/icon_mcourse_76x76.png" data-original="${ctx }/static/img/side/yuna.jpg" />
					<h3 class="am-thumbnail-caption">{{this.name}}{{this.age}}</h3>
				</div>
			</div>
		{{/each}}	
	</script>
</body>
<script>
	$().ready(function() {
		//添加主页到主屏幕
		//addToHomescreen();

		//初始化热门课程
		initHotCourse();
	})

	function initHotCourse() {

		mc.postAjax("${ctx}/app/course/getHotCourse", {}, function(result) {
			var dataModel = result.params;
			//渲染模板
			mc.render($("#hotcourse"), $("#hotcourse-tpl"), dataModel);
			$("img.lazyload").lazyload();
		});
	}

	function showvideo(videoid) {
		window.location.href = "${ctx}/app/video/showvideo?videoid=" + videoid;
	}
</script>
</html>
