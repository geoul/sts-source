package com.melon.album.vo;

import com.melon.common.web.pager.Pager;
import com.melon.common.web.pager.PagerFactory;

public class AlbumSearchVO {

	private Pager pager;
	
	private String artistId;

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	
	
}
