package com.melon.admin.authorization.dao;

import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public interface AuthorizationDao {

	public String generateNewAuthorizationId();
	
	public int insertNewAuthorization(AuthorizationVO authorizationVO);
	
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO);
	
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO);
	
	public int updateAuthorizationInfo(AuthorizationVO authorizationVO);
	
	public int deleteOneAuthorization(String authorizationId);
	
}
