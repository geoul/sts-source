package com.ktds.jgu.board.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.board.user.biz.UsersBiz;
import com.ktds.jgu.board.user.biz.UsersBizImpl;
import com.ktds.jgu.board.user.vo.UsersVO;

public class DoSignUpActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsersBiz usersBiz;
	
    public DoSignUpActionServlet() {
    	usersBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String joinDate = request.getParameter("joinDate");
		
		String ip = request.getRemoteAddr();
		userName = userName + "(" + ip + ")";
		
		System.out.println(userName);
		System.out.println(userPassword);
		System.out.println(joinDate);
		
		UsersVO usersVO = new UsersVO();
		usersVO.setUserName(userName);
		usersVO.setUserPassword(userPassword);
		usersVO.setJoinDate(joinDate);
		
		if ( usersBiz.signUpUser(usersVO) ) {
			response.sendRedirect("/user/login");
		}
		else {
			response.sendRedirect("/user/signUp");
		}
		
		usersBiz.signUpUser(usersVO);
	}

}
