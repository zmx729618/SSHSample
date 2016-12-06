package org.nercita.bcp.system.domain;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.nercita.core.orm.BaseUidEntity;

/**
 * 用户权限
 * @author zhangwenchao
 *
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name="T_S_Authority")
@Cacheable  
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Authority extends BaseUidEntity{

	private static final long serialVersionUID = 1558957611918721365L;
	
	//ROLE_"为前缀的代码
	@Column(length=64, nullable=false, unique=true)
    @Index(name="Index_Authority_code")
	private String code;

	//权限名称
	@Column(length=64,nullable=false)
    private String name;
	
	//父权限
	@ManyToOne
	private Authority parentAuthority;
	
	//资源路径
	private String resourceUrl;
	
	//是否为父权限
	private boolean isParent;
	
	//权限树形深度
    private Integer deepLevel = 0;
    
    
    //权限是否可用
    private boolean enabled=true;
    
    //权限排序
    private Integer orderNum = 0;
    
    
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
    
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority getParentAuthority() {
		return parentAuthority;
	}

	public void setParentAuthority(Authority parentAuthority) {
		this.parentAuthority = parentAuthority;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getDeepLevel() {
		return deepLevel;
	}

	public void setDeepLevel(Integer deepLevel) {
		this.deepLevel = deepLevel;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
    
  
	

}
