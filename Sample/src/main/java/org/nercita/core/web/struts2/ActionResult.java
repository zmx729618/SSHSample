package org.nercita.core.web.struts2;

/**
 * struts2跳转参数
 * @author wangzz
 *
 */
public interface ActionResult {
	
	/**
	 * 跳转成功
	 */
	public static final String SUCCESS = "success";
	
	
	
	/**
	 * 跳转到添加页
	 */
	public static final String ADD = "add";		
	
	
	
	/**
	 * 跳转到编辑页
	 */
	public static final String EDIT = "edit";
	
	
	
	/**
	 * 跳转到列表页
	 */
	public static final String LIST = "list";
	
	
	
	/**
	 * 成功后跳转到list,可定义参数
	 * <pre>
	 *      page.pageNo 分页参数
	 *      queryName 查询条件
	 *      queryValue 查询值
	 *      notice 提示信息
	 * </pre>
	 */
	public static final String REDIRECT = "redirect";
	
	
	
	/**
	 * 成功后跳转到自定义页面<br>
	 * 必须设置setResultUrl()
	 * <pre>
	 * resultUrl 要跳转的页面,需自定义
	 * </pre>
	 */
	public static final String FOWARD = "redirect";
	
	
	
	/**
	 * 成功后跳转到自定义action<br>
	 * 必须设置setResultUrl()
	 * <pre>
	 * resultUrl 要跳转的action,需自定义
	 * messageError 提示错误信息,需自定义
	 * </pre>
	 */
	public static final String JUMPURL = "jumpUrl";
	
	
	
	
}
