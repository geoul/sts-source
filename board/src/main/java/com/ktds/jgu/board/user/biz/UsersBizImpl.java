package com.ktds.jgu.board.user.biz;

import java.util.List;

import com.ktds.jgu.board.user.dao.UsersDao;
import com.ktds.jgu.board.user.dao.UsersDaoImpl;
import com.ktds.jgu.board.user.vo.UsersVO;

public class UsersBizImpl implements UsersBiz {

	private UsersDao usersDao;
	
	public UsersBizImpl() {
		usersDao = new UsersDaoImpl();
	}
	
	@Override
	public boolean signUpUser(UsersVO user) {
		return usersDao.insertNewUser(user) > 0;
	}

	@Override
	public List<UsersVO> getAllUsers() {
		return usersDao.selectAllUsers();
	}

	@Override
	public UsersVO getOneUser(String userId, String userPassword) {
		return usersDao.selectOneUser(userId, userPassword);
	}

	@Override
	public UsersVO removeOneUser(String userId, String userPassword) {
		return usersDao.removeOneUser(userId, userPassword);
	}

	@Override
	public boolean signInUser(UsersVO user) {
		return usersDao.insertNewUser(user) > 0;
	}

}
