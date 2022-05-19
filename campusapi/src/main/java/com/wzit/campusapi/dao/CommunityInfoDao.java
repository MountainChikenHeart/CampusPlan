package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.CommunityInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子表(CommunityInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface CommunityInfoDao {

    /**
     * 查询单条数据
     */
    CommunityInfo selectone(Long communityId);

    /**
     * 分页查询
     */
    List<CommunityInfo> selectpage(CommunityInfo communityInfo);

    /**
     * 查询所有
     */
    List<CommunityInfo> selectall(CommunityInfo communityInfo);

    /**
     * 新增数据
     */
    int insert(CommunityInfo communityInfo);

    /**
     * 修改数据
     */
    int update(CommunityInfo communityInfo);

    /**
     * 删除数据
     */
    int delete(Long communityId);
}

