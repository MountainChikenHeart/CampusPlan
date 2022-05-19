package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.CommunityInfoView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (CommunityInfoView)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface CommunityInfoViewDao {

    /**
     * 查询单条数据
     */
    CommunityInfoView selectone();

    /**
     * 分页查询
     */
    List<CommunityInfoView> selectpage(CommunityInfoView communityInfoView);

    /**
     * 查询所有
     */
    List<CommunityInfoView> selectall(CommunityInfoView communityInfoView);

    /**
     * 新增数据
     */
    int insert(CommunityInfoView communityInfoView);

    /**
     * 修改数据
     */
    int update(CommunityInfoView communityInfoView);

    /**
     * 删除数据
     */
    int delete();

    List<CommunityInfoView> selectallLimit(CommunityInfoView communityInfoView);
}

