package org.nercita.core.web.struts2;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 测试拦截器
 * 
 * @author wangzz
 *
 */
public class TestActionInterceptor extends AbstractInterceptor  {
	
	private static final long serialVersionUID = 1357966474930614876L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		long start = System.currentTimeMillis();
		HttpServletRequest request = ServletActionContext.getRequest();
		String method = invocation.getProxy().getMethod();
		String namespace = invocation.getProxy().getNamespace();
		String URL = request.getRequestURI();//invocation.getProxy().getActionName();
		String actionName = invocation.getProxy().getActionName();
		String className = invocation.getAction().getClass().toString();
		String address = request.getRemoteAddr();
		String result = invocation.invoke();
		long end = System.currentTimeMillis();
		long timed = end - start;
		Object[] objects = {className, URL, actionName, method, namespace, address, timed};
		logger.info("[ {},  访问Url: {},  action名: {},  方法: {},  包: {},  ip: {}, 运行时间:{}毫秒 ]", objects);
		return result;
	}

}
