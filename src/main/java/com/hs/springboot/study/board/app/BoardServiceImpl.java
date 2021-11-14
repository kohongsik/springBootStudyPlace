package com.hs.springboot.study.board.app;

import com.hs.springboot.study.board.entity.BoardFileVO;
import com.hs.springboot.study.board.entity.BoardSearchVO;
import com.hs.springboot.study.board.entity.BoardVO;
import com.hs.springboot.study.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import com.hs.springboot.study.common.util.FileUtils;
@Service
@Transactional
@RequiredArgsConstructor
// @Slf4j
public class BoardServiceImpl implements BoardService{
    final BoardMapper boardMapper;
    final FileUtils fileUtils;
    Logger log = LoggerFactory.getLogger("board");
    @Override
    public List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO) {
        return boardMapper.selectBoardList(boardSearchVO);
    }
    @Override
    public int insertBoard(BoardVO boardVO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        int ret = 0;
        boardMapper.insertBoard(boardVO);
        BoardVO myBoardInfo = boardMapper.selectBoardDetailRecentOne(BoardSearchVO.builder().build());
        if (myBoardInfo == null) return ret;
        // board File 삭제..
        boardMapper.deleteBoardFile(boardVO);
        List<BoardFileVO> boardFileVOList = fileUtils.parseFileInfo(myBoardInfo.getBoardIdx(), multipartHttpServletRequest);
        if (boardFileVOList != null) {
            for (BoardFileVO fileVO : boardFileVOList) {
                boardMapper.createBoardFile(fileVO);
            }
        }
//        if (!ObjectUtils.isEmpty(multipartHttpServletRequest)) {
//            Iterator iterator = multipartHttpServletRequest.getFileNames();
//            while(iterator.hasNext()) {
//                String fileName = String.valueOf(iterator.next());
//                log.debug("file tab name is " + fileName);
//                List<MultipartFile> list = multipartHttpServletRequest.getFiles(fileName);
//                for (MultipartFile file : list) {
//                    log.debug("start file info");
//                    log.debug("key of this FileObject is : " + fileName);
//                    log.debug("file OriginalFilename : " + file.getOriginalFilename());
//                    log.debug("file Si ze : " + file.getSize());
//                    log.debug("file ContentType : " + file.getContentType());
//                    log.debug("end file info");
//                }
//            }
//        }

        // fileUtils.parseFileInfo(boardVO.getBoardIdx(), multipartHttpServletRequest);
        return ret;
    }
    @Override
    public BoardVO selectBoardDetail(BoardSearchVO boardSearchVO) {
        boardMapper.updateHitCnt(
                BoardVO.builder().boardIdx(boardSearchVO.getBoardIdx()).build()
        );
        BoardVO boardVO = boardMapper.selectBoardDetail(boardSearchVO);
        if (boardVO != null) {
            boardVO.setBoardFileVOList(boardMapper.selectBoardFileList(
                    BoardSearchVO.builder().boardIdx(boardVO.getBoardIdx()).build()
            ));
        }
        return boardVO;
    }
    @Override
    public int updateBoard(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO);
    }
    @Override
    public int deleteBoard(BoardVO boardVO) {
        return boardMapper.deleteBoard(boardVO);
    }
    @Override
    public BoardFileVO selectBoardFileDetail(BoardSearchVO boardSearchVO) {
        return boardMapper.selectBoardFileDetail(boardSearchVO);
    }
}
