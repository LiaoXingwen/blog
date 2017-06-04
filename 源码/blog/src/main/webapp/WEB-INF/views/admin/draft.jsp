<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>控制台</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta name="keywords" content="" />
<meta name="description" content="" />

<script src="${path}/js/lib/jquery.min.js"></script>
<script src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/admin/draft.js"></script>
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
					<li><a href="${path}/admin/index"><i class="fa fa-home"></i>总览</a></li>
					<li><a href="${path}/admin/released"><i class="fa fa-home"></i>已发布</a></li>
					<li class='active'><a href="${path}/admin/draft"><i
							class="fa fa-home"></i>草稿箱</a></li>
					<li><a href="${path}/admin/message"><i class="fa fa-cubes"></i><span
							class="badge pull-right">9</span>信息</a></li>
					<li><a href="${path}/admin/setter"><i class="fa fa-cog"></i>设置</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row" align="right"
			style="min-width: 250px; margin-top: -20px; margin-right: 10px;">
			<a class="btn btn-success" href="${path}/admin/editor" role="button">添加</a>
		</div>
		<div class="row">
			<c:forEach var="article" items="${mv.list}">
				<div class="col-xs-12 col-sm-5 col-md-5  col-lg-5"
					style="margin: 10px; min-width: 250px; background: #FFFFE0"
					id='aid-${article.aid}'>
					<p class="inline_p_title">${article.title}</p>
					<p class="inline_p">
						<span class=" text-info">创建时间：</span><label><b>${article.saveTime}</b></label>
					</p>
					<p>
					<p class="inline_p">
						<span class=" text-info">最后更新：</span><label>${article.updateTime}</label>
					</p>
					<p>
						<button type="button" class="btn btn-primary "
							onclick="window.location.href='./editor?aid=${article.aid}'"
							role="button">
							<span class="glyphicon glyphicon-edit"></span> 编 辑
						</button>

						<button class="btn btn-danger" onclick="delArt(${article.aid})">
							<span class="glyphicon glyphicon-trash"></span> 删 除
						</button>

						<button type="button" class="btn btn-success "
							onclick="window.location.href='./pushaid?aid=${article.aid}'"
							role="button">
							<span class="glyphicon glyphicon-export"></span> 发布
						</button>
					</p>
				</div>
			</c:forEach>
		</div>
		<!--  
			<div class="col-xs-12 col-sm-5 col-md-5  col-lg-5"
				style="background-color: #FFF0F5; margin: 10px; min-width: 250px">
				<h2>标题</h2>
				<p
					style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">标签，标签标签标签标签标签标签标签，标签标签标签标签标签标签标签，标签标签标签标签标签标签标签，标签标签标签标签标签标签</p>
				<br>
				<p>
					<a class="btn btn-primary" href="#" role="button">编 辑 </a> <a
						class="btn btn-danger" href="#" role="button">删 除</a> <a
						class="btn btn-info" href="#" role="button">发 布</a>
				</p>
			</div>
			-->
		<div class="row">
			<p align="center">
				<c:if test="mv.pager!=0&&mv.maxPager>5">
					<button type="button" class="btn btn-success btn-sm"
						style="margin-right: 10px" onClick="location.href='#'">首</button>
				</c:if>
				<c:forEach var="p" items="${mv.pagers}">
					<c:choose>
						<c:when test="${mv.pager!=p}">
							<button type="button" class="btn btn-success btn-sm"
								onClick="location.href='./draft?pager=${p}'">${p}</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-success btn-sm"
								disabled="disabled">${p}</button>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="mv.pager!=mv.maxPager&&mv.maxPager>5">
					<button type="button" class="btn btn-success btn-sm"
						style="margin-left: 10px" onClick="location.href='#'">尾</button>
				</c:if>

			</p>
		</div>
	</div>
</body>
</html>