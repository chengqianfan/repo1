package com.lagou.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PromotionSpace {

    private Integer id;
    private String name;
    private String spaceKey;
    private Date createTime;
    private Date updateTime;
    private Integer isDel;

}
