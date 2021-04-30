package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseConentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseConentServiceImpl implements CourseConentService {

    @Autowired
    private CourseContentMapper courseContentMapper;


    @Override
    //根据课程ID查询对应的课程内容（章节+课时）
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    @Override
    //回显章节对应的课程信息
    public Course findCourseByCourseId(int courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    //新增章节信息
    public void saveSection(CourseSection courseSection) {
        // 1.补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //2. 调courseContentMapper方法
        courseContentMapper.saveSection(courseSection);

    }

    @Override
    //更新章节信息
    public void updateSection(CourseSection courseSection) {

        // 1. 补全信息
        courseSection.setUpdateTime(new Date());

        //2.调courseContentMapper方法
        courseContentMapper.updateSection(courseSection);


    }

    @Override
    //修改章节状态
    public void updateSectionStatus(int id, int status) {

        // 封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        // 调用mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    //保存课时信息
    public void saveLesson(CourseLesson courseLesson) {
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveLesson(courseLesson);
    }

    @Override
    //更新课时信息
    public void updateLesson(CourseLesson courseLesson) {
        courseLesson.setUpdateTime(new Date());
        courseContentMapper.updateLesson(courseLesson);
    }


}
