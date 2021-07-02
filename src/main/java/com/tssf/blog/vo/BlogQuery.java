package com.tssf.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
