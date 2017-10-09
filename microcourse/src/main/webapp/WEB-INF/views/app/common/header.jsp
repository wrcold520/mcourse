<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/base/base_taglib.jsp"%>
<!-- header -->
<header data-am-widget="header" class="am-header am-header-default am-header-fixed">
	<div class="am-header-left am-header-nav">
		<a href="${ctx }/app/index" class=""> <i class="am-header-icon am-icon-home"></i>
		</a>
	</div>
	<h1 class="am-header-title">
		<a href="#title-link" class="">mcourse.cc</a>
	</h1>
	<div class="am-header-right am-header-nav am-dropdown" data-am-dropdown>
		<a class="am-dropdown-toggle" data-am-dropdown-toggle> <i class="am-header-icon am-icon-bars"></i>
		</a>
		<ul class="am-dropdown-content">
			<li><a href="#">推荐微课</a></li>
			<li class="am-divider"></li>
			<li><a href="${ctx }/app/login">个人中心</a></li>
			<li class="am-divider"></li>
			<li><a href="#">问题反馈</a></li>
		</ul>
	</div>
</header>