package com.wzit.campusapi.dao;

import com.wzit.campusapi.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表(UserInfo)表数据库访问层
 *
 * @author yyw
 */
@Mapper
public interface UserInfoDao {

    /**
     * 查询单条数据
     */
    UserInfo selectone(Long userId);

    /**
     * 分页查询
     */
    List<UserInfo> selectpage(UserInfo userInfo);

    /**
     * 查询所有
     */
    List<UserInfo> selectall(UserInfo userInfo);

    /**
     * 新增数据
     */
    int insert(UserInfo userInfo);

    /**
     * 修改数据
     */
    int update(UserInfo userInfo);

    /**
     * 删除数据
     */
    int delete(Long userId);

}

