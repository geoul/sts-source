package blog.naver.com.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.naver.com.board.board.biz.BoardBiz;
import blog.naver.com.board.board.biz.BoardBizImpl;
import blog.naver.com.board.board.vo.BoardSearchVO;
import blog.naver.com.board.board.vo.BoardVO;
import blog.naver.com.common.web.pager.ClassicPageExplorer;
import blog.naver.com.common.web.pager.PageExplorer;
import blog.naver.com.common.web.pager.Pager;
import blog.naver.com.common.web.pager.PagerFactory;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;

	public ListServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNo = request.getParameter("pageNo");
		Pager pager = PagerFactory.getPager(Pager.ORACLE);
		pager.setPageNumber(pageNo);
		
		BoardSearchVO searchVO = new BoardSearchVO();
		searchVO.setPager(pager);
		List<BoardVO> boardList = boardBiz.getAllAtricles(searchVO);
		
		PageExplorer pageExplorer = new ClassicPageExplorer(pager);
		String pages = pageExplorer.getPagingList("pageNo", "[@]", "PREV", "NEXT", "searchForm");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/list.jsp");
		request.setAttribute("boards", boardList); 
		request.setAttribute("count", pager.getTotalArticleCount());
		request.setAttribute("pager", pages);
		dispatcher.forward(request, response);
	}
}
