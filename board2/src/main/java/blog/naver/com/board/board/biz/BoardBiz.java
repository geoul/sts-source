package blog.naver.com.board.board.biz;

import java.util.List;

import blog.naver.com.board.board.vo.BoardSearchVO;
import blog.naver.com.board.board.vo.BoardVO;

public interface BoardBiz {
	public List<BoardVO> getAllAtricles(BoardSearchVO searchVO);
	
	public boolean writeArticle(BoardVO boardVO);

	public BoardVO getOneArticle(int boardId);

	public boolean deleteArticle(int boardId);

	public boolean updateArticle(BoardVO boardVO);
	
}
