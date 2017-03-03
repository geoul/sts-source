package com.ktds.jgu.board.board.dao;

import java.util.List;

import com.ktds.jgu.board.board.vo.BoardSearchVO;
import com.ktds.jgu.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardDao {
	
	public int getAllArticlesCount(BoardSearchVO boardSearchVO);

    public int insertArticle(BoardVO boardVO);

    public List<BoardVO> selectAllArticles(BoardSearchVO boardSearchVO);

    public BoardVO selectOneArticles(int boardId);

    public BoardVO removeOneArticle(int boardId);
    
    public BoardVO updateOneArticle(BoardVO boardVO);

}
