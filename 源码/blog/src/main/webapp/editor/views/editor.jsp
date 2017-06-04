<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>编辑器</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="../css/editormd.css" />
<link rel="shortcut icon"
	href="https://pandao.github.io/editor.md/favicon.ico"
	type="image/x-icon" />
</head>
<body>
	<div id="layout">
		<header>
			<h1>标题：</h1>
		</header>
		<div id="test-editormd">
			<textarea style="display: none;">[TOC]

#### Disabled options

- TeX (Based on KaTeX);
- Emoji;
- Task lists;
- HTML tags decode;
- Flowchart and Sequence Diagram;

#### Editor.md directory

    editor.md/
            lib/
            css/
            scss/
            tests/
            fonts/
            images/
            plugins/
            examples/
            languages/     
            editormd.js
            ...

```html
&lt;!-- English --&gt;
&lt;script src="../dist/js/languages/en.js"&gt;&lt;/script&gt;

&lt;!-- ç¹é«ä¸­æ --&gt;
&lt;script src="../dist/js/languages/zh-tw.js"&gt;&lt;/script&gt;
```
</textarea>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="../editormd.min.js"></script>
	<script type="text/javascript">
			var testEditor;

            $(function() {
                testEditor = editormd("test-editormd", {
                    width   : "100%",
                    height  : 500,
                    syncScrolling : "single",
                    path    : "../lib/"
                });
                
                /*
                // or
                testEditor = editormd({
                    id      : "test-editormd",
                    width   : "90%",
                    height  : 640,
                    path    : "../lib/"
                });
                */
            });
        </script>
</body>
</html>