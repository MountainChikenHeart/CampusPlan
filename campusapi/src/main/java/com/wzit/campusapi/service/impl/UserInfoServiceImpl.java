package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.UserInfo;
import com.wzit.campusapi.dao.UserInfoDao;
import com.wzit.campusapi.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户表(UserInfo)表服务实现类
 *
 * @author yyw
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 查询单条
     */
    @Override
    public UserInfo selectone(Long userId) {
        return this.userInfoDao.selectone(userId);
    }

    /**
     * 新增
     */
    @Override
    public UserInfo insert(UserInfo userInfo) {
        this.userInfoDao.insert(userInfo);
        return userInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(UserInfo userInfo) {
        if ((userInfo.getUserId() + "").isEmpty()) {
            return false;
        } else {
            return this.userInfoDao.update(userInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(UserInfo userInfo) {
        if ((userInfo.getUserId() + "").isEmpty()) {
            return false;
        } else {
            return this.userInfoDao.delete(userInfo.getUserId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<UserInfo> selectpage(UserInfo userInfo) {
        if (userInfo.getPageNum() != 0) {
            int pageSize = userInfo.getPageSize();
            int pageNum = (userInfo.getPageNum() - 1) * pageSize;
            userInfo.setPageNum(pageNum);
        }
        return this.userInfoDao.selectpage(userInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<UserInfo> selectall(UserInfo userInfo) {
        return this.userInfoDao.selectall(userInfo);
    }
}
