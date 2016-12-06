<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>   
    <link rel="stylesheet" href="${ctx}/js/validation.engine/css/validationEngine.jquery.css"  />
	<link rel="stylesheet" href="${ctx}/js/validation.engine/css/template.css" />
	<script type="text/javascript"  src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>	
	<script type="text/javascript"  src="${ctx}/js/validation.engine/jquery.validationEngine.js"></script>
	<script type="text/javascript"  src="${ctx}/js/validation.engine/jquery.validationEngine-zh_CN.js"></script>    
	<title>ValidationEngine-测试</title>
	
		<script type="text/javascript">
		
		function validate() {
			
		}
		function formSuccess() {
			alert('Success!');
		}
		
		function formFailure() {
			alert('Failure!');
		}
		jQuery(document).ready(function(){
			//binds form submission and fields to the validation engine
			jQuery("#formID").validationEngine({
				promptPosition: "topRight",
				onFormSuccess:formSuccess,
				onFormFailure:formFailure
			});
		});

		function checkHELLO(field, rules, i, options){
			if (field.val() != "QWER") {
				// this allows to use i18 for the error msgs
				//return options.allrules.validate2fields.alertText;
				return "请输入 'QWER'";
			}
		}
		
		var validator;
		function save(){
			validator = $("#formID").validationEngine('validate');
			//alert(!validator);
		}
	</script>
		
</head>
<body>     
    
	<div>
	   hello,${data}!
	</div>
	<div>	   
	   <form action="" id="formID" class="">
		  <p>
		   <label for="cname1"> 用户名：</label> 
		    <input type="text" name=“username” id="username_id" class="validate[required, ajax[ajaxUser] text-input" /><br><br>
		  </p>
		  
		  <p>
		    <label for="cname2">密码： </label> 
		    <input type="text" name=“password” id="password_id"  class="validate[required] text-input"/><br><br>
		  </p>
		  
		  <p>
		    <label for="cname6">QWER</label> 
		    <input type="text" name="name" id="name_id" class="validate[required,funcCall[checkHELLO]] text-input"/><br><br>
		  </p>
		  
		  <p>
		    <label for="cname5">年龄： </label> 
		    <input type="text" name=“age” id="age_id"  class="validate[required,custom[integer],min[1]] text-input"/><br><br>
		  </p>
		  <p>
		      <span>性别:</span>  
	                <select name="sport" id="sport"  class="validate[required]"  id="sport"  >  
	                    <option value="">-请选择-</option>  
	                    <option value="option1">男</option>  
	                    <option value="option2">女</option>  
	                </select>  
	      </p>
		  <p>
		    <label for="cname3">邮箱：</label> 
		    <input type="text" name=“email” id="email_id" class="validate[required,custom[email]] text-input"/><br><br>
		  </p>
		  <p>
		    <label for="cname4">网址：</label> 
		    <input type="text" name=“url” id="url_id" class="validate[required,custom[url]] text-input"/><br><br>
		  </p>
		  <p>
		    <label for="cname5">资产：</label> 
		    <input type="text" name=“price” id="price_id" class="validate[required,custom[number],custom[validate42]] text-input"/><br><br>
		  </p>
		  <p>
		   <span>radio 1: </span>  
	       <input class="validate[required] radio" type="radio" name="data[User][preferedColor]"  id="radio1"  value="5">  
	       <span>radio 2: </span>  
	       <input class="validate[required] radio" type="radio" name="data[User][preferedColor]"  id="radio2"  value="3"/>  
	       <span>radio 3: </span>  
	       <input class="validate[required] radio" type="radio" name="data[User][preferedColor]"  id="radio3"  value="9"/>
	       </p>
		   <p>
	  		<input class="submit" type="button" onclick="save()" value="验证"/>
	  	   </p>
  </form>
	
	
	
	</div>
	
	
	
</body>
</html>