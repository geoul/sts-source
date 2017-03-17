package com.melon.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.melon.admin.authorization.biz.AuthorizationBiz;
import com.melon.admin.authorization.biz.AuthorizationBizImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.user.biz.UserBiz;
import com.melon.admin.user.biz.UserBizImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	private AuthorizationBiz authorizationBiz;
	
	public UserServiceImpl() {
		userBiz = new UserBizImpl();
		authorizationBiz = new AuthorizationBizImpl();
	}

	@Override
	public List<UserVO> getAllUsers(UserSearchVO userSearchVO) {
		return userBiz.getAllUsers(userSearchVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userBiz.getOneUser(userId);
	}

	@Override
	public boolean registNewUser(UserVO newUserVO) {
		return userBiz.registNewUser(newUserVO);
	}

	@Override
	public boolean updateUser(UserVO userVO) { // Servlet쪽에서 넘겨주는 VO 이기 때문에 권한 정보가 있다.
		UserVO tempUserVO = getOneUser(userVO.getUserId()); // 원본을 가지고 온다.
		
		// 권한 정보가 null이 아니고 권한정보를 수정했다면,
		if ( userVO.getAuthorizationId() != null && userVO.getAuthorizationId().length() > 0 ) {
			tempUserVO.setAuthorizationId( userVO.getAuthorizationId() ); // 원본에 수정된 부분만 덮어쓰고
		}
		// 데이터가 있다면 (수정이 됐다면)
		if ( userVO.getUserPoint() > 0 ) {
			tempUserVO.setUserPoint( userVO.getUserPoint() );
		}
		if ( userVO.getUserPassword() != null && userVO.getUserPassword().length() > 0 ) {
			tempUserVO.setUserPassword( userVO.getUserPassword() );
		}
		
		return userBiz.updateUser(tempUserVO); // 원본을 넘겨준다.
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userBiz.deleteOneUser(userId);
	}

	// 권한 목록을 가져오기 위해 추가함.
	@Override
	public Map<String, Object> getOneUserWithAuthorizations(String userId) {
		
		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(0);
		
		// 두개의 정보를 가져오기 위해서 Map 이 필요하다.
		Map<String, Object> user = new HashMap<String, Object>();
		// userVO
		user.put("user", userBiz.getOneUser(userId));
		// authorizationBiz(목록)
		user.put("authorizations", authorizationBiz.getAllAuthorizations(authorizationSearchVO));
		
		return user;
	}

	@Override
	public boolean updateAllAuthorization(String toAuth, String fromAuth) {
		return userBiz.updateAllAuthorization(toAuth, fromAuth);
	}

	@Override
	public boolean updateAllAuthorization(String[] userArray, String toAuth, String fromAuth) {
		UserVO userVO = null;
		for (String userId : userArray) {
			userVO = new UserVO();
			userVO.setUserId(userId);
			userVO.setAuthorizationId(toAuth);
			updateUser(userVO);
		}
		return true;
	}

	@Override
	public boolean updateAuthorization(String toAuth, String fromAuth) {
		return false;
	}
	
}
