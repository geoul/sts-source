package com.ktds.jgu.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.board.board.biz.BoardBiz;
import com.ktds.jgu.board.board.biz.BoardBizImpl;
import com.ktds.jgu.board.board.vo.BoardVO;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz; // 멤버변수 정의
       
    public ListServlet() {
//        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setAttribute("name", "장거울"); // Key, Value (Map 형식)
//		request.setAttribute("age", 26);
//		request.setAttribute("hobby", "드라마 보기");
//		// request 정보를 /list.jsp페이지와 함께 보내줌.
		
		List<BoardVO> articleList = boardBiz.getAllArticles();
		request.setAttribute("articleList", articleList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/list.jsp");
		// 보여주고 싶은 jsp파일을 적는다.  /는 Context Root 이다.
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
