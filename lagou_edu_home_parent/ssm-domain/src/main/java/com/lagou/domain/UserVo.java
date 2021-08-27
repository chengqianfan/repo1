package com.lagou.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
public class UserVo {

    private Integer currentPage;
    private Integer pageSize;

    // 多条件查询：用户名（手机号）
    private String username;
    // 注册起始时间 2020/11/11 2020-08-04
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    // 注册结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private List<Integer> roleIdList;

    private Integer userId;

}
