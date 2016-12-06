package org.nercita.bcp.excel.service;

import java.util.List;

import org.nercita.bcp.excel.dao.TestDao;
import org.nercita.bcp.excel.domain.TestExImport;
import org.nercita.core.orm.IBaseDao;
import org.nercita.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService extends BaseService<TestExImport, Long> {

	@Autowired
	private TestDao testDao;

	@Override
	protected IBaseDao<TestExImport, Long> getEntityDao() {
		return testDao;
	}

	// 查询
	public String[][] getTableData(String table, List<String> field,
			String strWhere) {
		List<Object[]> list = testDao.getTableData(table, field, strWhere);
		String[][] arr = new String[list.size()][field.size()];
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = list.get(i);
			for (int j = 0; j < field.size(); j++) {
				arr[i][j] = objs[j] == null ? "" : objs[j].toString();
			}
		}
		return arr;
	}
}
