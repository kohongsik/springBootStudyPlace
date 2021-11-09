package com.hs.springboot.study.board.rest;

import com.hs.springboot.study.board.app.BoardService;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import com.mysql.cj.log.LogFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {
    private Logger logger = LoggerFactory.getLogger("board");
    final BoardService boardService;

    @RequestMapping(value = "/openBoardList.do")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList");
        logger.debug("open Board list");
        List<BoardVO> list = boardService.selectBoardList(BoardSearchVO.builder().build());
        mv.addObject("list", list);

        return mv;
    }
    @PostMapping(value = "/insertBoard.do")
    public String insertBoard (BoardVO boardVO) {
        int ret = boardService.insertBoard(boardVO);
        if (ret > 0) {
            System.out.println("등록 성공...!!");
        }
        return "redirect:/board/openBoardList.do";
    }
    @GetMapping(value = "/openBoardWrite.do")
    public String boardWrting() {
        return "/board/boardWrite";
    }
    @GetMapping(value = "/openBoardDetail.do")
    public ModelAndView getBoardDetail(BoardSearchVO boardSearchVO) throws Exception{
        ModelAndView mv = new ModelAndView("/board/boardDetail");
        BoardVO boardVO = boardService.selectBoardDetail(boardSearchVO);
        mv.addObject("board", boardVO);
        return mv;
    }
    @PostMapping(value = "/updateBoard.do")
    public String updateBoard(BoardVO boardVO) {
        boardService.updateBoard(boardVO);
        return "redirect:/board/openBoardList.do";
    }
    @PostMapping(value = "/deleteBoard.do")
    public String deleteBoard(BoardVO boardVO) {
        boardService.deleteBoard(boardVO);
        return "redirect:/board/openBoardList.do";
    }
}
