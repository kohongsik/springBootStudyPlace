package com.hs.springboot.study.sample.rest;

import com.hs.springboot.study.board.app.BoardService;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sample")
@RequiredArgsConstructor
public class SampleController {
    final BoardService boardService;
    @GetMapping(value = "/boards")
    public ResponseEntity boardsGet () {
        return ResponseEntity.ok(boardService.selectBoardList(BoardSearchVO.builder().build()));
    }
    @PostMapping(value = "/boards")
    public ResponseEntity boardsPost () {
        return ResponseEntity.ok(boardService.selectBoardList(BoardSearchVO.builder().build()));
    }
}
