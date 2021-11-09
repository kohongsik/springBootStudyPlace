package com.hs.springboot.study.board.app;

import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import com.hs.springboot.study.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    final BoardMapper boardMapper;
    @Override
    public List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO) {
        return boardMapper.selectBoardList(boardSearchVO);
    }
    @Override
    public int insertBoard(BoardVO boardVO) {
        return boardMapper.insertBoard(boardVO);
    }
    @Override
    public BoardVO selectBoardDetail(BoardSearchVO boardSearchVO) {
        boardMapper.updateHitCnt(
                BoardVO.builder().boardIdx(boardSearchVO.getBoardIdx()).build()
        );
        return boardMapper.selectBoardDetail(boardSearchVO);
    }
    @Override
    public int updateBoard(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO);
    }
    @Override
    public int deleteBoard(BoardVO boardVO) {
        return boardMapper.deleteBoard(boardVO);
    }
}
