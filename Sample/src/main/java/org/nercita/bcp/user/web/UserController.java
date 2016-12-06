package org.nercita.bcp.user.web;



import java.io.IOException;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nercita.bcp.user.domain.User;
import org.nercita.bcp.user.service.UserService;
import org.nercita.bcp.user.utils.MailUtils;
import org.nercita.core.utils.BundleUtil;
import org.nercita.core.utils.UUIDUtils;
import org.nercita.core.utils.ValidateCodeGeneratorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class UserController{
	
	private static Logger logger = LogManager.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	
    @RequestMapping("/hello")
    public String hello(ModelMap map){
    	      
    	      logger.error("测试 spring mvc");
              map.addAttribute("spring", "spring mvc");
              return "/hello";

    }

	
	
	
	/**
	 * 跳转到用户注册页面
	 * @return
	 */
	@RequestMapping("/user/registPage")
	public String registPage(){		
		return "/user/regist";
	} 
	
	/**
	 * 验证用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/user/findByName")
	@ResponseBody
	public  String  findByName(HttpServletRequest request,String username){
		ResourceBundle resourceBundle  =BundleUtil.getResourceBundle(request, "message/message-info");
		User user =  userService.findUniqueByProperty("username",username);
		System.out.println(user);
		if(user ==null){
			return "<font style='font-weight:bold; color:green'>"+resourceBundle.getString("msg.user.use")+"</font>";
		}else{
			return "<font style='font-weight:bold; color:red'>"+resourceBundle.getString("msg.user.notUse")+"</font>";
		}
		
	}
	
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping("/user/checkImg")
	public void getValidateCode(HttpServletRequest request,HttpServletResponse response){
		
		try {
			ValidateCodeGeneratorUtil.createBufferedImage(request, response, 120, 30);
		} catch (IOException e) {
			logger.error("验证码生成异常!",e);
			e.printStackTrace();
		}	

	}
	
	
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/register")
	@ResponseBody
	public  String  register(User user,String checkcode,HttpServletRequest request){
		
		String randomCode =(String)request.getSession().getAttribute("randomCode");
		if(!checkcode.equalsIgnoreCase(randomCode)){
			return "vcerror";
		}
		user.setState(0); //设置用户未激活
		String code  = UUIDUtils.getUUID();
		user.setCode(code);
		try {
			userService.save(user);
			String msg="<h3>点击下面链接激活账户</h3><h4><a href='http://localhost:8080/BCP/user/activate?code="+code+"'>http://localhost:8080/FashionShop/user/activate?code="+code+"</a><h4>";
			MailUtils.sendHTMLMail("金种子云平台官方用户注册激活邮件", user.getEmail(), msg);
		} catch (Exception e) {
			logger.error("用户注册失败", e);
			return "error";
		}
		return "ok"; 
	}
	
	
	/**
	 * 激活用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/activate")
	public  String  register(String code, ModelMap modelMap){		
		User user =  userService.findUniqueByProperty("code", code);
		if(user!=null){
			user.setState(1);
			user.setCode(null);
			try {
				userService.update(user);
				modelMap.put("msg", "用户激活成功");
			} catch (Exception e) {
				logger.error("数据更新异常", e);	
			}
			
		}else{
			modelMap.put("msg", "用户激活失败，激活码可能失效");
		}

		return "user/activate"; 
	}
	
	
	/**
	 * 跳转到用户登陆页面
	 * @return
	 */
	@RequestMapping("/user/loginPage")
	public String loginPage(){
		
		return "/user/login";
	} 
	
	/**
	 * 用户登陆
	 * @return
	 */
	@RequestMapping("/user/login")
	public String login(User user, HttpServletRequest request, ModelMap modelMap  ){
		User loginUser =  userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		if(loginUser ==null){ //登陆失败
			modelMap.put("msg", "登录失败，用户名或密码错误或者用户未激活");
			return "/login";
		}else{//登陆成功
			request.getSession().setAttribute("loginUser", loginUser.getUsername());
			return "/index";
		}

	} 
	
	
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping("/user/quit")
	public String login(HttpServletRequest request){
		
			request.getSession().invalidate();
			return "/index";
		

	} 
	
	

	
	
	
	
	
	
	
	
	

}
