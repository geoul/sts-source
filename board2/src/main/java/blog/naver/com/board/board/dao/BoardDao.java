package blog.naver.com.board.board.dao;

import java.util.List;

import blog.naver.com.board.board.vo.BoardSearchVO;
import blog.naver.com.board.board.vo.BoardVO;

public interface BoardDao {
	
	public int getAllArticlesCount(BoardSearchVO boardSearchVO);
	
	public List<BoardVO> getAllArticles(BoardSearchVO boardSearchVO);
	
	public int addArticle(BoardVO boardVO);

	public BoardVO getOneArticle(int boardId);

	public int deleteArticle(int boardId);

	public int updateArticle(BoardVO boardVO);
	
}
