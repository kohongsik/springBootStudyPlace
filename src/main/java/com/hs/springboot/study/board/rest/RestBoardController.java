package com.hs.springboot.study.board.rest;

import com.hs.springboot.study.board.app.BoardService;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/api/board")
@RequiredArgsConstructor
public class RestBoardController {
    final BoardService boardService;
    @GetMapping(value = "/")
    public ResponseEntity openBoardList(BoardSearchVO searchVO) {
        return ResponseEntity.ok(boardService.selectBoardList(searchVO));
    }
    @PostMapping(value = "/insert")
    public ResponseEntity insertBoard(@RequestBody BoardVO boardVO) throws Exception {
        return ResponseEntity.ok(boardService.insertBoard(boardVO, null));
    }
    @GetMapping(value="/{boardIdx}")
    public ResponseEntity openBoardDetail(@PathVariable("boardIdx") int boardIdx) {
        return ResponseEntity.ok(boardService.selectBoardDetail(BoardSearchVO
                .builder()
                .boardIdx(String.valueOf(boardIdx))
                .build()));
    }
    @PutMapping(value = "/{boardIdx}")
    public ResponseEntity updateBoard(@RequestBody BoardVO boardVO) throws Exception {
        // fixme 첨부파일 확인 하기.
        return ResponseEntity.ok(boardService.updateBoard(boardVO));
    }
    @DeleteMapping(value = "/{boardIdx}")
    public ResponseEntity deleteBoard(@PathVariable("boardIdx") int boardIdx) {
        return ResponseEntity.ok(boardService.deleteBoard(
                BoardVO.builder().boardIdx(String.valueOf(boardIdx)).build()
        ));
    }

}
