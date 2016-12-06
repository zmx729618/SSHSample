package org.nercita.bcp.excel.dao;

import java.util.List;


import org.nercita.bcp.excel.domain.TestExImport;
import org.nercita.core.orm.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unchecked")
@Repository
public class TestDao extends HibernateBaseDao<TestExImport, Long>{

	public List<Object[]> getTableData(String table, List<String> field,String strWhere) {
		String s= field.toString(); 
		s = s.replace("[", "");
		s = s.replace("]", "");
		String sql="select " + s + " from " + table + " where 1=1";
		if(strWhere!=null){
			sql += strWhere;
		}
		List<Object[]> objs= createSqlQuery(sql).list();
		return objs;
	}   
}
