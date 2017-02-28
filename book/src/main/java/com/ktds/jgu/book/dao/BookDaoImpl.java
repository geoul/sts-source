package com.ktds.jgu.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.jgu.book.vo.BookVO;
import com.ktds.jgu.dao.support.JDBCDaoSupport;
import com.ktds.jgu.dao.support.QueryHandler;
import com.ktds.jgu.dao.support.annotation.BindData;

public class BookDaoImpl extends JDBCDaoSupport implements BookDao {

	@Override
	public int insertBook(BookVO bookVO) {
		return update (new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" INSERT	INTO BOOK( ");
				query.append(" 				BOOK_ID ");
				query.append(" 				, BOOK_NM ");
				query.append(" 				, BOOK_SUB_NM ");
				query.append(" 				, IDX ");
				query.append(" 					) ");
				query.append(" VALUES			( ");
				query.append(" 				BOOK_ID_SEQ.NEXTVAL ");
				query.append(" 				, ? ");
				query.append(" 				, ? ");
				query.append(" 				, ? ");
				query.append(" 					) ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, bookVO.getBookNm());
				stmt.setString(2, bookVO.getBookSubNm());
				stmt.setString(3, bookVO.getIndex());
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
			
		});
	}

	@Override
	public List<BookVO> selectAllBooks() {
		return selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT		B.BOOK_ID ");
				query.append(" 				, B.BOOK_NM ");
				query.append(" 				, B.BOOK_SUB_NM ");
				query.append(" 				, B.IDX ");
				query.append(" FROM			BOOK B ");
				query.append(" WHERE		ORDER BY DESC ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				
			}

			@Override
			public Object getData(ResultSet rs) {
				BookVO bookVO = new BookVO();
				BindData.bindData(rs, bookVO);
				
				return bookVO;
			}
			
		});
	}

	@Override
	public BookVO selectOneBook(int bookId) {
		return (BookVO) selectOne (new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT		B.BOOK_ID ");
				query.append("				, B.BOOK_NM ");
				query.append("				, B.BOOK_SUB_NM ");
				query.append("				, B.IDX ");
				query.append(" FROM 		BOOK B ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, bookId);
			}

			@Override
			public Object getData(ResultSet rs) {
				BookVO bookVO = new BookVO();
				BindData.bindData(rs, bookVO);
				
				return bookVO;
			}
			
		});
	}

	@Override
	public BookVO deleteOneBook(int bookId) {
		return (BookVO) selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE			");
				query.append(" FROM			BOOK ");
				query.append(" WHERE		BOOK_ID = ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, bookId );
			}

			@Override
			public Object getData(ResultSet rs) {
				BookVO bookVO = new BookVO();
				BindData.bindData(rs, bookVO);
				
				return bookVO;
			}
			
		});
	}

	@Override
	public BookVO updateOneBook(BookVO bookVO) {
		return (BookVO) selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" UPDATE 		BOOK ");
				query.append(" SET 			BOOK_NM = ? ");
				query.append("  			, BOOK_SUB_NM = ? ");
				query.append("  			, IDX = ? ");
				query.append(" WHERE 		BOOK_ID = ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, bookVO.getBookNm());
				stmt.setString(2, bookVO.getBookSubNm());
				stmt.setString(3, bookVO.getIndex());
				stmt.setInt(4, bookVO.getBookId());
			}

			@Override
			public Object getData(ResultSet rs) {
				BookVO bookVO = new BookVO();
				BindData.bindData(rs, bookVO);
				
				return bookVO;
			}
			
		});
	}

}
