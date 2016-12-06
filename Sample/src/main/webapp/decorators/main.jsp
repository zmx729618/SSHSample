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
    	<!-- 头部 -->
		<div>		
			<jsp:include page="header.jsp" />
		</div>

		<!-- 中部 -->
		<div >
			<sitemesh:write property="body" />
		</div>
		
        <!-- 尾部 -->
		<div>			
			<jsp:include page="footer.jsp" />
		</div>
  </body>
</html>
