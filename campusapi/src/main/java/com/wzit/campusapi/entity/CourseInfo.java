package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程表(CourseInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 11:00:32
 */
public class CourseInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -24095375808815053L;
    /**
     * 主键ID
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程编号
     */
    private String courseNum;
    /**
     * 教室
     */
    private String coursePlace;
    /**
     * 教室
     */
    private String courseTeacher;
    /**
     * 星期
     */
    private String courseWeek;
    /**
     * 上课时间
     */
    private String courseTime;
    /**
     * 节次
     */
    private String courseSection;
    /**
     * 用户ID
     */
    private Long userId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(String courseWeek) {
        this.courseWeek = courseWeek;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

