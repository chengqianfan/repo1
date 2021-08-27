package com.lagou.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Resource {

    private Integer id;
    private String name;
    private String url;
    private Integer categoryId;
    private String description;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedBy;
}
