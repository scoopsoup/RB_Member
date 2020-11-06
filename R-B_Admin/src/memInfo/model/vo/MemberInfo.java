package memInfo.model.vo;

import java.sql.Date;

public class MemberInfo {

	private String userName;
	private String userId;
	private String userBD;
	private String userABO;
	private String userPhone;
	private String userEmail;
	private String userAddr;
	
	public MemberInfo() {}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserBD() {
		return userBD;
	}

	public void setUserBD(String userBD) {
		this.userBD = userBD;
	}

	public String getUserABO() {
		return userABO;
	}

	public void setUserABO(String userABO) {
		this.userABO = userABO;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}


	@Override
	public String toString() {
		return "MemberInfo [userName=" + userName + ", userId=" + userId + ", userBD=" + userBD + ", userABO=" + userABO
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", userAddr=" + userAddr + "]";
	}

	
	
	
}
