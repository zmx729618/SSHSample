package org.nercita.bcp.user.dao;

import java.util.List;

import org.hibernate.Query;
import org.nercita.bcp.user.domain.User;
import org.nercita.core.orm.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDao extends HibernateBaseDao<User, Long>{

	
	@SuppressWarnings("rawtypes")
	public User findByUsernameAndPassword(String username, String password) {
	
		
		String hql = "FROM User u where u.username=? and u.password=? and u.state=1";
		Query query = getSession().createQuery(hql)
		                 .setParameter(0, username)
		                 .setParameter(1, password);

		List list = query.list();
		
		if(list !=null && list.size()>0){
			User u = (User)list.get(0);
			return u;
		}else{
			return null;
		}
		
	    
		
	}

}
