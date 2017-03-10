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

public class ViewDetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;
	
    public ViewDetailServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String boardIdString = request.getParameter("boardId");
		
		int boardId = 0;
		try{ // 웹 예외처리 기본 방식
			boardId = Integer.parseInt(boardIdString);
		}
		catch(NumberFormatException e){
			throw new RuntimeException("존재하지 않는 게시물");
		}
		
		BoardVO board = boardBiz.getOneArticle(boardId);
		
		request.setAttribute("boards", board); // 데이터 가져와서 요청에 넣어주고 보여준다.
		request.setAttribute("title", "BOARD");
		
		RequestDispatcher dp = request.getRequestDispatcher("/view/board/detail.jsp");
		dp.forward(request, response);
	}

}
