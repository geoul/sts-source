package com.board3.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board3.user.vo.UserSearchVO;
import com.board3.user.vo.UserVO;

public class UserDaoImpl implements UserDao {
	
	@Override
	public int insertNewUser(UserVO userVO) {
		
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
			conn = DriverManager.getConnection(url, "BOARD3", "BOARD3");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO	USR	(                                                                                    ");
			query.append(" 					USR_ID                                                                               ");
			query.append(" 					, USR_NM                                                                             ");
			query.append(" 					, USR_PWD                                                                            ");
			query.append(" 					, JOIN_DT                                                                            ");
			query.append(" 				)                                                                                        ");
			query.append(" 		VALUES	(                                                                                        ");
			query.append(" 					'AL-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(USR_ID_SEQ.NEXTVAL, 6, '0') ");
			query.append(" 					, ?                                                                                  ");
			query.append(" 					, ?                                                                                  ");
			query.append(" 					, ?                                                                                  ");
			query.append(" 				)                                                                                        ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserName());
			stmt.setString(2, userVO.getUserPassword());
			stmt.setString(3, userVO.getJoinDate());
			
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
	public UserVO selectOneUser(UserVO user) {
		
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
			conn = DriverManager.getConnection(url, "BOARD3", "BOARD3");
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	USR_ID                          ");
			query.append(" 			, USR_NM                            ");
			query.append(" 			, USR_PWD                           ");
			query.append(" 			, JOIN_DT                           ");
			query.append(" FROM		USR                                 ");
			query.append(" WHERE	USR_ID = ?                      ");
			query.append(" WHERE	USR_PWD = ?                      ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserPassword());
			
			rs = stmt.executeQuery();
			
			UserVO userVO = null;
			if(rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setJoinDate(rs.getString("JOIN_DT"));
				
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

}
