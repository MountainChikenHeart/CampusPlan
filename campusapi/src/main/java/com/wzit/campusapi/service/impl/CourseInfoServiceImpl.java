package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.CourseInfo;
import com.wzit.campusapi.dao.CourseInfoDao;
import com.wzit.campusapi.service.CourseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 课程表(CourseInfo)表服务实现类
 *
 * @author yyw
 */
@Service("courseInfoService")
public class CourseInfoServiceImpl implements CourseInfoService {

    @Resource
    private CourseInfoDao courseInfoDao;

    /**
     * 查询单条
     */
    @Override
    public CourseInfo selectone(Long courseId) {
        return this.courseInfoDao.selectone(courseId);
    }

    /**
     * 新增
     */
    @Override
    public CourseInfo insert(CourseInfo courseInfo) {
        this.courseInfoDao.insert(courseInfo);
        return courseInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(CourseInfo courseInfo) {
        if ((courseInfo.getCourseId() + "").isEmpty()) {
            return false;
        } else {
            return this.courseInfoDao.update(courseInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(CourseInfo courseInfo) {
        if ((courseInfo.getCourseId() + "").isEmpty()) {
            return false;
        } else {
            return this.courseInfoDao.delete(courseInfo.getCourseId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<CourseInfo> selectpage(CourseInfo courseInfo) {
        if (courseInfo.getPageNum() != 0) {
            int pageSize = courseInfo.getPageSize();
            int pageNum = (courseInfo.getPageNum() - 1) * pageSize;
            courseInfo.setPageNum(pageNum);
        }
        return this.courseInfoDao.selectpage(courseInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CourseInfo> selectall(CourseInfo courseInfo) {
        return this.courseInfoDao.selectall(courseInfo);
    }
}
