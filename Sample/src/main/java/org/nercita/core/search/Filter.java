package org.nercita.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.util.Assert;

/**
 * 查询条件<br>
 * 在<code>Search<code>中使用
 * 
 * @author wangzz
 * @since 2009-07-23
 */
@SuppressWarnings("unchecked")
public class Filter implements Serializable {

	private static final long serialVersionUID = -2322605186634414935L;


	/**
	 * 查询条件.<br>
	 * 例如:<code>"name", "dateOfBirth", "employee.age", "employee.spouse.job.title"</code>
	 */
	protected String property;

	
	/**
	 * 与property匹配的查询值.<br>
	 * 例如: <code>"Fred", new Date(), 45</code>
	 */
	protected Object value;

	
	/**
	 * 查询方式
	 * <code>OP_EQAUL, OP_NOT_EQUAL, OP_LESS_THAN, OP_GREATER_THAN, LESS_OR_EQUAL, OP_GREATER_OR_EQUAL, OP_IN, OP_NOT_IN, OP_LIKE, OP_ILIKE, OP_NULL, OP_NOT_NULL, OP_EMPTY, OP_NOT_EMPTY, OP_SOME, OP_ALL, OP_NONE, OP_AND, OP_OR, OP_NOT</code> .
	 */
	protected int operator;

	public Filter() {

	}
	
	public Filter(String property, Object value, int operator) {
		Assert.hasText(property, "property不能为空");
		Assert.notNull(operator, "operator不能为空");
		this.property = property;
		this.value = value;
		this.operator = operator;
	}
	
	/**
	 * 默认查询条件为EQ
	 * @param property 查询条件
	 * @param value 查询值
	 */
	public Filter(String property, Object value) {
		this.property = property;
		this.value = value;
		this.operator = OP_EQUAL;
	}

	public static final int OP_EQUAL = 0, OP_NOT_EQUAL = 1, OP_LESS_THAN = 2, OP_GREATER_THAN = 3, OP_LESS_OR_EQUAL = 4, OP_GREATER_OR_EQUAL = 5, OP_LIKE = 6, OP_ILIKE = 7,
			OP_IN = 8, OP_NOT_IN = 9, OP_NULL = 10, OP_NOT_NULL = 11, OP_EMPTY = 12, OP_NOT_EMPTY = 13, OP_NOT = 14;

	public static final int OP_AND = 100, OP_OR = 101;
	public static final int OP_SOME = 200, OP_ALL = 201, OP_NONE = 202;
	
	
	/**
	 * 建立一个Filter使用"等于"操作符. 
	 * <p>example: property == value <p>
	 */
	public static Filter equal(String property, Object value) {
		return new Filter(property, value, OP_EQUAL);
	}

	/**
	 * 建立一个Filter使用"小于"操作符. 
	 * <p>example: property < value <p>
	 */
	public static Filter lessThan(String property, Object value) {
		return new Filter(property, value, OP_LESS_THAN);
	}

	/**
	 * 建立一个Filter使用"大于"操作符. 
	 * <p>example: property > value <p>
	 */
	public static Filter greaterThan(String property, Object value) {
		return new Filter(property, value, OP_GREATER_THAN);
	}

	/**
	 * 建立一个Filter使用"小于等于"操作符. 
	 * <p>example: property <= value <p>
	 */
	public static Filter lessOrEqual(String property, Object value) {
		return new Filter(property, value, OP_LESS_OR_EQUAL);
	}

	/**
	 * 建立一个Filter使用"大于等于"操作符. 
	 * <p>example: property >= value <p>
	 */
	public static Filter greaterOrEqual(String property, Object value) {
		return new Filter(property, value, OP_GREATER_OR_EQUAL);
	}

	/**
	 * 建立一个Filter使用"in"操作符. 
	 * <p>example: property in Collection of value <p>
	 */
	public static Filter in(String property, Collection<?> value) {
		return new Filter(property, value, OP_IN);
	}

	/**
	 * 建立一个Filter使用"in"操作符. 
	 * <p>example: property in Object[].. of value <p>
	 */
	public static Filter in(String property, Object... value) {
		return new Filter(property, value, OP_IN);
	}

	/**
	 * 建立一个Filter使用"not in"操作符. 
	 * <p>example: property not in Collection of value <p>
	 */
	public static Filter notIn(String property, Collection<?> value) {
		return new Filter(property, value, OP_NOT_IN);
	}

	/**
	 * 建立一个Filter使用"not in"操作符. 
	 * <p>example: property not in Object[].. of value <p>
	 */
	public static Filter notIn(String property, Object... value) {
		return new Filter(property, value, OP_NOT_IN);
	}

	/**
	 * 建立一个Filter使用"like"操作符. 
	 * <p>example: property like value <p>
	 */
	public static Filter like(String property, String value) {
		return new Filter(property, value, OP_LIKE);
	}

	/**
	 * 建立一个Filter使用"ilike"操作符. 
	 * <p>example: property ilike value <p>
	 */
	public static Filter ilike(String property, String value) {
		return new Filter(property, value, OP_ILIKE);
	}

	/**
	 * 建立一个Filter使用"不等于"操作符. 
	 * <p>example: property != value <p>
	 */
	public static Filter notEqual(String property, Object value) {
		return new Filter(property, value, OP_NOT_EQUAL);
	}

	/**
	 * 建立一个Filter使用"为空"操作符. 
	 * <p>example: property is null <p>
	 */
	public static Filter isNull(String property) {
		return new Filter(property, true, OP_NULL);
	}

	/**
	 * 建立一个Filter使用"不为空"操作符. 
	 * <p>example: property is not null <p>
	 */
	public static Filter isNotNull(String property) {
		return new Filter(property, true, OP_NOT_NULL);
	}

	/**
	 * 建立一个Filter使用"集合为空"操作符. 
	 * <p>example: property is empty <p>
	 * 
	 * @param property 必须为collection类型
	 */
	public static Filter isEmpty(String property) {
		return new Filter(property, true, OP_EMPTY);
	}

	/**
	 * 建立一个Filter使用"集合不为空"操作符. 
	 * <p>example: property is not empty <p>
	 * 
	 * @param property 必须为collection类型
	 */
	public static Filter isNotEmpty(String property) {
		return new Filter(property, true, OP_NOT_EMPTY);
	}

	/**
	 * 建立一个Filter使用"and"操作符. 
	 * <p>example: filter1 and filter2 and ... <p>
	 * 
	 * @param filters 多个and连接的filter
	 */
	public static Filter and(Filter... filters) {
		Filter filter = new Filter("AND", null, OP_AND);
		for (Filter f : filters) {
			filter.add(f);
		}
		return filter;
	}

	/**
	 * 建立一个Filter使用"or"操作符. 
	 * <p>example: filter1 or filter2 or ... <p>
	 * 
	 * @param filters 多个or连接的filter
	 */
	public static Filter or(Filter... filters) {
		Filter filter = and(filters);
		filter.property = "OR";
		filter.operator = OP_OR;
		return filter;
	}
	
	
	/**
	 * 添加一个Filter使用and操作符
	 * @param filter
	 * @return Filter
	 */
	public Filter andFilter(Filter filter) {
		this.add(filter);
		this.property = "AND";
		this.operator = OP_AND;
		return this;
	}
	
	/**
	 * 添加一个Filter使用or操作符
	 * @param filter
	 * @return Filter
	 */
	public Filter orFilter(Filter filter) {
		this.add(filter);
		this.property = "OR";
		this.operator = OP_OR;
		return this;
	}
	

	/**
	 * 建立一个Filter使用"not"操作符. 
	 * <p>example: not filter <p>
	 * 
	 * @param filter 不包含的filter
	 */
	public static Filter not(Filter filter) {
		return new Filter("NOT", filter, OP_NOT);
	}


	/**
	 * 添加一个Filter
	 */
	@SuppressWarnings("rawtypes")
	public void add(Filter filter) {
		if (value == null || !(value instanceof List)) {
			value = new ArrayList();
		}
		((List) value).add(filter);
	}

	/**
	 * 移除一个Filter
	 */
	@SuppressWarnings("rawtypes")
	public void remove(Filter filter) {
		if (value == null || !(value instanceof List)) {
			return;
		}
		((List) value).remove(filter);
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	
	/**
	 * 如果不是连接filter返回true
	 *         <p>
	 *         <code>EEQUAL, EQUAL, LESS_THAN, GREATER_THAN, LESS_OR_EQUAL, GREATER_OR_EQUAL, LIKE, ILIKE,
	 *			IN, NOT_IN, NULL, NOT_NULL, EMPTY, NOT_EMPTY</code>
	 */
	public boolean isSimpleFilter() {
		return operator < 50;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operator;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Filter other = (Filter) obj;
		if (operator != other.operator)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String toString() {
		switch (operator) {
		case Filter.OP_IN:
			return "`" + property + "` in (" + InternalUtil.paramDisplayString(value) + ")";
		case Filter.OP_NOT_IN:
			return "`" + property + "` not in (" + InternalUtil.paramDisplayString(value) + ")";
		case Filter.OP_EQUAL:
			return "`" + property + "` = " + InternalUtil.paramDisplayString(value);
		case Filter.OP_NOT_EQUAL:
			return "`" + property + "` != " + InternalUtil.paramDisplayString(value);
		case Filter.OP_GREATER_THAN:
			return "`" + property + "` > " + InternalUtil.paramDisplayString(value);
		case Filter.OP_LESS_THAN:
			return "`" + property + "` < " + InternalUtil.paramDisplayString(value);
		case Filter.OP_GREATER_OR_EQUAL:
			return "`" + property + "` >= " + InternalUtil.paramDisplayString(value);
		case Filter.OP_LESS_OR_EQUAL:
			return "`" + property + "` <= " + InternalUtil.paramDisplayString(value);
		case Filter.OP_LIKE:
			return "`" + property + "` LIKE " + InternalUtil.paramDisplayString(value);
		case Filter.OP_ILIKE:
			return "`" + property + "` ILIKE " + InternalUtil.paramDisplayString(value);
		case Filter.OP_NULL:
			return "`" + property + "` IS NULL";
		case Filter.OP_NOT_NULL:
			return "`" + property + "` IS NOT NULL";
		case Filter.OP_EMPTY:
			return "`" + property + "` IS EMPTY";
		case Filter.OP_NOT_EMPTY:
			return "`" + property + "` IS NOT EMPTY";
		case Filter.OP_AND:
		case Filter.OP_OR:
			if (!(value instanceof List)) {
				return (operator == Filter.OP_AND ? "AND: " : "OR: ") + "**INVALID VALUE - NOT A LIST: (" + value
						+ ") **";
			}

			String op = operator == Filter.OP_AND ? " and " : " or ";

			StringBuilder sb = new StringBuilder("(");
			boolean first = true;
			for (Object o : ((List) value)) {
				if (first) {
					first = false;
				} else {
					sb.append(op);
				}
				if (o instanceof Filter) {
					sb.append(o.toString());
				} else {
					sb.append("**INVALID VALUE - NOT A FILTER: (" + o + ") **");
				}
			}
			if (first)
				return (operator == Filter.OP_AND ? "AND: " : "OR: ") + "**EMPTY LIST**";

			sb.append(")");
			return sb.toString();
		case Filter.OP_NOT:
			if (!(value instanceof Filter)) {
				return "NOT: **INVALID VALUE - NOT A FILTER: (" + value + ") **";
			}
			return "not " + value.toString();
		case Filter.OP_SOME:
			if (!(value instanceof Filter)) {
				return "SOME: **INVALID VALUE - NOT A FILTER: (" + value + ") **";
			}
			return "some `" + property + "` {" + value.toString() + "}";
		case Filter.OP_ALL:
			if (!(value instanceof Filter)) {
				return "ALL: **INVALID VALUE - NOT A FILTER: (" + value + ") **";
			}
			return "all `" + property + "` {" + value.toString() + "}";
		case Filter.OP_NONE:
			if (!(value instanceof Filter)) {
				return "NONE: **INVALID VALUE - NOT A FILTER: (" + value + ") **";
			}
			return "none `" + property + "` {" + value.toString() + "}";
		default:
			return "**INVALID OPERATOR: (" + operator + ") - VALUE: " + InternalUtil.paramDisplayString(value) + " **";
		}
	}



}
