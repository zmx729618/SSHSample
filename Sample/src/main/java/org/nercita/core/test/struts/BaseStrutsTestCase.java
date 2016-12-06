package org.nercita.core.test.struts;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.views.JspSupportServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;

import junit.framework.TestCase;

/**
 * struts2的测试基类
 * 可根据项目自设置config_Locations, actionPackages
 * 
 * @author wangzz
 * @since 2009-03-24
 */
@SuppressWarnings({"unchecked"})
public class BaseStrutsTestCase extends TestCase {
	
	private String config_Locations = "applicationContext.xml";
	private String actionPackages = "org.nercita.haccp.web.action";
	
	private static ApplicationContext applicationContext;
	private Dispatcher dispatcher;
	protected ActionProxy proxy;
	protected static MockServletContext servletContext;
	protected static MockServletConfig servletConfig;
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	
	public String getConfig_Locations() {
		return config_Locations;
	}
	
	/**
	 * 设置spring配置文件
	 * @param config_Locations
	 */
	public void setConfig_Locations(String config_Locations) {
		this.config_Locations = config_Locations;
	}

	public String getActionPackages() {
		return actionPackages;
	}
	
	/**
	 * 设置struts2的Action包路径
	 * @param actionPackages
	 */
	public void setActionPackages(String actionPackages) {
		this.actionPackages = actionPackages;
	}

//	public BaseStrutsTestCase(String name) {
//		super(name);
//	}

	/**
	 * 通过命名空间和名称创建Action
	 * 
	 * @param clazz 要创建的Action Class
	 * @param namespace Action的命名空间
	 * @param name Action的名称
	 * @return Action class
	 * @throws Exception Catch-all exception
	 */
	@SuppressWarnings("rawtypes")
	protected <T> T createAction(Class<T> clazz, String namespace, String name)
			throws Exception {

		// create a proxy class which is just a wrapper around the action call.
		// The proxy is created by checking the namespace and name against the
		// struts.xml configuration
		proxy = dispatcher.getContainer().getInstance(ActionProxyFactory.class)
				.createActionProxy(namespace, name, null, null, true, false);

		// by default, don't pass in any request parameters
		proxy.getInvocation().getInvocationContext().setParameters(
				new HashMap());

		// do not execute the result after executing the action
		proxy.setExecuteResult(true);

		// set the actions context to the one which the proxy is using
		ServletActionContext.setContext(proxy.getInvocation()
				.getInvocationContext());
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);
		ServletActionContext.setServletContext(servletContext);
		return (T) proxy.getAction();
	}
	
	/**
	 * 通过Action名称创建Action(命名空间默认为"/default")
	 * @param <T>
	 * @param clazz 要创建的Action类
	 * @param name struts.xml里定义的action名称(name)
	 * @return Action class
	 * @throws Exception Catch-all exception
	 */
	protected <T> T createAction(Class<T> clazz, String name)
			throws Exception {
		return createAction(clazz, "/default", name);	
	}
	

	@SuppressWarnings("rawtypes")
	protected void setUp() throws Exception {
		if (applicationContext == null) {
			// this is the first time so initialize Spring context
			servletContext = new MockServletContext();
			servletContext.addInitParameter(
					ContextLoader.CONFIG_LOCATION_PARAM, config_Locations);
			applicationContext = (new ContextLoader())
					.initWebApplicationContext(servletContext);

			// Struts JSP support servlet (for Freemarker)
			new JspSupportServlet().init(new MockServletConfig(servletContext));
		}
		// Dispatcher is the guy that actually handles all requests. Pass in
		// an empty. Map as the parameters but if you want to change stuff like
		// what config files to read, you need to specify them here. Here's how
		// to
		// scan packages for actions (thanks to Hardy Ferentschik - Comment 66)
		// (see Dispatcher's source code)
		HashMap params = new HashMap();
		params.put("actionPackages", actionPackages);
		dispatcher = new Dispatcher(servletContext, params);
		dispatcher.init();
		Dispatcher.setInstance(dispatcher);
	}
	
	/**
	 * 执行Action流程
	 * 
	 * @return 返回Action执行结果Result
	 * @throws Exception 
	 */
	public String execute() throws Exception{
		return this.proxy.execute();
	}
	
	
	/**
	 * 向Action里传递单个参数
	 * @param name 参数名称
	 * @param type 参数值
	 */
	@SuppressWarnings("rawtypes")
	public void setParameter(String name, Object type){
		Map map = new HashMap();
		map.put(name, type);
		proxy.getInvocation().getInvocationContext().setParameters(map);
	}
	
	
	/**
	 * 向Action里传递多个参数
	 * @param map 封装的参数map
	 */
	@SuppressWarnings("rawtypes")
	public void setParameters(Map map){
		proxy.getInvocation().getInvocationContext().setParameters(map);
	}


	
	

}
