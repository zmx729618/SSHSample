package org.nercita.core.orm;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 主键id为递增的entity基类.
 * 
 * @author wangzz
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseIncEntity implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	
}
