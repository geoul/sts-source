package com.melon.admin.user.vo;

public class UserVO {
	
	private String userId;
	private String userPassword;
	private String userName;
	private int userPoint;
	private String authorizationId;
	
	private AuthorizationVO authorizationVO;
	
	public AuthorizationVO getAuthorizationVO() {
		if ( authorizationVO == null ) {
			authorizationVO = new AuthorizationVO();
		}
		return authorizationVO;
	}

	public void setAuthorizationVO(AuthorizationVO authorizationVO) {
		this.authorizationVO = authorizationVO;
	}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

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
