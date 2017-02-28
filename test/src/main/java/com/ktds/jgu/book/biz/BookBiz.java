package com.ktds.jgu.book.biz;

import java.util.List;

import com.ktds.jgu.book.vo.BookVO;

public interface BookBiz {
 
	public boolean writeBooks(BookVO bookVO);
	
	public List<BookVO> getAllBooks();
	
	public BookVO getOneBook();
	
	public BookVO removeBook();
	
}
