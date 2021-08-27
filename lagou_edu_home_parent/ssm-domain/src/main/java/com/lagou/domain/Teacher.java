package com.lagou.domain;

import lombok.Data;

import java.util.Date;

/**
 * 教师类
 * */

@Data
public class Teacher {

    //id
    private int id;

    //课程id
    private int courseId;

    //讲师姓名
    private String teacherName;

    //讲师职务
    private String position;

    //介绍
    private String description;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //是否删除
    private int isDel;
}
