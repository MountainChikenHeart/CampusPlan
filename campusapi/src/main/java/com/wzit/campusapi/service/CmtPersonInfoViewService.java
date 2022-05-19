package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.CmtPersonInfoView;

import java.util.List;

/**
 * (CmtPersonInfoView)表服务接口
 *
 * @author yyw
 */
public interface CmtPersonInfoViewService {

    /**
     * 查询单条
     */
    CmtPersonInfoView selectone();

    /**
     * 新增
     */
    CmtPersonInfoView insert(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 修改
     */
    boolean update(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 删除
     */
    boolean delete(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 分页查询
     */
    List<CmtPersonInfoView> selectpage(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 查询所有
     */
    List<CmtPersonInfoView> selectall(CmtPersonInfoView cmtPersonInfoView);
}
