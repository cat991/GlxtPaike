<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h2>管理首页</h2>
		<div id="welcome" class="manage">
			<div class="shadow">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<div class="msg">
						<c:if test="${not empty sessionScope.userName}">
							<p>欢迎回来</p>
						</c:if>
						<c:if test="${empty sessionScope.userName}">
							<p>请先登录！</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@include file="/html/common/footer.jsp"%>
</body>
</html>
