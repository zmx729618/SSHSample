<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>jqGrid练习</title>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet"  href="${ctx}/js/jqgrid/jquery-ui-custom/jquery-ui.css" />
	<link rel="stylesheet"  href="${ctx}/js/jqgrid/css/ui.jqgrid.css"/>		
	<script type="text/javascript" src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>	
	<script type="text/javascript" src="${ctx}/js/jqgrid/jquerry-ui-custom/jquery-ui.js"></script>	
	<script type="text/javascript" src="${ctx}/js/jqgrid/js/i18n/grid.locale-cn.js"></script>	
	<script type="text/javascript" src="${ctx}/js/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript">
	
		$(function(){
		  pageInit();
		});
		function pageInit(){
		  var lastsel;
		  jQuery("#list2").jqGrid(
		      {
		        datatype : "local",
		        height : 250,
		        colNames : [ 'Inv No', 'Date', 'Client', 'Amount', 'Tax','Total', 'Notes' ],
		        colModel : [ 
		                     {name : 'id',index : 'id',width : 60,sorttype : "int"}, 
		                     {name : 'invdate',index : 'invdate',width : 90,sorttype : "date"}, 
		                     {name : 'name',index : 'name',width : 100}, 
		                     {name : 'amount',index : 'amount',width : 80,align : "right",sorttype : "float"}, 
		                     {name : 'tax',index : 'tax',width : 80,align : "right",sorttype : "float"}, 
		                     {name : 'total',index : 'total',width : 80,align : "right",sorttype : "float"}, 
		                     {name : 'note',index : 'note',width : 150,sortable : false} 
		                   ],
		        rownumber:10,
		        rowList:[10,20,30],
		        pager:'pager2',
		        viewrecords: true,
		        sortorder: "desc",
		        multiselect : true,
		        caption : "Manipulating Array Data"
		      });
		     
		  var mydata = [ 
		                 {id : "1",invdate : "2007-10-01",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"}, 
		                 {id : "2",invdate : "2007-10-02",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"}, 
		                 {id : "3",invdate : "2007-09-01",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"}, 
		                 {id : "4",invdate : "2007-10-04",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"}, 
		                 {id : "5",invdate : "2007-10-05",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"}, 
		                 {id : "6",invdate : "2007-09-06",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"}, 
		                 {id : "7",invdate : "2007-10-04",name : "test",note : "note",amount : "200.00",tax : "10.00",total : "210.00"}, 
		                 {id : "8",invdate : "2007-10-03",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"}, 
		                 {id : "9",invdate : "2007-09-01",name : "test3",note : "note3",amount : "400.00",tax : "30.00",total : "430.00"},
		                 {id : "10",invdate : "2015-10-03",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"}, 
		                 {id : "11",invdate : "2015-10-13",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"},
		                 {id : "12",invdate : "2015-10-05",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"}, 
		                 {id : "13",invdate : "2015-10-15",name : "test2",note : "note2",amount : "300.00",tax : "20.00",total : "320.00"} 
		               ];
		  for ( var i = 0; i <= mydata.length; i++){
		    jQuery("#list2").jqGrid('addRowData', i + 1, mydata[i]);
		  }
		  
		  jQuery("#list2").jqGrid('navGrid','#psortrows',{edit:false,add:false,del:false});
		  jQuery("#list2").jqGrid('sortableRows');
		  
		 }
	</script>
 
</head>
<body>
    <table id="list2"></table> 
    <div id="pager2"></div>

</body>
</html>
