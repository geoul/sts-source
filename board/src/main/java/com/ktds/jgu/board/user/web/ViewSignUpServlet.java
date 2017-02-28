package com.ktds.jgu.board.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.board.user.biz.UsersBiz;
import com.ktds.jgu.board.user.biz.UsersBizImpl;
import com.ktds.jgu.board.user.vo.UsersVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsersBiz usersBiz;
	
    public ViewSignUpServlet() {
    	usersBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usersIdString = request.getParameter("userId");
		String userId = "";
		String userPassword = "";
		
//		UsersVO usersVO = usersBiz.getOneUser(userId, userPassword);
//		
//		String content = usersVO.getUserName();
//		content = content.replaceAll("<br/>", "\n");
//		usersVO.setContent(content);
//		
//		request.setAttribute("user", usersVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user/signUp.jsp");
		dispatcher.forward(request, response);
		
	}

}
