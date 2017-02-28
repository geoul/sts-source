package com.ktds.jgu.board.board.biz;

import java.util.List;

import com.ktds.jgu.board.board.dao.BoardDao;
import com.ktds.jgu.board.board.dao.BoardDaoImpl;
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
    public List<BoardVO> getAllArticles() {
        return boardDao.selectAllArticles();
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
