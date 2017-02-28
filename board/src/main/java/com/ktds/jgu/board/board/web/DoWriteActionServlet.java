package com.ktds.jgu.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgu.board.board.biz.BoardBiz;
import com.ktds.jgu.board.board.biz.BoardBizImpl;
import com.ktds.jgu.board.board.vo.BoardVO;
import com.ktds.jgu.board.user.vo.UsersVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
       
    public DoWriteActionServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// 작성자의 ip 얻어 오기
		String ip = request.getRemoteAddr();
//		writer = writer + "(" + ip + ")";
		HttpSession session = request.getSession();
		// session container 에서 내것만 쏙 빼온것.
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		writer = user.getUserName() + "(" + request.getRemoteAddr() + ")";
		
		System.out.println(writer);
		System.out.println(subject);
		System.out.println(content);
		
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		
		if ( boardBiz.writeArticle(boardVO) ) {
			// list 페이지로 이동한다.
			response.sendRedirect("/board/list");  //브라우저의 URL을 바꿔주는 역할
		}
		else {
			// write 페이지로 이동한다.
			response.sendRedirect("/board/write");
		}
		
		boardBiz.writeArticle(boardVO);
		
	}

}
