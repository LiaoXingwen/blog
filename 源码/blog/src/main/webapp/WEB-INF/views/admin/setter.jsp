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
<script src="${path}/js/lib/jquery.min.js"></script>
<script src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/admin/setter.js"></script>
<link rel="stylesheet" href="${path}/css/jquery.minicolors.css">

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
				<a class="navbar-brand" href="${path}/index"><span
					class="glyphicon glyphicon-home"></span>首页</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${path}/admin/index"><i class="fa fa-home"></i>总览</a></li>
					<li><a href="${path}/admin/released"><i class="fa fa-home"></i>已发布</a></li>
					<li><a href="${path}/admin/draft"><i class="fa fa-home"></i>草稿箱</a></li>
					<li><a href="${path}/admin/message"><i class="fa fa-cubes"></i><span
							class="badge pull-right">9</span>信息</a></li>
					<li class='active'><a href="${path}/admin/setter"><i
							class="fa fa-cog"></i>设置</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<span class="text-warning"><b>颜色搭配：</b></span>
		<hr>
		<div class="row">
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4"
				style="margin-bottom: 10px" align="center" onclick="changeStyle('#3CB371','#F5FFFA')">
				<button class="btn" 
					style="width: 50px; height: 50px; background-color: #3CB371; color: #F5FFFA;">0.0</button>
				<P>默认</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4"
				style="margin-bottom: 10px" align="center" onclick="changeStyle('#0F0F0F','#F4F4F4')">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #0F0F0F; color: #F4F4F4;">0.0</button>
				<P>雅黑</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#666666','#ffffff')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #666666; color: #ffffff;">0.0</button>
				<P>竹墨</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#CD4F39','#F5FFFA')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #CD4F39; color: #F5FFFA;">0.0</button>
				<P>杏红</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#CD919E','#ffffff')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #CD919E; color: #ffffff;">0.0</button>
				<P>优雅</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#778899','#ffffff')"				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #778899; color: #ffffff">0.0</button>
				<P>经典</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#DB7093','#ffffff')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #DB7093; color: #ffffff;">0.0</button>
				<P>粉色</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#36648B','#ffffff')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #36648B; color: #ffffff;">0.0</button>
				<P>深蓝</P>
			</div>
			
			<div class="col-lg-1 col-md-2 col-sm-3 col-xs-4" onclick="changeStyle('#528B8B','#ffffff')"	
				style="margin-bottom: 10px" align="center">
				<button class="btn"
					style="width: 50px; height: 50px; background-color: #528B8B; color: #ffffff;">0.0</button>
				<P>深邃</P>
			</div>
		</div>
		<hr>


	</div>

</body>
</html>