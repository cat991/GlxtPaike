<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
	function mytime(){
		var a = new Date();
		var b = a.toLocaleTimeString();
		var c = a.toLocaleDateString();
		document.getElementById("time").innerHTML = c+"&nbsp"+b;
	}
	setInterval(function() {mytime()},0);
</script>

<div id="childNav">


	<div class="welcome wrap">
		<c:if test="${empty sessionScope.userName}">
			游客
		</c:if>
		<c:if test="${sessionScope.userType == 1}">
			管理员
		</c:if>
		<c:if test="${sessionScope.userType == 0}">
			用户
		</c:if>
		<a href="#" style="color: #0000FF; ">
			<c:if test="${not empty sessionScope.userName}">
				${sessionScope.userName}
			</c:if>
		</a>
		您好，今天是<label id="time" style="color: 	#0000FF; "></label>
		，欢迎回到实验室管理系统  。
		<c:if test="${empty sessionScope.userName}">
				欢迎登录/注册。
		</c:if>
	</div>
</div>

<div id="position" class="wrap">
		您现在的位置：<a href="${pageContext.request.contextPath}/html/manage/index.jsp">实验室管理系统  </a> &gt; 管理后台
</div>