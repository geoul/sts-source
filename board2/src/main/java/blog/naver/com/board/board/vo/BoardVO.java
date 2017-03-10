package blog.naver.com.board.board.vo;

import blog.naver.com.board.user.vo.UsersVO;

public class BoardVO {
	private int boardId;
	private String subject;
	private String content;
	private String writer;
	private int likeCount;
	private String writeDate;
	private String ip;

	private UsersVO user;

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public UsersVO getUser() {
		if ( user == null ) {
			user = new UsersVO();
		}
		return user;
	}

	public void setUser(UsersVO user) {
		this.user = user;
	}

}
