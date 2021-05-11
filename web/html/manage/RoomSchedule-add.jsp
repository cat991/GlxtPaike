<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Glxtpaike"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增教学安排</title>
	<%-- 静态包含 base标签、css、jQuery --%>
	<%@include file="/html/common/head.jsp"%>
	
	<style type="text/css">
		span.error {
    		display: inline-block;
    		border: 1px solid #ff835a;
    		background-color: #ffe8e0;
    		
    		vertical-align: middle;
    		margin-left: 10px;
    		padding-left: 22px;
    		padding-right: 10px;
    		background: url(./html/images/bg.png) -232px -63px no-repeat;
		}
	</style>
	
</head>
<body>


	<%@include file="/html/common/hTheader.jsp"%>
	<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
	<%@include file="/html/common/hTlefter.jsp"%>
	<div class="main">
		<h2>新增教学安排</h2>
		<div class="manage">
			<form id="roomAdd" action="/GuanLiXT/PaikeServlet?opr=paiKeAdd" method="post" >
				<table class="form">
					<tr>
						<td class="field">课程名称(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKECOURSENAME" name="PAIKECOURSENAME" placeholder="例：java基础" />
							<span></span>
						</td>
					</tr>
                    <tr>
						<td class="field">任课老师(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKETEACHER" name="PAIKETEACHER" placeholder="例：张三" />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课周数(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEWEEKS" name="PAIKEWEEKS" placeholder="例：1~2周" />
							<span></span>
						</td>
					</tr>					
					<tr>
						<td class="field">上课节数(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEJIESHU" name="PAIKEJIESHU" placeholder="填一位阿拉伯数字例：1" />
							<span></span>
						</td>
					</tr>
					
					<tr>
						<td class="field">周几上课(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEWEEKDAY" name="PAIKEWEEKDAY" placeholder="填一位阿拉伯数字例：1；周日用数字7代替" />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课班级(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKECLASSNAME" name="PAIKECLASSNAME" placeholder="例：java2班"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课地点(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEROOMNAME" name="PAIKEROOMNAME" placeholder="例：博雅B123"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" id="submOk" name="submit" value="确定" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<%@include file="/html/common/footer.jsp"%>
</body>
</html>
