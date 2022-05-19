package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.CmtPersonInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子成员表(CmtPersonInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface CmtPersonInfoDao {

    /**
     * 查询单条数据
     */
    CmtPersonInfo selectone(Long cmtPersonId);

    /**
     * 分页查询
     */
    List<CmtPersonInfo> selectpage(CmtPersonInfo cmtPersonInfo);

    /**
     * 查询所有
     */
    List<CmtPersonInfo> selectall(CmtPersonInfo cmtPersonInfo);

    /**
     * 新增数据
     */
    int insert(CmtPersonInfo cmtPersonInfo);

    /**
     * 修改数据
     */
    int update(CmtPersonInfo cmtPersonInfo);

    /**
     * 删除数据
     */
    int delete(Long cmtPersonId);
}

