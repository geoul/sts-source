package com.ktds.jgu.book.biz;

import java.util.List;

import com.ktds.jgu.book.vo.BookVO;

public interface BookBiz {
	
	public boolean writeBook(BookVO bookVO);
	
	public List<BookVO> listAllBooks();
	
	public BookVO detailBook(int bookId);
	
	public BookVO modifyBook(BookVO bookVO);
	
}
