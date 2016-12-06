package org.nercita.core.orm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nercita.core.search.Filter;
import org.nercita.core.search.Search;
import org.springframework.web.util.WebUtils;

/**
 * web层组装查询参数
 * 
 * @author wangzz
 * @since 2009-09-04
 */
public class WebSearchBuild {
	
	/**
	 * 属性比较类型.
	 */
	public enum MatchType {
		EQ, LIKE, ILIKE, LT, GT, LE, GE;
	}
	
	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建Filter.
	 * 默认Filter属性名前缀为f_.
	 */
	public static Search buildPropertyFilters(final HttpServletRequest request) {
		return buildPropertyFilters(request, "f_");
	}
	
	
	/**
	 * 根据按Filter命名规则的Request参数, 创建Filter.
	 * Filter命名规则为Filter属性前缀_比较类型_属性名.
	 * <br>
	 * eg.
	 * f_EQ_name, f_LIKE_name, f_OR_email
	 * <br>
	 * 比较类型: eq(==),ge(>=),gt(>),le(<=),lt(>=)
	 * 
	 */
	public static Search buildPropertyFilters(final HttpServletRequest request, final String filterPrefix) {
		Search search = new Search();
		//从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, Object> filterParamMap = WebUtils.getParametersStartingWith(request, filterPrefix);
		if(filterParamMap.size()>0){
			//分析参数Map,构造Filter,默认or连接
			for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
				String filterName = entry.getKey();
				String value = entry.getValue().toString();
				//如果value值为空,则忽略此filter.
				boolean omit = StringUtils.isBlank(value);
				if (!omit) {
					search.addFilter(getFilterByParam(filterName, value));
					request.setAttribute(filterPrefix + filterName, value);
				}
			}
		}
		return search;
	}
	
	
	private static Filter getFilterByParam(String filterName, String value){
		String matchTypeStr = StringUtils.substringBefore(filterName, "_").toUpperCase();
		Filter filter = null;
		MatchType matchType;
		try {
			matchType = MatchType.valueOf(matchTypeStr.toUpperCase());
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}
		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		
		//根据MatchType构造Filter
		switch (matchType) {
		case EQ:
			filter = Filter.equal(propertyNameStr, value);
			break;
		case LIKE:
			filter = Filter.like(propertyNameStr, value);
			break;
		case ILIKE:
			filter = Filter.ilike(propertyNameStr, value);
			break;
		case LT:
			filter = Filter.lessThan(propertyNameStr, value);
			break;
		case GT:
			filter = Filter.greaterThan(propertyNameStr, value);
			break;
		case LE:
			filter = Filter.lessOrEqual(propertyNameStr, value);
			break;
		case GE:
			filter = Filter.greaterOrEqual(propertyNameStr, value);
			break;
		}		
		return filter;
	}
	
	
}
