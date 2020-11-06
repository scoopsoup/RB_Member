package member.model.vo;

import java.sql.Date;

public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String userBD;
	private String userGender;
	private String userPhone;
	private String userAddr;
	private String userEmail;
	private String userABO;

	public Member() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBD() {
		return userBD;
	}

	public void setUserBD(String userBD) {
		this.userBD = userBD;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserABO() {
		return userABO;
	}

	public void setUserABO(String userABO) {
		this.userABO = userABO;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userBD=" + userBD
				+ ", userGender=" + userGender + ", userPhone=" + userPhone + ", userAddr=" + userAddr + ", userEmail="
				+ userEmail + ", userABO=" + userABO + "]";
	}
	
	

}


