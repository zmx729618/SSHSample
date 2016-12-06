package org.nercita.bcp.user.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nercita.core.orm.BaseUidEntity;


/**
 * 用户实体
 * @author zhangwenchao
 *
 */ 

@Entity(name="User")
@Table(name = "T_P_User")
@Cacheable  
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseUidEntity{
 
	private static final long serialVersionUID = -7325713842096716468L;
	
	@Column(unique=true)
	private String username;
	
	@Column(nullable=false)
    private String password;
	
    private String name;
    
    private String realName;
    
    private String company;
    
    private String email;
    
    @Column(nullable=false)
    private String phone;
    
    @Column(nullable=false)
    private String addr;
    
    @Column(nullable=false)
    private int state=0;
    
    private String code;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
    
    
    
    
    

	
	
	

}
