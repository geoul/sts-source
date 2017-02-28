package com.ktds.jgu.board.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.jgu.board.board.vo.BoardVO;
import com.ktds.jgu.board.user.vo.UsersVO;
import com.ktds.jgu.dao.support.JDBCDaoSupport;
import com.ktds.jgu.dao.support.QueryHandler;
import com.ktds.jgu.dao.support.annotation.BindData;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardDaoImpl extends JDBCDaoSupport implements BoardDao {

    @Override
    public BoardVO selectOneArticles(int boardId) {
        return (BoardVO) selectOne(new QueryHandler() {
            @Override
            public String preparedQuery() {
                StringBuffer query = new StringBuffer();
                query.append(" SELECT    B.BOARD_ID ");
                query.append("           , B.SUBJECT ");
                query.append("           , B.WRITER ");
                query.append("           , B.LIKE_COUNT ");
                query.append("           , B.WRITE_DATE ");
                query.append("			, B. IP ");
                query.append("			, U.USR_ID ");
                query.append("			, U.USR_NM ");
                query.append("			, U.JOIN_DT ");
                query.append(" FROM      BOARD B ");
                query.append("			,USRS U ");
                query.append(" WHERE	B.WRITER = U.USR_ID ");
                query.append(" AND	BOARD_ID = ? ");
                return query.toString();
            }

            @Override
            public void mappingParameters(PreparedStatement stmt) throws SQLException {
            	stmt.setInt(1, boardId);
            }

            @Override
            public Object getData(ResultSet rs) {
                BoardVO boardVO = new BoardVO();
                BindData.bindData(rs, boardVO);
                return boardVO;
            }
        });
    }

    @Override
    public int insertArticle(BoardVO boardVO) {
        return update(new QueryHandler() {
            @Override
            public String preparedQuery() {
                StringBuffer query = new StringBuffer();
                query.append(" INSERT   INTO BOARD ( ");
                query.append("                          BOARD_ID ");
                query.append("                          , SUBJECT ");
                query.append("                          , CONTENT ");
                query.append("                          , WRITER ");
                query.append("                          , LIKE_COUNT ");
                query.append("                          , WRITE_DATE ");
                query.append("                      ) ");
                query.append(" VALUES               ( ");
                query.append("                          BOARD_ID_SEQ.NEXTVAL ");
                query.append("                          , ? ");
                query.append("                          , ? ");
                query.append("                          , ? ");
                query.append("                          , 0 ");
                query.append("                          , SYSDATE ");
                query.append("                      ) ");
                return  query.toString();
            }

            @Override
            public void mappingParameters(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, boardVO.getSubject());  //숫자 1 : 이 쿼리에서 첫 번째 물음표를 가리킨다.
                stmt.setString(2, boardVO.getContent());
                stmt.setString(3, boardVO.getWriter());
//                stmt.setString(4, boardVO.getIp());
            }

            @Override
            public Object getData(ResultSet rs) {
                return null;
            }
        });
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<BoardVO> selectAllArticles() {
        return selectList(new QueryHandler() {
            @Override
            public String preparedQuery() {
                StringBuffer query = new StringBuffer();
                query.append(" SELECT    B.BOARD_ID ");
                query.append("           , B.SUBJECT ");
                query.append("           , B.WRITER ");
                query.append("           , B.LIKE_COUNT ");
                query.append("           , B.WRITE_DATE ");
                query.append("			, B. IP ");
                query.append("			, U.USR_ID ");
                query.append("			, U.USR_NM ");
                query.append("			, U.JOIN_DT ");
                query.append(" FROM      BOARD B ");
                query.append("			,USRS U ");
                query.append("WHERE		B.WRITER = U.USR_ID ");
                query.append(" ORDER		BY BOARD_ID DESC ");
                return query.toString();
            }

            @Override
            public void mappingParameters(PreparedStatement stmt) throws SQLException {
            	
            }

            @Override
            public Object getData(ResultSet rs) {
                BoardVO boardVO = new BoardVO();
                UsersVO usersVO = new UsersVO();
                BindData.bindData(rs, boardVO);
                BindData.bindData(rs, usersVO);
                
                return boardVO;
            }
        });
    }

	@Override
	public BoardVO removeOneArticle(int boardId) {
		return (BoardVO) selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE 			");
				query.append(" FROM			BOARD ");
				query.append(" WHERE 		BOARD_ID = ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, boardId);
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
                BindData.bindData(rs, boardVO);
                return boardVO;
			}
			
		});
	}

	@Override
	public BoardVO updateOneArticle(BoardVO boardVO) {
		return (BoardVO) selectList(new QueryHandler(){

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
				
				query.append(" UPDATE		BOARD ");
				query.append(" SET			WRITER = ? ");
				query.append(" 				, SUBJECT = ? ");
				query.append(" 				, CONTENT = ? ");
				query.append(" WHERE		BOARD_ID = ? ");
				
				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, boardVO.getWriter());
				stmt.setString(2, boardVO.getSubject());
				stmt.setString(3, boardVO.getContent());
				stmt.setInt(4, boardVO.getBoardId());
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
                BindData.bindData(rs, boardVO);
                return boardVO;
			}
			
		});
	}
}
