<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">
<c:set var="path" value="${pageContext.request.contextPath}" />
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<script src="${path}/js/lib/jquery.min.js"></script>
<script src="${path}/js/lib/bootstrap.min.js"></script>
<title>操作失败</title>
</head>
<body>
<p align="right">
			<a style="margin: 15px;" href="${path}/index"> <span
				class="glyphicon glyphicon-home"></span>
			</a> <a style="margin-left: 0px; margin-right: 15px"
				onclick="history.back();"> <span
				class="glyphicon glyphicon-share-alt"></span>
			</a>
		</p>
		<br>
		<br>
	<h1 align="center" class="text-info">404</h1>
	<h2 align="center" class="text-danger"><b>${message}</b></h2>
</body>
</html>