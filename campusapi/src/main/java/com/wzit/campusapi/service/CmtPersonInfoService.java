package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.CmtPersonInfo;

import java.util.List;

/**
 * 圈子成员表(CmtPersonInfo)表服务接口
 *
 * @author yyw
 */
public interface CmtPersonInfoService {

    /**
     * 查询单条
     */
    CmtPersonInfo selectone(Long cmtPersonId);

    /**
     * 新增
     */
    CmtPersonInfo insert(CmtPersonInfo cmtPersonInfo);

    /**
     * 修改
     */
    boolean update(CmtPersonInfo cmtPersonInfo);

    /**
     * 删除
     */
    boolean delete(CmtPersonInfo cmtPersonInfo);

    /**
     * 分页查询
     */
    List<CmtPersonInfo> selectpage(CmtPersonInfo cmtPersonInfo);

    /**
     * 查询所有
     */
    List<CmtPersonInfo> selectall(CmtPersonInfo cmtPersonInfo);
}
