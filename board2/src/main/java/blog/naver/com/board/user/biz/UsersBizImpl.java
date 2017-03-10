package blog.naver.com.board.user.biz;

import blog.naver.com.board.user.dao.UsersDao;
import blog.naver.com.board.user.dao.UsersDaoImpl;
import blog.naver.com.board.user.vo.UsersVO;

public class UsersBizImpl implements UsersBiz {

	private UsersDao userDao;
	
	public UsersBizImpl() {
		this.userDao = new UsersDaoImpl();
	}

	@Override
	public boolean signUpUser(UsersVO user) {
		return userDao.insertNewUser(user) > 0;
	}

	@Override
	public UsersVO signInUser(UsersVO user) {
		return userDao.selectOneUser(user);
	}
	
	

}
