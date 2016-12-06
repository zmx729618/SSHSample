<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>

         <head>

                   <meta charset="utf-8">

                   <title>sprint hello</title>

         </head>

         <body>hello ${spring}!

         </body>

</html>
