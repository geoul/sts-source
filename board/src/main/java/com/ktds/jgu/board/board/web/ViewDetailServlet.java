package com.ktds.jgu.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.board.board.biz.BoardBiz;
import com.ktds.jgu.board.board.biz.BoardBizImpl;
import com.ktds.jgu.board.board.vo.BoardVO;

public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BoardBiz boardBiz;
	
    public ViewDetailServlet() {
    	// 내가 찾고자 하는 데이터가 DB에 있으니까 Biz가 필요하다
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답 쪽에 영어가 아닌 데이터가 들어갈 수 있으니 글자가 깨지지 않도록 인코딩을 변경해준다
		
		String boardIdString = request.getParameter("boardId");
		System.out.println(boardIdString);
		
		int boardId = 0;
		try {
			boardId = Integer.parseInt(boardIdString);
			//NumberFormatException이 발생할 수 있으므로 try ~ catch를 걸어준다.
		}
		catch(NumberFormatException e) {
//			throw new RuntimeException("잘못된 접근입니다.");
//			위에 처럼 하면 에러메세지가 너무 광범위 하니까 아래 처럼 바꿔준다.
			throw new RuntimeException("존재하지 않는 게시물 입니다.");
		}
		
		// 데이터를 가져와서 request에 넣어주고 페이지를 보여준다.
		BoardVO board = boardBiz.getOneArticles(boardId);
		
		request.setAttribute("board", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/detail.jsp");
		dispatcher.forward(request, response);
	}

}
