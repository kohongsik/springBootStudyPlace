package com.hs.springboot.study.board.mapper;

import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO);
    int insertBoard(BoardVO boardVO);
    BoardVO selectBoardDetail(BoardSearchVO boardSearchVO);
    int updateHitCnt(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
}
