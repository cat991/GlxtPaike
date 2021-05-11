<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/27
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理 - 实验室管理系统  </title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp"%>
</head>
<body>
<%@include file="/html/common/hTheader.jsp"%>
<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp"%>

    <div class="main">
        <h2>实验室管理</h2>
        <div class="manage" style="overflow-x: auto; overflow-y: auto;">
            <table class="list">
                <tr>
                    <th>实验室编号</th>
                    <th>实验室名称</th>
                    <th>实验室类型</th>
                    <th>实验室备注</th>
                    <th>实验室操作</th>
                </tr>
                <c:forEach items="${sessionScope.getLabList}" var="getLabList">
                    <tr>
                        <td><a>${getLabList.labId}</a></td>
                        <td>${getLabList.labName}</td>
                        <td>${getLabList.labType}</td>
                        <td><c:if test="${empty getLabList.labDesc}">
                            无备注信息
                        </c:if><c:if test="${not empty getLabList.labDesc}">
                            ${getLabList.labDesc}
                        </c:if></td>

                        <td>
                            <a href="${pageContext.request.contextPath}/glxtLab?action=getLabById&id=${getLabList.labId}">修改</a>
                            <a href="${pageContext.request.contextPath}/glxtLab?action=delLab&id=${getLabList.labId}">删除</a>
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

