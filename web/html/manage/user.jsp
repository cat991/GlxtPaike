<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理 - 易买网</title>
    <%-- 静态包含 base标签、css、jQuery --%>
    <%@include file="/html/common/head.jsp" %>
</head>
<body>
<%@include file="/html/common/hTheader.jsp" %>
<%@include file="/html/common/hTchildNav.jsp" %>
<div id="main" class="wrap">
    <%@include file="/html/common/hTlefter.jsp" %>
    <div class="main">
        <h2>用户管理</h2>
        <div class="manage">
            <table class="list">
                <tr>
                    <th>用户ID</th>
                    <th>用户名</th>
                    <th>用户类型</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${sessionScope.userLists}" var="userList">
                    <tr>
                        <td class="w2 c">${userList.userId}</td>
                        <td class="w2 c">${userList.userName}</td>
                        <c:if test="${userList.userType eq '1'}">
                            <td class="w2 c">管理员</td>
                        </c:if>
                        <c:if test="${userList.userType eq '0'}">
                            <td class="w2 c">用户</td>
                        </c:if>

                        <c:if test="${userList.userId != sessionScope.userId}">
                            <c:if test="${userList.userStatus == '0'}">
                                <td class="w2 c">封号中</td>
                            </c:if>
                            <c:if test="${userList.userStatus == '1'}">
                                <td class="w2 c">正常</td>
                            </c:if>
                        </c:if>
                        <c:if test="${userList.userId == sessionScope.userId}">
                                <td class="w2 c">本人</td>

                        </c:if>
                        <td class="w1 c">
                            <c:if test="${userList.userType != '1'}">
                                <a href="javascript:void(0);"  class="managexg">升级为管理员</a>
                            </c:if>
                            <c:if test="${userList.userType == '1'}">
                                <a href="javascript:void(0);"  class="managexg">降级为普通用户</a>
                            </c:if>

                            <c:if test="${userList.userStatus != '1'}">
                                <a href="javascript:void(0);" class="managefh">解冻</a>
                            </c:if>
                            <c:if test="${userList.userStatus == '1'}">
                                <a href="javascript:void(0);" class="managefh">封号</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>


            </table>
        </div>
    </div>
    <%@include file="/html/common/pager.jsp" %>
</div>
<%@include file="/html/common/footer.jsp" %>
</body>
<script type="text/javascript">
    $(function () {
        //给封号的a标签绑定单击事件，用于封号的确认提示框
        $("a.managefh").click(function () {
            var chValue = confirm("你确定要封号【 " + $(this).parent().parent().find("td").eq(1).text() + " 】用户？");
            var userStID = $(this).parent().parent().find("td").eq(0).text();

            //封装成一个对象
            var obj = {}
            obj.userStID = userStID

            if(chValue){
                window.location.href="${pageContext.request.contextPath}/userSer?action=manageDele&userStID="+userStID + "&id=2";
            }
        });

        //给升级管理员的a标签绑定单击事件，用于升级管理员的确认提示框
        $("a.managexg").click(function () {
            var chValue = confirm("你确定要升级【 " + $(this).parent().parent().find("td").eq(1).text() + " 】用户为管理员？");
            var userStID = $(this).parent().parent().find("td").eq(0).text();

            //封装成一个对象
            var obj = {}
            obj.userStID = userStID

            if(chValue){
                window.location.href="${pageContext.request.contextPath}/userSer?action=manageDele&userStID="+userStID + "&id=1";
            }
        });
    });
</script>

</html>
