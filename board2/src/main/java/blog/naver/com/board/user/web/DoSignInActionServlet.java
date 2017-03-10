package blog.naver.com.board.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.naver.com.board.user.biz.UsersBiz;
import blog.naver.com.board.user.biz.UsersBizImpl;
import blog.naver.com.board.user.vo.UsersVO;

public class DoSignInActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsersBiz userBiz;
	
    public DoSignInActionServlet() {
    	userBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UsersVO user = new UsersVO();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		
		user = userBiz.signInUser(user);
		
		if ( user == null ) {
			response.sendRedirect("/board2/user/login");
		}
		else {
			
			HttpSession session = request.getSession();
			session.setAttribute("_USER_", user);
			
			System.out.println("User ID : " + user.getUserId());
			System.out.println("User Name : " + user.getUserName());
			System.out.println("User Password : " + user.getUserPassword());
			System.out.println("Join Date : " + user.getJoinDate());
			response.sendRedirect("/board2/list");
		}
		
		
	}

}
