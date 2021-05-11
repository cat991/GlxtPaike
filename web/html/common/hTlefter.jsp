<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%int userType = Integer.parseInt(String.valueOf(request.getAttribute("userType") != null ? request.getAttribute("userType") : "0").trim());%>
<div id="menu-mng" class="lefter">
    <div class="box">
        <dl>
            <dt>用户管理</dt>
            <dd><a href="${pageContext.request.contextPath}/html/user/user.jsp">个人信息</a></dd>
            <c:if test="${sessionScope.userType == 1}">
                <dd><a href="${pageContext.request.contextPath}/userSer?action=manageSelect">用户管理</a></dd>
            </c:if>
            <dt>教学安排
                <%--					  <em><a href="${pageContext.request.contextPath}/html/paike/newpaike.jsp">新增</a></em>--%>
            </dt>
            <dd><a href="${pageContext.request.contextPath}/paike?action=SRoom&id=0">班级课表</a></dd>
            <dd><a href="${pageContext.request.contextPath}/paike?action=SRoom&id=1">实验室课表</a></dd>
            <c:if test="${sessionScope.userType == 1}">
                <dd><a href="${pageContext.request.contextPath}/paike?action=getPaikeList">管理课表</a></dd>
                <dd><a href="${pageContext.request.contextPath}/html/paike/newpaike.jsp">新增课表</a></dd>

                <dt>班级管理</dt>
                <dd><a href="${pageContext.request.contextPath}/html/glxtClass/newclass.jsp">新增班级</a></dd>

                <dd><a href="${pageContext.request.contextPath}/glxtClass?action=getClassList">班级管理</a></dd>
                <dt>实验室管理</dt>
                <dd><a href="${pageContext.request.contextPath}/html/glxtLab/newlab.jsp">新增实验室</a></dd>
                <dd><a href="${pageContext.request.contextPath}/glxtLab?action=getLabList">实验室管理</a></dd>
            </c:if>
        </dl>
    </div>
</div>