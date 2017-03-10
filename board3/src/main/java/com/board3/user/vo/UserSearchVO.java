package com.board3.user.vo;

import com.board3.common.web.pager.Pager;

public class UserSearchVO {

	private Pager pager;
	
	private String userId;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
