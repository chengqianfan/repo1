package com.lagou.domain;

import lombok.Data;

@Data
public class MenuVo {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer id;

}
