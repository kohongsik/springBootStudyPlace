package com.hs.springboot.study.board.rest;

import com.hs.springboot.study.board.app.BoardService;
import com.hs.springboot.study.board.entity.BoardFileVO;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import com.mysql.cj.log.LogFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public String insertBoard (BoardVO boardVO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        int ret = boardService.insertBoard(boardVO, multipartHttpServletRequest);
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
    @GetMapping(value = "/downloadBoardFile.do")
    public void downloadBoardFile (BoardSearchVO boardSearchVO, HttpServletResponse httpServletResponse) throws Exception {
        BoardFileVO boardFileVO = boardService.selectBoardFileDetail(boardSearchVO);
        if (boardFileVO != null) {
            String fileName = boardFileVO.getOriginalFileName();
            byte[] file = FileUtils.readFileToByteArray(new File(boardFileVO.getStoredFilePath()));
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.setContentLength(file.length);
            httpServletResponse.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            httpServletResponse.setHeader("Content-Transfer-Encoding", "binary");
            OutputStream outputStream = httpServletResponse.getOutputStream();
            outputStream.write(file);
            outputStream.flush();
            outputStream.close();
        }
    }

}
