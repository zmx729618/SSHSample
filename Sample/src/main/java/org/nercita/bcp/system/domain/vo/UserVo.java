package org.nercita.bcp.system.domain.vo;
/**
 * user id name vo
 * @author zhangwenchao
 *
 */
public class UserVo {
	
	//用户id
	private String id;
	
	//用户名称
	private String name;
	
	//用户名称拼音
	private String pinyin;
	
	//用户名称简拼
	private String jianpin;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getJianpin() {
		return jianpin;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

}
