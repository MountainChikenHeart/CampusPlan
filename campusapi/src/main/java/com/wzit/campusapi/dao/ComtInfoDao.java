package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.ComtInfo;
import com.wzit.campusapi.entity.UserComtUrge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子任务表(ComtInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface ComtInfoDao {

    /**
     * 查询单条数据
     */
    ComtInfo selectone(Long comtId);

    /**
     * 分页查询
     */
    List<ComtInfo> selectpage(ComtInfo comtInfo);

    /**
     * 查询所有
     */
    List<ComtInfo> selectall(ComtInfo comtInfo);

    /**
     * 新增数据
     */
    int insert(ComtInfo comtInfo);

    /**
     * 修改数据
     */
    int update(ComtInfo comtInfo);

    /**
     * 删除数据
     */
    int delete(Long comtId);

    /**
     * 插入提醒
     */
    int insertUrge(UserComtUrge userComtUrge);

    boolean updateUrge(UserComtUrge userComtUrge);

    UserComtUrge selectUrge(UserComtUrge userComtUrge);
}

