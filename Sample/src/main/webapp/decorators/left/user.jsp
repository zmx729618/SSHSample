<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
  <head>
    <title><sitemesh:write property="title" /></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
    <sitemesh:write property="head" /> 
  </head>
  
  <body>   	
    	<!-- user left -->
		<div >		
			<div style="width: 200px; height: 1500px; border: 1px solid #FF0000;float: left;">左侧栏</div>
		</div>
		<div>		
			<div style="width: 1200px; height: 1500px; border: 1px solid #FF0000; ">
			      <sitemesh:write property="body" />
			</div>
		</div>
		 	
  </body>
</html>
