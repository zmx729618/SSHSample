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
		<title>导出Demo</title>
     
		 <style>
		 	#field-source span{
		 		margin-right:15px;
		 	}
			#selector-set .ss-item{
				position: relative;
				display: inline-block;
				height: 22px;
				line-height: 22px;
				border: 1px solid #bbb;
				font-size: 12px;
				vertical-align: top;
				margin: 0 5px 5px 0;
				padding: 0 26px 0 4px;
				cursor: pointer;
				background-color:#eef;
			}			
			#selector-set .ss-item i {
				display: block;
				position: absolute;
				width: 25px;
				height: 22px;
				right: 0;
				top: 0;
				background: url(${ctx}/static/images/a-del.png) no-repeat 7px -140px;
			}
			#selector-set .ss-item:hover{border-color:#e4393c;text-decoration:none}			
			#selector-set .ss-item:hover i{background-color:#e4393c;background-position:7px -158px}
			#selector-set .ss-item em{color:#e4393c;font-style:normal;}
			
		</style>
		
	<script type="text/javascript">
     
     //===========导出【开始】===================
     $(function(){
    	 $("input[name='tableField']").each(function() {
 			$(this).click(function () {
 				var f=this.checked;
 				var fieldName = $(this).val();
				var fieldText = $(this).parent().text();
 				if(f==true){
 					if($("input:hidden[value='" + fieldName + "']").length==0){
 						var html="<a class='ss-item' ><input type='hidden' value='" + fieldName + "'/><em>" + fieldText + "</em><i></i></a>";
 	 					$("#selector-set").append(html);
 	 					var sobj=$("input:hidden[value='" + fieldName + "']");
 	 					$(sobj).parent().find("i").click(function (){
 	 						removeItem(sobj);
 	 					});
 					}
 					//var lobj = $("#selector-set>a:last"); 					
 				}else{
 					var sobj=$("input:hidden[value='" + fieldName + "']");
 					if(sobj.length>0){
 						removeItem(sobj); 						
 					}
 				}				
 			});			
 		});	
     });
     
     //添加所有
     function checkAll(){
    	 $("#selector-set").empty();
    	 if($("#selectAll").prop('checked')==true){    		 
    		 $("input[name='tableField']").each(function() {
    			 $(this).click();
    		 });
    		 $('input[type=checkbox]').prop('checked',true);
    	 }else{
    		 $('input[type=checkbox]').prop('checked',false);
    	 }
     }	 
     
     function removeItem(obj){
    	 var objParent = $(obj).parent();
    	 var fieldName = objParent.find("input").val();
    	 $("input:checkbox[value='" + fieldName + "']").prop('checked',false);
    	 $("#selectAll").prop('checked',false);
    	 objParent.remove();
     }
     
     function clearall(){
    	 $("#selector-set").empty();
    	 $('input[type=checkbox]').prop('checked', false);
     }   
     
     
     function exportToXls() {
    	 var header="";
    	 $(".ss-item").each(function() {
    		 var fieldName = $(this).find("input").val();
			 var fieldText = $(this).text();				
			 header += "," + fieldName + "@" + fieldText;
		 });
    	 if(header==""){
    		 alert("请选择要导出的字段！");
    		 return;
    	 }
    	 if(header.length>1){
    		 header = header.substring(1);
    	 }
    	 var url = "${ctx}/test/exportXLS?header=" + encodeURI(header) + "&d="+Math.random();
    	 window.location.href = url;
     }
   //===========导出【结束】===================
	   
	
   	</script>
   	</head>
	<body >
		<!--页签-->
		 <div class="tab_bg"><span class="tab_white"></span>
	       <ul class="tab">
				<li >
					导出Demo
				</li>				
	        </ul>
	     </div>
	     <!--search-->
	     <div class="searchbox">
         	<div class="searchbox_inner">
           		<div class="searchbox_bg" style="width:50%">				
					<table id="field-source">
						<tr> 	
							<td nowrap="nowrap" style="padding-left: 15px">
								<input type="checkbox" id="selectAll" name="selectAll" onclick="checkAll();">全选</input>	<br /><br />
							</td>
						</tr>
						<tr> 	
							<td nowrap="nowrap" style="padding-left: 15px">
								<span><input type="checkbox" name="tableField" value="name01"/>名称01</span>
								<span><input type="checkbox" name="tableField" value="name02"/>名称02</span>
								<span><input type="checkbox" name="tableField" value="name03"/>名称03</span>
								<span><input type="checkbox" name="tableField" value="name04"/>名称04</span>
								<span><input type="checkbox" name="tableField" value="name05"/>名称05</span>						
								<span><input type="checkbox" name="tableField" value="name06"/>名称06</span>
							</td>
						</tr>
					</table>
           		</div>
         	</div>
         	<br />
         	<div class="searchbox_inner" style="position:relative;">
           		<div class="searchbox_bg" style="background: #fff;min-height:30px;width:50%;">				
					<div id="selector-set"></div>					
           		</div>
           		<button type="button" class="addbox_btn2"  onclick="clearall();" style="margin-left:15px;position:absolute;top:20px;left:52%;">清空</button> 
         	</div>
         	<br />
         	<br />
         	<button type="button" class="addbox_btn2" id="exportLayout" name="exportLayout" onclick="exportToXls();" style="margin-left:15px;">导出</button>
         	<br />
         	<br />
         	
       </div>
        

</body>
</html>
