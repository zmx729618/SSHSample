package org.nercita.bcp.system.domain;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nercita.bcp.system.domain.reference.RoleType;
import org.nercita.bcp.system.domain.reference.UserState;
import org.nercita.bcp.system.domain.reference.UserType;
import org.nercita.core.orm.BaseUidEntity;


/**
 * 用户实体
 * @author zhangwenchao
 */
@Entity
@Table(name = "T_S_User")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseUidEntity {

	private static final long serialVersionUID = -2512321822386351911L;

	// 用户名
	@Column(length = 64, unique = true, nullable = false)
	private String name;

	// 密码
	@Column(length = 64, nullable = false)
	private String password;

	// 真实姓名
	@Column(length = 64, nullable = false)
	private String contacter;

	// 手机
	@Column(length = 13)
	private String phone;
	

	// 公司或单位
	@Column(length = 64)
	private String company;
	
	// 地址
	@Column(length = 64)
	private String address;

	// 电子邮件
	@Column(length = 64)
	private String email;

	// 是否可用
	private Boolean enabled;


	/**
	 * 用户类型: 0：管理员  1：普通用户
	 */
	private UserType userType = UserType.General;

	/**
	 * 用户状态： 0：禁用  1：启用
	 */
	private UserState userState = UserState.Enable;
	
	
	/**
	 * 角色类型：区试处(0) 主持人(1) 试验点(2) 种子站(3)
	 */
	private RoleType roleType;
	
	/**
	 * 用户角色：用于获取用户权限控
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	
	// 记录人
	@Column(updatable = false)
	private String recorder;

	// 记录时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date recordTime;

	// 修改人
	private String modifier;

	// 修改时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true)
	private Date modifyTime;

	@Column(length = 255)
	private String remark;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserState getUserState() {
		return userState;
	}

	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User(String name, String password, Boolean enabled, UserType userType, UserState userState, RoleType roleType) {
		super();
		this.name = name;
		this.password = password;
		this.enabled = enabled;
		this.userType = userType;
		this.userState = userState;
		this.roleType = roleType;
	}

}
