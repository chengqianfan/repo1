package com.lagou.domain;

import lombok.Data;

import java.util.List;

/**
 * @author Sails
 * @create 2021-08-31 15:07
 */

@Data
public class RoleResourceVo {
    private Integer roleId;

    private List<Integer> resourceIdList;
}
