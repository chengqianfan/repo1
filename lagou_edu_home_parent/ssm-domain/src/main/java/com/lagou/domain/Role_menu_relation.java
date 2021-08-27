package com.lagou.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Role_menu_relation {

    private Integer id;
    private Integer menuId;
    private Integer roleId;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;
    private String updatedby;

}
