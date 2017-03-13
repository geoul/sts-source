package com.melon.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService;
	
    public ViewSignUpServlet() {
        userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		
		if ( userId == null || userId.length() == 0 ) {
			response.sendRedirect("/melon/user/signUp?errorCode=0");
		}
		if ( userPassword == null || userPassword.length() == 0 ) {
			response.sendRedirect("/melon/user/signUp?errorCode=1");
		}
		if ( userName == null || userName.length() == 0 ) {
			response.sendRedirect("/melon/user/signUp?errorCode=2");
		}
		if ( userService.isDuplicateUserId(userId) ) {
			response.sendRedirect("/melon/user/signUp?errorCode=3");
		}
		
		UserVO newUserVO = new UserVO();
		newUserVO.setUserId(userId);
		newUserVO.setUserPassword(userPassword);
		newUserVO.setUserName(userName);
		request.setAttribute("user", newUserVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dispatcher.forward(request, response);
	}

}
