package com.lagou.domain;

import lombok.Data;

import java.util.Date;


@Data
public class User {

    // 用户id
    private Integer id;
    // 用户昵称
    private String name;
    // 用户头像地址
    private String portrait;
    // 注册手机
    private String phone;
    // 用户密码（可以为空，支持只用验证码注册、登录）
    private String password;
    // 注册ip
    private String regIp;
    // 是否有效用户
    private Integer accountNonExpired;
    // 账号是否未过期
    private Integer credentialsNonExpired;
    // 是否未锁定
    private Integer accountNonLocked;
    // 用户状态
    private String status;
    // 是否删除
    private Integer isDel;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

}
