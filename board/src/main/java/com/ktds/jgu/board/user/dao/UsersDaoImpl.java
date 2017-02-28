package com.ktds.jgu.board.user.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.jgu.board.user.vo.UsersVO;
import com.ktds.jgu.dao.support.QueryHandler;
import com.ktds.jgu.dao.support.JDBCDaoSupport;
import com.ktds.jgu.dao.support.annotation.BindData;

public class UsersDaoImpl extends JDBCDaoSupport implements UsersDao {

	@Override
	public int insertNewUser(UsersVO user) {
		return update(new QueryHandler(){
		
			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT   INTO USRS ( ");
				query.append("          USR_ID ");
				query.append("          , USR_NM ");
				query.append("          , USR_PWD ");
				query.append("          , JOIN_DT ");
				query.append("          ) ");
				query.append(" VALUES   ( ");
				query.append("          ? ");
				query.append("          , ? ");
                query.append("          , ? ");
                query.append("          , SYSDATE ");
                query.append("          ) ");
                
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, user.getUserId());
				stmt.setString(2, user.getUserName());
                stmt.setString(3, user.getUserPassword());
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
			
		});
	}

	@Override
	public List<UsersVO> selectAllUsers() {
		return selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
                query.append(" SELECT    USR_ID ");
                query.append("           , USR_NM ");
                query.append("           , USR_PWD ");
                query.append("           , JOIN_DT ");
                query.append(" FROM      USRS ");
                return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				
			}

			@Override
			public Object getData(ResultSet rs) {
				UsersVO user = new UsersVO();
                BindData.bindData(rs, user);
                return user;
			}
			
		});
	}

	@Override
	public UsersVO selectOneUser(String userId, String userPassword) {
		return (UsersVO) selectOne(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT 		USR_ID ");
				query.append(" 		 		, USR_NM ");
				query.append(" 		 		, USR_PWD ");
				query.append(" 		 		, JOIN_DT ");
				query.append(" FROM 		USRS ");
				query.append(" WHERE 		USR_ID = ? ");
				query.append(" AND 			USR_PWD = ? ");
				
				return 	query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, userId);
				stmt.setString(2, userPassword);
			}

			@Override
			public Object getData(ResultSet rs) {
				UsersVO user = new UsersVO();
                BindData.bindData(rs, user);
                return user;
			}
			
		});
	}

	@Override
	public UsersVO removeOneUser(String userId, String userPassword) {
		return (UsersVO) selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE			");
				query.append(" FROM				USRS ");
				query.append(" WHERE 		USR_ID = ? ");
				query.append(" AND 			USR_PWD = ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, userId);
				stmt.setString(2, userPassword);
			}

			@Override
			public Object getData(ResultSet rs) {
				UsersVO usersVO = new UsersVO();
                BindData.bindData(rs, usersVO);
                return usersVO;
			}
			
		});
	}

}
