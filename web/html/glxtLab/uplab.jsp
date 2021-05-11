<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/26
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <h2>修改实验室信息</h2>
        <div class="manage" style="overflow-x: auto; overflow-y: auto;">
            <table class="list">
                <tr>
                    <th>实验室编号</th>
                    <th>实验室名称</th>
                    <th>实验室类型</th>
                    <th>实验室备注</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <form method="post" action="${pageContext.request.contextPath}/glxtLab?action=upLab">
                        <input type="hidden" name="labId"  value="${sessionScope.glxtlab.labId}"/>
                        <td >${sessionScope.glxtlab.labId}</td>
                        <td><input type="text" name="labName" value="${sessionScope.glxtlab.labName}"/></td>
                        <td><input type="text" name="labType" value="${sessionScope.glxtlab.labType}"/></td>
                        <td><input type="text" name="labDesc" value="${sessionScope.glxtlab.labDesc}"/></td>
                        <td><input type="submit" value="确定修改"></td>
                    </form>
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
