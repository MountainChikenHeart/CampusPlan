package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.SubComtInfo;
import com.wzit.campusapi.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子子任务提交表(SubComtInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface SubComtInfoDao {

    /**
     * 查询单条数据
     */
    SubComtInfo selectone(Long subComtId);

    /**
     * 分页查询
     */
    List<SubComtInfo> selectpage(SubComtInfo subComtInfo);

    /**
     * 查询所有
     */
    List<SubComtInfo> selectall(SubComtInfo subComtInfo);

    /**
     * 新增数据
     */
    int insert(SubComtInfo subComtInfo);

    /**
     * 修改数据
     */
    int update(SubComtInfo subComtInfo);

    /**
     * 删除数据
     */
    int delete(Long subComtId);

    /**
     * 删除该任务所有的提交数据
     */
    int deleteAll(Long comtId);

    Integer selectcount(SubComtInfo subComtInfo);
}

