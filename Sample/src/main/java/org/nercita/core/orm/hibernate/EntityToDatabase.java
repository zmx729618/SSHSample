package org.nercita.core.orm.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * 扫描实体，生成数据库表
 * 
 * @author wangzz
 * @since 2009-03-25
 *
 */
public class EntityToDatabase {
	
	
	/**
	 * 根据实体导出数据包<BR>
	 * spring配置文件默认为"applicationContext.xml"<br>
	 * sessionFactory的bean的名称默认为"sessionFactory"
	 */
	public static void exportToDataBase(){
		exportToDataBase("applicationContext.xml", "sessionFactory");
	}
	
	/**
	 * 根据实体导出数据包<BR>
	 * spring配置文件默认为"applicationContext.xml"<br>
	 */
	public static void exportToDataBase(String sessionFactory){
		exportToDataBase("applicationContext.xml", sessionFactory);
	}
	
	/**
	 * 根据实体导出数据包
	 * @param contextName spring配置文件名称
	 * @param sessionFactoryName 配置文件中sessionFactory的bean的名称
	 */
	@SuppressWarnings("deprecation")
	public static void exportToDataBase(String contextName, String sessionFactoryName){
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(contextName);
			LocalSessionFactoryBean localSessionFactory = (LocalSessionFactoryBean) context
					.getBean("&" + sessionFactoryName);
			SessionFactoryImplementor sessionFactory = (SessionFactoryImplementor) context
					.getBean(sessionFactoryName);
//			Settings settings = sessionFactory.getSettings();
			Configuration config  = localSessionFactory.getConfiguration();
//			SchemaExport export = new SchemaExport(config, settings);
			SchemaExport export = new SchemaExport(config, sessionFactory.getProperties());
			export.create(true, true);
			
			
			System.out.println("OK");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error");
		}
	}
	
	public static void main(String[] args) {
		EntityToDatabase.exportToDataBase("applicationContext.xml", "sessionFactory");
	}
}
