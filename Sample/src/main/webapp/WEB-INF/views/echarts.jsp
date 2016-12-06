<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<script type="text/javascript"  src="${ctx}/js/echarts/echarts-all.js"></script>
    <script type="text/javascript"  src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
	<title>ECharts-测试</title>
</head>
<body>     
	   
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	    <div id="main" style="height:400px;width: 800px"></div>
	    <script type="text/javascript">	        
	        // 基于准备好的dom，初始化echarts图表
	        var myChart = echarts.init(document.getElementById('main'));        
	        var option = {
	            tooltip: {
	                show: true
	            },
	            legend: {
	                data:['销量']
	            },
	            xAxis : [
	                {
	                    type : 'category',
	                    data :  eval('${data}')
	                }
	            ],
	            yAxis : [
	                {
	                    type : 'value'
	                }
	            ],
	            series : [
	                {
	                    "name":"销量",
	                    "type":"bar",
	                    "data":[5, 20, 40, 10, 10, 20]
	                }
	            ]
	        };
	        // 为echarts对象加载数据 
	        myChart.setOption(option); 
	    </script>
	<div>
	   hello,${data}!
	</div>
</body>
</html>