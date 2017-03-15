package com.melon.admin.authorization.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.dao.JdbcDaoSupport;

public class AuthorizationDaoImpl implements AuthorizationDao {

	@Override
	public int insertNewAuthorization(AuthorizationVO authorizationVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO		ATHRZTN (                  ");
			query.append(" 							ATHRZTN_ID         ");
			query.append(" 							, ATHRZTN_NM       ");
			query.append(" 							, PRNT_ATHRZTN_ID  ");
			query.append(" 							)                  ");
			query.append(" VALUES					(                  ");
			query.append(" 							? 				   ");
			query.append(" 							, ?                ");
			query.append(" 							, ?                ");
			query.append(" 						)                      ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationVO.getAuthorizationId());
			stmt.setString(2, authorizationVO.getAuthorizationName());
			stmt.setString(3, authorizationVO.getParentAuthorizationId());
			
			return stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
	}

	@Override
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	COUNT(1) CNT     ");
			query.append(" FROM		ATHRZTN          ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			if ( rs.next() ) {
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
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	*                                        ");
			query.append(" FROM	(                                            ");
			query.append(" 			SELECT 	ROWNUM RNUM                      ");
			query.append(" 					, A.*                            ");
			query.append(" 			FROM	(                                ");
			query.append(" 						SELECT	ATHRZTN_ID           ");
			query.append(" 								, ATHRZTN_NM         ");
			query.append(" 								, PRNT_ATHRZTN_ID    ");
			query.append(" 						FROM	ATHRZTN              ");
//			query.append(" 						WHERE	ATHRZTN_ID = ?       ");
			query.append(" 					) A                              ");
			query.append(" 			WHERE	ROWNUM <= ?                      ");
			query.append(" 		)                                            ");
			query.append(" WHERE	RNUM >= ?                                ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, authorizationSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, authorizationSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<AuthorizationVO> authorizationList = new ArrayList<AuthorizationVO>();
			AuthorizationVO authorizationVO = null;
			if(rs.next()) {
				authorizationVO = new AuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
				authorizationList.add(authorizationVO);
			}
			
			return authorizationList;
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
	}

	@Override
	public int updateAuthorizationInfo(AuthorizationVO authorizationVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" UPDATE	ATHRZTN         	  ");
			query.append(" SET		ATHRZTN_NM = ?     	  ");
			query.append(" 			, PRNT_ATHRZTN_ID = ? ");
			query.append(" WHERE	ATHRZTN_ID = ?    	  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationVO.getAuthorizationName());
			stmt.setString(2, authorizationVO.getParentAuthorizationId());
			stmt.setString(3, authorizationVO.getAuthorizationId());
			
			return stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
	}

	@Override
	public int deleteOneAuthorization(String authorizationId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" DELETE                   ");
			query.append(" FROM		ATHRZTN         ");
			query.append(" WHERE 	ATHRZTN_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationId);
			
			return stmt.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
	}

	@Override
	public String generateNewAuthorizationId() {

		// 추상화 클래스는 객체화 시킬 수 없다.(구체화 된게 없으니까)
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	'AT-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ ");
				query.append(" FROM	DUAL                                                                                          ");
				
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
			}

			@Override
			public Object bindData(ResultSet rs) throws SQLException {
				return rs.getString("SEQ");
			}
		};
		
		return (String) dao.selectOne();
		
		
		
		// JdbcDaoSupport.java 에 추상화 클래스로 공통되는 부분 만들어 놓음.
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		
//		try {
//			conn = DriverManager.getConnection(url, "MELON", "MELON");
//			
//			StringBuffer query = new StringBuffer();
//			query.append(" SELECT	'AT-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ ");
//			query.append(" FROM	DUAL                                                                                          ");
//		
//			stmt = conn.prepareStatement(query.toString());
//			
//			rs = stmt.executeQuery();
//			
//			if (rs.next()) {
//				return rs.getString("SEQ");
//			}
//			return null;
//		} 
//		catch (SQLException e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {}
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
	}

}
