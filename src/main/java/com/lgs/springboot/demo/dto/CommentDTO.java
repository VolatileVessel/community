package com.lgs.springboot.demo.dto;

import lombok.Data;

/**
 * @author GS
 * @date 2020/4/22
 **/
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
