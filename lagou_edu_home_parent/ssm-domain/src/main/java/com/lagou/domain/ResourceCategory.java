package com.lagou.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceCategory {

    private Integer id;
    private String name;
    private Integer sort;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;

}
