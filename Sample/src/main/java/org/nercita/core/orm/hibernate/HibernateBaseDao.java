package org.nercita.core.orm.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.nercita.core.orm.IBaseDao;
import org.nercita.core.orm.Page;
import org.nercita.core.search.Filter;
import org.nercita.core.search.Search;
import org.nercita.core.search.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.<p/>
 * 
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * @author wangzz
 * 
 * @param <T>  DAO操作的对象类型
 * @param <PK>  主键类型
 * 
 * @since 2008-12-23
 * @see org.nercita.core.orm.hibernate.HibernateGenericDao
 */
@SuppressWarnings("unchecked")
public class HibernateBaseDao<T, PK extends Serializable> extends
		HibernateGenericDao implements IBaseDao<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public HibernateBaseDao() {
		if(this.entityClass == null){
			this.entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}
	
	
	public T findById(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(getEntityClass(), id);
	}
	

	public T findByIdForLock(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(getEntityClass(), id, LockOptions.UPGRADE);
	}


	public void save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().save(entity);
		logger.info("save entity: {}", entity);
	}
	
	/**
	 * 保存实体列表
	 * @param entity 实体列表对象
	 */
	public void save(final List<T> entities){
		Assert.notNull(entities, "entities不能为空");
		for(int i=0;i<entities.size();i++)
	     getSession().save(entities.get(i));
		logger.info("save entities: {}", entities);
	}
	

	public void update(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
		logger.info("update entity: {}", entity);
	}
	
	public void saveOrUpdate(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}

	
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.info("delete entity: {}", entity);
	}
	
	/**
	 * 批量删除实体列表
	 * @param entity 实体对象列表
	 */
	public void delete(List<T> entities){
		Assert.notNull(entities, "entity不能为空");
		for(int i=0;i<entities.size();i++)
		getSession().delete(entities.get(i));
		logger.info("delete entities: {}", entities);
	}
	

	public void deleteById(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(findById(id));
		logger.info("delete entity {}, id is {}", entityClass.getSimpleName() , id);
	}
	

	public int execute(final String queryString, final Object... values){
		Assert.hasText(queryString, "queryString不能为空");
		return createQuery(queryString, values).executeUpdate();	
	}
		
	
	public List<T> findAll() {
		return findByCriteria();
	}
	
	public List<T> findAllLike(String queryName, Object queryValue) {
		Criterion criterion = Restrictions.like(queryName, queryValue);
		return findAll(criterion);
	}
	
	public List<T> findAllEq(String queryName, Object queryValue) {
		Criterion criterion = Restrictions.eq(queryName, queryValue);
		return findAll(criterion);
	}
	
	
	public List<T> findAll(Criterion... criterion) {
		return findByCriteria(criterion);
	}
	
	
	public Page<T> findPage(final Page<T> page) {
		Assert.notNull(page, "page不能为空");
		return findPageByCriteria(page);
	}
	
	
	public T findUniqueByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}
	
	
	/**
	 * 按HQL分页查询.
	 * 暂不支持自动获取总结果数,需用户另行执行查询.
	 * @param page 分页参数.包括pageSize 和firstResult.
	 * @param hql HQL查询语句, 参数必须用“?”设置.
	 * @param values 数量可变的参数, 对应查询语句里的“?”.
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPageByHql(Page<T> page, String hql, Object... values) {
		Assert.notNull(page, "page不能为空");
		Assert.hasText(hql, "hql不能为空");
		long count = findCountByHql(hql, values);
		if(count>-1)
			page.setTotalCount((int)count);
		Query q = createQuery(hql, values);
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getPageSize());
	
		page.setResult(q.list());
		return page;
	}
	
	
	
	public long findCountByHql(String hql, Object... values) {
		if(hql.toLowerCase().indexOf("group by")>0 || hql.toLowerCase().indexOf(" distinct ")>0){
			String sql = getCountSql(removeOrders(hql));
			Query query = createSqlQuery(sql, values);
			return Long.parseLong(query.uniqueResult().toString());
		}else {
			return findUniqueLong(replaceCountHql(hql), values);
		}
	}
	
	
	/**
	 * 根据查询条件分页查询
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @param matchMode 查询的精确类型<br>
	 * MatchMode: EXACT-精确查询,ANYWHERE-模糊查询,START-开头匹配,END-结尾匹配
	 * @param criterion 数量可变的Criterion.
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByQuerys(Page<T> page, T entity, MatchMode matchMode, Criterion... criterion) {
		Assert.notNull(page, "page不能为空");
		Criteria c = createCriteria(criterion);
		if(entity!=null){
			Example example = Example.create(entity);
			example.enableLike(matchMode);		//设置查询类型	
			c.add(example);
		}
		// 获取根据条件分页查询的总行数 
		page.setTotalCount(getCountByCriteria(c));
		c.setFirstResult(page.getFirst());
		c.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');
			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		page.setResult(c.list());
		return page;
	}

	/**
	 * 根据查询条件分页查询
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @param matchMode 查询的精确类型<br>
	 * MatchMode: EXACT-精确查询,ANYWHERE-模糊查询,START-开头匹配,END-结尾匹配
	 * @param criterion  Criterion List.
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByQuerys(Page<T> page, T entity, MatchMode matchMode, List<Criterion> criterion) {
		Assert.notNull(page, "page不能为空");
		Criteria c = createCriteria(criterion);
		if(entity!=null){
			Example example = Example.create(entity);
			example.enableLike(matchMode);		//设置查询类型	
			c.add(example);
		}
		page.setTotalCount(getCountByCriteria(c));
		c.setFirstResult(page.getFirst());
		c.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		page.setResult(c.list());
		return page;
	}
	
	
	
	/**
	 * 按Criterion分页查询.
	 * @param page 分页参数
	 * @param criterion 数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPageByCriteria(Page<T> page, Criterion... criterion) {
		return findPageByQuerys(page, null, null, criterion);
	}
	
	
	/**
	 * 按Criterion分页查询.
	 * @param page 分页参数
	 * @param criterion Criterion List.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPageByCriteria(Page<T> page, List<Criterion> criterion) {
		return findPageByQuerys(page, null, null, criterion);
	}
	
	
	/**
	 * 按Criterion查询结果数.
	 * @param criterion 数量可变的Criterion.
	 * @return 查询结果
	 */
	public long findCountByCriteria(Criterion... criterion) {
		Criteria c = createCriteria(criterion);
		return getCountByCriteria(c);
	}
	
	
	/**
	 * 根据查询条件分页查询
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @param matchMode 查询的精确类型<br>
	 * MatchMode: EXACT-精确查询,ANYWHERE-模糊查询,START-开头匹配,END-结尾匹配
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByQueryMatch(Page<T> page, T entity, MatchMode matchMode) {
		return findPageByQuerys(page, entity, matchMode);
	}
	

	public Page<T> findPageByQuerysBlur(Page<T> page, T entity) {
		return findPageByQuerys(page, entity, MatchMode.ANYWHERE);
	}
	
	
	public Page<T> findPageByQuerysExact(Page<T> page, T entity) {
		return findPageByQuerys(page, entity, MatchMode.EXACT);
	}
	
	
	
	public Page<T> findPageByPropertyExact(Page<T> page, String queryName, Object queryValue) {
		Criterion criterion = Restrictions.eq(queryName, queryValue);
		return findPageByCriteria(page, criterion);
	}
	
	
	public Page<T> findPageByPropertyLike(Page<T> page, String queryName, Object queryValue) {
		Criterion criterion = Restrictions.like(queryName, "%"+queryValue+"%");
		return findPageByCriteria(page, criterion);
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public Page<T> findPageByMap(Page<T> page, Map queryMap) {
		Assert.notNull(page, "page不能为空");
		Assert.notNull(queryMap, "queryMap不能为空");
		StringBuffer hql = new StringBuffer("from ");
		hql.append(getEntityClass().getName()).append(" where 1=1 ");
		Iterator it = queryMap.keySet().iterator();   
        while (it.hasNext()) { 
            Object key = it.next();
            hql.append(" and ").append(key.toString()).append(" like '%").append(replaceInject(String.valueOf(queryMap.get(key)))).append("%'");
        }
		return findPageByHql(page, hql.toString());
	}
	
	
	/**
	 * 根据查询构建器查询所有记录
	 * @param search 查询构建器
	 * @return List
	 */
	public List<T> search(Search search) {
		Assert.notNull(search, "search不能为空");
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Filter> filters = search.getFilters();
		for(Filter filter : filters){
			if(!filter.getValue().equals("") && filter.getValue()!=null)
			criterionList.add(filter.isSimpleFilter() ? 
					buildCriterionBySimFilter(filter) : buildCriterionByConnFilter(filter));
		}
		Criteria c = createCriteria(criterionList);
		List<Sort> sorts = search.getSorts();
		for(Sort sort : sorts){
			if(sort.getOrder().equals(Sort.ASC)){
				c.addOrder(Order.asc(sort.getProperty()));
			}
			if(sort.getOrder().equals(Sort.DESC)){
				c.addOrder(Order.desc(sort.getProperty()));
			}
		}
		return c.list();
	}
	
	
	/**
	 * 根据查询构建器查询Page
	 * @param page 分页参数
	 * @param search 查询构建器
	 * @return Page
	 */
	public Page<T> search(Page<T> page, Search search) {
		Assert.notNull(search, "search不能为空");
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Filter> filters = search.getFilters();
		for(Filter filter : filters){
			if(!"".equals(filter.getValue()) && null != filter.getValue())
			criterionList.add(filter.isSimpleFilter() ? 
					buildCriterionBySimFilter(filter) : buildCriterionByConnFilter(filter));
		}
		Criteria c = createCriteria(criterionList);
		page.setTotalCount(getCountByCriteria(c));
		c.setFirstResult(page.getFirst());
		c.setMaxResults(page.getPageSize());
		List<Sort> sorts = search.getSorts();
		for(Sort sort : sorts){
			if(sort.getOrder().equals(Sort.ASC)){
				c.addOrder(Order.asc(sort.getProperty()));
			}
			if(sort.getOrder().equals(Sort.DESC)){
				c.addOrder(Order.desc(sort.getProperty()));
			}
		}
		page.setResult(c.list());
		return page;
	}
	
	
	
	/**
	 * 根据Filter构造过滤条件
	 */
	public Criterion buildCriterionBySimFilter(Filter filter){
		String propertyName = filter.getProperty();
		Object value = filter.getValue();
		int operator = filter.getOperator();
		Criterion criterion = null;
		switch (operator) {
		case Filter.OP_EMPTY:
			criterion = Restrictions.isEmpty(propertyName);
			break;
		case Filter.OP_EQUAL:
			criterion = Restrictions.eq(propertyName, value);
			break;
		case Filter.OP_GREATER_OR_EQUAL:
			criterion = Restrictions.ge(propertyName, value);
			break;
		case Filter.OP_GREATER_THAN:
			criterion = Restrictions.gt(propertyName, value);
			break;
		case Filter.OP_ILIKE:
			criterion = Restrictions.ilike(propertyName, "%"+value+"%");
			break;
		case Filter.OP_IN:
			if(value instanceof Object[])
				criterion = Restrictions.in(propertyName, (Object[])value);
			if(value instanceof Collection<?>)
				criterion = Restrictions.in(propertyName, (Collection<?>)value);
			break;
		case Filter.OP_LESS_OR_EQUAL:
			criterion = Restrictions.le(propertyName, value);
			break;
		case Filter.OP_LESS_THAN:
			criterion = Restrictions.lt(propertyName, value);
			break;
		case Filter.OP_LIKE:
			criterion = Restrictions.like(propertyName, "%"+value+"%");
			break;
		case Filter.OP_NOT_EMPTY:
			criterion = Restrictions.isNotEmpty(propertyName);
			break;
		case Filter.OP_NOT_EQUAL:
			criterion = Restrictions.ne(propertyName, value);
			break;
		case Filter.OP_NOT_IN:
			if(value instanceof Object[])
				criterion = Restrictions.in(propertyName, (Object[])value);
			if(value instanceof Collection<?>)
				criterion = Restrictions.in(propertyName, (Collection<?>)value);
			criterion = Restrictions.not(criterion);
			break;
		case Filter.OP_NOT_NULL:
			criterion = Restrictions.isNotNull(propertyName);
			break;
		case Filter.OP_NULL:
			criterion = Restrictions.isNull(propertyName);
			break;
		case Filter.OP_NOT:
			Filter filterNot = (Filter) filter.getValue();
			criterion = Restrictions.not(filterNot.isSimpleFilter() ?
					buildCriterionBySimFilter(filterNot) : buildCriterionByConnFilter(filterNot));
			break;
		}
		return criterion;
	}
	
	/**
	 * 构造连接过滤条件
	 */
	public Criterion buildCriterionByConnFilter(Filter filter){
		Criterion criterion = null;
		switch (filter.getOperator()) {
		case Filter.OP_AND:
			Junction andCri = Restrictions.conjunction();
			List<Filter> andList = (List<Filter>) filter.getValue();
			for(Filter f : andList){
				andCri.add(f.isSimpleFilter() ?
						buildCriterionBySimFilter(f) : buildCriterionByConnFilter(f));
			}
			criterion = andCri;
			break;
		case Filter.OP_OR:
			Junction orCri = Restrictions.disjunction();
			List<Filter> orList = (List<Filter>) filter.getValue();
			for(Filter f : orList){
				orCri.add(f.isSimpleFilter() ?
						buildCriterionBySimFilter(f) : buildCriterionByConnFilter(f));
			}
			criterion = orCri;
			break;
		}
		return criterion;
	}

	

	
	


}
