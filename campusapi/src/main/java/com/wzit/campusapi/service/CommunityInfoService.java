package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.CommunityInfo;

import java.util.List;

/**
 * 圈子表(CommunityInfo)表服务接口
 *
 * @author yyw
 */
public interface CommunityInfoService {

    /**
     * 查询单条
     */
    CommunityInfo selectone(Long communityId);

    /**
     * 新增
     */
    CommunityInfo insert(CommunityInfo communityInfo);

    /**
     * 修改
     */
    boolean update(CommunityInfo communityInfo);

    /**
     * 删除
     */
    boolean delete(CommunityInfo communityInfo);

    /**
     * 分页查询
     */
    List<CommunityInfo> selectpage(CommunityInfo communityInfo);

    /**
     * 查询所有
     */
    List<CommunityInfo> selectall(CommunityInfo communityInfo);
}
