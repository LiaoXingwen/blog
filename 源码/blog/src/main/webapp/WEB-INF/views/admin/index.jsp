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
					<li class='active'><a href="${path}/admin/index"><i
							class="fa fa-home"></i>总览</a></li>
					<li><a href="${path}/admin/released"><i class="fa fa-home"></i>已发布</a></li>
					<li><a href="${path}/admin/draft"><i class="fa fa-home"></i>草稿箱</a></li>
					<li><a href="${path}/admin/message"><i class="fa fa-cubes"></i><span
							class="badge pull-right">9</span>信息</a></li>
					<li><a href="${path}/admin/setter"><i class="fa fa-cog"></i>设置</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="templatemo-content-wrapper">
		<div class="templatemo-content">
			<div class="row" style="margin: 50px;">

				<div class="col-md-6 col-lg-6">
					<div class="progress">
						<div class="progress-bar progress-bar-success" role="progressbar"
							aria-valuenow="60" aria-valuemin="0" aria-valuemax="${pushcount}"
							style="width:${(pushcount/(draftcount+pushcount))*100}%;">
							<span class="sr-only">发布</span>
						</div>
					</div>

					<p align="center">
						发布/草稿： <b class="text text-warning"> ${pushcount} </b>/<b
							class="text text-warning"> ${draftcount} </b>
					</p>
				</div>

				<!-- 
					<div class="col-md-6 col-lg-4">
						<div class="progress">
							<div class="progress-bar progress-bar-success" role="progressbar"
								aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
								style="width: 60%;">
								<span class="sr-only">好评</span>
							</div>
							<div class="progress-bar progress-bar-warning" role="progressbar"
								aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
								style="width: 40%;">
								<span class="sr-only">差评</span>
							</div>
						</div>

						<p align="center">
							好评/差评： <b class="text text-warning"> 150 </b>/<b
								class="text text-warning"> 50 </b>
						</p>
					</div>
					 -->
			</div>
		</div>
	</div>

	<script src="${path}/js/lib/jquery.min.js"></script>
	<script src="${path}/js/lib/bootstrap.min.js"></script>
</body>
</html>