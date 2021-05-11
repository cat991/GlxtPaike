
<!DOCTYPE html>
<html>
	<head>
		<title>出错了</title> 
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/html/css/style.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/html/css/error_all.css"  type="text/css"/>
		<style>
 
.error_content{ margin: 1% auto; width: 1000px; height: 600px;   border: 1px solid #cccccc;}
.error_left{ margin: 120px 0 0 50px; width: 330px ; height: 345px; background: url(${pageContext.request.contextPath}/html/images/timg.png) no-repeat;     background-size: 100%;float: left;}
.error_right{ width: 450px; height: 590px;  float: left; }
.error_detail { margin: 180px 0 0 120px;     width: 400px; height: auto; }
.error_detail .error_p_title{ font-size: 28px; color: #eb8531;}
.error_detail .error_p_con{ font-size:14px; margin-top: 10px; line-height: 20px;}
.sp_con{ margin-left: 128px; color:#1A4EC0;margin-top: 39px;position: absolute;font-size: 18px; }
.btn_error { margin: 80px 0 0 160px;}
.btn_error a{  padding: 5px; border: 1px solid  #CCCCCC; }
.btn_back1{background: dodgerblue; color: #ffffff;}
/*.btn_back2{ margin-left:25px;background: #CCCCCC;}*/
			
	/*		 .btn {
    width: 40px;
    height: 38px;
    cursor: pointer;
    color: #FF813B;
    float: right;
    margin-top: 1px;
    border-left: solid 1px #FF813B;
    font-size: 20px;
    background: no-repeat;
}*/
 
 
		</style>
	</head>
	<body >
		<div class="error_content">
			<div class="error_left">
				 <span class="sp_con">赶紧修，大家等着呢。</span> 
			</div>
			<div class="error_right">
				<div class="error_detail">
					<p class="error_p_title">哎呦~ 服务器居然累倒了!</p>
					<p class="error_p_con">●别急，工程师正在紧急处理，马上就好。</p>
					<p class="error_p_con">●您可致电168-1688转888,通知开发人员!</p>
					<p class="error_p_con">●易买网的进步需要您的反馈,感谢您对易买网的使用,请您耐心等待!</p>
				</div>
				<div class="btn_error">
					<a class="btn_back1" href="${pageContext.request.contextPath}/html/index.jsp">返回首页</a>
				</div>
				 
			</div>
		</div>
	</body>
</html>