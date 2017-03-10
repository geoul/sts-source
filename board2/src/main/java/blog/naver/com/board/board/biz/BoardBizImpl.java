package blog.naver.com.board.board.biz;

import java.util.ArrayList;
import java.util.List;

import blog.naver.com.board.board.dao.BoardDao;
import blog.naver.com.board.board.dao.BoardDaoImpl;
import blog.naver.com.board.board.vo.BoardSearchVO;
import blog.naver.com.board.board.vo.BoardVO;

public class BoardBizImpl implements BoardBiz {

	private BoardDao boardDao;

	public BoardBizImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public List<BoardVO> getAllAtricles(BoardSearchVO boardSearchVO) {
		
		// TODO 게시글의 개수를 조회해 Pager객체에 할당한다.
		// 게시글의 개수가 0이라면, 비어있는 리스트를 리턴하고
		// 그렇지 않다면, getAllArticles();를 리턴한다.
		int articleCount = boardDao.getAllArticlesCount(boardSearchVO);
		boardSearchVO.getPager().setTotalArticleCount(articleCount);
		
		if ( articleCount == 0 ) {
			return new ArrayList<BoardVO>();
		}
		else {
			return boardDao.getAllArticles(boardSearchVO);
		}
	}

	@Override
	public boolean writeArticle(BoardVO boardVO) {
		return boardDao.addArticle(boardVO) > 0;
	}

	@Override
	public BoardVO getOneArticle(int boardId) {
		BoardVO board = boardDao.getOneArticle(boardId);
		return board;
	}

	@Override
	public boolean deleteArticle(int boardId) {
		return boardDao.deleteArticle(boardId) > 0;
	}

	@Override
	public boolean updateArticle(BoardVO boardVO) {
		return boardDao.updateArticle(boardVO) > 0;
	}

}
