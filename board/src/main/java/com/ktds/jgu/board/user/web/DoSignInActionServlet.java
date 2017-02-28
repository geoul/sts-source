package com.ktds.jgu.board.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgu.board.user.biz.UsersBiz;
import com.ktds.jgu.board.user.biz.UsersBizImpl;
import com.ktds.jgu.board.user.vo.UsersVO;

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
		
		user = userBiz.getOneUser(userId, userPassword);
		
		if ( user == null ) {
			response.sendRedirect("/board/user/login");
		}
		else {
			// Session Container를 가져오는 것임.
			HttpSession session = request.getSession(true);
			session.invalidate();
			// Session Container에 값을 집어넣는 것임.
			// Session의 Key는 문장 끝은 언더바로 감싼다.(규칙)
			session.setAttribute("_USER_", user);
			
			System.out.println("User ID : " + user.getUserId());
			System.out.println("User Name : " + user.getUserName());
			System.out.println("User Password : " + user.getUserPassword());
			System.out.println("User JoinDate : " + user.getJoinDate());
			response.sendRedirect("/board/list");
		}
		
		
	}

}
