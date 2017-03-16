package com.melon.admin.user.biz;

import java.util.List;

import com.melon.admin.user.dao.UserDao;
import com.melon.admin.user.dao.UserDaoImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	
	private UserDao userDao;

	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public List<UserVO> getAllUsers(UserSearchVO userSearchVO) {
		return userDao.selectAllUser(userSearchVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userDao.selectOneUser(userId);
	}

	@Override
	public boolean registNewUser(UserVO newUserVO) {
//		boolean isSuccess = userDao.insertNewUser(newUserVO) > 0;
//		if ( isSuccess ) {
//			isSuccess = managePoint(newUserVO.getUserId(), 300);
//		}
		
		return userDao.insertNewUser(newUserVO) > 0;
	}
	
//	@Override
//	public boolean managePoint(String userId, int point) {
//		return userDao.updatePoint(userId, point) > 0;
//	}
	

	@Override
	public boolean updateUser(UserVO userVO) {
		return userDao.updateUserInfo(userVO) > 0;
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userDao.deleteOneUser(userId) > 0;
	}

}
