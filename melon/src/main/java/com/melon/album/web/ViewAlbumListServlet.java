package com.melon.album.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.album.biz.AlbumBiz;
import com.melon.album.biz.AlbumBizImpl;
import com.melon.album.vo.AlbumSearchVO;
import com.melon.album.vo.AlbumVO;
import com.melon.common.web.pager.ClassicPageExplorer;
import com.melon.common.web.pager.PageExplorer;

public class ViewAlbumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AlbumBiz albumBiz;
	
    public ViewAlbumListServlet() {
        super();
        albumBiz = new AlbumBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo = request.getParameter("pageNo");
		String artistId = request.getParameter("artistId");
		
		AlbumSearchVO albumSearchVO = new AlbumSearchVO();
		albumSearchVO.setArtistId(artistId);
		albumSearchVO.getPager().setPageNumber(pageNo);
		
		List<AlbumVO> albumList = albumBiz.getAllAlbums(albumSearchVO);
		
		PageExplorer pager = new ClassicPageExplorer(albumSearchVO.getPager());
		
		request.setAttribute("albumList", albumList);
		request.setAttribute("albumCount", albumSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "이전", "다음", "searchForm"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/album/list.jsp");
		dispatcher.forward(request, response);
	}

}
