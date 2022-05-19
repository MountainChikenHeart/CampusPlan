package com.wzit.campusapi.controller;

import com.wzit.campusapi.entity.CourseInfo;
import com.wzit.campusapi.service.CourseInfoService;
import org.springframework.web.bind.annotation.*;
import com.wzit.campusapi.web.BaseController;
import com.wzit.campusapi.web.BaseEntity;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.wzit.campusapi.utils.ResultUtils.*;
import static com.wzit.campusapi.utils.ResultCode.*;
import static com.wzit.campusapi.utils.ResultMsg.*;
import static com.wzit.campusapi.utils.WzitUtils.*;

/**
 * 课程表(CourseInfo)表控制层
 *
 * @author yyw
 */
@RestController
@RequestMapping("/courseInfo")
public class CourseInfoController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseInfoService courseInfoService;

    /**
     * 添加
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody CourseInfo courseInfo) {
        courseInfo.setCreateTime(new Date());
        if (SaTokenCheck() == 1) {
            CourseInfo ins = courseInfoService.insert(courseInfo);
            if (ins == null) {
                return ResultErr(1);
            }
            return ResultOK(1, ins, 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody CourseInfo courseInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = courseInfoService.delete(courseInfo);
            if (!b) {
                return ResultErr(2);
            }
            return ResultOK(2, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody CourseInfo courseInfo) {
        if (SaTokenCheck() == 1) {
            boolean b = courseInfoService.update(courseInfo);
            if (!b) {
                return ResultErr(3);
            }
            return ResultOK(3, new HashMap<>(), 1);
        } else {
            return NoSatoken();
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/selectpage")
    public Object selectpage(@RequestBody CourseInfo courseInfo) {
        int pageNum = courseInfo.getPageNum();
        List<CourseInfo> list = courseInfoService.selectpage(courseInfo);
        return ResultApi(list, pageNum, courseInfo.getPageSize());
    }

    /**
     * 查询所有
     */
    @PostMapping("/selectall")
    public Object selectall(@RequestBody CourseInfo courseInfo) {
        if (SaTokenCheck() == 1) {
            List<CourseInfo> list = courseInfoService.selectall(courseInfo);
            if (list.size() <= 0) {
                return ResultErr(4);
            }
            return ResultOK(4, list, list.size());
        } else {
            return NoSatoken();
        }
    }
}

