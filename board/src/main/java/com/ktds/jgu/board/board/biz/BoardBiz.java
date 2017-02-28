package com.ktds.jgu.board.board.biz;

import java.util.List;

import com.ktds.jgu.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardBiz {

    public boolean writeArticle(BoardVO boardVO);   // insertArticle()을 호출

    public List<BoardVO> getAllArticles();   // selectArticles()를 호츨

    public BoardVO getOneArticles(int boardId);   // selectOne()을 호출

    public BoardVO removeOneArticle(int boardId);   // removeOne

}
