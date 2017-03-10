package com.melon.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class MusicDaoImpl implements MusicDao {

	@Override
	public int insertNewMusic(MusicVO musicVO) {
		
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
				query.append( " INSERT INTO		MSC	(                                                                                       ");
				query.append( " 					MSC_ID                                                                                  ");
				query.append( " 					, ALBM_ID                                                                               ");
				query.append( " 					, TTL                                                                                   ");
				query.append( " 					, LK_CNT                                                                                ");
				query.append( " 					, MP3_FL                                                                                ");
				query.append( " 					, MSCN                                                                                  ");
				query.append( " 					, DRTR                                                                                  ");
				query.append( " 					, LRCS                                                                                  ");
				query.append( " 					)                                                                                       ");
				query.append( " VALUES				(                                                                                       ");
				query.append( " 						'MS-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(MSC_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 						, ?                                                                                 ");
				query.append( " 					)                                                                                       ");
				
				stmt = conn.prepareStatement(query.toString());
				stmt.setString(1, musicVO.getAlbumId());
				stmt.setString(2, musicVO.getTitle());
				stmt.setInt(3, musicVO.getLikeCount());
				stmt.setString(4, musicVO.getMp3File());
				stmt.setString(5, musicVO.getMusician());
				stmt.setString(6, musicVO.getDirector());
				stmt.setString(7, musicVO.getLyrics());
				
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
	public int selectAllMusicCount(MusicSearchVO musicSearchVO) {
		
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
			query.append(" SELECT 	COUNT(1) CNT               ");
			query.append(" FROM		MSC M                          ");
			query.append(" 			, ALBM AL                      ");
			query.append(" 			, ARTST AR                     ");
			query.append(" WHERE	M.ALBM_ID = AL.ALBM_ID     ");
			query.append(" AND		AL.ARTST_ID = AR.ARTST_ID  ");
			query.append(" AND 		AL.ALBM_ID = ?                 ");
			query.append(" AND     	AR.ARTST_ID = ?             ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicSearchVO.getAlbumId());
			stmt.setString(2, musicSearchVO.getArtistId());
			
			rs = stmt.executeQuery();
			if (rs.next()) {
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
	public List<MusicVO> selectAllMusic(MusicSearchVO musicSearchVO) {
		
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
			query.append(" SELECT	*                                                                 ");
			query.append(" FROM	(                                                                     ");
			query.append(" 			SELECT ROWNUM RNUM                                                ");
			query.append(" 					, A.*                                                     ");
			query.append(" 			FROM	(                                                         ");
			query.append(" 						SELECT	M.MSC_ID                                      ");
			query.append(" 								, M.ALBM_ID                                   ");
			query.append(" 								, M.TTL                                       ");
			query.append(" 								, M.LK_CNT                                    ");
			query.append(" 								, M.MP3_FL                                    ");
			query.append(" 								, M.MSCN                                      ");
			query.append(" 								, M.DRTR                                      ");
			query.append(" 								, M.LRCS                                      ");
			query.append(" 								, AL.ALBM_ID AL_ALBM_ID                       ");
			query.append(" 								, AL.ARTST_ID AL_ARTST_ID                     ");
			query.append(" 								, AL.RLS_DT                                   ");
			query.append(" 								, AL.PBLSHR                                   ");
			query.append(" 								, AL.ENTMNT                                   ");
			query.append(" 								, AL.GNR                                      ");
			query.append(" 								, AL.PST                                      ");
			query.append(" 								, AL.ALBM_NM                                  ");
			query.append(" 								, AR.ARTST_ID AR_ARTST_ID                                 ");
			query.append(" 								, AR.MBR                                      ");
			query.append(" 								, TO_CHAR(AR.DEBUT_DT, 'YYYY-MM-DD') DEBUT_DT ");
			query.append(" 								, AR.DEBUT_TTL                                ");
			query.append(" 						FROM 	MSC M                                         ");
			query.append(" 								, ALBM AL                                     ");
			query.append(" 								, ARTST AR                                    ");
			query.append(" 						WHERE	M.ALBM_ID = AL.ALBM_ID                        ");
			query.append(" 						AND		AL.ARTST_ID = AR.ARTST_ID                     ");
			query.append(" 						AND		AL.ALBM_ID = ?                                ");
			query.append(" 						AND 	AR.ARTST_ID = ?                               ");
			query.append("						ORDER	BY MSC_ID ASC								  ");
			query.append(" 					) A                                                       ");
			query.append(" 			WHERE	ROWNUM <= ?                                               ");
			query.append(" 		)                                                                     ");
			query.append(" WHERE 	RNUM >= ?                                                         ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicSearchVO.getAlbumId());
			stmt.setString(2, musicSearchVO.getArtistId());
			stmt.setInt(3, musicSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(4, musicSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<MusicVO> musicList = new ArrayList<MusicVO>();
			MusicVO musicVO = null;
			while(rs.next()) {
				musicVO = new MusicVO();
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector(rs.getString("DRTR"));
				musicVO.setLyrics(rs.getString("LRCS"));
				musicVO.getAlbumVO().setAlbumId(rs.getString("AL_ALBM_ID"));
				musicVO.getAlbumVO().setArtistId(rs.getString("AL_ARTST_ID"));
				musicVO.getAlbumVO().setReleaseDate(rs.getString("RLS_DT"));
				musicVO.getAlbumVO().setPublisher(rs.getString("PBLSHR"));
				musicVO.getAlbumVO().setEntertainment(rs.getString("ENTMNT"));
				musicVO.getAlbumVO().setGenre(rs.getString("GNR"));
				musicVO.getAlbumVO().setPost(rs.getString("PST"));
				musicVO.getAlbumVO().setAlbumName(rs.getString("ALBM_NM"));
				musicVO.getAlbumVO().getArtistVO().setArtistId(rs.getString("AR_ARTST_ID"));
				musicVO.getAlbumVO().getArtistVO().setMember(rs.getString("MBR"));
				musicVO.getAlbumVO().getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
				musicVO.getAlbumVO().getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));
				
				musicList.add(musicVO);
				
			}
			return musicList;
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
	public MusicVO selectOneMusic(String musicId) {
		
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
			query.append(" SELECT	MSC_ID      ");
			query.append(" 		, ALBM_ID       ");
			query.append(" 		, TTL           ");
			query.append(" 		, LK_CNT        ");
			query.append(" 		, MP3_FL        ");
			query.append(" 		, MSCN          ");
			query.append(" 		, DRTR          ");
			query.append(" 		, LRCS          ");
			query.append(" FROM 	MSC         ");
			query.append(" WHERE	MSC_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, musicId);
			
			rs = stmt.executeQuery();
			
			MusicVO musicVO = null;
			if(rs.next()) {
				musicVO = new MusicVO();
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector(rs.getString("DRTR"));
				musicVO.setLyrics(rs.getString("LRCS"));
				
				return musicVO;
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
	public int deleteOneMusic(String musicId) {
		
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
			query.append(" DELECT               ");
			query.append(" FROM		MSC             ");
			query.append(" WHERE	MSC_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.executeQuery();
			
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
