<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- saved from url=(0038)http://v3.bootcss.com/examples/signin/ -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="lxw">

<title>邮箱认证</title>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${path}/css/ie10-viewport-bug-workaround.css" rel="stylesheet">


<script type="text/javascript" src="${path}/js/lib/lib/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/lib/ie-emulation-modes-warning.js"></script>
<script src="${path}/js/register.js"></script>
<script src="${path}/js/lib/popover.js"></script>
<script src="${path}/js/lib/tooltip.js"></script>
<style>
@font-face {
	font-family: uc-nexus-iconfont;
	src:
		url(chrome-extension://pogijhnlcfmcppgimcaccdkmbedjkmhi/res/font_9qmmi8b8jsxxbt9.woff)
		format('woff'),
		url(chrome-extension://pogijhnlcfmcppgimcaccdkmbedjkmhi/res/font_9qmmi8b8jsxxbt9.ttf)
		format('truetype')
}

</style>
</head>

<body style="background-color: #ffffff">

	<div class="container" align="center">
		<h3 align="center">邮箱认证</h3>
		<p class="text-muted" align="center"> ${tip}</p>
		<br>
		<p class="text-muted" align="center"><a href="../register" style="color: red;">${message}</a></p>
			<input type="submit" style="max-width: 500px" class="form-control btn btn-primary " value="下一步"  ${disabled} id="submit" onclick="location.href='../login'"/>
			
			<p align="right"><a href="./sendRegisterEmail?email=${email}" >没有收到？重新发送一次</a> </p>
			
	</div>

	<script src="${path}/js/lib/ie10-viewport-bug-workaround.js"></script>

</body>
</html>