package blog.naver.com.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.naver.com.board.board.biz.BoardBiz;
import blog.naver.com.board.board.biz.BoardBizImpl;
import blog.naver.com.board.board.vo.BoardVO;
import blog.naver.com.board.user.vo.UsersVO;

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardBiz boardBiz;   
	
    public DoModifyActionServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		
		BoardVO boardVO = new BoardVO();
		
		String boardIdString = request.getParameter("boardId");
		int boardId = 0;
		
		try{
			boardId = Integer.parseInt(boardIdString);
		}catch(NumberFormatException e){
			System.out.println("NumberFormat Exception");
			System.out.println(e.getMessage());
		}
		
		HttpSession session = request.getSession();
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		boardVO.setWriter(user.getUserId());
		
		String content = request.getParameter("contents");
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "<br/>");
		boardVO.setContent(content);
		boardVO.setSubject(request.getParameter("subjects"));
		boardVO.setBoardId(boardId);
		
		if(boardBiz.updateArticle(boardVO)){
			response.sendRedirect("/board2/list");
		}else{
			response.sendRedirect("/board2/write");
		}
		
	}

}
