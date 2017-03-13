package com.melon.user.service;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	
	public UserServiceImpl() {
		userBiz = new UserBizImpl();
	}
	
	@Override
	public boolean registNewUser(UserVO newUserVO) {
		return userBiz.registNewUser(newUserVO);
	}

	@Override
	public UserVO loginUser(UserVO userVO) {
		return userBiz.loginUser(userVO);
	}

	@Override
	public boolean managePoint(String userId, int point) {
		return userBiz.managePoint(userId, point);
	}

	@Override
	public boolean isDuplicateUserId(String userId) {
		return userBiz.isDuplicateUserId(userId);
	}
	
}
