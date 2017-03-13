package com.melon.user.biz;

import com.melon.user.vo.UserVO;

public interface UserBiz {

	public boolean registNewUser(UserVO newUserVO);
	
	public UserVO loginUser(UserVO userVO);
	
	public boolean managePoint(String userId, int point);
	
	// 아이디 중복 체크
	public boolean isDuplicateUserId(String userId);
	
}
