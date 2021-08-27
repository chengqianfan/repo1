package com.lagou.domain;

import lombok.Data;

import java.util.Date;


@Data
public class User_Role_relation {

    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedby;
}
