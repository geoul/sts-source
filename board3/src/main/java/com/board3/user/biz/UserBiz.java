package com.board3.user.biz;

import java.util.List;

import com.board3.user.vo.UserSearchVO;
import com.board3.user.vo.UserVO;

public interface UserBiz {
	
	public boolean signUpUser(UserVO user);
	
	public UserVO signInUser(UserVO user);
	
}
