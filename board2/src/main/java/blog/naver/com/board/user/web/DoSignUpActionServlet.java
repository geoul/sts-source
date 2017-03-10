package blog.naver.com.board.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.naver.com.board.user.biz.UsersBiz;
import blog.naver.com.board.user.biz.UsersBizImpl;
import blog.naver.com.board.user.vo.UsersVO;

public class DoSignUpActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsersBiz userBiz;
	
    public DoSignUpActionServlet() {
    	userBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		
		UsersVO user = new UsersVO();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		
		if ( userBiz.signUpUser(user) ) {
			response.sendRedirect("/board2/user/login");
		}
		else {
			response.sendRedirect("/board2/user/signUp");
		}
		
	}

}
