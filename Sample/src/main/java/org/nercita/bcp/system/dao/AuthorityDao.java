package org.nercita.bcp.system.dao;

import java.util.List;

import org.nercita.bcp.system.domain.Authority;
import org.nercita.core.orm.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 * 权限持久类
 * @author zhangwenchao
 *
 */
@Repository("authorityDao")
public class AuthorityDao extends HibernateBaseDao<Authority, String>{

	/**
	 * 根据权限编码获取权限
	 * @param code
	 * @return
	 */
	public Authority findAuthorityByCode(String code) {
		return findUniqueByProperty("code", code);
	}
	
	
	/**
	 * 获取所有根权限列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<Authority> getAllHighestAuthority() { 
		List<Authority> list = getSession().createQuery("from Authority a where a.parentAuthority is null ").list();
        return list;
    }
	
	
	/**
	 * 获取所有根权限列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<Authority> getAllByBaseAuthorityCode(String baseAuthorityCode) { 
		List<Authority> list = getSession().createQuery("from Authority a where a.code like '%"+baseAuthorityCode+"%' ").list();
        return list;
    }
	
	
	

}
