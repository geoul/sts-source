package com.ktds.jgu.book.dao;

import java.util.List;

import com.ktds.jgu.book.vo.BookVO;

public interface BookDao {

	public List<BookVO> getAllBooks();
	
	public BookVO getOneBook(int bookId);
	
	public int writeBook(BookVO bookVO);
	
	public int modifyBook(BookVO bookVO);
	
	public int deleteBook(int bookId);
	
}
