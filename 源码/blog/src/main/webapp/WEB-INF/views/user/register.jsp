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

<title>注册新用户</title>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${path}/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${path}/css/signin.css" rel="stylesheet">

<script type="text/javascript" src="${path}/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/lib/ie-emulation-modes-warning.js"></script>
<script src="${path}/js/lib/popover.js"></script>
<script src="${path}/js/lib/tooltip.js"></script>
<script src="${path}/js/user/register.js"></script>
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

	<div class="container">
		<h3>注册用户</h3>
		<p class="text-muted">账号使用邮箱注册，请填入正确信息。</p>
		<p style="color: red;">${message} </p>
		<br>
		<form class="form-signin" action="./user/addUser" method="post">

			<div class="form-group has-feedback has-error" id="emaildiv">
				<input type="email" class="form-control" placeholder="邮箱"
					name="email" id="email" onchange="checkEmail()"
					data-placement="bottom" data-trigger="click" data-html="true"
					title="" data-content="输入您的邮箱，而且必须是可以使用的邮箱"> <i
					class="form-control-feedback glyphicon glyphicon-envelope"
					data-bv-icon-for="email" style="display: block;"></i>
			</div>
			<p></p>
			<div class="form-group has-feedback has-error" id="passworddiv">
				<input type="password" class="form-control" placeholder="登陆密码"
					name="password" id="password" onchange="checkPassword()"
					data-placement="bottom" data-trigger="click" data-html="true"
					title="" data-content="输入您的本站的登陆密码，并非指一定为邮箱密码，密码至少需要六位以上包含字母 或 特殊符号 或 数字 其中任意两项"> 
					<i class="form-control-feedback glyphicon glyphicon-eye-open"
					data-bv-icon-for="password" style="display: block;" id="showView"></i>
					
					<!--覆盖在小图标上面的元素-->
					<a style="display:inline-block;border:0px;width:45px;height:45px;position:absolute;right:2px;z-index:100;cursor: pointer;margin-top: -45px" onclick="showPassword()"></a> 
			</div>
			<p></p>
			<div class="form-group has-feedback has-error" align="center" id='confirmDiv'>
				<input type="password" class="form-control" placeholder="登陆密码"
					name="confirmPassword" id="confirmPassword" oncuechange="checkConfirmPassword()" >
					<i class="form-control-feedback glyphicon glyphicon-remove"
					data-bv-icon-for="confirmPassword" style="display: block;" id="confirmView"></i>
			</div>

			<input type="submit" class="form-control btn btn-primary" value="注册"  disabled="disabled" id="submit" onclick="submit()"/>
		</form>
	</div>

	<script src="${path}/js/lib/ie10-viewport-bug-workaround.js"></script>

</body>
</html>