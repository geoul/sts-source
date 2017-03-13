package com.melon.user.dao;

import com.melon.user.vo.UserVO;

public interface UserDao {

	public int insertNewUser(UserVO userVO);
	
	public UserVO selectOneUser(UserVO userVO);
	
	public int updatePoint(String userId, int point);
	
	// 이 아이디가 있는지 없는지만 체크 (아이디 중복 체크)
	public int selectCountByUserId(String userId);
}
