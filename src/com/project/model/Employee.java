package com.project.model;

public class Employee {

	private String eId ;
	private String fname;
	private String lname;
	private String address;
	private int telephone;
	private String dob;
	
	public Employee() {
        super();
    }
	
	public Employee(String eId, String fname, String lname, String address, int telephone, String dob) {
		super();
		this.eId = eId;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.telephone = telephone;
		this.dob = dob;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
}
