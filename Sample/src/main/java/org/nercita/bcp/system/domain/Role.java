package org.nercita.bcp.system.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nercita.core.orm.BaseUidEntity;

/**
 * 用户角色
 * @author zhangwenchao
 *
 */
@Entity
@Table(name="T_S_Role")
@Cacheable  
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseUidEntity{
	

	private static final long serialVersionUID = -2875847535122040245L;


	//   角色名称： 区试处(0),主持人(1),试验点(2), 种子站(3), ... 
	@Column(length=64,nullable=false,unique=true)
    private String name;
	
	
    //是否可用
    private boolean enabled=true;
    
    
	/**
	 * 权限集合 多对多
	 */
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinTable(name = "T_Role_Authority", 
			joinColumns = { @JoinColumn(name ="role_id" )}, 
			inverseJoinColumns = { @JoinColumn(name = "authoritiy_id") })
	@OrderBy("id")
    private Set<Authority> authoritySet= new HashSet<Authority>();
    
    
	//记录人
	@Column(updatable = false)
	private String recorder;
    
    
	//记录时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date  recordTime;
    
    //修改人
    private String modifier;
    
    
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date modifyTime;
    
    //备注
    @Column(length=255)
    private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthoritySet() {
		return authoritySet;
	}

	public void setAuthoritySet(Set<Authority> authoritySet) {
		this.authoritySet = authoritySet;
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
	
	
    
	
	

}
