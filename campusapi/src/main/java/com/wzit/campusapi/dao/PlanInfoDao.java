package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.PlanInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务计划表(PlanInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface PlanInfoDao {

    /**
     * 查询单条数据
     */
    PlanInfo selectone(Long planId);

    /**
     * 分页查询
     */
    List<PlanInfo> selectpage(PlanInfo planInfo);

    /**
     * 查询所有
     */
    List<PlanInfo> selectall(PlanInfo planInfo);

    /**
     * 新增数据
     */
    int insert(PlanInfo planInfo);

    /**
     * 修改数据
     */
    int update(PlanInfo planInfo);

    /**
     * 删除数据
     */
    int delete(Long planId);

}

