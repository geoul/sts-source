package com.melon.admin.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class DoAuthorizationModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AuthorizationService authorizationService;
	
    public DoAuthorizationModifyActionServlet() {
    	authorizationService = new AuthorizationServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String authorizationId = request.getParameter("authorizationId");
		String authorizationName = request.getParameter("authorizationName");
		String parentAuthorizationId = request.getParameter("parentAuthorizationId");
		
		AuthorizationVO authorizationVO = new AuthorizationVO();
		authorizationVO.setAuthorizationId(authorizationId);
		authorizationVO.setAuthorizationName(authorizationName);
		authorizationVO.setParentAuthorizationId(parentAuthorizationId);
		
		// 수정한 결과를 isSuccess 변수에 담는다.
		boolean isSuccess = authorizationService.updateAuthorization(authorizationVO);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", isSuccess ?  "success" : "fail" );
		map.put("authorizationId", authorizationId);
		
		// map을 json으로 바꿈.
		Gson gson = new Gson();
		String json = gson.toJson(map);
		/*
		 * {
		 * 		status: 'success'
		 * 		authorizationId: 'at-201231232-13213123'
		 * }
		 */
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}
}
