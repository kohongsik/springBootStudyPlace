package com.hs.springboot.study.common.util;

import com.hs.springboot.study.board.entity.BoardFileVO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {
    public List<BoardFileVO> parseFileInfo (String boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if (ObjectUtils.isEmpty(multipartHttpServletRequest)) return null;
        List<BoardFileVO> boardFileVOList = new ArrayList<>();
        // file path 및 폴더 생성
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();
        String path = "attchedFiles/" + current.format(dateTimeFormatter);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // convert to fileVO
        Iterator iterator = multipartHttpServletRequest.getFileNames();
        String newFileName, originFileExtension, contentType;
        while(iterator.hasNext()) {
            String name = String.valueOf(iterator.next());
            List<MultipartFile> files = multipartHttpServletRequest.getFiles(name);
            for (MultipartFile multipartFile : files) {
                 if (!multipartFile.isEmpty()) {
                     contentType = multipartFile.getContentType();
                     if (ObjectUtils.isEmpty(contentType)) break;
                     if (contentType.contains("image/jpeg")) {
                         originFileExtension = ".jpg";
                     } else if (contentType.contains("image/png")) {
                         originFileExtension = ".png";
                     } else if (contentType.contains("image/gif")) {
                         originFileExtension = ".gif";
                     } else break;
                     newFileName = Long.toString(System.nanoTime()) + originFileExtension;
                     BoardFileVO boardFileVO = BoardFileVO
                             .builder()
                             .boardIdx(boardIdx)
                             .fileSize(String.valueOf(multipartFile.getSize()))
                             .originalFileName(multipartFile.getOriginalFilename())
                             .storedFilePath(path + "/" + newFileName)
                             .deleteYn("N")
                             .build();
                     boardFileVOList.add(boardFileVO);

                     file = new File(path + "/" + newFileName);
                     multipartFile.transferTo(file);
                 }
            }
        }

        return boardFileVOList;
    }
}
