package org.nercita.bcp.test;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nercita.bcp.user.dao.UserDao;
import org.nercita.bcp.user.domain.User;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:config/webmvc-config.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class UserTest{
	
	private static Logger logger = LogManager.getLogger(UserTest.class);
	
	@Resource(name="userDao")
	private  UserDao userDao; 
	
	@Resource(name="messageSource")
	private ResourceBundleMessageSource bundleMessageSource;
	
	@Test
	public void saveUser(){
		User u = new User();
		u.setName("zxxxx1");
		u.setPassword("1BDMBDMNDMDNND"); //123343
		u.setAddr("北京");
		u.setPhone("1332222222222222222");
		
		
        Session session = userDao.getSession();    
        session.save(u);
       

	}
	
	
	/**
	 * 测试
	 */	
	@Test
	public void testGetUser(){
        Session session = userDao.getSession();    
        User u1 = (User) session.get(User.class, "4028f58157e14be40157e14be9320000");  
        System.out.println(u1.getName());  

	}
	
	
	/**
	 * 测试二级缓存
	 */
	@Test
	public void testEhcacheUser(){
		
        Session session = userDao.getSession();    
        User u1 = (User) session.get(User.class, "40288e9350035da00150035e87210000");  
        System.out.println(u1.getName());  
        System.out.println("-------------------------------------");
        Session session2 = userDao.getSession();          
        User u2 = (User) session2.get(User.class, "40288e9350035da00150035e87210000");  
        System.out.println(u2.getName());  

	}
	
	
	/**
	 * 测试查询缓存
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testQueryCacheUsers(){
		Session session = userDao.getSession();    
		Query query = session.createQuery("from User");
		//启用查询查询缓存
		query.setCacheable(true);
		List<User> users = query.list();
		for (Iterator<User> iter=users.iterator();iter.hasNext(); ) {
			User u = iter.next();
			System.out.println(u.getEmail());
		}
		System.out.println("-------------------------------------");
	
		query = session.createQuery("from User");
		//启用查询查询缓存（想使用上面已经存储到缓存中的数据的话，就必须再次开启查询缓存）
		query.setCacheable(true);
		//没有发出查询sql，因为启用了查询缓存
		users = query.list();
		for (Iterator<User> iter=users.iterator();iter.hasNext(); ) {
		   User u = iter.next();
		   System.out.println(u.getEmail());
		}
		
	}
	
	
	
	@Test
	public void testI18N(){		
		String name = bundleMessageSource.getMessage("message.name", null, null);
		System.out.println(name);
	}
	
	
	@Test
	public void testLog4J2(){
		
		logger.info("测试log4j2");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
