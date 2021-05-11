<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>实验室课表</title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp" %>

    <script type="text/javascript">


        function ChkValue() {
            var chValue = confirm("你确定要查询【 " + $("#Sel_ROOM").find("option:selected").text()+ " 】的课表？");
            var roomVal = $("#Sel_ROOM").val();

            //封装成一个对象
            var obj = {}
            obj.roomVal = roomVal

            if (chValue) {
                window.location.href = "./paike?action=list&roomVal=" + roomVal + "&id=1";
            }
        }
    </script>
</head>
<body>


<%@include file="/html/common/hTheader.jsp" %>
<%@include file="/html/common/hTchildNav.jsp" %>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp" %>
    <div class="main">
        <h2>实验室课表</h2>
        <div class="manage">
            <td align="center" valign="bottom">实验室</td>
            <select id="Sel_ROOM" name="Sel_ROOM" style="height:20px;width:250px">
                <option value="">请选择实验室</option>
                <c:forEach items="${sessionScope.paikelab}" var="paiKeRooms">
                    <option value="${paiKeRooms.labId}">${paiKeRooms.labName}</option>
                </c:forEach>
            </select>


            <input name="btnSearch" type="button" value="检索" class="button" onclick="ChkValue()">
            <c:if test="${not empty sessionScope.paikeLabList}">
                <table class="list">
                    <tr>
                        <th width="9%" colspan="2">&ensp;</th>
                        <th width="13%" align="center">星期一</th>
                        <th width="13%" align="center">星期二</th>
                        <th width="13%" align="center">星期三</th>
                        <th width="13%" align="center">星期四</th>
                        <th width="13%" align="center">星期五</th>
                        <th width="13%" align="center">星期六</th>
                        <th width="13%" align="center">星期日</th>
                    </tr>
                    <c:set var="ii" value="${fn:split('一,二,三,四,五,六', ',')}"/>
                    <c:forEach var="i" begin="0" end="5">
                        <tr>
                            <c:if test="${i==0}">
                                <td align="center" width="5%" rowspan="2" valign="middle">
                                    上<br>午
                                </td>
                            </c:if>
                            <c:if test="${i==2}">
                                <td align="center" width="5%" rowspan="2" valign="middle">
                                    下<br>午
                                </td>
                            </c:if>
                            <c:if test="${i==4}">
                                <td align="center" width="5%" rowspan="2" valign="middle">
                                    晚<br>上
                                </td>
                            </c:if>

                            <td align="center" width="4%" height="150px">${ii[i]}</td>
                            <c:forEach var="riqi" begin="1" end="7">
                                <td valign="top" width="13%">
                                    <c:forEach items="${sessionScope.paikeLabList}" var="roomSchedule">
                                        <c:if test="${roomSchedule.paikeJieshu-1 == i && roomSchedule.paikeWeekDay == riqi}">
                                            <a href="${pageContext.request.contextPath}/paike?opr=getListById&listId=${roomSchedule.paikeId}">${roomSchedule.paikeCourseName}</a>
                                            <br>${roomSchedule.paikeTeacher}
                                            <br>${roomSchedule.paikeWeeks}
                                            <br/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>

                </table>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/html/common/footer.jsp" %>
</body>
</html>
