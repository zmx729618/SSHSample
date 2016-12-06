<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html> 
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>			    
	    <script type="text/javascript"  src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
	    <script type="text/javascript"  src="${ctx}/js/jquery.form-3.46/jquery.form-3.46.js"></script>
		<title>数据查看</title>		
		<script type="text/javascript">
			function confirmImport(){
				if(confirm("是否确定导入？")){
					alert("导入成功！");
					parent.layouttab.removeAll();
				}				
			}
	    </script>
	    <style>
	    	table,th,td{
	    		border:1px solid #B8E4F6;
	    		font-size:12px;
	    		border-collapse: collapse;
	    		border-spacing: 0px;
	    		padding:4px;    		
	    	}
	    </style>
	</head>
	<body>
		<h1>导入数据查看！</h1>
<!--    <button type="button" id="confirmBtn" name="confirmBtn" onclick="confirmImport();" >确定导入</button>
		<button type="button" id="confirmBtn" name="confirmBtn" onclick="parent.layouttab.removeAll();" style="margin-left:20px;">取消导入</button> -->
		<br />
		<br />
		<table cellpadding="0px" cellspacing="1">
				<thead>
					<tr>
						<c:forEach items="${requestScope.listHeaderText}" var="li" varStatus="status">
							<th>
								${li}
							</th>
						</c:forEach>	
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${importdata}" var="row" varStatus="i">
						<tr>
							<c:forEach items="${row}" var="col" varStatus="j">
								<td>
									${col}
								</td>
							</c:forEach>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</body>
</html>
