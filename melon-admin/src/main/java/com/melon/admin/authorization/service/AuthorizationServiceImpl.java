package com.melon.admin.authorization.service;

import java.util.List;

import com.melon.admin.authorization.biz.AuthorizationBiz;
import com.melon.admin.authorization.biz.AuthorizationBizImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class AuthorizationServiceImpl implements AuthorizationService {

	private AuthorizationBiz authorizationBiz;
	
	public AuthorizationServiceImpl() {
		authorizationBiz = new AuthorizationBizImpl();
	}
	
	@Override
	public List<AuthorizationVO> getAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		return authorizationBiz.getAllAuthorizations(authorizationSearchVO);
	}

	@Override
	public boolean registNewAuthorization(AuthorizationVO authorizationVO) {
		return authorizationBiz.registNewAuthorization(authorizationVO);
	}

	@Override
	public boolean updateAuthorization(AuthorizationVO authorizationVO) {
		return authorizationBiz.updateAuthorizationInfo(authorizationVO);
	}

	@Override
	public boolean deleteAuthorization(String authorizationId) {
		return authorizationBiz.deleteOneAuthorization(authorizationId);
	}

}
