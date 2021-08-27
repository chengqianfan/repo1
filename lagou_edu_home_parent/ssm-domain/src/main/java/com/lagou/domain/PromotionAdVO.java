package com.lagou.domain;


import lombok.Data;

@Data
public class PromotionAdVO {

    //当前页
    private Integer currentPage;

    //每页显示的条数
    private Integer pageSize;

}
