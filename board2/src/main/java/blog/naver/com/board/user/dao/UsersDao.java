package blog.naver.com.board.user.dao;

import blog.naver.com.board.user.vo.UsersVO;

public interface UsersDao {

	public int insertNewUser(UsersVO user);
	
	public UsersVO selectOneUser(UsersVO user);
	
}
