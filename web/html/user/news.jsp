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
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>新闻标题</th>
					<th>操作</th>
				</tr>
				<tr>
					<td class="first w4 c">1</td>
					<td>高老庄地震了</td>
					<td class="w1 c"><a href="html/manage/news-modify.jsp">修改</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
				</tr>
				<tr>
					<td class="first w4 c">1</td>
					<td>高老庄地震了</td>
					<td class="w1 c"><a href="html/manage/news-modify.jsp">修改</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
    <%@include file="/html/common/pager.jsp"%>
</div>
	<%@include file="/html/common/footer.jsp"%>
</body>
</html>
