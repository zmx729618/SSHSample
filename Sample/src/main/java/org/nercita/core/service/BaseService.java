package org.nercita.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.nercita.core.orm.IBaseDao;
import org.nercita.core.orm.Page;
import org.nercita.core.search.Search;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通用service基类，封装了一般的CURD<BR>
 * 简单的service只要实现此类就可实现添删改查<BR>
 * @author wangzz
 *
 * @param <T> 实体
 * @param <PK> 主键类型
 * @since 2009-1-3
 */
@Transactional
public abstract class BaseService<T, PK extends Serializable> {	
	
	/**
	 * 获取当前dao<br>
	 * 子类实现此方法注入dao
	 * @return 继承IBaseDao的dao
	 */
	protected abstract IBaseDao<T, PK> getEntityDao();
	
	
	/**
	 * 根据Id查询实体
	 * @param id
	 * @return 实体
	 */
	@Transactional(readOnly = true)
	public T findById(PK id){
		return getEntityDao().findById(id);
	}
	
	
	/**
	 * 保存实体
	 * @param entity 实体对象
	 */
	public void save(T entity){
		getEntityDao().save(entity);
	}
	
	
	/**
	 * 保存实体列表
	 * @param entity 实体列表对象
	 */
	public void save(List<T> entities){
		for(int i=0;i<entities.size();i++)
		getEntityDao().save(entities.get(i));
	}
	
	
	/**
	 * 更新实体
	 * @param entity 实体对象
	 */
	public void update(T entity){
		getEntityDao().update(entity);
	}
	
	
	/**
	 * 保存或更新实体
	 * @param entity 实体对象
	 */
	public void saveOrUpdate(T entity){
		getEntityDao().saveOrUpdate(entity);
	}
	
	
	/**
	 * 删除实体
	 * @param entity 实体对象
	 */
	public void delete(T entity){
		getEntityDao().delete(entity);
	}
	
	
	/**
	 * 批量删除实体列表
	 * @param entity 实体对象列表
	 */
	public void delete(List<T> entities){
		for(int i=0;i<entities.size();i++)
		getEntityDao().delete(entities.get(i));
	}
	
	
	/**
	 * 根据Id删除实体
	 * @param id 
	 */
	public void deleteById(PK id){
		getEntityDao().deleteById(id);
	}
	
	
	/**
	 * 根据Id数组批量删除实体
	 */
	public void deleteById(PK[] id) {	
		for(PK pk : id){
			deleteById(pk);
		}
	}
	
	
	/**
	 * 根据hql执行语句,用于批量更新删除
	 * @param hql hql语句
 	 * @param values 可变参数
	 * @return 影响的个数
	 */
	public int execute(String hql, Object... values){
		return getEntityDao().execute(hql, values);
	}
	
	
	/**
	 * 根据条件模糊查询所有实体
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAllLike(String queryName, Object queryValue) {
		if("".equals(queryValue)||queryValue==null || "".equals(queryName)||queryName==null){
			return getEntityDao().findAll();
		}else {
			return getEntityDao().findAllLike(queryName, queryValue);
		}
	}
	
	
	/**
	 * 根据条件精确查询所有实体
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAllEq(String queryName, Object queryValue) {
		if("".equals(queryValue)||queryValue==null || "".equals(queryName)||queryName==null){
			return getEntityDao().findAll();
		}else {
			return getEntityDao().findAllEq(queryName, queryValue);
		}
	}
	
	
	/**
	 * 查询所有实体
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(){
		return getEntityDao().findAll();
	}
	
	
	/**
	 * 分页查询所有实体
	 * @param page 分页条件
	 * @return Page 分页查询结果,附带结果列表及所有查询时的参数.<br>
	 * 				可通过page.getResult()获取.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPage(Page<T> page){
		return getEntityDao().findPage(page);
	}
	
	
	/**
	 * 根据查询条件分页查询(模糊查询)
	 * @param page 分页参数
	 * @param queryMap 查询条件，在map中以键、值对保存
	 * @return Page
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public Page<T> findPageByMap(Page<T> page, Map queryMap){
		return getEntityDao().findPageByMap(page, queryMap);
	}
	
	
	/**
	 * 按属性查找唯一对象.
	 * @param propertyName 要查询的列名
	 * @param value 要查询的值(精确查询)
	 * @return 实体对象
	 */
	@Transactional(readOnly = true)
	public T findUniqueByProperty(String propertyName, Object value){
		return getEntityDao().findUniqueByProperty(propertyName, value);
	}
	
	
	/**
	 * 根据单个查询条件分页(模糊查询)
	 * @param page 分页参数
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return page
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByPropertyLike(Page<T> page, String queryName, Object queryValue){
		if("".equals(queryValue)||queryValue==null || "".equals(queryName)||queryName==null){
			return getEntityDao().findPage(page);
		}
		else
			return getEntityDao().findPageByPropertyLike(page, queryName, queryValue);
	}
	
	
	/**
	 * 根据单个查询条件分页(精确查询)
	 * @param page 分页参数
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return Page 分页查询结果.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByPropertyExact(Page<T> page, String queryName, Object queryValue){
		if("".equals(queryValue)||queryValue==null || "".equals(queryName)||queryName==null)
			return getEntityDao().findPage(page);
		else
			return getEntityDao().findPageByPropertyExact(page, queryName, queryValue);
	}
	
	
	/**
	 * 根据查询条件分页查询(模糊查询)
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @return Page 分页查询结果.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByQuerysBlur(Page<T> page, T entity){
		return getEntityDao().findPageByQuerysBlur(page, entity);
	}
	
	
	/**
	 * 根据查询条件分页查询(精确查询)
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @return Page 分页查询结果.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByQuerysExact(Page<T> page, T entity){
		return getEntityDao().findPageByQuerysExact(page, entity);
	}
	
	
	
	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	@Transactional(readOnly = true)
	public boolean isPropertyUnique(String propertyName, Object newValue, Object oldValue) {
		if (newValue == null || "".equals(newValue) || newValue.equals(oldValue))
			return true;
		T object = getEntityDao().findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}
	
	
	
	/**
	 * 根据查询构建器查询所有记录
	 * @param search 查询构建器
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<T> search(Search search){
		return getEntityDao().search(search);
	}
	
	
	/**
	 * 根据查询构建器查询Page
	 * @param page 分页参数
	 * @param search 查询构建器
	 * @return Page
	 */
	@Transactional(readOnly = true)
	public Page<T> search(Page<T> page, Search search){
		return getEntityDao().search(page, search);
	}
	
	
	
	
}
