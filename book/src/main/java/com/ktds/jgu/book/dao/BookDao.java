package com.ktds.jgu.book.dao;

import java.util.List;

import com.ktds.jgu.book.vo.BookVO;

public interface BookDao {

	public int insertBook(BookVO bookVO);
	
	public List<BookVO> selectAllBooks();
	
	public BookVO selectOneBook(int bookId);
	
	public BookVO deleteOneBook(int bookId);
	
	public BookVO updateOneBook(BookVO bookVO);
	
}
