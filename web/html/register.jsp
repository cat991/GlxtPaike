<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>实验室管理系统 - 注册</title>

	<%@include file="/html/common/head.jsp"%>
	<script type="text/javascript" src="<%=basePath%>html/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	
	<div id="register" class="wrap" style="margin-bottom: 170px;">
		<div class="shadow">
			<em class="corner lb"></em>
			<em class="corner rt"></em>
			<div class="box">
				<h1>欢迎注册实验室管理系统</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>填写注册信息</li>
					<li class="last"><em></em>注册成功</li>
				</ul>
				<div class="ddd">
					<span class="userErrorMsg" style="color:#F00">
						${requestScope.msg}
					</span>
				</div>
				<form id="regForm" method="post" action="${pageContext.request.contextPath}/userSer" >
					<input type="hidden" name="action"  value="regs"/>
					<table>
						<tr>
							<td class="field">用户名(*)：</td>
							<td>
								<input class="text" type="text" name="userId"  id="userId" maxlength="20"  />
								<span></span>
							</td>
						</tr>

						<tr>
							<td class="field">登录密码(*)：</td>
							<td><input class="text" type="password" id="password" name="password" maxlength="20" /><span></span></td>
						</tr>
						<tr>
							<td class="field">确认密码(*)：</td>
							<td><input class="text" type="password" name="confirmPassword" maxlength="20" /><span></span></td>
						</tr>

						<tr>
							<td></td>
							<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="${pageContext.request.contextPath}/html/login.jsp" class="dl">已有账号？点击快速登录！</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	
	<%@include file="/html/common/footer.jsp"%>
	
</body>
<script type="text/javascript">
	$(function () {
		//给用户名输入框绑定失去焦点事件，检查用户名是否不存在
		$("#userId").blur(function () {
			var usernameText = this.value;
			$.getJSON("${pageContext.request.contextPath}/userSer","action=ajaxExistsUsername&userName="+usernameText,function (data) {
				if (!data.existsUsername) {
					alert("sorry!这个用户名太火爆了已经被抢先注册了呢，换一个试试吧！");
				}else {
					$("span.errorMsg").text("");
				}
			});
		});
	});
</script>

</html>
