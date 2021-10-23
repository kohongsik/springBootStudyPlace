package com.hs.springboot.study.board.app;

import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;

import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO);
    int insertBoard(BoardVO boardVO);
    BoardVO selectBoardDetail(BoardSearchVO boardSearchVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
}
