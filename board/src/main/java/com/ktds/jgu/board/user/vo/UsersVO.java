package com.ktds.jgu.board.user.vo;

import com.ktds.jgu.dao.support.annotation.Types;

public class UsersVO {

	// 테이블 만들 때 축약해서 Naming을 해줬다면 VO에서는 풀어서 써준다.
	@Types(alias="USR_ID")
	private String userId;
	
	@Types(alias="USR_NM")
	private String userName;
	
	@Types(alias="USR_PWD")
	private String userPassword;
	
	@Types(alias="JOIN_DT")
	private String joinDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
