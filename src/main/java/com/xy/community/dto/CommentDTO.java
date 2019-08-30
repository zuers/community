package com.xy.community.dto;

import lombok.Data;

/**
 * @Author:Kori
 * @Description:
 * @Date: Create in 11:23 2019/8/30
 * @Modified by:
 */
@Data
public class CommentDTO {
    private Long parentId;
    private  String content;
    private Integer type;
}
