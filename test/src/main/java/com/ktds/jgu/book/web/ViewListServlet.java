package com.ktds.jgu.book.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.book.biz.BookBiz;
import com.ktds.jgu.book.biz.BookBizImpl;
import com.ktds.jgu.book.vo.BookVO;

public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BookBiz bookBiz;
	
    public ViewListServlet() {
        bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookVO> bookList = new ArrayList<BookVO>();
		request.setAttribute("bookList", bookList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book/list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
