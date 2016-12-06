package org.nercita.bcp.system.dao;

import org.nercita.bcp.system.domain.Role;
import org.nercita.core.orm.hibernate.HibernateBaseDao;
import org.springframework.stereotype.Repository;

/**
 * 角色持久类
 * @author zhangwenchao
 *
 */
@Repository("roleDao")
public class RoleDao extends HibernateBaseDao<Role, String>{

}
