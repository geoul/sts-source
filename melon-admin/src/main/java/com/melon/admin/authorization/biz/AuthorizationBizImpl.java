package com.melon.admin.authorization.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.dao.AuthorizationDao;
import com.melon.admin.authorization.dao.AuthorizationDaoImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class AuthorizationBizImpl implements AuthorizationBiz {

	private AuthorizationDao authorizationDao;
	
	public AuthorizationBizImpl() {
		authorizationDao = new AuthorizationDaoImpl();
	}
	
	@Override
	public boolean registNewAuthorization(AuthorizationVO authorizationVO) {
		String id = authorizationDao.generateNewAuthorizationId();
		authorizationVO.setAuthorizationId(id);
		return authorizationDao.insertNewAuthorization(authorizationVO) > 0;
	}

	@Override
	public List<AuthorizationVO> getAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		int totalCount = authorizationDao.selectAllAuthorizationCount(authorizationSearchVO);
		authorizationSearchVO.getPager().setTotalArticleCount(totalCount);
		
		if(totalCount == 0) {
			return new ArrayList<AuthorizationVO>();
		}
		return authorizationDao.selectAllAuthorizations(authorizationSearchVO);
	}

	@Override
	public boolean updateAuthorizationInfo(AuthorizationVO authorizationVO) {
		return authorizationDao.updateAuthorizationInfo(authorizationVO) > 0;
	}

	@Override
	public boolean deleteOneAuthorization(String authorizationId) {
		return authorizationDao.deleteOneAuthorization(authorizationId) > 0;
	}
	
}
