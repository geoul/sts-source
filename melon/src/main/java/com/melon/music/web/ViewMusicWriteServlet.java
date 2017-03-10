package com.melon.music.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicVO;

public class ViewMusicWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicBiz musicBiz;
	
    public ViewMusicWriteServlet() {
    	musicBiz = new MusicBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);
		
		String albumId = request.getParameter("albumId");
		String title = multipart.getParameter("title");
		String musician = multipart.getParameter("musician");
		String director = multipart.getParameter("director");
		String lyrics = multipart.getParameter("lyrics");
		MultipartFile mp3File = multipart.getFile("mp3File");
		
		lyrics = lyrics.replaceAll("\n", "<br/>");
		lyrics = lyrics.replaceAll("\r", "");
		
		if ( mp3File != null && mp3File.getFileSize() > 0 ) {
//			String path = "D:\\uploadFiles\\post\\mp3\\";
			String path = "C:\\Users\\Admin\\Documents\\workspace-sts-3.8.3.RELEASE\\melon\\src\\main\\webapp\\mp3\\";
			path += albumId;
			
			File dir = new File(path);
			dir.mkdirs();
			
			mp3File.write(path + File.separator + mp3File.getFileName());
		}
		
		MusicVO musicVO = new MusicVO();
		musicVO.setAlbumId(albumId);
		musicVO.setDirector(director);
		musicVO.setLikeCount(0);
		musicVO.setLyrics(lyrics);
		musicVO.setMp3File(mp3File.getFileName());
		musicVO.setMusician(musician);
		musicVO.setTitle(title);
		
		if ( musicBiz.addNewMusic(musicVO) ) {
			PrintWriter writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("    opener.location.reload();");
			sb.append("    self.close();");
			sb.append("</script>");
			
			writer.write(sb.toString());
			writer.flush();
			writer.close();
		}
		else {
			response.sendRedirect("/melon/music/write?albumId=" + albumId);
		}
	}

}
