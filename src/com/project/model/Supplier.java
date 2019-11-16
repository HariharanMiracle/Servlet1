package com.project.model;

public class Supplier {
	private String supId;
	private String supName;
	private int cotactNum;
	private String email;
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(String supId, String supName, int cotactNum, String email) {
		super();
		this.supId = supId;
		this.supName = supName;
		this.cotactNum = cotactNum;
		this.email = email;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public int getCotactNum() {
		return cotactNum;
	}

	public void setCotactNum(int cotactNum) {
		this.cotactNum = cotactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
