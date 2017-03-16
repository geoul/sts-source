package com.melon.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.common.web.pager.ClassicPageExplorer;
import com.melon.admin.common.web.pager.PageExplorer;
import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class ViewUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   private UserService userService;
	   
	    public ViewUserListServlet() {
	       userService = new UserServiceImpl(); 
	    }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doPost(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      String pageNo = request.getParameter("pageNo"); //AJAX라 UI를 표현할 수가 없어서 그냥 Number
	      if (pageNo == null || pageNo.length() == 0) {
	         pageNo ="0";
	      }
	      
	      UserSearchVO userSearchVO = new UserSearchVO();
	      userSearchVO.getPager().setPageNumber(pageNo);
	      
	      List<UserVO> userList = userService.getAllUsers(userSearchVO); 
	      
	      StringBuffer json = new StringBuffer();
	      json.append(" { ");
	      json.append(" \"status\": \"success\", ");
	      json.append(" \"size\": "+ userList.size() +", ");  //조회한 페이지는 몇개?
	      json.append(" \"pageNo\": "+ pageNo +", ");         //조회한 페이지의 번호는 뭐?
	      json.append(" [ ");
	      
	      String userData="";
	      for (UserVO userVO : userList) {
	         userData +=
	               String.format("{\"index\": \"%s\", \"userId\": \"%s\", \"userName\": \"%s\", \"userPoint\": \"%s\", \"authorizationId\": \"%s\"},",
	               userVO.getIndex()
	               , userVO.getUserId()
	               , userVO.getUserName()
	               , userVO.getUserPoint()
	               , userVO.getAuthorizationId() );
	      }
	      
	      if (userData.length() > 0) {
	         userData = userData.substring(0, userData.length()-1);
	         //자르는 것 반복하다보면 마지막은 콤마가 남는데 이거 에러 생기는 걸 방지한 코드이다 
	         //인덱스를 이용하여 글자를 배열 형식으로 생각하면 맨 마지막의 콤마는 마지막 인덱스이다 
	         //그래서 전체 길이에서 2를 뺀 마지막이 글자고 그다음은 쓸데 없는 콤마라 버려주는 방식 authorizationData.length()-1
	      }
	      json.append(userData);
	      
	      json.append(" ] ");
	      json.append(" } ");
	      
	      PrintWriter writer = response.getWriter();
	      writer.write(json.toString());
	      writer.flush();
	      writer.close();
	   }

}
