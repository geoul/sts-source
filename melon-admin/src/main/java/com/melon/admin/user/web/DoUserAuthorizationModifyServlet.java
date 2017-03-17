package com.melon.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserVO;

public class DoUserAuthorizationModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   private UserService userService;
	   
	   public DoUserAuthorizationModifyServlet() {
		   userService = new UserServiceImpl(); 
	   }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       doPost(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		   String toAuth = request.getParameter("toAuth");
		   String fromAuth = request.getParameter("fromAuth");
		   
		   String[] userIdArray = request.getParameterValues("userId");
		   if ( userIdArray != null ) {
			   userService.updateAllAuthorization(userIdArray, toAuth, fromAuth);
		   }
		   else {
			   userService.updateAllAuthorization(toAuth, fromAuth);
		   }
		   response.sendRedirect("/melon-admin/user/list");
		  
	   }


}
