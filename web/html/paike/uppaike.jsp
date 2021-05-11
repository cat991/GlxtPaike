<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/4/26
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理 - 实验室管理系统  </title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp" %>
</head>
<body>
<%@include file="/html/common/hTheader.jsp" %>
<%@include file="/html/common/hTchildNav.jsp" %>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp" %>

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
                    <th>周数</th>
                    <th>节数</th>
                    <th>星期</th>
                    <th>课程名称</th>
                    <th>课程老师</th>
                    <th>课程班级</th>
                    <th>课程课室</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <form method="post" action="${pageContext.request.contextPath}/paike?action=upPaike">
                        <c:set var="ii" value="${fn:split('一,二,三,四,五,六,七', ',')}"/>
                        <input type="hidden" name="paikeId" value="${sessionScope.onepaike.paikeId}"/>
                        <td><input type="text" name="paikeWeeks" value="${sessionScope.onepaike.paikeWeeks}"/></td>
                        <td><select id="jieshu" name="jieshu">

                            <c:forEach items="${fn:split('0,1,2,3,4,5', ',')}" var="jieshu">
                                <option value="${jieshu+1}"
                                <c:if test="${sessionScope.onepaike.paikeJieshu ==jieshu+1}">selected="selected"</c:if>>
                                    第${ii[jieshu]}大节
                                </option>
                            </c:forEach>
                        </select>
                        </td>
                        <td><select id="WeekDay" name="WeeDay">

                            <c:forEach items="${fn:split('0,1,2,3,4,5,6', ',')}" var="day">
                                <option value="${day+1}"
                                        <c:if test="${sessionScope.onepaike.paikeWeekDay ==day+1}">selected="selected"</c:if>>
                                    星期${ii[day]}</option>
                            </c:forEach>
                        </select></td>
                        <td><input type="text" name="paikeCourseName" value="${sessionScope.onepaike.paikeCourseName}"/>
                        </td>
                        <td><input type="text" name="paikeTeacher" value="${sessionScope.onepaike.paikeTeacher}"/></td>
                        <td><select id="paiekClassId" name="paikeClassId">
                        <c:forEach items="${sessionScope.paikeClass}" var="paiKeRooms">
                        <option value="${paiKeRooms.classId}"
                        <c:if test="${sessionScope.onepaike.paikeClassId ==paiKeRooms.classId}">selected="selected"</c:if>>${paiKeRooms.className}</option>
                        </c:forEach></select></td>

                        <td><select id="paikeLabId" name="paikeLabId">
                        <c:forEach items="${sessionScope.paikelab}" var="paiKeRooms">
                                <option value="${paiKeRooms.labId}"
                                        <c:if test="${sessionScope.onepaike.paikeLabId ==paiKeRooms.labId}">selected="selected"</c:if>>${paiKeRooms.labName}</option>
                            </c:forEach></select></td>
                        <td><input type="submit" value="确定修改"></td>
                    </form>
                </tr>

            </table>
        </div>
    </div>
    <div class="clear"></div>
    <%@include file="/html/common/pager.jsp" %>
</div>
<%@include file="/html/common/footer.jsp" %>
</body>
</html>
