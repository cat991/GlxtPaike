
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>网页访问不鸟</title>
<%-- 静态包含 base标签、css、jQuery --%>
	<%@include file="/html/common/head.jsp"%>
</head>
<body class="error-404">
<div id="doc_main">
	<section class="bd clearfix">
		<div class="module-error">
			<div class="error-main clearfix">
				<div class="label"></div>
				<div class="info">
					<h3 class="title">啊哦，你所访问的页面不存在了，可能是炸了</h3>
					<div class="reason">
						<p>可能的原因：</p>
						<p>1.手抖打错了。</p>
						<p>2.链接过了保质期。</p>
					</div>
					<div class="oper">
						<p><a href="${pageContext.request.contextPath}/html/index.jsp">回到网站首页&gt;</a></p>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

</body>
</html>
