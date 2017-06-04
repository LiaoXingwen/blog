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

<title>完善个人资料</title>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- Bootstrap core CSS -->
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${path}/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<script type="text/javascript" src="${path}/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${path}/js/lib/jquery.form.js"></script>
<script type="text/javascript" src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/js/lib/ie-emulation-modes-warning.js"></script>
<script src="${path}/js/user/completeData.js"></script>
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

	<div class="container" align="center" id="container">
		<h3>完善个人资料</h3>
		<br> <img src="" alt="头像" class="img-circle"
			style="width: 80px; height: 80px" id="headimg" onclick="uploadHead()"> <br> 
			
			<form action="${path}/fileupload/uploadImg/head" enctype="multipart/form-data" id='uploadForm' method="post" style="display: none;">
			本地：<input
			type="file" name='file' class="btn btn-info file" style="color: white;" onchange="upload()">
			</form>
			
		<p style="color: red">${message}</p>
		<form class="from" style="max-width: 600px; min-width: 300px"
			onsubmit="return check()" action="./dataCheck" method="post">
			<div class="form-group has-feedback has-error" hidden="hidden">
				<input type="text" name='headurl'  id='url' 
					class="form-control" placeholder="可直接输入网络图片的url，但要保证url不是临时地址">
			</div>
	<br>

			<div class="form-group has-feedback has-error" id="namediv">
				<input type="text" class="form-control" placeholder="昵称"
					name="username" id="username" data-placement="bottom"
					data-trigger="click" data-html="true" title=""
					 onchange="checkUserName()"
					oninput="checkUserName()"
					data-content="输入您的昵称，如果一旦设置就不能再更改的哦,至少5个字符">
			</div>
			<br>
			<div class="form-group has-feedback has-success" id="passworddiv">
				<textarea class="form-control" placeholder="个性签名" name="description"
					id="des" data-placement="bottom" data-trigger="click"
					data-html="true" title="" data-content="输入您的本个性签名"></textarea>
			</div>
			<br> <span style="color: blue; margin-right: 20px"> 性别:</span>
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-primary active"> <input
					type="checkbox" autocomplete="off" value="男" id='man'
					onclick="changeChecked()" checked="checked"> 男
				</label> <label class="btn btn-primary"> <input type="checkbox"
					onclick="changeChecked()" autocomplete="off" value="女" id='woman'>
					女
				</label> <input hidden="hidden" name="sex" value="男" id='sex'>
			</div>

			<br> <br>
			<div class="form-group " id="passworddiv">
				<span style="color: blue; margin-right: 20px">出生日期：</span> <input
					type="date" name="brithDay" value='1990-01-01' />
			</div>

			<br> <input type="submit" class="form-control btn btn-primary"
				value="完成" id="submit" disabled="disabled" />
		</form>
	</div>


	<script src="${path}/js/lib/ie10-viewport-bug-workaround.js"></script>

</body>
</html>