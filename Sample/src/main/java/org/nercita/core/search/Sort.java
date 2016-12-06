package org.nercita.core.search;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 排序类,可设置要排序的字段和方向
 * 
 * @author wangzz
 * @since 2009-07-23
 */
public class Sort implements Serializable {
	
	private static final long serialVersionUID = -694409065068247317L;

	/**
	 * 排序属性
	 */
	protected String property;
	
	/**
	 * 排序方式, 默认为: 顺序-ASC
	 */
	protected String order = ASC;	//默认为顺序
	
	/**
	 * 顺序
	 */
	public static final String ASC = "asc";
	
	/**
	 * 倒序
	 */
	public static final String DESC = "desc";

	
	/**
	 * 初始化排序
	 * @param property 排序属性
	 * @param desc 排序方式: 顺序-ASC, 倒序-DESC
	 */
	public Sort(String property, String order) {
		Assert.hasText(property, "property不能为空");
		Assert.hasText(order, "order不能为空");
		this.property = property;
		if (!StringUtils.equals(DESC, order) && !StringUtils.equals(ASC, order))
			throw new IllegalArgumentException("排序方向" + order + "不是合法值");
		this.order = StringUtils.lowerCase(order);
	}
	
	/**
	 * 初始化Sort, 默认为顺序排序
	 * @param property 排序属性
	 */
	public Sort(String property) {
		this.property = property;
	}

	/**
	 * 顺序排序
	 * @param property 排序属性
	 * @return Sort对象
	 */
	public static Sort asc(String property) {
		return new Sort(property);
	}

	/**
	 * 倒序排序
	 * @param property 排序属性
	 * @return Sort对象
	 */
	public static Sort desc(String property) {
		return new Sort(property, Sort.DESC);
	}
	
	/**
	 * 获取排序属性
	 * @return property
	 */
	public String getProperty() {
		return property;
	}
	
	/**
	 * 设置排序属性
	 * @param property 排序属性
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	
	/**
	 * 获取排序方式
	 * @return order
	 */
	public String getOrder() {
		return order;
	}
	
	/**
	 * 设置排序方式, 顺序-ASC, 倒序-DESC
	 * @param order 排序方向，可为：<code>Order.ASC<code>, <code>"desc"<code>
	 */
	public void setOrder(String order) {
		if (!StringUtils.equals(DESC, order) && !StringUtils.equals(ASC, order))
			throw new IllegalArgumentException("排序方向" + order + "不是合法值");
		this.order = StringUtils.lowerCase(order);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sort other = (Sort) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (property == null) {
			sb.append("null");
		} else {
			sb.append("`");
			sb.append(property);
			sb.append("`");
		}
		if (property == null) {
			sb.append("null");
		} else {
			sb.append("`");
			sb.append(order);
			sb.append("`");
		}
		return sb.toString();
	}


	

}
