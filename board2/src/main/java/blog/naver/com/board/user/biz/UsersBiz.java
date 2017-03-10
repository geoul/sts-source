package blog.naver.com.board.user.biz;

import blog.naver.com.board.user.vo.UsersVO;

public interface UsersBiz {

	public boolean signUpUser(UsersVO user);
	
	public UsersVO signInUser(UsersVO user);
	
}
