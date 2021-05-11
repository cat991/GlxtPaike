<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理 - 易买网</title>
	<%-- 静态包含 base标签、css、jQuery --%>
	<%@include file="/html/common/head.jsp"%>
</head>
<body>
	<%@include file="/html/common/hTheader.jsp"%>
	<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
	<%@include file="/html/common/hTlefter.jsp"%>
	<div class="main">
		<h2>添加新闻</h2>
		<div class="manage">
			<form id="newsAdd" action="${pageContext.request.contextPath}/html/manage/manage-result.jsp">
				<table class="form">
					<tr>
						<td class="field">新闻标题：</td>
						<td><input type="text" class="text" name="title" value="" /><span></span></td>
					</tr>
					<tr>
						<td class="field">新闻内容：</td>
						<td><textarea class="text" name="content"></textarea><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
    
</div>
	<%@include file="/html/common/footer.jsp"%>
</body>
</html>
