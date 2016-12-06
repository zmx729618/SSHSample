package org.nercita.core.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.nercita.core.search.Search;

/**
 * 针对单个实体对象的操作定义.不依赖于具体ORM实现方案.
 * 
 * @author wangzz
 *
 * @param <T> 实体
 * @param <PK> 主键类型
 * 
 * @since 2008-12-23
 * 
 */

public interface IBaseDao<T, PK extends Serializable> {
	
	/**
	 * 根据Id查询实体
	 * @param id
	 * @return 实体对象
	 */
	T findById(PK id);
	
	
	/**
	 * 根据id查询实体，并加锁
	 * @param id 
	 * @return 实体对象
	 */
	public T findByIdForLock(PK id);
	
	
	/**
	 * 保存实体
	 * @param entity 实体对象
	 */
	void save(T entity);
	
	
	/**
	 * 更新实体
	 * @param entity 实体对象
	 */
	void update(T entity);
	
	
	/**
	 * 更新实体
	 * @param entity 实体对象
	 */
	void saveOrUpdate(T entity);
	
	
	/**
	 * 删除实体
	 * @param entity 实体对象
	 */
	void delete(T entity);
		
	
	/**
	 * 根据hql执行语句,用于批量更新删除
	 * @param queryString hql语句
	 * @param values 可变参数
	 * @return 影响的个数
	 */
	public int execute(String queryString, Object... values);
	
	
	/**
	 * 根据Id删除实体
	 * @param id 
	 */
	void deleteById(PK id);
	
	
	/**
	 * 查询所有实体
	 * @return List 查询结果集
	 */
	List<T> findAll();
	
	
	/**
	 * 根据条件模糊查询所有实体
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return List 查询结果集
	 */
	List<T> findAllLike(String queryName, Object queryValue);
	
	
	/**
	 * 根据条件精确查询所有实体
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return List 查询结果集
	 */
	List<T> findAllEq(String queryName, Object queryValue);
	
	
	/**
	 * 分页查询所有实体
	 * @param page 分页条件
	 * @return Page 分页查询结果,附带结果列表及所有查询时的参数.<br>
	 * 				可通过page.getResult()获取.
	 */
	Page<T> findPage(Page<T> page);
	
	
	
	/**
	 * 按属性查找唯一对象.
	 * @param propertyName 要查询的列名
	 * @param value 要查询的值(精确查询)
	 * @return 实体对象
	 */
	public T findUniqueByProperty(String propertyName, Object value);
	
	
	
	/**
	 * 根据单个查询条件分页(精确查询)
	 * @param page 分页参数
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByPropertyExact(Page<T> page, String queryName, Object queryValue);
	
	
	
	/**
	 * 根据单个查询条件分页(模糊查询)
	 * @param page 分页参数
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByPropertyLike(Page<T> page, String queryName, Object queryValue);
	
	
	
	/**
	 * 根据查询条件分页查询(模糊查询)
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByQuerysBlur(Page<T> page, T entity);
	
	
	
	/**
	 * 根据查询条件分页查询(精确查询)
	 * @param page 分页参数
	 * @param entity 查询实体，设置了要查询的条件
	 * @return Page 分页查询结果.
	 */
	public Page<T> findPageByQuerysExact(Page<T> page, T entity);
	
	
	
	/**
	 * 根据查询条件分页查询(模糊查询)
	 * @param page 分页参数
	 * @param queryMap 查询条件，在map中以键、值对保存
	 * @return Page 分页查询结果.
	 */
	
	@SuppressWarnings("rawtypes")
	public Page<T> findPageByMap(Page<T> page, Map queryMap);
	
	
	
	/**
	 * 根据查询构建器查询所有记录
	 * @param search 查询构建器
	 * @return List
	 */
	public List<T> search(Search search);
	
	
	
	/**
	 * 根据查询构建器查询Page
	 * @param page 分页参数
	 * @param search 查询构建器
	 * @return Page
	 */
	public Page<T> search(Page<T> page, Search search);
	
}
