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
import com.ktds.jgu.board.board.vo.BoardSearchVO;
import com.ktds.jgu.board.board.vo.BoardVO;
import com.ktds.jgu.common.web.pager.ClassicPageExplorer;
import com.ktds.jgu.common.web.pager.PageExplorer;
import com.ktds.jgu.common.web.pager.Pager;
import com.ktds.jgu.common.web.pager.PagerFactory;

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
		
		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		BoardSearchVO boardSearchVO = new BoardSearchVO();
		boardSearchVO.setPager(pager);
		List<BoardVO> articleList = boardBiz.getAllArticles(boardSearchVO);
		
		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
		String pages = pageExplorer.getPagingList("pageNo", "[@]", "PREV", "NEXT", "searchForm");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/list.jsp");
		// 보여주고 싶은 jsp파일을 적는다.  /는 Context Root 이다.
		
		request.setAttribute("articleList", articleList);
		request.setAttribute("count", pager.getTotalArticleCount());
		request.setAttribute("pager", pages);
		
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
