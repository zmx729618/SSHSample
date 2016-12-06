package org.nercita.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.Assert;

/**
 * 封装查询条件<br>
 * 不依赖于任何ORM
 * 
 * @author wangzz
 * @since 2009-07-23
 */
public class Search implements Serializable {

	private static final long serialVersionUID = 4893087551093825787L;

	/**
	 * 查询条件
	 */
	protected List<Filter> filters = new ArrayList<Filter>();	
	
	/**
	 * 排序条件
	 */
	protected List<Sort> sorts = new ArrayList<Sort>();			

	
	//filter
	/**
	 * 添加一个filter
	 */
	public Search addFilter(Filter filter) {
		if(filters!=null)
			filters.add(filter);
		return this;
	}
	
	/**
	 * 添加一个filter,匹配类型为 == .
	 */
	public Search addFilterEqual(String property, Object value) {
		return addFilter(Filter.equal(property, value));
	}
	
	/**
	 * 添加一个filter,匹配类型为 >= .
	 */
	public Search addFilterGreaterOrEqual(String property, Object value) {
		return addFilter(Filter.greaterOrEqual(property, value));
	}
	
	/**
	 * 添加一个filter,匹配类型为 > .
	 */
	public Search addFilterGreaterThan(String property, Object value) {
		return addFilter(Filter.greaterThan(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 IN .
	 */
	public Search addFilterIn(String property, Collection<?> value) {
		return addFilter(Filter.in(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 IN .
	 */
	public Search addFilterIn(String property, Object... value) {
		return addFilter(Filter.in(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 NOT IN .
	 */
	public Search addFilterNotIn(String property, Collection<?> value) {
		return addFilter(Filter.notIn(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 NOT IN .
	 */
	public Search addFilterNotIn(String property, Object... value) {
		return addFilter(Filter.notIn(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 <= .
	 */
	public Search addFilterLessOrEqual(String property, Object value) {
		return addFilter(Filter.lessOrEqual(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 < .
	 */
	public Search addFilterLessThan(String property, Object value) {
		return addFilter(Filter.lessThan(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 LIKE .
	 */
	public Search addFilterLike(String property, String value) {
		return addFilter(Filter.like(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 ILIKE .
	 */
	public Search addFilterILike(String property, String value) {
		return addFilter(Filter.ilike(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 != .
	 */
	public Search addFilterNotEqual(String property, Object value) {
		return addFilter(Filter.notEqual(property, value));
	}

	/**
	 * 添加一个filter,匹配类型为 IS NULL .
	 */
	public Search addFilterNull(String property) {
		return addFilter(Filter.isNull(property));
	}

	/**
	 * 添加一个filter,匹配类型为 IS NOT NULL .
	 */
	public Search addFilterNotNull(String property) {
		return addFilter(Filter.isNotNull(property));
	}

	/**
	 * 添加一个filter,匹配类型为 IS EMPTY .
	 * @param property 必须为collection类型, 常用与一对多
	 */
	public Search addFilterEmpty(String property) {
		return addFilter(Filter.isEmpty(property));
	}
	
	/**
	 * 添加一个filter,匹配类型为 IS NOT EMPTY .
	 * @param property 必须为collection类型, 常用与一对多
	 */
	public Search addFilterNotEmpty(String property) {
		return addFilter(Filter.isNotEmpty(property));
	}
	
	/**
	 * 添加filter,匹配类型为 AND .
	 * @param filters 多个filter
	 */
	public Search addFilterAnd(Filter... filters) {
		return addFilter(Filter.and(filters));
	}

	/**
	 * 添加filter,匹配类型为 OR .
	 * @param filters 多个filter
	 */
	public Search addFilterOr(Filter... filters) {
		return addFilter(Filter.or(filters));
	}

	/**
	 * 添加一个filter,匹配类型为 NOT .
	 */
	public Search addFilterNot(Filter filter) {
		return addFilter(Filter.not(filter));
	}
	
	
	/**
	 * 删除filter
	 * @param filter 要删除的对象
	 */
	public void removeFilter(Filter filter) {
		Assert.notNull(filter, "filter不能为空");
		if(filters!=null)
			filters.remove(filter);
	}

	/**
	 * 根据参数删除filter
	 * @param property filter的查询条件
	 */
	public void removeFiltersOnProperty(String property) {
		Assert.hasText(property, "property不能为空");
		Iterator<Filter> itr = getFilters().iterator();
		while (itr.hasNext()) {
			if (property.equals(itr.next().getProperty()))
				itr.remove();
		}
	}
	
	/**
	 * 清除所有filter
	 */
	public void clearFilters() {
		if(filters!=null)
			filters.clear();
	}


	// Sorts
	/**
	 * 添加sort
	 */
	public Search addSort(Sort sort) {
		Assert.notNull(sort, "sort不能为空");
		if(sorts==null)
			sorts = new ArrayList<Sort>();
		sorts.add(sort);
		return this;
	}
	
	/**
	 * 添加sort
	 * @param property 要排序的属性
	 * @param order 排序方式: 顺序-ASC, 倒序-DESC
	 */
	public Search addSort(String property, String order) {
		Assert.hasText(property, "property不能为空");
		Assert.hasText(order, "order不能为空");
		return addSort(new Sort(property, order));
	}
	
	/**
	 * 添加sort按照顺序排列
	 * @param property 要排序的属性
	 */
	public Search addSortAsc(String property) {
		Assert.hasText(property, "property不能为空");
		return addSort(Sort.asc(property));
	}

	/**
	 * 添加sort按照倒序排列
	 * @param property 要排序的属性
	 */
	public Search addSortDesc(String property) {
		Assert.hasText(property, "property不能为空");
		return addSort(Sort.desc(property));
	}

	/**
	 * 删除sort
	 * @param sort 要删除的对象
	 */
	public void removeSort(Sort sort) {
		if (getSorts() != null)
			getSorts().remove(sort);
	}
	
	/**
	 * 根据属性删除sort
	 * @param property 要删除的sort属性
	 */
	public void removeSort(String property) {
		Assert.hasText(property, "property不能为空");
		if(sorts==null)
			sorts = new ArrayList<Sort>();
		Iterator<Sort> itr = sorts.iterator();
		while (itr.hasNext()) {
			if (property.equals(itr.next().getProperty()))
				itr.remove();
		}
	}
	
	/**
	 * 清除所有sort
	 */
	public void clearSorts() {
		if(sorts!=null)
			sorts.clear();
	}
	
	/**
	 * 清除Search内所有属性
	 */
	public void clear() {
		clearFilters();
		clearSorts();
	}
	
	public List<Filter> getFilters() {
		return filters;
	}

	public Search setFilters(List<Filter> filters) {
		this.filters = filters;
		return this;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public Search setSorts(List<Sort> sorts) {
		this.sorts = sorts;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Search s = (Search) obj;
		if (getFilters() == null ? s.getFilters() != null : !getFilters().equals(s.getFilters()))
			return false;
		if (getSorts() == null ? s.getSorts() != null : !getSorts().equals(s.getSorts()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + (getFilters() == null ? 0 : getFilters().hashCode());
		hash = hash * 31 + (getSorts() == null ? 0 : getSorts().hashCode());
		return hash;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Search");
		sb.append("[filters: {\n  ");
		appendList(sb, getFilters(), ",\n  ");
		sb.append("\n },\n sorts: { ");
		appendList(sb, getSorts(), ", ");
		sb.append(" }\n]");
		return sb.toString();
	}
	
	private void appendList(StringBuilder sb, List<?> list, String separator) {
		if (list == null) {
			sb.append("null");
			return;
		}
		boolean first = true;
		for (Object o : list) {
			if (first) {
				first = false;
			} else {
				sb.append(separator);
			}
			sb.append(o);
		}
	}


}
