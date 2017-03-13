package com.melon.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class DoSignInActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserBiz userBiz;
    public DoSignInActionServlet() {
        userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UserVO user = new UserVO();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		
		user = userBiz.loginUser(user);
		
		if ( user == null ) {
			response.sendRedirect("/melon/user/signIn");
		}
		else {
			response.sendRedirect("/melon/music/list");
		}
		
	}

}
