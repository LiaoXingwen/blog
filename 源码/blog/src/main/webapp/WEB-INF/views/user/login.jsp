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
<meta name="description" content="bbcc博客系统登陆页面">
<meta name="author" content="lxw">

<title>登陆</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- Bootstrap core CSS -->
<!-- Bootstrap core CSS -->
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${path}/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${path}/css/signin.css" rel="stylesheet">
<script src="${path}/js/lib/ie-emulation-modes-warning.js"></script>
<style>
@font-face {
	font-family: uc-nexus-iconfont;
	src:
		url(chrome-extension://pogijhnlcfmcppgimcaccdkmbedjkmhi/res/font_9qmmi8b8jsxxbt9.woff)
		format('woff'),
		url(chrome-extension://pogijhnlcfmcppgimcaccdkmbedjkmhi/res/font_9qmmi8b8jsxxbt9.ttf)
		format('truetype')
}
 
body {
    font-size: 14px;
    color: #666;
    font-family: "Microsoft YaHei", "微软雅黑", Helvetica, Tahoma, STXihei, "华文细黑", STHeiti, "Helvetica Neue", Helvetica, Tahoma, "Droid Sans", "wenquanyi micro hei", FreeSans, Arimo, Arial, SimSun, "宋体", Heiti, "黑体", sans-serif; 
    background: #fff;
    text-align: center;
}
</style>
</head>

<body>

	<div class="container">
		<img src="${path}/image/logo.png" alt="头像" class="img-circle " >
		<p></p>
		<p style="color: red" align="center">${message}</p>
		<p></p>
		<form class="form-signin" action="${path}/user/logincheck" method="post">
		
            <input type="email" name="email" class="form-control" placeholder="请输入登陆邮箱" onchange="isInputEmail()" id="email" value="${email}">
            <br>
            <input type="password"  name="password" class="form-control" placeholder="请输入登陆密码" onchange="isInputEmail()">
            <p class="text-right" id="forgetpw"><a href="#" class="text-warning">忘记密码？</a></p>
             <input type="submit" class="form-control btn-info" value="登陆">
              <p></p>
         	<p class="text-center "><a href="${path}/register" class="text-danger">还没有账号？注册</a></p>
		</form>
        </div>

	<script src="${path}/js/lib/ie10-viewport-bug-workaround.js"></script>

</body>
</html>