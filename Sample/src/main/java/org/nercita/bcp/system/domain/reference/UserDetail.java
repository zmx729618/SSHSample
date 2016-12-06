package org.nercita.bcp.system.domain.reference;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
/**
 * 登录用户的详细信息
 * @author zhangwenchao
 * 
 */
public class UserDetail extends User{
	
	private static final long serialVersionUID = -6889247576063361649L;
    private String userId;        //用户名Id
    private String realName;      //真实姓名
    private UserType userType;    //用户类型
    private RoleType roleType;    //角色类型
    
    public UserDetail(String username, String password,Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserDetail(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}


	

}
