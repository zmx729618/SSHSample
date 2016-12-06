package org.nercita.bcp.user.service;

import javax.annotation.Resource;

import org.nercita.bcp.user.dao.UserDao;
import org.nercita.bcp.user.domain.User;
import org.nercita.core.orm.IBaseDao;
import org.nercita.core.service.BaseService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService extends BaseService<User, Long>{
	
	@Resource(name="userDao")
	private  UserDao userDao;  

	@Override
	protected IBaseDao<User, Long> getEntityDao() {		
		return  userDao;
	}


	public User findByUsernameAndPassword(String username, String password) {
		
		return userDao.findByUsernameAndPassword( username,  password);
	}

}
