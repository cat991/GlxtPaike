<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>实验室管理系统 - 登录</title>
	<%-- 静态包含 base标签、css、jQuery --%>

	<%@include file="/html/common/head.jsp"%>
</head>
<body>
	<div id="register" class="wrap"  style="margin-bottom: 170px;">
		<div class="shadow">
			<em class="corner lb"></em>
			<em class="corner rt"></em>
			<div class="box">
				<h1>欢迎回到实验室管理系统</h1>
				<div class="ddd">
					<span class="userErrorMsg" style="color:#F00">
						${requestScope.msg}
					</span>
				</div>

				<form id="loginForm" method="post" action="${pageContext.request.contextPath}/userSer?action=login" ><%--${pageContext.request.contextPath}/userSer?action=login--%>
					<table>
						<tr>
							<td class="field">用户名：</td>
							<td>
	                            <input class="text" type="text" id="userId" name="userId" maxlength="20" value="${ requestScope.returnName}"/>
								<span></span>
							</td>
						</tr>
						<tr>
							<td class="field">登录密码：</td>
							<td>
	                            <input class="text" type="password" id="password" name="password" maxlength="20" />
								<span></span>
							</td>
						</tr>
						<tr>
						<td class="field">验证码：</td>
							<td>
		                        <input class="text" type="text" id="code" name="code" maxlength="6">
								<span></span>
								<br/>
								<img src="${pageContext.request.contextPath}/verificationCode" id="codeImg2" onClick="kk()"/>
							</td>

						</tr>
						<tr>
							<td></td>
							<td><label class="ui-green"><input onclick="login()" type="submit" name="submit" value="立即登录" /></label></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="${pageContext.request.contextPath}/html/register.jsp" class="zc">还未注册？点击立即前往注册！</a></td>
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
		function kk(){
			var img = document.getElementById("codeImg2");
			img.setAttribute("src","./verificationCode?r="+Math.random())
		}
		

	</script>
</html>
