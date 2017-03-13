package com.melon.user.vo;

public class UserVO {

	private String userId;
	private String userPassword;
	private String userName;
	private int userPoint;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUserPoint() {
		return userPoint;
	}
	
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
}
