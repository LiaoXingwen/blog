<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>${article.title}</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<script src="${path}/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/lib/jquery.form.js"></script>
<script src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/article/view.js"></script>
<style type="text/css">
.commentmodel {
	margin: 10px;
	margin-bottom: 20px;
}

.commentmodel img {
	margin: 10px;
	width: 25px;
	height: 25px;
}

.commentmodel a {
	margin: 10px;
	word-break: break-all;
}

.commentmodel span {
	margin: 10px;
	word-break: break-all;
}
</style>
</head>
<body>


	<div class="container">
		<div class="row" align="right">
			<a class="glyphicon glyphicon-home" href="${path}/index"
				style="font-size: 1em; margin-right: 30px; margin-top: 10px;"></a> <a
				style="font-size: 1em; margin-right: 20px; margin-top: 10px;"
				onclick="history.back();"> <span
				class="glyphicon glyphicon-share-alt"></span>
			</a>
		</div>
		<div class="row">
			<div align="center">
				<img src="${user.headURl}" alt="头像" class="img-circle"
					style="width: 35px; height: 35px">
				<h6>
					<a class="text text-success" href="${path}/user/show/${user.id}/">${user.username}</a>
				</h6>
				<h5>
					<b>${article.title}</b><span class="label label-success">${article.label}</span>
				</h5>


			</div>
		</div>
		<c:if test="${article.uid==fromid}">
			<div class="row">
				<p align="right">
					<a href="${path}/admin/editor?aid=${article.aid}"
						class="btn btn-danger btn-xs">编辑</a>
				</p>
			</div>
		</c:if>
		<hr>

		<!-- 内容区域 -->
		<div class="row contentdiv" style="min-height: 250px">${article.html}</div>

		<div class="row" style="margin-top: 20px;">
			<p align="center">
				<button type="button" class="btn btn-default" onclick="thumbsup()"
					id='thumbsup'>
					<span class="glyphicon glyphicon-thumbs-up "></span> <b
						id='thumbsupnum' style="margin: 5px">${article.thumbsUp}</b>
				</button>

				<button type="button" class="btn btn-default"
					style="margin-left: 20px;" onclick="thumbsdown()" id='thumbsdown'>
					<span class="glyphicon glyphicon-thumbs-down"></span><b
						id='thumbsdownnum' style="margin: 5px">${article.thumbsDown}</b>
				</button>
			</p>
		</div>
		<div class="row">
			<p align="right">
				<b class="text text-default"> <span
					class="glyphicon glyphicon-calendar"></span> ${article.saveTime}
				</b> <b class="text text-default" style="margin-left: 40px;"> <span
					class="glyphicon glyphicon-eye-open"></span> ${article.viewCount}
				</b>
			</p>
		</div>
		<hr>

		<div class="row commentdiv">
			<form action="${path}/article/addcomment" class="form" id="aComment"
				method="post">
				<input type="hidden" name="type" value="0"> <input
					type="hidden" name="oid" value="${article.aid}">
				<textarea rows="3" class="form-control commentcontent"
					name="content"></textarea>
			</form>
			<p align="right">
				<button type="button" class="btn btn-default btn-sm"
					onclick="submit('${path}')">
					<span class="glyphicon glyphicon-edit"></span><b
						style="margin: 10px">评论</b>
				</button>
			</p>
			<br>
		</div>

		<div class="row commentlistdiv">
			<hr>
			<hr class="hr">
		</div>

		<div class="row" align="center">
			<c:if test="${hasComment}">
				<button type="button" class="btn btn-default btn-sm showcomment"
					onclick="getCommentList(1,0,${article.aid},'${path}')">
					<span class="glyphicon glyphicon-list-alt"></span> <b
						id='thumbsdownnum' style="margin: 10px">显示评论</b>
				</button>
			</c:if>

		</div>

<script type="text/javascript">
window.onload=function(){ 
	setPath('${path}');
	thumbs();
	$('.contentdiv img').each(function(){
		$(this).addClass("img-responsive center-block");
	});
}
</script>
	</div>
</body>
</html>