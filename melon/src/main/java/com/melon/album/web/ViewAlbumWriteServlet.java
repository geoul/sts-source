package com.melon.album.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.album.biz.AlbumBiz;
import com.melon.album.biz.AlbumBizImpl;
import com.melon.album.vo.AlbumVO;
import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;

public class ViewAlbumWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AlbumBiz albumBiz;
	
    public ViewAlbumWriteServlet() {
        albumBiz = new AlbumBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/view/album/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
		String artistId = request.getParameter("artistId");
		String albumName = multipart.getParameter("albumName");
		String releaseDate = multipart.getParameter("releaseDate");
		String publisher = multipart.getParameter("publisher");
		String entertainment = multipart.getParameter("entertainment");
		String genre = multipart.getParameter("genre");
		
		// post에 들어갈 것임.
		String postFileName = "";
		
		
		// form으로 전송된 파일(사용자가 올린 사진 파일)
		MultipartFile post = multipart.getFile("post");
		// 파일이 올라 왔다면
		if ( post != null && post.getFileSize() > 0 ) {
			
			postFileName = post.getFileName();
			
			// 동적으로 경로 정하기
			File dir = new File("D:\\uploadFiles\\post\\" + artistId + File.separator + albumName);
			dir.mkdirs();
			
			post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
		}
		
		AlbumVO albumVO = new AlbumVO();
		albumVO.setAlbumName(albumName);
		albumVO.setArtistId(artistId);
		albumVO.setReleaseDate(releaseDate);
		albumVO.setPublisher(publisher);
		albumVO.setEntertainment(entertainment);
		albumVO.setGenre(genre);
		albumVO.setPost(postFileName);
		
		if ( albumBiz.addNewAlbum(albumVO) ) {
			StringBuffer script = new StringBuffer();
			script.append("<script type='text/javascript'>");
			script.append("    opener.location.reload();");
			script.append("    self.close();");
			script.append("</script>");
			
			PrintWriter writer = response.getWriter();
			writer.write(script.toString());
			writer.flush();
			writer.close();
		}
		else {
			response.sendRedirect("/melon/album/write");
		}
		
	}

}
