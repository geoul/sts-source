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

public class DoUserModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   private UserService userService;
	   
	   public DoUserModifyActionServlet() {
		   userService = new UserServiceImpl(); 
	   }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       response.sendError(404);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		   String pointParam = request.getParameter("userPoint");
		   String password = request.getParameter("userPassword");
		   String authId = request.getParameter("authorizationId");
		   String userId = request.getParameter("userId");
		   
		   int userPoint = 0;
		   try {
			   userPoint = Integer.parseInt(pointParam);
		   }
		   catch(NumberFormatException e) {
			   userPoint = 0;
		   }
		   
		   // null이면 null인채로, 데이터가 있으면 있는데로 넣어준다.
		   UserVO userVO = new UserVO();
		   userVO.setUserPoint(userPoint);
		   userVO.setUserPassword(password);
		   userVO.setAuthorizationId(authId);
		   userVO.setUserId(userId);
		   
		   boolean isSuccess = userService.updateUser(userVO);
		   
		   Map<String, Object> map = new HashMap<String, Object>();
		   map.put("status", isSuccess ? "success" : "fail");
		   
		   Gson gson = new Gson();
		   String json = gson.toJson(map);
		   
		   PrintWriter writer = response.getWriter();
		   writer.write(json);
		   writer.flush();
		   writer.close();
		  
	   }


}
