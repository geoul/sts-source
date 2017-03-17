package com.melon.admin.user.biz;

import java.util.List;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public interface UserBiz {

	public List<UserVO> getAllUsers(UserSearchVO userSearchVO);
	
	public UserVO getOneUser(UserVO userVO);
	
	public UserVO getOneUser(String userId);
	
	public boolean registNewUser(UserVO newUserVO);
	
	public boolean updateUser(UserVO userVO);
	
	public boolean deleteOneUser(String userId);
	
	public boolean updateAllAuthorization(String toAuth, String fromAuth);
	
//	public boolean managePoint(String userId, int point);
	
}
