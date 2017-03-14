package com.melon.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserBiz userBiz;
	
    public ViewSignInServlet() {
        userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usersIdString = request.getParameter("userId");
		String userId = "";
		String userPassword = "";
		
		UserVO userVO = new UserVO();
		userVO = userBiz.loginUser(userVO);
		
		request.setAttribute("userVO", userVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
		dispatcher.forward(request, response);
	}

}
