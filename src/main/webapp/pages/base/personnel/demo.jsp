<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	

</head>
<body>
<link rel="stylesheet" href="themes/default/default.css" />
	<link rel="stylesheet" href="plugins/code/prettify.css" />
	<script charset="utf-8" src="editor/kindeditor.js"></script>
	<script charset="utf-8" src="editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="plugins/code/prettify.js"></script>
		<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
	<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content1"]', {
			cssPath : '../plugins/code/prettify.css',
			//uploadJson : '../jsp/upload_json.jsp',
			//fileManagerJson : '../jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					
				});
			}
		});

	});
	</script>
</body>
</html>
