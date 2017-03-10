package com.board3.user.dao;

import java.util.List;

import com.board3.user.vo.UserSearchVO;
import com.board3.user.vo.UserVO;

public interface UserDao {

	public int insertNewUser(UserVO user);
	
	public UserVO selectOneUser(UserVO user);
	
}
