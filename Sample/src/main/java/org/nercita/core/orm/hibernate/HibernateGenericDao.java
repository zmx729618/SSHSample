package org.nercita.core.orm.hibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;

/**
 * Hibernate DAO 操作工具类, 封装了基本操作.
 * 
 * @author wangzz
 * @since 2008-12-23
 */
public class HibernateGenericDao {
	
	/**
	 * DAO所管理的Entity类型.
	 */
	@SuppressWarnings("rawtypes")
	protected Class entityClass;

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	/**
	 * 获取当前session
	 * @return Session
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 获取当前sessionFactory
	 * @return sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("rawtypes")
	public void setEntityClass(Class entityClass){
		this.entityClass = entityClass;
	}
	
	/**
	 * 刷新session
	 */
	public void flush() {
        getSession().flush();
    }

	/**
	 * 清空session
	 */
    public void clear() {
        getSession().clear();
    }
    
    
    /**
     * 根据sql创建Query
     * @param queryString sql语句, 参数必须用"?"设置
     * @param values 可变参数, 对应查询语句里的" ? "
     * @return Query Query对象
     */
    public Query createSqlQuery(String queryString, Object... values) {
		Query queryObject = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	
	
	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理.<br>
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 
	 * @param queryString HQL查询语句, 参数必须用"?"设置
	 * @param values 可变参数, 对应查询语句里的" ? "
	 * @return Query Query对象
	 */
	public Query createQuery(String queryString, Object... values) {
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理.
	 * @param criterions 可变的Restrictions条件列表, 封装了查询条件
	 * @return Criteria Criteria对象
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Map<String, String> aliasMap = new HashMap<String, String>();
		for (Criterion c : criterions) {
			String propertyName = null;
			try {
				propertyName = getFieldValue(c, "propertyName").toString();
				if (propertyName!=null) {
					String[] paramStrs = propertyName.split("\\.");
					if(paramStrs.length > 1){
						String alias = paramStrs[0];
						Field f  = entityClass.getDeclaredField(alias);
						if(isRelatingObject(f)){
							String param = paramStrs[1];
							String identifier = getIdentifierName(f.getType());
							if(!identifier.equals(param)){
								if(criteria instanceof CriteriaImpl){
									aliasMap.put(alias, alias);
								}
							}
						}
					}
				}
			} catch (NoSuchFieldException e) {
				//没有propertyName属性，直接跳出
			}
			criteria.add(c);
		}
		Iterator<String> iterator = aliasMap.values().iterator();
		while (iterator.hasNext()) {
			String alias = iterator.next();
			criteria.createAlias(alias, alias);
		}
		return criteria;
	}
	
	
	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理.
	 * @param criterions 可变的Restrictions条件列表, 封装了查询条件
	 * @return Criteria Criteria对象
	 */
	public Criteria createCriteria(List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Map<String, String> aliasMap = new HashMap<String, String>();
		for (Criterion c : criterions) {
			String propertyName = null;
			try {
				propertyName = getFieldValue(c, "propertyName").toString();
				if (propertyName!=null) {
					String[] paramStrs = propertyName.split("\\.");
					if(paramStrs.length > 1){
						String alias = paramStrs[0];
						Field f  = entityClass.getDeclaredField(alias);
						if(isRelatingObject(f)){
							String param = paramStrs[1];
							String identifier = getIdentifierName(f.getType());
							if(!identifier.equals(param)){
								if(criteria instanceof CriteriaImpl){
									aliasMap.put(alias, alias);
								}
							}
						}
					}
				}	
			} catch (NoSuchFieldException e) {
				//没有propertyName属性，直接跳出
			}			
			criteria.add(c);
		}
		Iterator<String> iterator = aliasMap.values().iterator();
		while (iterator.hasNext()) {
			String alias = iterator.next();
			criteria.createAlias(alias, alias);
		}
		return criteria;
	}
	
	
	
	
	
	
	
	/**
	 * 按HQL查询对象列表.
	 * @param hql hql语句, 参数必须用"?"设置
	 * @param values 可变参数, 对应查询语句里的" ? "
	 * @return List 查询出的对象类表
	 */
	@SuppressWarnings("rawtypes")
	public List findByHql(String hql, Object... values) {
		return createQuery(hql, values).list();
	}
	
	
	/**
	 * 按Criterion查询对象列表.
	 * @param criterion 数量可变的Criterion.
	 * @return List 查询出的对象类表
	 */
	@SuppressWarnings("rawtypes")
	public List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}
	
	
	
	
	/**
	 * 按HQL查询唯一对象.
	 * @param hql HQL语句, 参数必须用"?"设置
	 * @param values 可变参数, 对应查询语句里的" ? "
	 * @return Object 查询出的唯一对象
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}
	
	
	/**
	 * 按Criteria查询唯一对象.
	 * @param criterion 数量可变的Criterion.
	 * @return Object 查询出的唯一对象
	 */
	public Object findUnique(Criterion... criterion) {
		return createCriteria(criterion).uniqueResult();
	}
	

	/**
	 * 按HQL查询Intger类形结果,不能用于count查询,count查询可使用findUniqueLong.  
	 * @param hql HQL语句, 参数必须用"?"设置
	 * @param values 可变参数, 对应查询语句里的" ? "
	 */
	public Integer findUniqueInt(String hql, Object... values) {
		return (Integer) findUnique(hql, values);
	}
	

	/**
	 * 按HQL查询Long类型结果,可用于count查询. 	 
	 * @param hql HQL语句, 参数必须用"?"设置
	 * @param values 可变参数, 对应查询语句里的" ? "
	 */
	public Long findUniqueLong(String hql, Object... values) {
		return (Long) findUnique(hql, values);
	}
	
	
	/**
	 * 初始化对象.
	 * 使用load()方法得到的仅是对象Proxy后, 在传到View层前需要进行初始化.
	 * initObject(user) ,初始化User的直接属性，但不会初始化延迟加载的关联集合和属性.
	 * initObject(user.getRoles())，初始化User的直接属性和关联集合.
	 * initObject(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initObject(Object object) {
		Hibernate.initialize(object);
	}

	/**
	 * 批量初始化对象.
	 * @see #initObject(Object)
	 */
	@SuppressWarnings("rawtypes")
	public void initObjects(List list) {
		for (Object object : list) {
			Hibernate.initialize(object);
		}
	}

	/**
	 * 通过Set将不唯一的对象列表唯一化.
	 * 主要用于HQL/Criteria预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	public <X> List<X> distinct(List<X> list) {
		Set<X> set = new LinkedHashSet<X>(list);
		return new ArrayList<X>(set);
	}

	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	public Criteria distinct(Criteria c) {
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return c;

	}
	
	/**
     * 获取当前实体的主键名称
     * @param entityClass 实体类
     * @return 主键字段名
     */
    public String getIdentifierName(){
    	return getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName();
    }
    
    /**
     * 获取给定实体的主键名称
     * @param entityClass 实体类
     * @return 主键字段名
     */
    @SuppressWarnings("rawtypes")
	public String getIdentifierName(Class entityClass){
    	return getSessionFactory().getClassMetadata(entityClass).getIdentifierPropertyName();
    }
	
	
	/**
	 * 去除hql的select 子句,未考虑union的情况,用于pagedQuery.
	 * @param hql HQL语句,只限于单实体查询
	 */
	public String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}
	
	

	/**
	 * 去除hql的orderby 子句,用于pagedQuery.
	 * @param hql HQL语句
	 */
	public String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	
	
	/**
	 * 过滤sql语句,防止注入
	 * @param hql SQL语句
	 */
	public String replaceInject(String hql) {
		return StringEscapeUtils.escapeSql(hql);
	}
	
	
	
	/**
	 * 将hql查询语句转化成count(*)统计结果集语句
	 * @param hql HQL语句
	 */
	public String replaceCountHql(String hql) {
		return "select count(*) " + removeOrders(removeSelect(hql));
	}
	
	
	
	/**
	 * 将hql转化为count(*) sql语句
	 * @param originalHql hql语句
	 * @return sql语句
	 */
	public String getCountSql(String originalHql) { 
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(originalHql, originalHql, 
		Collections.EMPTY_MAP, (org.hibernate.engine.spi.SessionFactoryImplementor)getSessionFactory()); 
		queryTranslator.compile(Collections.EMPTY_MAP, false); 
		return "select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t"; 
	}
	
	

	/**
	 * 通过count查询获得本次查询所能获得的对象总数.
	 * @param c Criteria 查询条件
	 * @return int 结果总数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long getCountByCriteria(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) getFieldValue(impl, "orderEntries");
			setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}	

		// 执行Count查询
		long totalCount = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		if (totalCount < 0)
			return -1;

		// 将之前的Projection和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}

		try {
			setFieldValue(impl, "orderEntries", orderEntries);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 * @throws NoSuchFieldException 
	 */
	protected Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		Object result = null;
		try {
			result = field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * @throws NoSuchFieldException 
	 */
	protected Field getDeclaredField(Object object, String fieldName) throws NoSuchFieldException {
		return getDeclaredField(object.getClass(), fieldName);
	}
	
	
	
	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("rawtypes")
	protected Field getDeclaredField(Class clazz, String fieldName) throws NoSuchFieldException {
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
	}
	
	
	
	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * @throws NoSuchFieldException 
	 */
	protected void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断此字段是否是多对一或者一对一关联
	 * @param field 字段名称
	 * @return “是”则返回true
	 */
	protected boolean isRelatingObject(Field field){
		return field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class);
	}
	
	

}
