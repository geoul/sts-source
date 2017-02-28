package com.ktds.jgu.book.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.book.biz.BookBiz;
import com.ktds.jgu.book.biz.BookBizImpl;
import com.ktds.jgu.book.vo.BookVO;

public class doDetailActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookBiz bookBiz;
	
    public doDetailActionServlet() {
        bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookIdString = request.getParameter("bookId");
		System.out.println(bookIdString);
		
		int bookId = 0;
		try {
			bookId = Integer.parseInt(bookIdString);
		}
		catch(NumberFormatException e) {
			throw new RuntimeException("존재하지 않는 책입니다.");
		}
		
		BookVO book = bookBiz.detailBook(bookId);
		
		request.setAttribute("book", book);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book/detail.jsp");
		dispatcher.forward(request, response);
	}

}
