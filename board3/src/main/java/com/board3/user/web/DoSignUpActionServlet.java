package com.board3.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board3.user.biz.UserBiz;
import com.board3.user.biz.UserBizImpl;
import com.board3.user.vo.UserVO;

public class DoSignUpActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserBiz userBiz;
       
    public DoSignUpActionServlet() {
        userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		
		UserVO user = new UserVO();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		user.setUserName(userName);
		
		if ( userBiz.signUpUser(user) ) {
			response.sendRedirect("/board3/user/login");
		}
		else {
			response.sendRedirect("/board3/user/signUp");
		}
	}

}
