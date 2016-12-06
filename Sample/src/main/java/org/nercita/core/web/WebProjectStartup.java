package org.nercita.core.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web项目系统启动时会执行一些初始化操作,该类已经在web.xml中注册
 * 
 * <br />
 * 	<listener>
 *		<listener-class>org.wangzz.core.web.WebProjectStartup</listener-class>
 *	</listener>
 */
@SuppressWarnings("unused")
public class WebProjectStartup implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		System.out.println("[org.wangzz.core.web.WebProjectStartup] 系统正在启动.... 在这里可以执行相关初始化操作");
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
	}

}
