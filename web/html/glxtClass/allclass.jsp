<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/26
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理 - 教学管理系统</title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp"%>
</head>
<body>
<%@include file="/html/common/hTheader.jsp"%>
<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp"%>

    <div class="main">
        <h2>班级管理</h2>
        <div class="manage" style="overflow-x: auto; overflow-y: auto;">
            <table class="list">
                <tr>
                    <th>班级编号</th>
                    <th>班级名称</th>
                    <th>班主任</th>
                    <th>人数</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${sessionScope.getClassList}" var="getclassList">
                    <tr>
                        <td><a>${getclassList.classId}</a></td>
                        <td>${getclassList.className}</td>
                        <td>${getclassList.classMaster}</td>
                        <td>${getclassList.classNum}</td>

                        <td>
                            <a href="${pageContext.request.contextPath}/glxtClass?action=getClassById&id=${getclassList.classId}">修改</a>
                            <a href="${pageContext.request.contextPath}/glxtClass?action=delClass&id=${getclassList.classId}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="clear"></div>
    <%@include file="/html/common/pager.jsp"%>
</div>
<%@include file="/html/common/footer.jsp"%>
</body>
</html>

