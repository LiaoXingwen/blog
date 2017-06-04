<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta charset="utf-8">
<title>控制台</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${path}/css/bootstrap.min.css">
<link rel="stylesheet" href="${path}/css/admin.css">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			<a class="navbar-brand" href="${path}/index"><span class="glyphicon glyphicon-home"></span>首页</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
					<li ><a href="${path}/admin/index"><i
							class="fa fa-home"></i>总览</a></li>
					<li><a href="${path}/admin/released"><i class="fa fa-home"></i>已发布</a></li>
					<li><a href="${path}/admin/draft"><i class="fa fa-home"></i>草稿箱</a></li>
					<li class='active'><a href="${path}/admin/message"><i class="fa fa-cubes"></i><span
							class="badge pull-right">9</span>信息</a></li>
					<li><a href="${path}/admin/setter"><i class="fa fa-cog"></i>设置</a></li>
				</ul>
			</div>
		</div>
	</nav>
<div class="templatemo-content-wrapper">
        </div>
	
	<script src="${path}/js/lib/jquery.min.js"></script>
	<script src="${path}/js/lib/bootstrap.min.js"></script>
	
</body>
</html>