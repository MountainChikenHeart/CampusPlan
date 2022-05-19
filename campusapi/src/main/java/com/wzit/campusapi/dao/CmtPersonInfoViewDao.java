package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.CmtPersonInfoView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (CmtPersonInfoView)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface CmtPersonInfoViewDao {

    /**
     * 查询单条数据
     */
    CmtPersonInfoView selectone();

    /**
     * 分页查询
     */
    List<CmtPersonInfoView> selectpage(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 查询所有
     */
    List<CmtPersonInfoView> selectall(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 新增数据
     */
    int insert(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 修改数据
     */
    int update(CmtPersonInfoView cmtPersonInfoView);

    /**
     * 删除数据
     */
    int delete();

}

