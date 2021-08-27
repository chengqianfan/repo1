package com.lagou.domain;


import lombok.Data;

@Data
public class ResourseVo {

    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private Integer categoryId;
    private String url;

}
