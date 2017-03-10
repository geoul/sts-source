package com.melon.music.vo;

import com.melon.common.web.pager.Pager;
import com.melon.common.web.pager.PagerFactory;

public class MusicSearchVO {

	private Pager pager;
	
	// 파라미터 추가 (DB에 물어봐서 찾으려고(검색하기 위해))
	private String albumId;
	private String artistId;
	
	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public Pager getPager() {
		if ( pager == null ) {
			// 한 페이지에 20개씩 보여주고 페이지 그룹에 10개의 페이지를 보여준다.
			pager = PagerFactory.getPager(Pager.ORACLE, 20, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	
	
}
