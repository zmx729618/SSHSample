package org.nercita.core.test.spring;

import org.hibernate.SessionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring支持数据库事务、依赖注入的基于JUnit 4 的基类的便捷简写与flush()函数.
 * @ContextConfiguration 设置spring配置文件
 * @TransactionConfiguration 配置了事务以及回滚属性，可根据需要进行修改，默认为自动回滚数据库操作
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class SpringTransactionalTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 刷新默认的sessionFactory,强制Hibernate执行SQL以验证ORM配置.
	 * SQL执行的结果只要不进行提交就不会影响测试数据库的实际数据.
	 */
	public void flush() {
		flush("sessionFactory");
	}

	/**
	 * 刷新sessionFactory,强制Hibernate执行SQL以验证ORM配置.
	 * SQL执行的结果只要不进行提交就不会影响测试数据库的实际数据.
	 */
	public void flush(String sessionFactoryName) {
		((SessionFactory) applicationContext.getBean(sessionFactoryName)).getCurrentSession().flush();
	}
	
}
