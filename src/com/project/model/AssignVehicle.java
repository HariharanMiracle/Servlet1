package com.project.model;

public class AssignVehicle {

	private String eId;
	private String vnum;
	private String purpose;
	private String sdate;
	private String edate;
	private int shrs;
	private int smins;
	private int ehrs;
	private int emins;
	
	public AssignVehicle() {
        super();
    }

	public AssignVehicle(String vnum, String eId, String purpose, String sdate, String edate, int shrs, int smins, int ehrs,
			int emins) {
		super();
		this.eId = eId;
		this.vnum = vnum;
		this.purpose = purpose;
		this.sdate = sdate;
		this.edate = edate;
		this.shrs = shrs;
		this.smins = smins;
		this.ehrs = ehrs;
		this.emins = emins;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getShrs() {
		return shrs;
	}

	public void setShrs(int shrs) {
		this.shrs = shrs;
	}

	public int getSmins() {
		return smins;
	}

	public void setSmins(int smins) {
		this.smins = smins;
	}

	public int getEhrs() {
		return ehrs;
	}

	public void setEhrs(int ehrs) {
		this.ehrs = ehrs;
	}

	public int getEmins() {
		return emins;
	}

	public void setEmins(int emins) {
		this.emins = emins;
	}

	@Override
	public String toString() {
		return "AssignVehicle [eId=" + eId + ", vnum=" + vnum + ", purpose=" + purpose + ", sdate=" + sdate + ", edate="
				+ edate + ", shrs=" + shrs + ", smins=" + smins + ", ehrs=" + ehrs + ", emins=" + emins + "]";
	}
	
}
