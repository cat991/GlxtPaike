<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function () {
		//
		$("a#userLogout").click(function () {
			return confirm("你确定要退出登录？");
		});
	});
</script>

<div id="header" class="wrap">
		<div id="logo"><img width="110"	height="60" src="${pageContext.request.contextPath}/html/images/logo.png" /></div>
		<div class="help">
			<c:if test="${not empty sessionScope.userName}">
				<a id="userLogout" href="${pageContext.request.contextPath}/userSer?action=logout&userId=${sessionScope.userId}">注销</a>
			</c:if>
			<c:if test="${empty sessionScope.userName}">
				<a href="${pageContext.request.contextPath}/html/login.jsp">登录</a>  |
				<a href="${pageContext.request.contextPath}/html/register.jsp">注册</a>
			</c:if>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li class="current"><a href="${pageContext.request.contextPath}/html/index.jsp">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/html/user/user.jsp">用户管理</a></li>
				<li><a href="${pageContext.request.contextPath}/Paike?opr=SRoom&id=2">教学安排</a></li>
				<c:if test="${sessionScope.userType == 1}">
					<li><a href="${pageContext.request.contextPath}/html/manage/guestbook.jsp">留言</a></li>
					<li><a href="${pageContext.request.contextPath}/html/manage/news.jsp">公告</a></li>
				</c:if>
			</ul>
		</div>
</div>
