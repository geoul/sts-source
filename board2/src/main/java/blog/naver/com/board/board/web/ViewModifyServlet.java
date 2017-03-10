package blog.naver.com.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.naver.com.board.board.biz.BoardBiz;
import blog.naver.com.board.board.biz.BoardBizImpl;
import blog.naver.com.board.board.vo.BoardVO;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;

	public ViewModifyServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		String boardIdString = request.getParameter("boardId");

		int boardId = 0;

		try {
			boardId = Integer.parseInt(boardIdString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("존재하지 않는 게시글, 잘못된 접근");
		}
		BoardVO boardVO = boardBiz.getOneArticle(boardId);
		
		String content = boardVO.getContent();
		content = content.replaceAll("<br/>", "\n");
		boardVO.setContent(content);
		
		request.setAttribute("board", boardVO);
		
		RequestDispatcher dp = request.getRequestDispatcher("/view/board/modify.jsp");
		dp.forward(request, response);
	}
}