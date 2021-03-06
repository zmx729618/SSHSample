package org.nercita.core.test.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring的支持数据库访问和依赖注入的JUnit4 集成测试基类.
 * 
 *  1.Spring Context IOC support
 *  2.Spring Transaction support 
 *  3.Spring JdbcTemplate and util functions
 *  4.JUnit Assert functions 
 *  
 * 子类需要定义applicationContext文件的位置, 如:
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" })
 *  
 * @see AbstractTransactionalJUnit4SpringContextTests
 * @see SpringContextTestCase
 * 
 */
@SuppressWarnings("deprecation")
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SpringTxTestCase extends SpringContextTestCase {

	protected DataSource dataSource;

	protected JdbcTemplate jdbcTemplate;

	protected String sqlScriptEncoding;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public void setSqlScriptEncoding(String sqlScriptEncoding) {
		this.sqlScriptEncoding = sqlScriptEncoding;
	}

	protected int countRowsInTable(String tableName) {
		return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
	}

	protected int deleteFromTables(String... names) {
		return JdbcTestUtils.deleteFromTables(this.jdbcTemplate, names);
	}

	protected void runSql(String sqlResourcePath, boolean continueOnError) throws DataAccessException {
		Resource resource = this.applicationContext.getResource(sqlResourcePath);
	    JdbcTestUtils.executeSqlScript(this.jdbcTemplate, new EncodedResource(resource, this.sqlScriptEncoding),
				continueOnError);
	}
}
