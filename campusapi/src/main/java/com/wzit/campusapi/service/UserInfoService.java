package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.UserInfo;

import java.util.List;

/**
 * 用户表(UserInfo)表服务接口
 *
 * @author yyw
 */
public interface UserInfoService {

    /**
     * 查询单条
     */
    UserInfo selectone(Long userId);

    /**
     * 新增
     */
    UserInfo insert(UserInfo userInfo);

    /**
     * 修改
     */
    boolean update(UserInfo userInfo);

    /**
     * 删除
     */
    boolean delete(UserInfo userInfo);

    /**
     * 分页查询
     */
    List<UserInfo> selectpage(UserInfo userInfo);

    /**
     * 查询所有
     */
    List<UserInfo> selectall(UserInfo userInfo);
}
