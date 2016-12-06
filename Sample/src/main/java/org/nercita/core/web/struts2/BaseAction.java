package org.nercita.core.web.struts2;

import com.opensymphony.xwork2.ActionSupport;


/**
 * struts2的Action基类
 *
 * @author wangzz
 * 
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	private String resultUrl = null;
	
	public String redirect(String resultUrl) {
		this.resultUrl = resultUrl;
		if(resultUrl.contains(".jsp")){
			return "redirect-jsp";
		}else {
			return "redirect-action";
		}
	}
	
	public String dispatcher(String resultUrl) {
		this.resultUrl = resultUrl;
		if(resultUrl.contains(".jsp")){
			return "redirect-jsp";
		}else {
			return "redirect-action";
		}
	}
	
	
	
	
	public String getResultUrl() {
		return resultUrl;
	}


	
	
	
}
