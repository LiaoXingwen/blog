<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>编辑器</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width">
<script src="${path}/js/lib/jquery.min.js"></script>
<script src="${path}/js/lib/classie.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/css/component.css" />
<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${path}/editor/views/css/style.css" />
<link rel="stylesheet" href="${path}/editor/css/editormd.css" />
<script src="${path}/js/lib/ie10-viewport-bug-workaround.js"></script>

<script src="${path }/js/admin/editor.js"></script>
<script type="text/javascript" src="${path}/js/lib/bootstrap.min.js"></script>
<script src="${path}/editor/editormd.min.js"></script>
<script type="text/javascript">
		var testEditor;

		$(function() {
			testEditor = editormd("test-editormd", {
				width : "100%",
				height:500,
				path : "${path}/editor/lib/",
				imageUpload    : true,
				imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp","PNG","JPG","JPEG","GIF","BMP"],
				imageUploadURL : "${path}/fileupload/editor/uploadImg",
				 onfullscreen : function() {
					 $('#title-span').hide();
					 $('#label-span').hide();
                 },
                 onfullscreenExit : function() {
                	 $('#title-span').show();
                	 $('#label-span').show();
                 }

			});
		});
		
		
		
	</script>
</head>
<body>
	<div>
		<p align="right">
			<a style="margin: 15px;" href="${path}/index"> <span
				class="glyphicon glyphicon-home"></span>
			</a> <a style="margin-left: 0px; margin-right: 15px"
				onclick="history.back();"> <span
				class="glyphicon glyphicon-share-alt"></span>
			</a>
		</p>
		<form action="${url }" id='form' method="post">
			<div id="layout">
				<div id="test-editormd" style="width: 100%; height: 100%">
					<textarea style="display: none;" name="md">${article.md}</textarea>
				</div>
			</div>
			<input hidden="hidden" id="md-html" name='html'>
			<p>
				<span class="input input--isao" id="title-span"> <input
					class="input__field input__field--isao" type="text" name="title"
					id="title" value="${article.title}"> <label
					class="input__label input__label--isao" for="input-38"
					data-content="标题"> <span
						class="input__label-content input__label-content--isao">标题</span>
				</label>
				</span>
			</p>
			<p>
				<span class="input input--isao" id="label-span"> <input
					class="input__field input__field--isao" type="text" name="label"
					id="label" value="${article.label}"> <label
					class="input__label input__label--isao" for="input-38"
					data-content="标签"> <span
						class="input__label-content input__label-content--isao">标签</span>
				</label>
				</span>
			</p>
			<button type="button"
				style="width: 150px; height: 40px; background-color: #8FBC8F; color: black; border: 0px;"
				onclick="save()">保存</button>
			<button type="button"
				style="width: 150px; height: 40px; background-color: #8FBC8F; color: black; border: 0px;"
				onclick="push(${article.aid})">发布</button>
		</form>

	</div>

</body>
</html>