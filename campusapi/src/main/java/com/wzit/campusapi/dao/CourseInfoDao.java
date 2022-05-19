package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 课程表(CourseInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface CourseInfoDao {

    /**
     * 查询单条数据
     */
    CourseInfo selectone(Long courseId);

    /**
     * 分页查询
     */
    List<CourseInfo> selectpage(CourseInfo courseInfo);

    /**
     * 查询所有
     */
    List<CourseInfo> selectall(CourseInfo courseInfo);

    /**
     * 新增数据
     */
    int insert(CourseInfo courseInfo);

    /**
     * 修改数据
     */
    int update(CourseInfo courseInfo);

    /**
     * 删除数据
     */
    int delete(Long courseId);

}

