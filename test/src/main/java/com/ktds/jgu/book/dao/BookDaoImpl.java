package com.ktds.jgu.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.jgu.book.vo.BookVO;

public class BookDaoImpl implements BookDao {

	@Override
	public List<BookVO> getAllBooks() {
//		1. OracleDriver loading
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("오라클 드라이버 생성 실패, 시스템을 종료합니다.");
			return null;
		}
		
//		2. JDBC Instance 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
//		3. Oracle Instance에 연결
		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "BOOK");
			
//			4. 쿼리 만들기
			String query = " SELECT " + " BOOK_ID, BOOK_NM, BOOK_SUB_NM, IDX " 
							+ " FROM BOOK ";
			
//			5. 쿼리 실행하기
			stmt = conn.prepareStatement(query);
			
//			6. 쿼리의 실행결과를 얻어오기
			rs = stmt.executeQuery();
			
//			6-1. 쿼리의 실행결과를 List객체에 할당한다.
			BookVO bookVO = null;
			List<BookVO> bookList = new ArrayList<BookVO>();
			
			while(rs.next()) {
//				6-2. Row의 정보를 VO에 셋팅한다.
				bookVO = new BookVO();
				bookVO.setBookId(rs.getInt("BOOK_ID"));
				bookVO.setBookName(rs.getString("BOOK_NM"));
				bookVO.setBookSubName(rs.getString("BOOK_SUB_NM"));
				bookVO.setBookIndex(rs.getString("IDX"));
				
//				6-3. List 객체에 VO를 add한다.
				bookList.add(bookVO);
			}
			return bookList;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Oracle Instance에 연결하지 못했습니다. 시스템을 종료합니다.");
			return null;
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
			}
		}
	}

	@Override
	public BookVO getOneBook(int bookId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버를 찾지 못했습니다. 시스템을 종료합니다.");
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "BOOK");
			
			String query = " SELECT " + " BOOK_ID, BOOK_NM, BOOK_SUB_NM, IDX " 
							+ " FROM BOOK " + " WHERE BOOK_ID = ? ";

			stmt = conn.prepareStatement(query);
			
			rs = stmt.executeQuery();
			
			BookVO bookVO = null;
			List<BookVO> bookList = new ArrayList<BookVO>();
			
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setBookId(rs.getInt("BOOK_ID"));
				bookVO.setBookName(rs.getString("BOOK_NM"));
				bookVO.setBookSubName(rs.getString("BOOK_SUB_NM"));
				bookVO.setBookIndex(rs.getString("IDX"));
				
				bookList.add(bookVO);
			}
			return (BookVO) bookList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Oracle Instance에 연결하지 못했습니다. 시스템을 종료합니다.");
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
			}
		}
		return null;
	}

	@Override
	public int writeBook(BookVO bookVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Oracle Driver를 찾지 못했습니다. 시스템을 종료합니다.");
			return 0;
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "BOOK");
			
			String query = " INSERT INTO ( " + " BOOK_ID, BOOK_NM, BOOK_SUB_NM, IDX " + " ) " 
							+ " VALUES ( " + " BOOK_ID_SEQ.NEXTVAL " + " ?, ?, ? " + " ) ";
			
			stmt = conn.prepareStatement(query);
			
			rs = stmt.executeQuery();
			
			int bookId = 0;
			List<BookVO> bookList = new ArrayList<BookVO>();
			
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setBookId(rs.getInt("BOOK_ID"));
			}
			return bookId;
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Oracle driver에 연결하지 못했습니다. 시스템을 종료합니다.");
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			}
			catch (SQLException e) {
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int modifyBook(BookVO bookVO) {
		return 0;
	}

	@Override
	public int deleteBook(int bookId) {
		return 0;
	}

}
