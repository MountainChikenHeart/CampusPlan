package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.CommunityInfoView;

import java.util.List;

/**
 * (CommunityInfoView)表服务接口
 *
 * @author yyw
 */
public interface CommunityInfoViewService {

    /**
     * 查询单条
     */
    CommunityInfoView selectone();

    /**
     * 新增
     */
    CommunityInfoView insert(CommunityInfoView communityInfoView);

    /**
     * 修改
     */
    boolean update(CommunityInfoView communityInfoView);

    /**
     * 删除
     */
    boolean delete(CommunityInfoView communityInfoView);

    /**
     * 分页查询
     */
    List<CommunityInfoView> selectpage(CommunityInfoView communityInfoView);

    /**
     * 查询所有
     */
    List<CommunityInfoView> selectall(CommunityInfoView communityInfoView);

    List<CommunityInfoView> selectallLimit(CommunityInfoView communityInfoView);
}
