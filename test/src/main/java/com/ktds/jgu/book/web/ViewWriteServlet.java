package com.ktds.jgu.book.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.book.biz.BookBiz;
import com.ktds.jgu.book.biz.BookBizImpl;

public class ViewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BookBiz bookBiz;
	
    public ViewWriteServlet() {
        bookBiz = new BookBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book/write.jsp");
		dispatcher.forward(request, response);
	}

}
