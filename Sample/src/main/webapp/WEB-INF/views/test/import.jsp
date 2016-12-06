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
	    <script type="text/javascript"  src="${ctx}/js/basic/win/artDialog.js"></script>	  
	    <script src="${ctx}/js/ligerui/core/base.js" type="text/javascript"></script>        
        <script src="${ctx}/js/ligerui/plugins/ligerTab.js" type="text/javascript"></script>
		<title>导入Demo</title>
    	      
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
     var sequence = 1;
     var layouttab = null;
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
    	 
    	//初始化tab
     	layouttab = $("#layouttab").ligerTab({height:'100%', heightDiff: 0, contextmenu:false,
     				onAfterSelectTabItem: function (tabid){}
     	}); 
    	
     	//$("input:checkbox[value='name']").click();
     	//$("input:checkbox[value='name']").trigger('click');
     	
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
     
     function getHeaderData(){
    	 var header="";
    	 $(".ss-item").each(function() {
    		 var fieldName = $(this).find("input").val();
			 var fieldText = $(this).text();				
			 header += "," + fieldName + "@" + fieldText;
		 });
    	 if(header.length>1){
    		 header = header.substring(1);
    	 }
    	 return header;
     }
     
     function exportToXls() {
    	 var header=getHeaderData();    	 
    	 if(header==""){
    		 alert("请选择要导出的字段！");
    		 return;
    	 }    	 
    	 var url = "${ctx}/test/exportXLS?header=" + encodeURI(header) + "&d="+Math.random();
    	 window.location.href = url;
     }
   //===========导出模板【结束】===================
	   
   //========数据导入【开始】========
   function importXls(){	
      var header=getHeaderData();
  	  if(header==""){
       		 alert("请选择要导入的字段！");
       		 return;
       } 
  	   $("#header").val(header);
       art.dialog({
          id:'importExcelNameWin',
          title:'请选择导入文件',
          width:308,
          height:100,
          fixed:true,
          lock:true,
          background: '#ffffff;', // 背景色
          opacity: 0.5,
          padding:0,
          content:document.getElementById('importExcelName_div')
      });
	}
	
	   //excel格式验证
 	function checkExcel(obj){
		var file = $("#"+obj.id).val();
		if(""!=file)
		{
			if(!(/(xls)$/i.test(obj.value))) {
				alert("请选择xls格式的文件!");				
				if(window.ActiveXObject) {//for IE
					obj.select();
					document.selection.clear();
				$("#text_file").text("");
		        } else if(window.opera) {//for opera
		            obj.type="text";
		            obj.type="file";
		        } else 
		        	obj.value="";//for FF,Chrome,Safari
		    }
		}
	}
		
	//导入文件确认
    function congfirmImportExcelName() {
          var text_file = $.trim($("#text_file").val());
          if(text_file == "" || text_file.length < 1){
       			alert("请选择文件!");
       	  }else{
	     	 if(confirm("是否确定导入？")){
	     	 	  //$("#importBtn").attr("disabled","disabled");
			      var options = { 
				 	 url:"${ctx}/test/importXLS?d="+Math.random(),
				 	 //dataType: 'text', 
				 	 success: function(msg) {
				 	 	$("#importBtn").removeAttr("disabled");
						if(msg.indexOf("fileIsOpenning") > 0) {
							alert("文件正在使用中，请关闭后在上传！");
						}else if(msg.indexOf("ok") > -1) {
							alert("导入成功！");	
							cancelImportExcelNameWin();	
							openTab();
						}else {
						    alert("操作失败,请稍后再尝试！");
						}
					}
			      };
			      $("#uploadExcelForm").ajaxSubmit(options);
	     	 }
		 }
      }
	
	function openTab(){
		var url3 = "${ctx}/test/importView";					
		var tabid = '数据查看'+sequence;
		sequence++;
		layouttab.addTabItem({ url: url3,tabid:tabid});
	}
	
	 //关闭模板名称输入窗口
     function cancelImportExcelNameWin() {
         art.dialog.list['importExcelNameWin'].close();
     }
   	</script>
   	</head>
	<body >
		
	     <div id="layouttab">
			  <div tabid="designSheet" title="模板下载"  lselected="true" id="dataGrid" style="width:100%; height:650px;">
			  		<!--配置信息-->
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
			         	<button type="button" class="addbox_btn4" id="exportLayout" name="exportLayout" onclick="exportToXls();" style="margin-left:15px;">1.导出模板</button>
			         	<button type="button" class="addbox_btn4" id="importLayout" name="importLayout" onclick="importXls();" style="margin-left:15px;">2.导入数据</button>
			         	<button type="button" class="addbox_btn4" id="importLayout" name="importLayout" onclick="openTab();" style="margin-left:15px;">3.查看数据</button>
			         	<br />
			         	<br />
			         	<br />
			       </div>
			  </div>
	     </div>
	     
        
 <div id="importExcelName_div" style="display: none;">
	   <form action="" id="uploadExcelForm" name="uploadExcelForm" enctype="multipart/form-data" method="post">
	   <div class="" >
	        <div class="inner">
				<table width="400px" border="0" cellspacing="0" cellpadding="0" class="addbox">
	                <tr>
	                    <th><span class="redstar">*</span>文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</th>
						<td nowrap="nowrap">
							<input type="hidden" id="header" name="header" value="" />
							<input type="file" name="file" id="file" style="position:absolute;top:50px;width:0px;height:0px;filter:alpha(opacity=0); " onchange="checkExcel(this);document.getElementById('text_file').value=this.value;" />
							<input id="text_file" readonly style="border: 1px solid #00A8E6;width:200px;height:19px;"/>&nbsp;&nbsp;
							<input type="button" style="height:21px;" onclick="file.click();" id="button_view" class="addbox_btn2" value="浏    览"/> 
						</td>
	                </tr>
	                <tr>
	                    <th><span class="redstar">*</span>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</th>
						<td nowrap="nowrap">
							<input type="radio" name="type" value="1" checked />添加&nbsp;&nbsp;
							<input type="radio" name="type" value="2" />覆盖&nbsp;&nbsp;
						</td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    <div class="popping_btn">
	        <input type="button" id="importBtn" class="addbox_btn2" value="确  定" onclick="congfirmImportExcelName();"/>
	        &nbsp;&nbsp;
	        <input type="button" class="addbox_btn2" value="取  消" onclick="cancelImportExcelNameWin();"/>
	    </div>
	    </form>
	</div>
</body>
</html>