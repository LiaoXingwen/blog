<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>bbcc博客系统</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta name="keywords" content="">
<meta name="description" content="一个简单，开源，高度自定义的博客系统，欢迎大家测试使用">
<meta name="author" content="lxw">
<meta name="baidu-site-verification" content="0wpQXa6KGh" />
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/css/index.css" rel="stylesheet">
<style type="text/css">
.mybg {
	background-color: ${bgcolor
}

;
}
.myfont {
	color: ${fontcolor
}
;
}
</style>
</head>
<body class="home-template" class="mybg">
	<header class="site-header mybg myfont">
		<nav class="navbar navbar-static-top main-navbar" id="top">
			<div class="container">
				<div class="navbar-header">
					<button class="navbar-toggle collapsed" type="button"
						data-toggle="collapse" data-target="#bs-navbar"
						aria-controls="bs-navbar" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<nav id="bs-navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${path}/index" style="color:${fontcolor} ">博客</a></li>
						<li><a href="${path}" style="color:${fontcolor}">关于</a></li>


						<c:choose>
							<c:when test="${uid!=null}">
								<li><a href="${path}/user/show/${uid}/"
									style="color:${fontcolor}">我</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${path}/login" style="color:${fontcolor}">登陆</a></li>
							</c:otherwise>

						</c:choose>




					</ul>
				</nav>
			</div>
		</nav>
		<div class="container jumbotron" align="center">
			<div class="row">
				<div
					class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-md-push-2 col-sm-push-1 col-lg-push-3 ">
					<div id="tip" class="hidden-xs">
						<h1>bblog.cc</h1>
						<h4>
							做个简简单单的博客平台<br>
						</h4>
						<p>
							<span>共收录了 <strong><b class="text-danger">${count}</b></strong>
								篇文章
							</span>
						</p>
					</div>
					<form class="search-wraper" role="search">
						<div class="input-group">
							<input type="text" class="form-control search clearable"
								value="${search}" placeholder="搜索关键字，例如：java" autocomplete="off"
								onfocus="hidetip()" onblur="showtip()"> <span
								class="input-group-addon myfont mybg" onclick="search()">
								搜索</span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>
	<div class="container" style="margin: 10px; width: 95%">
		<div class="row">
			<p align="right">
				<button type="button" class="mybg myfont btn btn-success btn-xs "
					onclick="window.location.href='${path}/index?type=viewCount&search=${search}'"
					<c:if test="${type=='viewCount'}">
					disabled="disabled"
					</c:if>>
					<span class="glyphicon glyphicon glyphicon-sort-by-attributes"></span>
					热度
				</button>
				<button type="button"
					class="mybg myfont btn btn-success btn-xs mybgfont"
					onclick="window.location.href='${path}/index?type=updateTime&search=${search}'"
					<c:if test="${type=='updateTime'}">
					disabled="disabled"
					</c:if>>
					<span class="glyphicon glyphicon glyphicon-sort-by-attributes "></span>
					时间
				</button>
				<button type="button" class="mybg myfont btn btn-success btn-xs"
					onclick="window.location.href='${path}/index?type=thumbsUp&search=${search}'"
					<c:if test="${type=='thumbsUp'}">
					disabled="disabled"
					</c:if>>
					<span class="glyphicon glyphicon glyphicon-sort-by-attributes"></span>
					好评
				</button>
				<button type="button" class="mybg myfont btn btn-success btn-xs "
					onclick="window.location.href='${path}/index?type=random&search=${search}'">
					<span class="glyphicon glyphicon-repeat"></span> 随机
				</button>
			</p>
		</div>

		<!--  
		<div class="row contentdiv">
			<img src="${path}/image/logo.png" alt="头像" class="img-circle ">
			<a>我的名字我的名字我的名字我的</a>
			<span class="label label-danger">精英</span>
			<p><span class="label label-success" style="font-size: 12px;background-color: ${bgcolor}">java</span><a>标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题标题</a></p>
			<p align="right" style="margin-right: 50px" >
				<button type="button" class="btn btn-success btn-sm"  >
					<span class="glyphicon glyphicon-thumbs-up" >10000</span>
				</button>
				<button type="button" class="btn btn-warning btn-sm">
					<span class="glyphicon glyphicon-thumbs-down">1000</span>
				</button>
			</p>
			
		</div>	
		-->
		<hr>

		<c:forEach var="info" items="${mv.list}">
			<div class="row contentdiv" onclick="window.location.href='${path}/article/${info.aid}/'">
				<p><img src="${info.user.headURl}" alt="头像" class="img-circle"> <a
					href="${path}/user/show/${info.uid}/">${info.user.username}</a> <span
					class="label label-success mybg myfont">${info.label}</span>
					</p>
				<p style="margin-left: 60px; font-size: 1.2em;">
					${info.title}
				</p>
				<p align="right" style="margin-right: 20px;">
					<span class="text text-info"> <span
						class="glyphicon glyphicon-eye-open "></span> <b
						style="margin: 5px">${info.viewCount}</b>
					</span> <span class="text text-info"> <span
						class="glyphicon glyphicon-thumbs-up "></span> <b
						style="margin: 5px">${info.thumbsUp}</b>
					</span> <span class="text text-info"> <span
						class="glyphicon glyphicon-thumbs-down "></span> <b
						style="margin: 5px">${info.thumbsDown}</b>
					</span>
				</p>

			</div>
			<hr>
		</c:forEach>


	</div>
	<script src="${path}/js/lib/jquery.min.js"></script>
	<script src="${path}/js/lib/bootstrap.min.js"></script>
	<script src="${path}/js/index/index.js"></script>
</body>
</html>