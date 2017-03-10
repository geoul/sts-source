package blog.naver.com.board.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.naver.com.board.user.vo.UsersVO;

public class UsersDaoImpl implements UsersDao {

	private final String URL = "jdbc:oracle:thin:@192.168.1.159:1521:XE";
	private final String USER_NAME = "BOARD";
	private final String PASSWORD = "board";
	
	@Override
	public int insertNewUser(UsersVO user) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO USRS ( ");
			query.append(" 						USR_ID ");
			query.append(" 						, USR_NM ");
			query.append(" 						, USR_PWD ");
			query.append(" 						, JOIN_DT ");
			query.append(" 					) ");
			query.append(" VALUES			( ");
			query.append(" 						? ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						, SYSDATE ");
			query.append(" 					) ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getUserPassword());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeInstances(conn, stmt, null);
		}
		
	}
	
	@Override
	public UsersVO selectOneUser(UsersVO user) {
		
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
			query.append(" SELECT	USR_ID ");
			query.append(" 			, USR_NM ");
			query.append(" 			, USR_PWD ");
			query.append(" 			, JOIN_DT ");
			query.append(" FROM		USRS ");
			query.append(" WHERE	USR_ID = ? ");
			query.append(" AND		USR_PWD = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserPassword());
			
			rs = stmt.executeQuery();
			
			user = null;
			if ( rs.next() ) {
				user = new UsersVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setUserName(rs.getString("USR_NM"));
				user.setUserPassword(rs.getString("USR_PWD"));
				user.setJoinDate(rs.getString("JOIN_DT"));
			}
			
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
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
	
}
