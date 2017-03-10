package blog.naver.com.board.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.naver.com.board.board.vo.BoardSearchVO;
import blog.naver.com.board.board.vo.BoardVO;
import blog.naver.com.board.user.vo.UsersVO;

public class BoardDaoImpl implements BoardDao {

	private final String URL = "jdbc:oracle:thin:@192.168.1.159:1521:XE";
	private final String USER_NAME = "BOARD";
	private final String PASSWORD = "board";
	
	@Override
	public int getAllArticlesCount(BoardSearchVO boardSearchVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	COUNT(B.BOARD_ID) CNT ");
			query.append(" FROM 		BOARD B ");
			query.append("  			, USRS U ");
			query.append(" WHERE		B.WRITER = U.USR_ID ");
			
			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();
			if( rs.next() ) {
				return rs.getInt("CNT");
			}
				
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if ( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if ( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if ( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return 0;
	}
	
	@Override
	public List<BoardVO> getAllArticles(BoardSearchVO boardSearchVO) {
		// 1. Oracle Driver loading
		loadOracleDriver();
		// 2. 객체 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 3. 연결
		try {

			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 4. 쿼리문 작성

			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT 	* ");
			query.append(" FROM		( ");
			query.append("				SELECT	ROWNUM RNUM ");
			query.append("						, RST.* ");
			query.append("				FROM		( ");
			
			query.append(" SELECT  	B.BOARD_ID ");
			query.append("     	   	, B.SUBJECT ");
			query.append("     	   	, B.CONTENT ");
			query.append("     	   	, B.WRITER ");
			query.append("  	   		, B.LIKE_COUNT ");
			query.append("  	   		, B.WRITE_DATE ");
			query.append("        	, B.IP ");
			query.append(" 			, U.USR_ID ");
			query.append(" 			, U.USR_NM ");
			query.append(" 			, U.JOIN_DT ");
			query.append(" FROM   	BOARD B ");
			query.append("		  	, USRS U ");
			query.append(" WHERE		B.WRITER = U.USR_ID ");
			query.append(" ORDER		BY B.BOARD_ID DESC ");

			query.append("						) RST ");
			query.append("				WHERE	ROWNUM <= ? ");
			query.append(" 			) ");
			query.append(" WHERE		RNUM >= ? ");
			
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, boardSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, boardSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();

			List<BoardVO> boardList = new ArrayList<BoardVO>();

			BoardVO boardVO = null;
			UsersVO usersVO = null;
			
			while (rs.next()) {
				boardVO = new BoardVO();

				boardVO.setBoardId(rs.getInt("BOARD_ID"));
				boardVO.setSubject(rs.getString("SUBJECT"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setWriter(rs.getString("WRITER"));
				boardVO.setLikeCount(rs.getInt("LIKE_COUNT"));
				boardVO.setWriteDate(rs.getString("WRITE_DATE"));
				boardVO.setIp(rs.getString("IP"));
				
				usersVO = boardVO.getUser();
				usersVO.setUserId(rs.getString("USR_ID"));
				usersVO.setUserName(rs.getString("USR_NM"));
				usersVO.setJoinDate(rs.getString("JOIN_DT"));
				
				boardList.add(boardVO);
			}

			return boardList;

		} catch (SQLException e) {
			System.out.println("url Connection Error");
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeInstances(conn, stmt, rs);
		}
	}

	private void closeInstances(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}

	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int addArticle(BoardVO boardVO) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			StringBuffer query = new StringBuffer();
			query.append(" INSERT    INTO    BOARD ( ");
			query.append("                          BOARD_ID ");
			query.append("                          , SUBJECT ");
			query.append("                          , CONTENT ");
			query.append("                          , WRITER ");
			query.append("                          , LIKE_COUNT ");
			query.append("                          , WRITE_DATE ");
			query.append("                          , IP ");
			query.append("                         ) ");
			query.append(" VALUES                  ( ");
			query.append("                              BOARD_ID_SEQ.NEXTVAL ");
			query.append("                              , ? ");
			query.append("                              , ? ");
			query.append("                              , ? ");
			query.append("                              , 0 ");
			query.append("                              , SYSDATE ");
			query.append("                              , ? ");
			query.append("                          ) ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, boardVO.getSubject());
			stmt.setString(2, boardVO.getContent());
			stmt.setString(3, boardVO.getWriter());
			stmt.setString(4, boardVO.getIp());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("url error");
			System.out.println(e.getMessage());
			return 0;
		} finally {
			closeInstances(conn, stmt, rs);
		}
	}

	@Override
	public BoardVO getOneArticle(int boardId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			StringBuffer query = new StringBuffer();

			query.append(" SELECT  	B.BOARD_ID ");
			query.append("     	   	, B.SUBJECT ");
			query.append("     	   	, B.CONTENT ");
			query.append("     	   	, B.WRITER ");
			query.append("  	   	, B.LIKE_COUNT ");
			query.append("  	   	, B.WRITE_DATE ");
			query.append("        	, B.IP ");
			query.append(" 			, U.USR_ID ");
			query.append(" 			, U.USR_NM ");
			query.append(" 			, U.JOIN_DT ");
			query.append(" FROM   	BOARD B ");
			query.append("		  	, USRS U ");
			query.append(" WHERE	B.WRITER = U.USR_ID ");
			query.append(" AND	 	BOARD_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, boardId);;
			rs = stmt.executeQuery();

			BoardVO boardVO = null;
			UsersVO usersVO = null;
			if (rs.next()) {
				boardVO = new BoardVO();
				usersVO = boardVO.getUser();
				
				boardVO.setBoardId(rs.getInt("BOARD_ID"));
				boardVO.setSubject(rs.getString("SUBJECT"));
				boardVO.setContent(rs.getString("CONTENT"));
				boardVO.setWriter(rs.getString("WRITER"));
				boardVO.setLikeCount(rs.getInt("LIKE_COUNT"));
				boardVO.setWriteDate(rs.getString("WRITE_DATE"));
				boardVO.setIp(rs.getString("IP"));
				
				
				usersVO.setUserId(rs.getString("USR_ID"));
				usersVO.setUserName(rs.getString("USR_NM"));
				usersVO.setJoinDate(rs.getString("JOIN_DT"));
			}
			
			return boardVO;
			
		} catch (SQLException e) {
			System.out.println("query Error");
			System.out.println(e.getMessage());
			return null;
		} finally {
			closeInstances(conn, stmt, rs);
		}
	}

	@Override
	public int deleteArticle(int boardId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" DELETE "               );
			query.append(" FROM	BOARD "           );
			query.append(" WHERE	BOARD_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, boardId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("url connection failed");
			System.out.println(e.getMessage());
			return 0;
		} finally{
			closeInstances(conn, stmt, rs);
		}
	}

	@Override
	public int updateArticle(BoardVO boardVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			query.append(" UPDATE	BOARD "           );
			query.append(" SET		WRITER = ? "      );
			query.append("			, SUBJECT = ? "    );
			query.append("			, CONTENT = ? "   );
			query.append(" WHERE	BOARD_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, boardVO.getWriter());
			stmt.setString(2, boardVO.getSubject());
			stmt.setString(3, boardVO.getContent());
			stmt.setInt(4, boardVO.getBoardId());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("URL Connect Error");
			System.out.println(e.getMessage());
			return 0;
		} finally{
			closeInstances(conn, stmt, rs);
		}
	}
}
