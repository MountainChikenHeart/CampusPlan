package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.PlanInfo;

import java.util.List;

/**
 * 任务计划表(PlanInfo)表服务接口
 *
 * @author yyw
 */
public interface PlanInfoService {

    /**
     * 查询单条
     */
    PlanInfo selectone(Long planId);

    /**
     * 新增
     */
    PlanInfo insert(PlanInfo planInfo);

    /**
     * 修改
     */
    boolean update(PlanInfo planInfo);

    /**
     * 删除
     */
    boolean delete(PlanInfo planInfo);

    /**
     * 分页查询
     */
    List<PlanInfo> selectpage(PlanInfo planInfo);

    /**
     * 查询所有
     */
    List<PlanInfo> selectall(PlanInfo planInfo);
}
