package reservation.personal.model.vo;

import java.sql.Date;

public class InterviewReservation {
	
	private int chkCode;
	private String crBody;
	private String crIn;
	private String crOut;
	private String crDrg;
	private String crHos;
	private String crRes;
	private Date crDate;
	private String userId;
	private String hpNumber;

	public InterviewReservation() {
		
	}

	public int getChkCode() {
		return chkCode;
	}

	public void setChkCode(int chkCode) {
		this.chkCode = chkCode;
	}

	public String getCrBody() {
		return crBody;
	}

	public void setCrBody(String crBody) {
		this.crBody = crBody;
	}

	public String getCrIn() {
		return crIn;
	}

	public void setCrIn(String crIn) {
		this.crIn = crIn;
	}

	public String getCrOut() {
		return crOut;
	}

	public void setCrOut(String crOut) {
		this.crOut = crOut;
	}

	public String getCrDrg() {
		return crDrg;
	}

	public void setCrDrg(String crDrg) {
		this.crDrg = crDrg;
	}

	public String getCrHos() {
		return crHos;
	}

	public void setCrHos(String crHos) {
		this.crHos = crHos;
	}

	public String getCrRes() {
		return crRes;
	}

	public void setCrRes(String crRes) {
		this.crRes = crRes;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHpNumber() {
		return hpNumber;
	}

	public void setHpNumber(String hpNumber) {
		this.hpNumber = hpNumber;
	}

	@Override
	public String toString() {
		return "InterviewReservation [chkCode=" + chkCode + ", crBody=" + crBody + ", crIn=" + crIn + ", crOut=" + crOut
				+ ", crDrg=" + crDrg + ", crHos=" + crHos + ", crRes=" + crRes + ", crDate=" + crDate + ", userId="
				+ userId + ", hpNumber=" + hpNumber + "]";
	}
	
	
}
