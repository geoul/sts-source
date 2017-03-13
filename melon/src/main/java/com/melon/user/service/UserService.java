package com.melon.user.service;

import com.melon.user.vo.UserVO;

public interface UserService {

	public boolean registNewUser(UserVO userVO);
	
	public UserVO loginUser(UserVO userVO);
	
	public boolean managePoint(String userId, int point);
	// 아이디 중복 체크
	public boolean isDuplicateUserId(String userId);
		
}
