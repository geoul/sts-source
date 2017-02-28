package com.ktds.jgu.book.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.jgu.book.biz.BookBiz;
import com.ktds.jgu.book.biz.BookBizImpl;
import com.ktds.jgu.book.vo.BookVO;

public class doWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookBiz bookBiz;
	
    public doWriteActionServlet() {
        bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookNm = request.getParameter("bookNm");
		String bookSubNm = request.getParameter("bookSubNm");
		String index = request.getParameter("index");
		
		String ip = request.getRemoteAddr();
		HttpSession session = request.getSession();
		
		System.out.println(bookNm);
		System.out.println(bookSubNm);
		System.out.println(index);
		
		BookVO bookVO = new BookVO();
		bookVO.setBookNm(bookNm);
		bookVO.setBookSubNm(bookSubNm);
		bookVO.setIndex(index);
		
		if (bookBiz.writeBook(bookVO)) {
			response.sendRedirect("/book/list");
		}
		else {
			response.sendRedirect("/book/write");
		}
		
		bookBiz.writeBook(bookVO);
		
	}

}
