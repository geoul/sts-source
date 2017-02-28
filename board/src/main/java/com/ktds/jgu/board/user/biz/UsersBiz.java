package com.ktds.jgu.board.user.biz;

import java.util.List;

import com.ktds.jgu.board.user.vo.UsersVO;

public interface UsersBiz {

	public boolean signUpUser(UsersVO user);
	
	public boolean signInUser(UsersVO user);
	
	public List<UsersVO> getAllUsers();
	
	public UsersVO getOneUser(String userId, String userPassword);
	
	public UsersVO removeOneUser(String userId, String userPassword);
	
}
