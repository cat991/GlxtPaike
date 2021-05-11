<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>实验室管理系统 - 返回首页中...</title>
	<%-- 静态包含 base标签、css、jQuery --%>
	<%@include file="/html/common/head.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/html/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em>
			<em class="corner rt"></em>
			<div class="box">
				<h1>欢迎注册实验室管理系统</h1>
				<ul class="steps clearfix">
					<li class="finished"><em></em>填写注册信息</li>
					<li class="last-current"><em></em>注册成功</li>
				</ul>
				<div class="msg">
					<p>恭喜：注册成功！</p>
					<p>正在进入登录页面...</p>
					<script type="text/javascript">
						setTimeout("location.href='${pageContext.request.contextPath}/html/login.jsp'", 3000);
					</script>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/html/common/footer.jsp"%>
	
</body>
</html>
