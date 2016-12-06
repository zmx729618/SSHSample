package org.nercita.core.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestContext;

public class BundleUtil {
	
	public static ResourceBundle getResourceBundle(HttpServletRequest request, String resourceBase ){
		 //根据request获取客户端Locale
		 RequestContext requestContext =new RequestContext(request);
		 Locale clientLocale = requestContext.getLocale();
		 //根据Locale获取ResourceBundle
		 ResourceBundle bundle = ResourceBundle.getBundle(resourceBase,clientLocale,BundleUtil.class.getClassLoader());
         return bundle;
	}
	

}
