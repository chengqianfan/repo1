package com.lagou.domain;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVo {

    private Integer roleId;

    private List<Integer> menuIdList;

}
