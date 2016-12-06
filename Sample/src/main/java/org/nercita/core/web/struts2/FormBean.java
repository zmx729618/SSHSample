package org.nercita.core.web.struts2;

public class FormBean {
	
	private Long id;
	
	private Long[] radioGroup;
	
	private String queryName, queryValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long[] getRadioGroup() {
		return radioGroup;
	}

	public void setRadioGroup(Long[] radioGroup) {
		this.radioGroup = radioGroup;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}


}
