package com.hs.springboot.study.board.entity;

import com.hs.springboot.study.common.entity.CommonVO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileVO extends CommonVO {
    private String idx;
    private String boardIdx;
    private String originalFileName;
    private String storedFilePath;
    private String fileSize;
    private String deleteYn;
}
