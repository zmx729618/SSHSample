<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>会员注册</title>
	<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/css/register.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/js/validate/css/tooltips.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/js/validate/css/style_min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"  src="${ctx}/js/validate/prototype_for_validation.js"></script>
    <script type="text/javascript"  src="${ctx}/js/validate/validation_cn.js"></script>
    <script type="text/javascript"  src="${ctx}/js/validate/tooltips.js"></script>
	<script type="text/javascript"  src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
	<script type="text/javascript"  src="${ctx}/js/jquery.form-3.46/jquery.form-3.46.js"></script>
	<script>
	     
	     function submitform(){ 	                 
	         //调用验证框架
	   	     var validation = new Validation("registerForm", {
			     immediate :true,
			     onSubmit :true,
			     stopOnFirst :false	    
		     });
		     var resultValidate = validation.validate();
		     if(resultValidate){
		       $("#submitBtn").attr("disabled","disabled"); 
	           var options = { 
	                url:"${ctx}/user/register?d="+Math.random(),
				    success:showResponse		   
	           };   
	           alert("wwwwww");	
	           $("#registerForm").ajaxSubmit(options);
	           
		     }
	
	    }
	    //submitform方法提交后的回调函数
	  	function showResponse(msg) {
			if(msg == "ok") {//操作成功，提示2s并自动跳转到用户浏览的页面
			    $("#submitBtn").removeAttr("disabled");	
			    var refUrl = document.referrer;
			    alert("用户注册成功，请到注册邮箱去激活用户！");
	            window.location.href = refUrl;        
				
			}else if(msg == "vcerror"){//操作失败，提示跳转到注册页面重新注册
				$("#submitBtn").removeAttr("disabled");
				alert("验证码错误！"); 
				change();								
			}else{//操作失败，提示跳转到注册页面重新注册
				$("#submitBtn").removeAttr("disabled");
				var refUrl = document.referrer;
				window.location.href = refUrl;  								
			}
		
		}
		
	/* 	function createXmlHttp(){
			   var xmlHttp;
			   try{ // Firefox, Opera 8.0+, Safari
			        xmlHttp=new XMLHttpRequest();
			    }
			    catch (e){
				   try{// Internet Explorer
				         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				      }
				    catch (e){
				      try{
				         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				      }
				      catch (e){}
				      }
			    }
	
				return xmlHttp;
		}
		
		
		function checkUsername1(){
			// 获得文件框值:
			var username = document.getElementById("username").value;
			// 1.创建异步交互对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						document.getElementById("span1").innerHTML = xhr.responseText;
					}
				}
			};
			// 3.打开连接
			xhr.open("GET","${ctx}/user/findByName?time="+new Date().getTime()+"&username="+username,true);
			// 4.发送
			xhr.send(null);
		} */
		
		function checkUsername(){
		   
		   var username = $("#username").val(); 
		   if(username!=null && username!=""){		   
			   $.ajax({
				   type: "GET",
				   url: "${ctx}/user/findByName?time="+new Date().getTime()+"&username="+username,
				   success: function(msg){
				     $("#span1").html(msg);
				   }
				});		   		   
		   } 

		}
		
	
	    //获取验证码	
		function change(){
			var img1 = document.getElementById("checkImg");
			img1.src="${ctx}/user/checkImg?"+new Date().getTime();
		}
	</script>
</head>
<body>
<div style="text-align: center;">
	<div class="title">
		<strong>会员注册</strong>USER REGISTER
	</div>
	
	<form id="registerForm"  name="registerForm" >
		<table >
			<tbody><tr>
				<th>
					<span class="requiredField">*</span>用户名:
				</th>
				<td>
					<input type="text" id="username" name="username" class="text required min-length-3" maxlength="20" onblur="checkUsername()"/>
					<span id="span1"></span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密&nbsp;&nbsp;码:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text required validate-alphanum min-length-6" maxlength="20" />						
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>确认密码:
				</th>
				<td>
					<input id="repassword" type="password" name="repassword" class="text required equals-password min-length-6" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>E-mail:
				</th>
				<td>
					<input type="text" id="email" name="email" class="text validate-email" maxlength="200">
					<span></span>
				</td>
			</tr>
					<tr>
						<th>
							姓名:
						</th>
						<td>
								<input type="text" name="name" class="text" maxlength="200"/>
								<span></span>
						</td>
					</tr>
					
					<tr>
						<th>
							电话:
						</th>
						<td>
								<input type="text" name="phone" class="text validate-mobile-phone" />
						</td>
					</tr>
					
					<tr>
						<th>
							地址:
						</th>
						<td>
								<input type="text" name="addr" class="text "  maxlength="200"/>
								<span></span>
						</td>
					</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>验证码:
					</th>
					<td>
						<span class="fieldSet">
							<input type="text" id="checkcode" name="checkcode" class="text captcha required" maxlength="4" >
							<img id="checkImg" class="captchaImage" src="${ctx}/user/checkImg" onclick="change()" title="点击更换验证码">
						</span>
					</td>
				</tr>
			<tr>
				<th>&nbsp;
					
				</th>
				<td>
					<input id="submitBtn" type="button" class="submit" value="同意以下协议并注册"  onclick="submitform();" >
				</td>
			</tr>
			<tr>
				<th>&nbsp;
					
				</th>
				<td>
					注册协议
				</td>
			</tr>
			<tr>
				<th>&nbsp;
					
				</th>
				<td>
					<div id="agreement" class="agreement" style="height: 200px;">
						<p>尊敬的用户欢迎您注册成为本网站会员。请用户仔细阅读以下全部内容。如用户不同意本服务条款任意内容，请不要注册或使用本网站服务。如用户通过本网站注册程序，即表示用户与本网站已达成协议，自愿接受本服务条款的所有内容。此后，用户不得以未阅读本服务条款内容作任何形式的抗辩。</p> <p>一、本站服务条款的确认和接纳<br>本网站涉及的各项服务的所有权和运作权归本网站所有。本网站所提供的服务必须按照其发布的服务条款和操作规则严格执行。本服务条款的效力范围及于本网站的一切产品和服务，用户在享受本网站的任何服务时，应当受本服务条款的约束。</p> <p>二、服务简介<br>本网站运用自己的操作系统通过国际互联网络为用户提供各项服务。用户必须:  1. 提供设备，如个人电脑、手机或其他上网设备。 2. 个人上网和支付与此服务有关的费用。</p> <p>三、用户在不得在本网站上发布下列违法信息<br>1. 反对宪法所确定的基本原则的； 2. 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的； 3. 损害国家荣誉和利益的； 4. 煽动民族仇恨、民族歧视，破坏民族团结的； 5. 破坏国家宗教政策，宣扬邪教和封建迷信的； 6. 散布谣言，扰乱社会秩序，破坏社会稳定的； 7. 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的； 8. 侮辱或者诽谤他人，侵害他人合法权益的； 9. 含有法律、行政法规禁止的其他内容的。</p> <p>四、有关个人资料<br>用户同意:  1. 提供及时、详尽及准确的个人资料。 2. 同意接收来自本网站的信息。 3. 不断更新注册资料，符合及时、详尽准确的要求。所有原始键入的资料将引用为注册资料。 4. 本网站不公开用户的姓名、地址、电子邮箱和笔名。除以下情况外:  a) 用户授权本站透露这些信息。 b) 相应的法律及程序要求本站提供用户的个人资料。</p> <p>五、服务条款的修改<br>本网站有权在必要时修改服务条款，一旦条款及服务内容产生变动，本网站将会在重要页面上提示修改内容。如果不同意所改动的内容，用户可以主动取消获得的本网站信息服务。如果用户继续享用本网站信息服务，则视为接受服务条款的变动。</p> <p>六、用户隐私制度<br>尊重用户个人隐私是本网站的一项基本政策。所以，本网站一定不会在未经合法用户授权时公开、编辑或透露其注册资料及保存在本网站中的非公开内容，除非有法律许可要求或本网站在诚信的基础上认为透露这些信息在以下四种情况是必要的:  1. 遵守有关法律规定，遵从本网站合法服务程序。 2. 保持维护本网站的商标所有权。 3. 在紧急情况下竭力维护用户个人和社会大众的隐私安全。 4. 符合其他相关的要求。</p> <p>七、用户的帐号、密码和安全性<br>用户一旦注册成功，将获得一个密码和用户名。用户需谨慎合理的保存、使用用户名和密码。如果你不保管好自己的帐号和密码安全，将负全部责任。另外，每个用户都要对其帐户中的所有活动和事件负全责。你可随时根据指示改变你的密码。用户若发现任何非法使用用户帐号或存在安全漏洞的情况，请立即通告本网站。   八、 拒绝提供担保 用户明确同意信息服务的使用由用户个人承担风险。本网站不担保服务不会受中断，对服务的及时性，安全性，出错发生都不作担保，但会在能力范围内，避免出错。</p> 
					</div>
				</td>
			</tr>
		</tbody>
		</table>
		</form>
	</div>
</body>
</html>