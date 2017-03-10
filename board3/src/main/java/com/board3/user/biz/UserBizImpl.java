package com.board3.user.biz;

import java.util.List;

import com.board3.user.dao.UserDao;
import com.board3.user.dao.UserDaoImpl;
import com.board3.user.vo.UserSearchVO;
import com.board3.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	
	private UserDao userDao;
	
	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean signUpUser(UserVO user) {
		return userDao.insertNewUser(user) > 0;
	}

	@Override
	public UserVO signInUser(UserVO user) {
		return userDao.selectOneUser(user);
	}
	
}
