package com.melon.admin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.user.vo.AuthorizationVO;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	@Override
	public int insertNewUser(UserVO newUserVO) {
		
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
			query.append(" INSERT INTO		USR (             ");
			query.append(" 						USR_ID        ");
			query.append(" 						, USR_PWD     ");
			query.append(" 						, USR_NM      ");
			query.append(" 						, USR_PNT     ");
			query.append(" 						, ATHRZTN_ID  ");
			query.append(" 					)                 ");
			query.append(" VALUES			(             	  ");
			query.append(" 						?             ");
			query.append(" 						, ?           ");
			query.append(" 						, ?           ");
			query.append(" 						, ?           ");
			query.append(" 						, ?           ");
			query.append(" 					)                 ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, newUserVO.getUserId());
			stmt.setString(2, newUserVO.getUserPassword());
			stmt.setString(3, newUserVO.getUserName());
			stmt.setInt(4, newUserVO.getUserPoint());
			stmt.setString(5, newUserVO.getAuthorizationId());
			
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
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {
		
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
			query.append(" SELECT	*                                                 ");
			query.append(" FROM	(                                                     ");
			query.append(" 			SELECT	ROWNUM RNUM                               ");
			query.append(" 					, A.*                                     ");
			query.append(" 			FROM	(                                         ");
			query.append(" 						SELECT	U.USR_ID                      ");
			query.append(" 								, U.USR_PWD                   ");
			query.append(" 								, U.USR_NM                    ");
			query.append(" 								, U.USR_PNT                   ");
			query.append(" 								, U.ATHRZTN_ID U_ATHRZTN_ID   ");
			query.append(" 								, AT.ATHRZTN_ID AT_ATHRZTN_ID   ");
			query.append(" 								, AT.ATHRZTN_NM                ");
			query.append(" 								, AT.PRNT_ATHRZTN_ID           ");
			query.append(" 						FROM 	USR U                         ");
			query.append(" 								, ATHRZTN AT                   ");
			query.append(" 						WHERE	U.ATHRZTN_ID = AT.ATHRZTN_ID   ");
//			query.append(" 						AND		U.ATHRZTN_ID = ?              ");
			query.append(" 						ORDER	BY U_ATHRZTN_ID DESC            ");
			query.append(" 					) A                                       ");
			query.append(" 			WHERE ROWNUM <= ?                                 ");
			query.append(" 		)                                                     ");
			query.append(" WHERE RNUM >= ?                                            ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setInt(1, userSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, userSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<UserVO> userList = new ArrayList<UserVO>();
			UserVO userVO = null;
			AuthorizationVO authorizationVO = null;
			if (rs.next()) {
				userVO = new UserVO();
				userVO.setIndex(rs.getInt("RNUM"));
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				
				authorizationVO = userVO.getAuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("AT_ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
				userList.add(userVO);
			}
			return userList;
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
	public UserVO selectOneUser(String userId) {
		
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
			query.append(" SELECT	U.USR_ID          ");
			query.append(" 			, U.USR_PWD           ");
			query.append(" 			, U.USR_NM            ");
			query.append(" 			, U.USR_PNT           ");
			query.append("			, U.ATHRZTN_ID U_ATHRZTN_ID ");
			query.append(" 			, A.ATHRZTN_ID A_ATHRZTN_ID ");
			query.append(" 			, A.ATHRZTN_NM        ");
			query.append(" 			, A.PRNT_ATHRZTN_ID   ");
			query.append(" FROM		USR U                 ");
			query.append(" 			, ATHRZTN A           ");
			query.append(" WHERE	USR_ID = ?        ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			
			UserVO userVO = null;
			AuthorizationVO authorizationVO = null;
			if( rs.next() ) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				
				authorizationVO = userVO.getAuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("A_ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
				return userVO;
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
		
		return null;
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) {
		
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
			query.append(" SELECT	USR_ID      ");
			query.append(" 			, USR_PWD       ");
			query.append(" 			, USR_NM        ");
			query.append(" 			, USR_PNT       ");
			query.append(" FROM		USR             ");
			query.append(" WHERE	USR_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			
			rs = stmt.executeQuery();
			
			UserVO user= null;
			if (rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setUserPassword(rs.getString("USR_PWD"));
				user.setUserName(rs.getString("USR_NM"));
				user.setUserPoint(rs.getInt("USR_PNT"));
				
				return user;
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
		
		return null;
	}

	@Override
	public int updateUserInfo(UserVO userVO) {
		
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
			query.append(" UPDATE	USR            ");
			query.append(" SET		, USR_PWD = ?  ");
			query.append(" 			, USR_NM = ?       ");
			query.append(" 			, USR_PNT = ?      ");
			query.append(" 			, ATHRZTN_ID = ?   ");
			query.append(" WHERE	USR_ID = ?     ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserPassword());
			stmt.setString(2, userVO.getUserName());
			stmt.setInt(3, userVO.getUserPoint());
			stmt.setString(4, userVO.getAuthorizationId());
			stmt.setString(5, userVO.getUserId());
			
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
	public int deleteOneUser(String userId) {
		
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
			query.append(" DELETE               ");
			query.append(" FROM		USR         ");
			query.append(" WHERE	USR_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);
			
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
	
	// 포인트 주는 것
		@Override
		public int updatePoint(String userId, int point) {
			
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
				query.append(" UPDATE	USR                    ");
				query.append(" SET		USR_PNT = USR_PNT + ?  ");
				query.append(" WHERE	USR_ID = ?             ");
				
				stmt = conn.prepareStatement(query.toString());
				stmt.setInt(1, point);
				stmt.setString(2, userId);
				
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
		public int updateAllAuthorization(String toAuth, String fromAuth) {
			
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
				
				query.append(" UPDATE	USR             ");
				query.append(" SET		ATHRZTN_ID = ?  ");
				if ( fromAuth == null || fromAuth.length() == 0 ) {
					query.append(" WHERE	ATHRZTN_ID IS NULL  ");
				}
				else {
					query.append(" WHERE	ATHRZTN_ID = ?  ");
				}
				
				stmt = conn.prepareStatement(query.toString());
				if ( toAuth == null || toAuth.length() == 0 ) {
					stmt.setNull(1, Types.VARCHAR);
				}
				else {
					stmt.setString(1, toAuth);
				}
				
				if ( fromAuth != null && fromAuth.length() > 0 ) {
					stmt.setString(2, fromAuth);
				}
				else {
					stmt.setNull(2, Types.VARCHAR);
				}
				
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

}
