package com.melon.artist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.artist.vo.ArtistSearchVO;
import com.melon.artist.vo.ArtistVO;

public class ArtistDaoImpl implements ArtistDao {

	@Override
	public int insertNewArtist(ArtistVO artistVO) {
		
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
			
			StringBuffer sb = new StringBuffer();
			
			sb.append(" INSERT INTO 	ARTST 	( 							");
			sb.append(" 				ARTST_ID 							");
			sb.append(" 				, MBR 								");
			sb.append(" 				, DEBUT_DT 							");
			sb.append(" 				, DEBUT_TTL 						");
			sb.append(" 		) 											");
			sb.append(" VALUES	( 											");
			sb.append(" 'AR-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ARTST_ID_SEQ.NEXTVAL, 6, '0') ");
			sb.append(" 			, ? 									");
			sb.append(" 			, TO_DATE(?, 'YYYY-MM-DD') 				");
			sb.append(" 			, ? 									");
			sb.append(" 		) 											");
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, artistVO.getMember());
			stmt.setString(2, artistVO.getDebutDate());
			stmt.setString(3, artistVO.getDebutTitle());
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
	public List<ArtistVO> selectAllArtists(ArtistSearchVO artistSearchVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT * ");
			query.append(" FROM		(                             ");
			query.append(" SELECT	ROWNUM RNUM                ");
			query.append("			, RST.*                         ");
			query.append("			FROM ( ");
			query.append("				SELECT	ARTST_ID          ");
			query.append("						, MBR             ");
			query.append("						, DEBUT_DT        ");
			query.append("						, DEBUT_TTL       ");
			query.append("				FROM	ARTST             ");
			query.append("				ORDER	BY ARTST_ID DESC  ");
			query.append("			) RST                           ");
			query.append("			WHERE	ROWNUM <= ?       	  ");
			query.append(" )                         			  ");
			query.append(" WHERE RNUM >= ?                        ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, artistSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, artistSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<ArtistVO> artistList = new ArrayList<ArtistVO>();
			ArtistVO artistVO = null;
			if( rs.next() ) {
				artistVO = new ArtistVO();
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
				
				artistList.add(artistVO);
			}
			return artistList;
		} 
		catch(SQLException e){
			throw new RuntimeException(e.getMessage(), e);
		}
		finally{
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public ArtistVO selectOneArtist(String artistId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "MELON");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	ARTST_ID     ");
			query.append(" 			, MBR        ");
			query.append(" 			, DEBUT_DT   ");
			query.append(" 			, DEBUT_TTL  ");
			query.append(" FROM 	ARTST        ");
			query.append(" WHERE 	ARTST_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, artistId);
			
			rs = stmt.executeQuery();
			
			ArtistVO artistVO = null;
			if (rs.next()) {
				artistVO = new ArtistVO();
				artistVO.setArtistId(rs.getString("ARTST_ID"));
				artistVO.setMember(rs.getString("MBR"));
				artistVO.setDebutDate(rs.getString("DEBUT_DT"));
				artistVO.setDebutTitle(rs.getString("DEBUT_TTL"));
				
				return artistVO;
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return null;
	}

	@Override
	public int deleteOneArtist(String artistId) {
		
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
			query.append(" DELETE	              ");
			query.append(" FROM	ARTST             ");
			query.append(" WHERE	ARTST_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, artistId);
			stmt.executeQuery();
			
			return stmt.executeUpdate();		
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public int selectAllArtistsCount(ArtistSearchVO artistSearchVO) {
		
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
			query.append(" SELECT	COUNT(1) CNT ");
			query.append(" FROM	ARTST    		 ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("CNT");
			}
			
			return 0;
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if( rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

}
