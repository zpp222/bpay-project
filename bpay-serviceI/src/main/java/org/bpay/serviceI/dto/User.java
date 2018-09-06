package org.bpay.serviceI.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class User implements Serializable{

	private static final long serialVersionUID = 6215798795710985677L;
	
	private String id;
	private String sex;
	private String phone;
	private String name;
	private String passwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
