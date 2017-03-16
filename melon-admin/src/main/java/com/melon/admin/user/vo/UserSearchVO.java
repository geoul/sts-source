package com.melon.admin.user.vo;

import com.melon.admin.common.web.pager.Pager;
import com.melon.admin.common.web.pager.PagerFactory;

public class UserSearchVO {

	private Pager pager;
	
	// 파라미터 추가 (DB에 물어봐서 찾으려고(검색하기 위해))
//	private String userId;
//	private String authorizationId;
	
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getAuthorizationId() {
//		return authorizationId;
//	}
//
//	public void setAuthorizationId(String authorizationId) {
//		this.authorizationId = authorizationId;
//	}

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE, 50, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
