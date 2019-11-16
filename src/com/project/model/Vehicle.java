package com.project.model;

public class Vehicle {

	private String vnum ;
	private String model;
	private String descr;
	private String cat;
	private String vAddDate;
	private int manYear;
	
	public Vehicle() {
        super();
    }
	
	public Vehicle(String vnum, String model, String descr, String cat, String vAddDate, int manYear) {
		super();
		this.vnum = vnum;
		this.model = model;
		this.descr = descr;
		this.cat = cat;
		this.vAddDate = vAddDate;
		this.manYear = manYear;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getvAddDate() {
		return vAddDate;
	}

	public void setvAddDate(String vAddDate) {
		this.vAddDate = vAddDate;
	}

	public int getManYear() {
		return manYear;
	}

	public void setManYear(int manYear) {
		this.manYear = manYear;
	}

	@Override
	public String toString() {
		return "Vehicle [vnum=" + vnum + ", model=" + model + ", descr=" + descr + ", cat=" + cat + ", vAddDate="
				+ vAddDate + ", manYear=" + manYear + "]";
	}
	
	
}
