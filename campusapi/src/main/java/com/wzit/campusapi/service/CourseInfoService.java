package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.CourseInfo;

import java.util.List;

/**
 * 课程表(CourseInfo)表服务接口
 *
 * @author yyw
 */
public interface CourseInfoService {

    /**
     * 查询单条
     */
    CourseInfo selectone(Long courseId);

    /**
     * 新增
     */
    CourseInfo insert(CourseInfo courseInfo);

    /**
     * 修改
     */
    boolean update(CourseInfo courseInfo);

    /**
     * 删除
     */
    boolean delete(CourseInfo courseInfo);

    /**
     * 分页查询
     */
    List<CourseInfo> selectpage(CourseInfo courseInfo);

    /**
     * 查询所有
     */
    List<CourseInfo> selectall(CourseInfo courseInfo);
}
