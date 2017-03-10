package blog.naver.com.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.naver.com.board.board.biz.BoardBiz;
import blog.naver.com.board.board.biz.BoardBizImpl;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;
	
    public DoDeleteActionServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
				
		int boardId = 0;
		
		try{
			boardId = Integer.parseInt(request.getParameter("boardId"));
		} catch(NumberFormatException e){
			System.out.println("boardId가 숫자가 아니다.");
			System.out.println(e.getMessage());
		} // Exception 발생하면 catch 블록만 수행 후 종료. return;조차 필요 없다.

		if(boardBiz.deleteArticle(boardId)){
			// list 페이지로 이동
			response.sendRedirect("/board2/list");
		}else{
			// write 페이지로 이동
			response.sendRedirect("/board2/write");
		}
		
	}

}
