<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <base href="<%=basePath%>">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>html/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/html/css/error_all.css">
	<script type="text/javascript" src="<%=basePath%>html/scripts/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>html/scripts/function.js"></script>
	