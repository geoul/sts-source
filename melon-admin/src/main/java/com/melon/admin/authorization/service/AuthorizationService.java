package com.melon.admin.authorization.service;

import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public interface AuthorizationService {

	// 권한 목록
	public List<AuthorizationVO> getAllAuthorizations(AuthorizationSearchVO authorizationSearchVO);
	
	// 권한 등록
	public boolean registNewAuthorization(AuthorizationVO authorizationVO);
	
	// 권한 수정
	public boolean updateAuthorization(AuthorizationVO authorizationVO);
	
	// 권한 삭제
	public boolean deleteAuthorization(String authorizationId);
	
}
