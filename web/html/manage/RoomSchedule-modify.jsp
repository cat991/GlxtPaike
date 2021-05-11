<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Glxtpaike"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改教学安排</title>
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
	
	<script type="text/javascript">
	function delClick() {
	var chValue = confirm("你确定要删除【 " + $("#PAIKECOURSENAME").val() + " 】的课表？");
		var delId = ${requestScope.paiKeList.paikeId};
			
			//封装成一个对象
			var obj = {}
			obj.delId = delId
			
      		if(chValue){
      			window.location.href="/GuanLiXT/PaikeServlet?opr=paiKeDel&delId="+delId;
      		}
	}
	</script>
	
</head>
<body>


	<%@include file="/html/common/hTheader.jsp"%>
	<%@include file="/html/common/hTchildNav.jsp"%>
<div id="main" class="wrap">
	<%@include file="/html/common/hTlefter.jsp"%>
	<div class="main">
		<h2>修改教学安排或删除教学安排</h2>
		<div class="manage">
			<form id="roomAdd" action="/GuanLiXT/PaikeServlet?opr=paiKeModify&pKId=${requestScope.paiKeList.paikeId}" method="post" >
				<table class="form">
					<tr>
						<td class="field">课程名称(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKECOURSENAME" name="PAIKECOURSENAME" value="${requestScope.paiKeList.paikeCourseName}"/>
							<span></span>
						</td>
					</tr>
                    <tr>
						<td class="field">任课老师(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKETEACHER" name="PAIKETEACHER" value="${requestScope.paiKeList.paikeTeacher}"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课周数(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEWEEKS" name="PAIKEWEEKS" value="${requestScope.paiKeList.paikeWeeks}"/>
							<span></span>
						</td>
					</tr>					
					<tr>
						<td class="field">上课节数(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEJIESHU" name="PAIKEJIESHU" value="${requestScope.paiKeList.paikeJieshu}"/>
							<span></span>
						</td>
					</tr>
					
					<tr>
						<td class="field">周几上课(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEWEEKDAY" name="PAIKEWEEKDAY" value="${requestScope.paiKeList.paikeWeekDay}"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课班级(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKECLASSNAME" name="PAIKECLASSNAME" value="${requestScope.paiKeList.paikeClassName}"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">上课地点(*)：</td>
						<td>
							<input type="text" class="text" id="PAIKEROOMNAME" name="PAIKEROOMNAME" value="${requestScope.paiKeList.paikeRoomName}"/>
							<span></span>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<label class="ui-blue"><input type="submit" id="submOk" name="submit" value="确定" /></label>
							
						</td>
					</tr>
				</table>
			</form>
			<label class="ui-blue" style="float:right;"><input type="button" onclick="delClick()" id="delpaike" name="delpaike" value="删除该课程" /></label>
		</div>
	</div>
</div>
<%@include file="/html/common/footer.jsp"%>
</body>
</html>
