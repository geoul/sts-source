package com.melon.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class ViewAllAuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   private AuthorizationService authorizationService;
	   
	    public ViewAllAuthorizationServlet() {
	       authorizationService = new AuthorizationServiceImpl(); 
	    }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doPost(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      String pageNo = request.getParameter("pageNo"); //AJAX라 UI를 표현할 수가 없어서 그냥 Number
	      if (pageNo == null || pageNo.length() == 0) {
	         pageNo ="0";
	      }
	      
	      AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
	      authorizationSearchVO.getPager().setPageNumber(pageNo);
	      
	      
	      
	      List<AuthorizationVO> authorizationList =
	            authorizationService.getAllAuthorizations(authorizationSearchVO); 
	      
	      StringBuffer json = new StringBuffer();
	      json.append(" { ");
	      json.append(" \"status\": \"success\", ");
	      json.append(" \"size\": "+authorizationList.size()+", ");  //조회한 페이지는 몇개?
	      json.append(" \"pageNo\": "+pageNo+", ");               //조회한 페이지의 번호는 뭐?
	      json.append(" \"authorizations\": [ ");
	      
	      String authorizationData="";
	      for (AuthorizationVO authorizationVO : authorizationList) {
	         authorizationData +=
	               String.format("{\"authorizationId\": \"%s\", \"authorizationName\": \"%s\", \"parentAuthorizationId\": \"%s\"},", 
	               authorizationVO.getAuthorizationId()
	               ,authorizationVO.getAuthorizationName()
	               ,authorizationVO.getParentAuthorizationId() );
	      }
	      
	      if (authorizationData.length() > 0) {
	         authorizationData = authorizationData.substring(0, authorizationData.length()-1);
	         //자르는 것 반복하다보면 마지막은 콤마가 남는데 이거 에러 생기는 걸 방지한 코드이다 
	         //인덱스를 이용하여 글자를 배열 형식으로 생각하면 맨 마지막의 콤마는 마지막 인덱스이다 
	         //그래서 전체 길이에서 2를 뺀 마지막이 글자고 그다음은 쓸데 없는 콤마라 버려주는 방식 authorizationData.length()-1
	      }
	      json.append(authorizationData);
	      
	      json.append(" ] ");
	      json.append(" } ");
	      
	      
	      
	      PrintWriter writer = response.getWriter();
	      writer.write(json.toString());
	      writer.flush();
	      writer.close();
	   }


}
