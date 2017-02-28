package com.ktds.jgu.book.biz;

import java.util.List;

import com.ktds.jgu.book.dao.BookDao;
import com.ktds.jgu.book.dao.BookDaoImpl;
import com.ktds.jgu.book.vo.BookVO;

public class BookBizImpl implements BookBiz {

	private BookDao bookDao;
	
	public BookBizImpl() {
		this.bookDao = new BookDaoImpl();
	}
	
	@Override
	public boolean writeBook(BookVO bookVO) {
		return bookDao.insertBook(bookVO) > 0;
	}

	@Override
	public List<BookVO> listAllBooks() {
		return bookDao.selectAllBooks();
	}

	@Override
	public BookVO detailBook(int bookId) {
		return bookDao.deleteOneBook(bookId);
	}

	@Override
	public BookVO modifyBook(BookVO bookVO) {
		return bookDao.updateOneBook(bookVO);
	}

}
