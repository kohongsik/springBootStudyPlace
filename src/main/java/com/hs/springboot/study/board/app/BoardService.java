package com.hs.springboot.study.board.app;

import com.hs.springboot.study.board.entity.BoardFileVO;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO);
    int insertBoard(BoardVO boardVO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
    BoardVO selectBoardDetail(BoardSearchVO boardSearchVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    BoardFileVO selectBoardFileDetail(BoardSearchVO boardSearchVO);
}
