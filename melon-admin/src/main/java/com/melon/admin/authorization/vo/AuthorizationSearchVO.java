package com.melon.admin.authorization.vo;

import com.melon.admin.common.web.pager.Pager;
import com.melon.admin.common.web.pager.PagerFactory;

public class AuthorizationSearchVO {

	private Pager pager;

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE, 1000, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
