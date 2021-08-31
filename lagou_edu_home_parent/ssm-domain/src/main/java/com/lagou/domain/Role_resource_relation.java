package com.lagou.domain;


import lombok.Data;

import java.util.Date;

@Data
public class Role_resource_relation {

  private Integer id;
  private int resourceId;
  private int roleId;
  private Date createdTime;
  private Date updatedTime;
  private String createdBy;
  private String updatedBy;


}
