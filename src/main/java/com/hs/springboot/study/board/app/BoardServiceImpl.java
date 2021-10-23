package com.hs.springboot.study.board.app;

import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import com.hs.springboot.study.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper boardMapper;
    @Override
    public List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO) {
        return boardMapper.selectBoardList(boardSearchVO);
    }
}
