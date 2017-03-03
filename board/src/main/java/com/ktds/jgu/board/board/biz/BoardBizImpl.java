package com.ktds.jgu.board.board.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.jgu.board.board.dao.BoardDao;
import com.ktds.jgu.board.board.dao.BoardDaoImpl;
import com.ktds.jgu.board.board.vo.BoardSearchVO;
import com.ktds.jgu.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardBizImpl implements BoardBiz {

    private BoardDao boardDao;

    public BoardBizImpl() {
        this.boardDao = new BoardDaoImpl();
    }

    @Override
    public boolean writeArticle(BoardVO boardVO) {
        return boardDao.insertArticle(boardVO) > 0;
    }

    @Override
    public List<BoardVO> getAllArticles(BoardSearchVO boardSearchVO) {
    	
    	// TODO 게시글의 개수를 조회해 Pager 객체에 할당한다.
    	// 게시글의 개수가 0이라면, 비어있는 리스트를 리턴하고
    	// 그렇지 않다면, getAllArticles();를 리턴한다.
    	int articleCount = boardDao.getAllArticlesCount(boardSearchVO);
    	boardSearchVO.getPager().setTotalArticleCount(articleCount);
    	
    	if( articleCount == 0 ) {
    		return new ArrayList<BoardVO>();
    	}
    	else {
    		return boardDao.selectAllArticles(boardSearchVO);
    	}
    }

    @Override
    public BoardVO getOneArticles(int boardId) {
        return boardDao.selectOneArticles(boardId);
    }

    @Override
    public BoardVO removeOneArticle(int boardId) {
        return boardDao.removeOneArticle(boardId);
    }
}
