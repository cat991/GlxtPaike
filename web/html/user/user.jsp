<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理 - 易买网</title>
	<%-- 静态包含 base标签、css、jQuery --%>
	<%@include file="/html/common/head.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/html/scripts/function.js"></script>
</head>
<body>
	<%@include file="/html/common/hTheader.jsp"%>
	<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
	<%@include file="/html/common/hTlefter.jsp"%>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<form method="post" action="${pageContext.request.contextPath}/userSer?action=upUser">
				<input type="hidden" name="userId"  value="${sessionScope.userId}"/>
				<input type="hidden" name="userName"  value="${sessionScope.userName}"/>
				<input type="hidden" name="userType"  value="${sessionScope.userType}"/>
				<table class="list">
					<tr>
						<th>用户ID</th>
						<th>用户名</th>
						<th>用户旧密码</th>
						<th>用户新密码</th>
						<th>再次确认新密码</th>
						<th>操作</th>
					</tr>
					<tr>
						<td><a>${sessionScope.userId}</a></td>
						<td>${sessionScope.userName}</td>
						<td><input id="pws" name="passwordJiu" type="password" maxlength="20"/></td>
						<td><input id="pws2" name="password" type="password" maxlength="20"/></td>
						<td><input id="cpws" name="confirmPassword" type="password" maxlength="20"/></td>
						<td><input class="upUserPwd" type="submit" value="确定修改"></td>
					</tr>
					<span></span>
				</table>
			</form>

			<div class="ddd">
					<span class="userErrorMsg" style="color:#F00">
						${requestScope.msg}
					</span>
			</div>
		</div>
	</div>
    <%@include file="/html/common/pager.jsp"%>
</div>
	<%@include file="/html/common/footer.jsp"%>
</body>
<script type="text/javascript">
	$("a.upUserPwd").click(function () {
		var chValue = confirm("你确定要修改密码？");
	});

	$(function () {
		//给输入框绑定失去焦点事件
		$("#pws").blur(function () {
			var pws = this.value;
			if (pws  == "") {
				alert("密码不能为空！");
			}
		});

		$("#pws2").blur(function () {
			var pws = this.value;
			if (pws  == "") {
				alert("密码不能为空！");
			}
		});

		$("#cpws").blur(function () {
			var pws = this.value;
			if (pws  == "") {
				alert("密码不能为空！");
			}if (pws != $("#pws2").val()){
				alert("两次密码不一致！");
			}
		});
	});
</script>

</html>
