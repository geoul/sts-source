package com.melon.album.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.album.vo.AlbumSearchVO;
import com.melon.album.vo.AlbumVO;

public class AlbumDaoImpl implements AlbumDao {

	@Override
	public int insertNewAlbum(AlbumVO albumVO) {
		
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
			query.append(" INSERT INTO	ALBM	(																					 ");
			query.append(" 						ALBM_ID                                                                              ");
			query.append(" 						, ARTST_ID                                                                           ");
			query.append(" 						, RLS_DT                                                                             ");
			query.append(" 						, PBLSHR                                                                             ");
			query.append(" 						, ENTMNT                                                                             ");
			query.append(" 						, GNR                                                                                ");
			query.append(" 						, PST                                                                                ");
			query.append(" 						, ALBM_NM                                                                                ");
			query.append(" 					)                                                                                        ");
			query.append(" 			VALUES	(                                                                                        ");
			query.append(" 						'AL-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ALBM_ID_SEQ.NEXTVAL, 6, '0')");
			query.append(" 						, ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					    , ?                                                                                  ");
			query.append(" 					)                                                                                        ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, albumVO.getArtistId());
			stmt.setString(2, albumVO.getReleaseDate());
			stmt.setString(3, albumVO.getPublisher());
			stmt.setString(4, albumVO.getEntertainment());
			stmt.setString(5, albumVO.getGenre());
			stmt.setString(6, albumVO.getPost());
			stmt.setString(7, albumVO.getAlbumName());
			
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
			if( conn != null ) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public int selectAllAlbumsCount(AlbumSearchVO albumSearchVO) {
		
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
			query.append(" SELECT 	COUNT(1) CNT  ");
			query.append(" FROM		ALBM AL             ");
			query.append(" 			, ARTST AR             ");
			query.append(" WHERE	AL.ARTST_ID = AR.ARTST_ID             ");
			query.append(" AND		AR.ARTST_ID = ?             ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, albumSearchVO.getArtistId());
			
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
	public List<AlbumVO> selectAllAlbums(AlbumSearchVO albumSearchVO) {
		
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
			query.append(" SELECT 	*                                                          ");
			query.append(" FROM	(                                                              ");
			query.append(" 		SELECT 	ROWNUM RNUM                                            ");
			query.append(" 				, A.*                                                  ");
			query.append(" 		FROM	(                                                      ");
			query.append(" 				SELECT 	AL.ALBM_ID                                     ");
			query.append(" 						, AL.ARTST_ID                                  ");
			query.append(" 						, TO_CHAR(AL.RLS_DT, 'YYYY-MM-DD') RLS_DT      ");
			query.append(" 						, AL.PBLSHR                                    ");
			query.append(" 						, AL.ENTMNT                                    ");
			query.append(" 						, AL.GNR                                       ");
			query.append(" 						, AL.PST                                       ");
			query.append(" 						, AL.ALBM_NM                                   ");
			query.append(" 						, AR.ARTST_ID AR_ARTST_ID                      ");
			query.append(" 						, AR.MBR                                       ");
			query.append(" 						, TO_CHAR(AR.DEBUT_DT, 'YYYY-MM-DD') DEBUT_DT  ");
			query.append(" 						, AR.DEBUT_TTL                                 ");
			query.append(" 				FROM 	ALBM AL                                        ");
			query.append(" 						, ARTST AR                                     ");
			query.append(" 				WHERE	AL.ARTST_ID = AR.ARTST_ID                      ");
			query.append(" 				AND		AR.ARTST_ID = ?                              ");
			query.append(" 				ORDER	BY AL.RLS_DT DESC                              ");
			query.append(" 				) A                                                    ");
			query.append(" 		WHERE 	ROWNUM <= ?                                            ");
			query.append(" 		)                                                              ");
			query.append(" WHERE RNUM >= ?                                                     ");
		
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, albumSearchVO.getArtistId());
			stmt.setInt(2, albumSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(3, albumSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<AlbumVO> albumList = new ArrayList<AlbumVO>();
			AlbumVO albumVO = null;
			if(rs.next()) {
				albumVO = new AlbumVO();
				albumVO.setAlbumId(rs.getString("ALBM_ID"));
				albumVO.setArtistId(rs.getString("ARTST_ID"));
				albumVO.setReleaseDate(rs.getString("RLS_DT"));
				albumVO.setPublisher(rs.getString("PBLSHR"));
				albumVO.setEntertainment(rs.getString("ENTMNT"));
				albumVO.setGenre(rs.getString("GNR"));
				albumVO.setPost(rs.getString("PST"));
				albumVO.setAlbumName(rs.getString("ALBM_NM"));
				albumVO.getArtistVO().setArtistId(rs.getString("ARTST_ID"));
				albumVO.getArtistVO().setMember(rs.getString("MBR"));
				albumVO.getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
				albumVO.getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));
				
				albumList.add(albumVO);
			}
			return albumList;
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch(SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {}
			}
		}
	}

	@Override
	public AlbumVO selectOneAlbum(String albumId) {
		
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
			query.append(" SELECT	ALBM_ID      	 ");
			query.append(" 			, ARTST_ID       ");
			query.append(" 			, RLS_DT         ");
			query.append(" 			, PBLSHR         ");
			query.append(" 			, ENTMNT         ");
			query.append(" 			, GNR            ");
			query.append(" 			, PST            ");
			query.append(" 			, ALBM_NM        ");
			query.append(" FROM 	ALBM             ");
			query.append(" WHERE	ALBM_ID = ?  	 ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, albumId);
			
			rs = stmt.executeQuery();
			
			AlbumVO albumVO = null;
			if(rs.next()) {
				albumVO = new AlbumVO();
				albumVO.setAlbumId(rs.getString("ALBM_ID"));
				albumVO.setArtistId(rs.getString("ARTST_ID"));
				albumVO.setReleaseDate(rs.getString("RLS_DT"));
				albumVO.setPublisher(rs.getString("PBLSHR"));
				albumVO.setEntertainment(rs.getString("ENTMNT"));
				albumVO.setGenre(rs.getString("GNR"));
				albumVO.setPost(rs.getString("PST"));
				albumVO.setAlbumName(rs.getString("ALBM_NM"));
				
				return albumVO;
			}
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return null;
	}

	@Override
	public int deleteOneAlbum(String albumId) {
		
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
			query.append(" FROM	ALBM            ");
			query.append(" WHERE	ALBM_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.executeQuery();
			
			return stmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
