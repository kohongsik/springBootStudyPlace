package com.hs.springboot.study.board.entity;

import com.hs.springboot.study.common.entity.CommonSearchVO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardSearchVO extends CommonSearchVO {
    private String boardIdx;
    private String title;
    private String contents;
    private String hitCnt;
    private String deleteYn;
    private String idx;
}
