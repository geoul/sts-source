package com.melon.admin.user.service;

import java.util.List;
import java.util.Map;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public interface UserService {

	public List<UserVO> getAllUsers(UserSearchVO userSearchVO);
	
	public UserVO getOneUser(UserVO userVO);
	
	public UserVO getOneUser(String userId);
	
	// 권한 목록을 가져오기 위해 추가함.
	public Map<String, Object> getOneUserWithAuthorizations(String userId);
	
	public boolean registNewUser(UserVO newUserVO);
	
	public boolean updateUser(UserVO userVO);
	
	public boolean deleteOneUser(String userId);
	
}
