<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>${user.username}的空间</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/css/me.css" rel="stylesheet">
<script src="${path}/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/lib/jquery.form.js"></script>
<script src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/user/me.js"></script>
<script src="${path}/js/lib/radialIndicator.js"></script>
</head>
<style type="text/css">
.mybg {
	background-color: ${user.style.bgcolor
}

;
}
.myfont {
	color: ${user.style.fontcolor
}
;
}
</style>
<body class="mybg myfont">

	<div class="headdiv container">

		<!-- 本人才开启的编辑栏 -->
		<div class="headdiv" align="right" style="margin-top: 20px">

			<c:choose>
				<c:when test="${pathid==user.id}">
					<a class="myfont mybg" href="${path}/admin/index"> <span
						class="glyphicon glyphicon-user"></span>管理
					</a>
				</c:when>
			</c:choose>
			<a class="myfont mybg" style="margin: 20px;" href="${path}/index">
				<span class="glyphicon glyphicon-home"></span>
			</a>
		</div>
		<!-- 个人基本信息模块 -->
		<div class="headdiv myfont" align="center">


			<img alt="头像" src="${user.headURl}" class="img-circle" id="headimg"
				<c:if test="${pathid==user.id }">
			onclick="uploadHead()" </c:if>
				style="width: 80px; height: 80px;">
			<c:if test="${pathid==user.id }">
				<form action="${path}/fileupload/uploadImg/head"
					enctype="multipart/form-data" id='uploadForm' method="post"
					style="display: none;">
					<input type="file" name='file' class="btn btn-info file"
						style="color: white;"
						onchange="upload('${path}/user/show/change/head')">
				</form>
			</c:if>
			<div class="headdiv" align="center">
				<p>${user.username}</p>
				<h4>${user.description}</h4>
			</div>

			<!-- 标星 -->
			<p>
				<c:forEach begin="0" end="4">
					<span class="glyphicon glyphicon-star text-danger"
						style="font-size: 20px;"></span>
				</c:forEach>
			</p>
		</div>
		<c:if test="${pathid!=user.id }">
			<p align="right">


				<button type="button"
					<c:choose>
			<c:when test="${pathid==-1 }">
			class="btn btn-default btn-xs" 
			onclick="toLogin('${path}')"
			</c:when>
			<c:otherwise>
			<c:if test="${isfollow}">
			class="btn btn-warning btn-xs" 
			</c:if>
			<c:if test="${!isfollow}">
			class="btn btn-default btn-xs" 
			</c:if>
			onclick="dostar('${path}',${user.id})"
			</c:otherwise>
			</c:choose>
					id="starbtn" style="margin-right: 10px">
					<span class="glyphicon glyphicon-star"></span>收藏
				</button>
			</p>
		</c:if>
		<hr class="myfont mybg">

		<div class="row">


			<!-- 时间控件 -->
			<c:if test="true">
				<!-- 个人论坛数据展示 ：论坛的活跃情况-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="timeContainer"></div>
					<p class="myfont">珍惜每一刻</p>
				</div>
				<script type="text/javascript">
					var timeContainer = radialIndicator('#timeContainer', {
						radius : 50,
						barWidth : 5,
						barColor : '#FF0000',
						minValue : 0,
						maxValue : 60,
						fontWeight : 'normal',
						roundCorner : true,
						format : function(value) {
							var date = new Date();
							return date.getHours() + ':' + date.getMinutes();
						}
					});

					setInterval(function() {
						timeContainer.value(new Date().getSeconds() + 1);
					}, 1000);
				</script>
			</c:if>

			<!-- 活跃控件 -->
			<c:if test="true">
				<!-- 个人论坛数据展示 ：论坛的活跃情况-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="active"></div>
					<p class="myfont">活跃度</p>
				</div>
				<script type="text/javascript">
					var active = radialIndicator('#active', {
						radius : 50,
						barWidth : 5,
						barColor : {
							0 : '#FF0000',
							33 : '#FFFF00',
							66 : '#0066FF',
							100 : '#006400'
						},
						bgColor : '#000000',
						minValue : 1,
						maxValue : 100,
						fontWeight : 'normal',
						roundCorner : true,
					});
					active.animate(${activecount});
				</script>
			</c:if>


			<c:if test="true">
				<!-- 个人论坛数据展示 ：发帖情况-->
				<!-- 个人论坛数据展示 ：论坛的活跃情况-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="push"></div>
					<p class="myfont">发帖数</p>
				</div>
				<script type="text/javascript">
					var push = radialIndicator('#push', {
						radius : 50,
						barWidth : 5,
						barColor : {
							0 : '#FF0000',
							33 : '#FFFF00',
							66 : '#0066FF',
							100 : '#006400'
						},
						bgColor : '#000000',
						minValue : 1,
						maxValue : 200,
						fontWeight : 'normal',
						roundCorner : true,
					});
					push.animate(${articlecount});
				</script>
			</c:if>


			<c:if test="true">
				<!-- 个人论坛数据展示 ：收藏人数-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="star"></div>
					<p class="myfont">收藏人数</p>
				</div>
				<script type="text/javascript">
					var star = radialIndicator('#star', {
						radius : 50,
						barWidth : 5,
						barColor : {
							0 : '#FF0000',
							50 : '#FFFF00',
							100 : '#0066FF',
							150 : '#006400'
						},
						bgColor : '#000000',
						minValue : 1,
						maxValue : 200,
						fontWeight : 'normal',
						roundCorner : true,
					});
					star.animate(${follower});
				</script>
			</c:if>

			<c:if test="true">
				<!-- 个人论坛数据展示 ：好评情况-->
				<!-- 个人论坛数据展示 ：论坛的活跃情况-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="thumbsup"></div>
					<p class="myfont">好评(赞${thumbsup}-贬${thumbsdown})</p>
				</div>
				<script type="text/javascript">
					var thumbsup = radialIndicator('#thumbsup', {
						radius : 50,
						barWidth : 5,
						barColor : {
							0 : '#FF0000',
							300 : '#FFFF00',
							600 : '#0066FF',
							800 : '#006400'
						},
						bgColor : '#000000',
						minValue : 1,
						maxValue : 1000,
						fontWeight : 'normal',
						roundCorner : true,
					});
					thumbsup.animate(${thumbsup}-${thumbsdown});
				</script>
			</c:if>

			<c:if test="true">
				<!-- 个人论坛数据展示 ：查看情况-->
				<!-- 个人论坛数据展示 ：论坛的活跃情况-->
				<div class="col-lg-2 col-md-3 col-sm-4" align="center"
					style="margin-bottom: 50px;">
					<div id="viewCount"></div>
					<p class="myfont">浏览次数</p>
				</div>
				<script type="text/javascript">
					var viewCount = radialIndicator('#viewCount', {
						radius : 50,
						barWidth : 5,
						barColor : {
							0 : '#FF0000',
							3000 : '#FFFF00',
							6000 : '#0066FF',
							8000 : '#006400'
						},
						bgColor : '#000000',
						minValue : 1,
						maxValue : 10000,
						fontWeight : 'normal',
						roundCorner : true,
					});
					viewCount.animate(${viewCount});
				</script>
			</c:if>
		</div>
		<hr class="myfont mybg">

		<p align="center">
			<span class=" text-danger glyphicon glyphicon-fire"></span> <span
				class="text-danger ">热门帖子</span>
		</p>
		<hr class="myfont mybg">
		<c:forEach var="info" items="${articles.list}">
			<div class="row "
				onclick="window.location.href='${path}/article/${info.aid}/'">
				<p style="margin-left: 20px" >${info.title}</p>
				<p align="right" style="margin-right: 20px;">
				<span  class="text text-default">
						<span class="glyphicon glyphicon-eye-open "></span> <b 
							 style="margin: 5px">${info.viewCount}</b>
					</span>
					<span  class="text text-default">
						<span class="glyphicon glyphicon-thumbs-up "></span> <b
							 style="margin: 5px">${info.thumbsUp}</b>
					</span>
					<span  class="text text-default">
						<span class="glyphicon glyphicon-thumbs-down "></span> <b
							 style="margin: 5px">${info.thumbsDown}</b>
					</span>
				</p>

			</div>
			<hr>
		</c:forEach>


	</div>
</html>