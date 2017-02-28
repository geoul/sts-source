package com.ktds.jgu.board.user.dao;

import java.util.List;

import com.ktds.jgu.board.user.vo.UsersVO;

public interface UsersDao {

	public int insertNewUser(UsersVO user);
	
	public List<UsersVO> selectAllUsers();
	
	public UsersVO selectOneUser(String userId, String userPassword);
	
	public UsersVO removeOneUser(String userId, String userPassword);
	
}
