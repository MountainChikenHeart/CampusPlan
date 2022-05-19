package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.ComtInfo;
import com.wzit.campusapi.entity.UserComtUrge;

import java.util.List;

/**
 * 圈子任务表(ComtInfo)表服务接口
 *
 * @author yyw
 */
public interface ComtInfoService {

    /**
     * 查询单条
     */
    ComtInfo selectone(Long comtId);

    /**
     * 新增
     */
    ComtInfo insert(ComtInfo comtInfo);

    /**
     * 修改
     */
    boolean update(ComtInfo comtInfo);

    /**
     * 删除
     */
    boolean delete(ComtInfo comtInfo);

    /**
     * 分页查询
     */
    List<ComtInfo> selectpage(ComtInfo comtInfo);

    /**
     * 查询所有
     */
    List<ComtInfo> selectall(ComtInfo comtInfo);

    boolean updateUrge(UserComtUrge userComtUrge);

    boolean selectUrge(UserComtUrge userComtUrge);
}
