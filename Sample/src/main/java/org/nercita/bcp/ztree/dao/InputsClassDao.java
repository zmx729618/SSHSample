package org.nercita.bcp.ztree.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.nercita.bcp.ztree.domain.InputsClass;
import org.nercita.core.orm.hibernate.HibernateBaseDao;

/**
 * 投入品分类持久类
 *
 */
@Repository("inputsClassDao")
public class InputsClassDao extends HibernateBaseDao<InputsClass, String>{
	/**
     * 获得投入品分类 树结构
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Object[]> findAllLevelInputsClasses(){
    	String hql ="select i.id, i.name, i.inputsClassLevel, i.parentInputsClass.id from InputsClass i where i.status=1 order by i.inputsClassLevel";
    	return findByHql(hql);
    }
	
	 /**
     * 根据投入品分类等级 获取投入品分类集合
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<InputsClass> findInputsClassListByLevel(int level){
		
		Query query = getSession().createQuery("from InputsClass i where i.status =1 and i.inputsClassLevel=? order by i.code asc");
		
		query.setParameter(0, level);

		query.setCacheable(true);

		return query.list();
    }
    

    /**
     * 根据名称 编码 级别获取投入品
     * @return List<Object[]>
     */
    @SuppressWarnings("unchecked")
	public List<InputsClass> findInputsClassList(String name,String code,String level,Integer status){
    	
		String hql = "FROM InputsClass s WHERE 1=1";
    	if(name != null && !"".equals(name)){
    		hql += " AND s.name LIKE '%" + name + "%'";
    	}
    	if(code != null && !"".equals(code)){
    		hql += " AND s.code LIKE '%" + code + "%'";
    	}
    	if(level != null && !"".equals(level)){
    		hql += " AND s.inputsClassLevel = " + level ;
    	}
    	if(status!=null){
    		hql+=" AND s.status=" + status;
    	}
    	hql += "ORDER BY modifyTime DESC";
    	
    	return findByHql(hql);
    	
    }
	
	/**
	 * 根据输入得到获得品类下拉框列表
	 * @author qinyl
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> selectThreeInputsClass(String name){
		char[] names = name.toCharArray();
		StringBuffer likeCondition = new StringBuffer("%");
		for(char a : names){
			likeCondition.append(a).append("%");
		}
																						  								
		//String hql = "SELECT ic.id,ic.name FROM InputsClass ic WHERE ic.status = 1 AND ic.inputsClassLevel=3 AND (ic.pinYin LIKE '" + likeCondition.toString() + "' OR ic.name LIKE '" + likeCondition.toString() + "')";
		String hql = "SELECT ic.id,ic.name FROM InputsClass ic WHERE ic.status = 1 AND (ic.pinYin LIKE '" + likeCondition.toString() + "' OR ic.name LIKE '" + likeCondition.toString() + "')";
		
		return findByHql(hql);
	}
	
	/**
	 * 判断该投入品分类下是否具有子类
	 * @param inputsClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean haveChildren(String inputsClassId){
		String hql = "FROM InputsClass s WHERE s.status=1 and s.parentInputsClass.id='"+inputsClassId+"'";
    	List<InputsClass> ls=findByHql(hql);
    	if(ls!=null&&ls.size()>0){
    		return true;
    	}
    	return false;
	}
	/**
	 * 判断该投入品分类下是否具有投入品
	 * @param inputsClassId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean haveInputs(String inputsClassId){
    	String hql = "FROM Inputs s WHERE s.status=1 and s.inputsClass.id='"+inputsClassId+"'";
    	List<InputsClass> ls=findByHql(hql);
    	if(ls!=null&&ls.size()>0){
    		return true;
    	}
    	return false;
	}
	
	/**
	 * 依名称、级别获取投入品分类列表
	 * @param name
	 * @param level
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InputsClass> findInputsClassList(String name,String level){
    	
		String hql = "FROM InputsClass s WHERE 1=1";
    	if(name != null && !"".equals(name)){
    		hql += " AND s.name = '" + name + "'";
    	}
    	if(level != null && !"".equals(level)){
    		hql += " AND s.inputsClassLevel = " + level ;
    	}
    	hql += "ORDER BY modifyTime DESC";
    	
    	return findByHql(hql);
    	
    }
	/**
	 * 获得当前投入品的父类编码
	 * @param inputsClassId : 当前三级投入品分类
	 * @return
	 */
	public int findCodeByParentInputsClass(String inputsClassId){
		int code = 0;
		if(inputsClassId == null || "".equals(inputsClassId)){
			return code;
		}
		String sql = "SELECT p.code FROM T_InputsClass p WHERE p.id=(SELECT i.pid FROM T_InputsClass i WHERE i.id='" + inputsClassId + "')";
		Object obj = createSqlQuery(sql).uniqueResult();
		if(obj != null){
			code = Integer.parseInt(obj.toString());
		}
		return code;
	}
}
