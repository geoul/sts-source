package com.ktds.jgu.book.vo;

import com.ktds.jgu.dao.support.annotation.Types;

public class BookVO {
	@Types
	private int bookId;
	
	@Types
	private String bookNm;
	
	@Types
	private String bookSubNm;
	
	@Types
	private String index;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookNm() {
		return bookNm;
	}

	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
	}

	public String getBookSubNm() {
		return bookSubNm;
	}

	public void setBookSubNm(String bookSubNm) {
		this.bookSubNm = bookSubNm;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
