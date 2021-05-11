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
    <title>后台管理 - 实验室管理系统  </title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp"%>
</head>
<body>
<%@include file="/html/common/hTheader.jsp"%>
<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp"%>

    <c:if test="${empty sessionScope.paikeClass}">
    <c:redirect url="/paike?action=AllSroom"/>
    </c:if>
    <c:if test="${empty sessionScope.paikelab}">
        <c:redirect url="/paike?action=AllSroom"/>
    </c:if>
    <div class="main">
        <h2>新增课表</h2>
        <div class="manage" style="overflow-x: auto; overflow-y: auto;">
            <table class="list">
                <tr>
                    <th>课程编号</th>
                    <th>周数</th>
                    <th>节数</th>
                    <th>星期</th>
                    <th>课程名称</th>
                    <th>课程老师</th>
                    <th>课程班级</th>
                    <th>课程课室</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${sessionScope.getPaikeList}" var="getpaikeList">
                <tr>
                    <td><a>${getpaikeList.paikeId}</a></td>
                        <td>${getpaikeList.paikeWeeks}</td>
                        <td>${getpaikeList.paikeJieshu}</td>
                        <td>${getpaikeList.paikeWeekDay}</td>
                        <td>${getpaikeList.paikeCourseName}</td>
                        <td>${getpaikeList.paikeTeacher}</td>
                    <c:forEach items="${sessionScope.paikeClass}" var="paikeclass">
                        <c:if test="${getpaikeList.paikeClassId == paikeclass.classId}">
                            <td>${paikeclass.className}</td>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${sessionScope.paikelab}" var="paikelab">
                        <c:if test="${getpaikeList.paikeLabId == paikelab.labId}">
                        <td>  ${paikelab.labName}</td>
                        </c:if>
                    </c:forEach>
                        <td><a href="${pageContext.request.contextPath}/paike?action=getPaikeById&id=${getpaikeList.paikeId}">修改</a> <a href="${pageContext.request.contextPath}/paike?action=delGlxtPaike&id=${getpaikeList.paikeId}">删除</a></td>

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

