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
			<h2>回复留言</h2>
			<div class="manage">
				<form action="${pageContext.request.contextPath}/html/manage/manage-result.jsp">
					<table class="form">
						<tr>
							<td class="field">留言编号：</td>
							<td>12</td>
						</tr>
						<tr>
							<td class="field">留言姓名：</td>
							<td>张三</td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td>高老庄的货发了没？</td>
						</tr>
						<tr>
							<td class="field">回复内容：</td>
							<td><textarea name="replyContent"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
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
