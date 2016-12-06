package org.nercita.bcp.system.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.nercita.bcp.system.domain.User;
import org.nercita.bcp.system.domain.reference.UserState;
import org.nercita.bcp.system.domain.reference.UserType;
import org.nercita.bcp.system.domain.vo.UserVo;
import org.nercita.bcp.utils.FilterDescriptor;
import org.nercita.bcp.utils.KendoPage;
import org.nercita.bcp.utils.SortDescriptor;
import org.nercita.core.orm.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 * 用户持久类
 * @author zhangwenchao
 *
 */
@Repository("userDao")
public class UserDao extends HibernateBaseDao<User, String>{
	
	
	/**
	 * 根据用户名密码查询用户
	 * @param name
	 * @param password
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public User findByUsernameAndPassword(String name, String password) {
	
		
	//	String hql = "FROM User u where u.name=? and u.password=? and u.userState="+UserState.Enable;
		String hql = "FROM User u where u.name=? and u.password=? and u.userState="+UserState.Enable.getValue();
		Query query = getSession().createQuery(hql)
		                 .setParameter(0, name)
		                 .setParameter(1, password);
		List list = query.list();		
		if(list !=null && list.size()>0){
			User u = (User)list.get(0);
			return u;
		}else{
			return null;
		}
	
	}
	
	
	/**
	 * 根据用户名密码查询用户
	 * @param name
	 * @param password
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public User findByUsernameAndPasswordAndImei(String name, String password,String imei) {
	
		
	//	String hql = "FROM User u where u.name=? and u.password=? and u.userState="+UserState.Enable;
		String hql = "FROM User u where u.name=? and u.password=? and u.imei=? and u.userState="+UserState.Enable.getValue();
		Query query = getSession().createQuery(hql)
		                 .setParameter(0, name)
		                 .setParameter(1, password)
		                 .setParameter(2, imei);
		List list = query.list();		
		if(list !=null && list.size()>0){
			User u = (User)list.get(0);
			return u;
		}else{
			return null;
		}
	
	}
	
	
	
	
    
	/**
	 * 根据用户账户查询用户
	 * @param userName
	 * @return
	 */
	public User findByName(String userName) {
		
		return findUniqueByProperty("name", userName);
	}

	
	/**
	 * 查询一般用户列表
	 * @param page
	 * @return
	 */
	public KendoPage<User> findPageByQuery(KendoPage<User> page) {
				
		String hql = "FROM User u WHERE  u.userState=1";	
		if(page.getFilter()!=null && page.getFilter().size()!=0){	
			for(FilterDescriptor filter: page.getFilter()){
				
				if(!hql.toLowerCase().contains("where")){ 
					hql += " where ";
			    }else{  
			    	hql += " and ";   
			    }
				if(filter.getField().equals("name")){	
				    hql += "  u.name like '%"+filter.getValue()+"%'";
				}
				if(filter.getField().equals("userGroup")){
					hql += "  u.userGroup.name like '%"+filter.getValue()+"%'";
				}
			}
		}
			
		if(page.getSort()!=null && page.getSort().size()!=0){
			for(SortDescriptor sort: page.getSort()){
				if(!hql.toLowerCase().contains("order by")){
					hql += " order by ";
				}else{
					hql += ", ";
				} 		
				//如需对前台field中字段进行重命名则需在此进行判断调整
				if( sort.getField().equals("name")){
					hql += " u.name "+ sort.getDir()+ " ";
				}else if( sort.getField().equals("groupName")){
					hql += " u.groupName.name " +  sort.getDir()+ " ";
				}else{
					hql += " u."+sort.getField() + " " + sort.getDir();
				}				
		    }
			
		}else{
			hql += " order by u.name asc";
		}
		return (KendoPage<User>) findPageByHql(page, hql);
	}
	
	
	/**
	 * 查询一般用户列表
	 * @param page
	 * @return
	 */
	public KendoPage<User> findPageByQuery(KendoPage<User> page, User user) {
		String hql = "FROM User u WHERE  u.name <> 'admin' ";
		if(user.getUserType() ==UserType.System){
			hql += "and u.userType = "+UserType.System.getValue();
		}else{
			hql += " and u.userType = "+UserType.General.getValue();
		}

		if(page.getFilter()!=null && page.getFilter().size()!=0){	
			for(FilterDescriptor filter: page.getFilter()){
				if(!hql.toLowerCase().contains("where")){ 
					hql += " where ";
			    }else{  
			    	hql += " and ";   
			    }
				if(filter.getField().equals("name")){	
				    hql += "  u.name like '%"+filter.getValue()+"%'";
				}else if(filter.getField().equals("userGroup")){
					hql += "  u.userGroup.name like '%"+filter.getValue()+"%'";
				}else{
					hql += " u."+filter.getField()+" like '%"+filter.getValue()+"%'";
				}
				
				
			}
		}
			
		if(page.getSort()!=null && page.getSort().size()!=0){
			for(SortDescriptor sort: page.getSort()){
				if(!hql.toLowerCase().contains("order by")){
					hql += " order by ";
				}else{
					hql += ", ";
				} 		
				//如需对前台field中字段进行重命名则需在此进行判断调整
				if( sort.getField().equals("name")){
					hql += " u.name "+ sort.getDir()+ " ";
				}else if( sort.getField().equals("groupName")){
					hql += " u.groupName.name " +  sort.getDir()+ " ";
				}else{
					hql += " u."+sort.getField() + " " + sort.getDir();
				}				
		    }
			
		}else{
			hql += " order by u.name asc";
		}
		return (KendoPage<User>) findPageByHql(page, hql);
	}	
	/**
	 * 查询所有系统用户列表
	 * @param page
	 * @return
	 */
	public KendoPage<User> findSystemUserPageByQuery(KendoPage<User> page, User user) {
		String hql = "FROM User u WHERE  u.name <> 'admin' ";
		if(user.getUserType() == UserType.System){
			hql += "and u.userType = "+UserType.System.getValue();
		}else{
			hql += "and u.id = '"+user.getId()+"' and u.userType = "+UserType.System.getValue();
		}
		if(page.getFilter()!=null && page.getFilter().size()!=0){	
			for(FilterDescriptor filter: page.getFilter()){
				if(!hql.toLowerCase().contains("where")){ 
					hql += " where ";
			    }else{  
			    	hql += " and ";   
			    }
				if(filter.getField().equals("name")){	
				    hql += "  u.name like '%"+filter.getValue()+"%'";
				}else if(filter.getField().equals("userGroup")){
					hql += "  u.userGroup.name like '%"+filter.getValue()+"%'";
				}else{
					hql += " u."+filter.getField()+" like '%"+filter.getValue()+"%'";
				}
				
				
			}
		}
			
		if(page.getSort()!=null && page.getSort().size()!=0){
			for(SortDescriptor sort: page.getSort()){
				if(!hql.toLowerCase().contains("order by")){
					hql += " order by ";
				}else{
					hql += ", ";
				} 		
				//如需对前台field中字段进行重命名则需在此进行判断调整
				if( sort.getField().equals("name")){
					hql += " u.name "+ sort.getDir()+ " ";
				}else if( sort.getField().equals("groupName")){
					hql += " u.groupName.name " +  sort.getDir()+ " ";
				}else{
					hql += " u."+sort.getField() + " " + sort.getDir();
				}				
		    }
			
		}else{
			hql += " order by u.name asc";
		}
		return (KendoPage<User>) findPageByHql(page, hql);
	}	
	

	/**
	 * 根据团队管理员获取该团队总的用户数量
	 * @param teamAdmin
	 * @return
	 */
	public Long getTeamUserCount(User teamAdmin){	
		String hql = "select count(*) FROM User u WHERE  u.userGroup.teamAdmin.id = '"+teamAdmin.getId()+"' ";		
	    return (Long)getSession().createQuery(hql).uniqueResult();		
	}
	
	
	
	/**
	 * 根据团队管理员获取该团队的所有用户的用户代号
	 * @param teamAdmin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getTeamUserMarks(User teamAdmin){	
		String hql = "Select u.userMark FROM User u WHERE  u.userGroup.teamAdmin.id = '"+teamAdmin.getId()+"' ";		
	    return getSession().createQuery(hql).list();		
	}
	
	
	
	/**
	 * 统计1个团队 1个作物对应的用户数量
	 * @param teamAdmin
	 */
	public Long getTeamUserCount(User teamAdmin,String cropid){
		String hql =  "select count(*) FROM User u  inner join  u.cropSet as crop where u.userGroup.teamAdmin.id = '"+teamAdmin.getId()+"' and  crop.id='"+cropid+"'";
		return (Long)getSession().createQuery(hql).uniqueResult();	
	
	}
	
	/**
	 * 根据团队管理员统计该团队每个作物对应的用户数量
	 * @param teamAdmin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserCountByCrop(User teamAdmin){						
		String hql =  "select crop.id, count(*) FROM User u  inner join  u.cropSet as crop where u.userGroup.teamAdmin.id = '"+teamAdmin.getId()+"' group by  crop.id ";
		List<Object[]>  ls = getSession().createQuery(hql).list();
	    return ls;		
	}
	
			
	
    /**
     * 查找未激活的用户
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<User> findNotActivatedUsers() {		
		String hql = "FROM  User u WHERE u.activationCode IS NOT NULL";
		Query query = getSession().createQuery(hql);
		List<User> list = (List<User>)query.list();
		return list;
		
	}
	
	/**
     * 查找指定的用户
     * @param idString 用户id的字符串;idSorString带索引号的用户Id
     * @return
     */
	@SuppressWarnings("unchecked")
    public String getNamesByIdList(String idString, String idSorString) {
        String sql = "select wm_concat(u.realName) from T_P_User u where u.id in (" + idString + ")";
        sql += " ORDER BY DECODE(u.id," + idSorString + ")";//按照in中的结果排序
        List<String> list = createSqlQuery(sql).list();
        if (list != null) {
        	return list.get(0);
		}
        return "";
    }
	
	
	/**
	 * 根据团队管理员id，作物id查询整个团队的用户列表
	 * @return List<UserVo>
	 */
	@SuppressWarnings("unchecked")
	public List<UserVo> getCurrentSystemUserList(String userId, String cropId) {
		String hql =  "SELECT distinct  u.id as id, u.realName as name FROM User u  inner join  u.cropSet as crop where u.userGroup.teamAdmin.id = '"+userId+"' and  crop.id='"+cropId+"'";
		Query query = getSession().createQuery(hql);
		List<UserVo> list = query.setResultTransformer(Transformers.aliasToBean(UserVo.class)).list();
		return list; 
	}

	/**
	 * 根据用户Id和作物ID获取用户的作物状态
	 * @param userId
	 * @param cropId
	 * @return
	 */
	public String getUserCropStatus(String userId, String cropId) {
		String sql = "select UseState from t_p_user_t_b_crop  where T_P_User_Id= '"+userId+"' and cropset_Id='"+cropId+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		return (String)query.uniqueResult();
		
	}

    /**
     * 查询所有 账户有效期小于截止日期 并且状态为激活、类型为团队管理员的用户列表
     * (仅仅查询团队管理员用户，一般用户的状态有团队管理员控制，系统默认团队管理员账户被锁定，其子用户默认也不能登录系统)
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<User> findExpiredUsers(Date deadline) {
		String hql = "FROM  User u WHERE u.expirationDate < ? and u.userState="+UserState.Enable.getValue() +"and u.userType="+UserType.General.getValue();
		Query query = getSession().createQuery(hql);
		query.setDate(0, deadline);
		List<User> list = (List<User>)query.list();
		return list;
	}


	
	

}
