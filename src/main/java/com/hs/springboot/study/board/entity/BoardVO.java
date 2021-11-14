package com.hs.springboot.study.board.entity;

import com.hs.springboot.study.common.entity.CommonVO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BoardVO extends CommonVO {
    private String boardIdx;
    private String title;
    private String contents;
    private String hitCnt;
    private String deleteYn;
    List<BoardFileVO> boardFileVOList;
}
