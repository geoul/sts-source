package com.ktds.jgu.board.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgu.board.user.biz.UsersBiz;
import com.ktds.jgu.board.user.biz.UsersBizImpl;

public class DoLogoutActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UsersBiz usersBiz;
	
    public DoLogoutActionServlet() {
        usersBiz = new UsersBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/user/signIn");
		dispatcher.forward(request, response);
		HttpSession session = request.getSession();
		session.invalidate();
	}

}
