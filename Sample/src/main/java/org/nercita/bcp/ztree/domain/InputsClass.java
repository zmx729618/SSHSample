package org.nercita.bcp.ztree.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nercita.core.orm.BaseUidEntity;

/**
 * 投入品分类
 *
 */
@Entity(name="InputsClass")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class InputsClass extends BaseUidEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5292649302868499654L;

	/**
	 * 投入品分类编码：唯一的，不能为空。
	 */
	@Column(unique = true, nullable = false,length=32)
	private String code;
	
	/**
	 * 名称：唯一的，不能为空。
	 */
	@Column(nullable = false,length=50)
	private String name;
	
	//名称的拼音
	@Column(length=255)
	private String pinYin;

	//拼音的首字母
	@Column(length=160)
	private String firstSpell;
	
	/**
	 * 上级分类
	 */	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
//	@JoinColumn(name="pid")
	private InputsClass parentInputsClass;
	
	/**
	 * 下级分类
	*/ 
	@OneToMany(fetch=FetchType.LAZY, mappedBy="parentInputsClass")
	private Set<InputsClass> childInputsClasses = new HashSet<InputsClass>();
	
	
	
	/**
	 * 级别：唯一的，不能为空。
	 */
	@Column(nullable = false)
	private int inputsClassLevel;
	
	/**
	 * 最初记录人：记录一次，不再更新
	 */
	@Column(updatable = false,nullable = false,length=16)
	private String recorder;
	
	/**
	 * 最初记录时间：记录一次，不再更新
	 */
	@Column(updatable = false,nullable = false)
	private Date recordTime;
	
	/**
	 * 最后近修改人
	 */
	@Column(length=16)
	private String modifier;
	
	/**
	 * 最后修改时间
	 */
	@Column(nullable = false)
	private Date modifyTime;
	
	/**
	 * 状态: 0,删除；1 正常。
	 */
	@Column(nullable = false,length=1)
	private int status = 1;

	/**
	 * 简介
	 */
	@Column(nullable = true,length=200)
	private String introduction;

	
	public InputsClass() {
		super();		
	}
	
	public InputsClass getParentInputsClass() {
		return parentInputsClass;
	}



	public void setParentInputsClass(InputsClass parentInputsClass) {
		this.parentInputsClass = parentInputsClass;
	}



	public Set<InputsClass> getChildInputsClasses() {
		return childInputsClasses;
	}



	public void setChildInputsClasses(Set<InputsClass> childInputsClasses) {
		this.childInputsClasses = childInputsClasses;
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
	
	public String getRecorder() {
		return recorder;
	}
	
	public int getInputsClassLevel() {
		return inputsClassLevel;
	}

	public void setInputsClassLevel(int inputsClassLevel) {
		this.inputsClassLevel = inputsClassLevel;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getFirstSpell() {
		return firstSpell;
	}

	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}

}
