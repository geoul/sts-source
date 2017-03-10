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

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardBiz boardBiz;   
	
    public DoWriteActionServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); 
		/*
		 * HttpServletRequest : url 등 요청 정보를 갖고 있는 객체
		 * HttpServletResponse : Tomcat에 응답할 정보를 갖고 있는 객체
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  // 요청 정보들을 UTF-8로 변환해 다국어 지원하도록 한다.
		
		BoardVO boardVO = new BoardVO();
//		String ip = request.getParameter("writers") + "(" + request.getRemoteAddr() + ")";
		
		HttpSession session = request.getSession();
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		boardVO.setWriter(user.getUserId());
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setSubject(request.getParameter("subjects"));
		String content = request.getParameter("contents");
		
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");
		boardVO.setContent(content);
		
		if(boardBiz.writeArticle(boardVO)){
			// list 페이지로 이동
			response.sendRedirect("/board2/list");
		}else{
			// write 페이지로 이동
			response.sendRedirect("/board2/write");
		}
	}
}
