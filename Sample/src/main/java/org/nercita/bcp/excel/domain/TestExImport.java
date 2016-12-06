package org.nercita.bcp.excel.domain;

import javax.persistence.Entity;

import org.nercita.core.orm.BaseUidEntity;


@Entity(name="TestExImport")
public class TestExImport extends BaseUidEntity {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6016942639452687266L;
	
	private String name01;	
	private String name02;
	private String name03;
	private String name04;
	private String name05;
	private String name06;
	
	public String getName01() {
		return name01;
	}
	public void setName01(String name01) {
		this.name01 = name01;
	}
	public String getName02() {
		return name02;
	}
	public void setName02(String name02) {
		this.name02 = name02;
	}
	public String getName03() {
		return name03;
	}
	public void setName03(String name03) {
		this.name03 = name03;
	}
	public String getName04() {
		return name04;
	}
	public void setName04(String name04) {
		this.name04 = name04;
	}
	public String getName05() {
		return name05;
	}
	public void setName05(String name05) {
		this.name05 = name05;
	}
	public String getName06() {
		return name06;
	}
	public void setName06(String name06) {
		this.name06 = name06;
	}
	
}
