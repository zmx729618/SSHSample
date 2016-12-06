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
        <title>导入导出Demo</title>
		 <style>
    		.ui_main{padding:1px !important; }
			.ui_content{padding:1px !important; }
		</style>
		
	<script type="text/javascript">
     
     //===========导出【开始】===================
     function exportToXls() {
    	 window.location.href = "${ctx}/test/export";
     }
   //===========导出【结束】===================
	   
	   
     
     //========数据导入【开始】========
     function importXls(){	
    	 window.location.href = "${ctx}/test/import";            
	}
	
   //========数据导入【结束】========
	
   	</script>
   	</head>
	<body >
		<!--页签-->
		 <div class="tab_bg"><span class="tab_white"></span>
	       <ul class="tab">
				<li >
					导入导出Demo
				</li>				
	        </ul>
	     </div>
	     <!--search-->
	     <div class="searchbox">
         <div class="searchbox_inner">
           <div class="searchbox_bg" >
				
				<table>
					<tr> 	
						<td nowrap="nowrap" style="padding-left: 15px">
							<button type="button" class="addbox_btn4" id="exportLayout" name="exportLayout" onclick="exportToXls();">导出Demo</button>&nbsp;&nbsp;
							<button type="button" class="addbox_btn4" id="importLayout" name="importLayout" onclick="importXls();">导入Demo</button>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
           </div>
         </div>
       </div>
        
 
</body>
</html>
