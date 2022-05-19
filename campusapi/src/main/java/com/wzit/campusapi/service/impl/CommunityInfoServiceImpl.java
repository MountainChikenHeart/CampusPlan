package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.CommunityInfo;
import com.wzit.campusapi.dao.CommunityInfoDao;
import com.wzit.campusapi.service.CommunityInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 圈子表(CommunityInfo)表服务实现类
 *
 * @author yyw
 */
@Service("communityInfoService")
public class CommunityInfoServiceImpl implements CommunityInfoService {

    @Resource
    private CommunityInfoDao communityInfoDao;

    /**
     * 查询单条
     */
    @Override
    public CommunityInfo selectone(Long communityId) {
        return this.communityInfoDao.selectone(communityId);
    }

    /**
     * 新增
     */
    @Override
    public CommunityInfo insert(CommunityInfo communityInfo) {
        this.communityInfoDao.insert(communityInfo);
        return communityInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(CommunityInfo communityInfo) {
        if ((communityInfo.getCommunityId() + "").isEmpty()) {
            return false;
        } else {
            return this.communityInfoDao.update(communityInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(CommunityInfo communityInfo) {
        if ((communityInfo.getCommunityId() + "").isEmpty()) {
            return false;
        } else {
            return this.communityInfoDao.delete(communityInfo.getCommunityId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<CommunityInfo> selectpage(CommunityInfo communityInfo) {
        if (communityInfo.getPageNum() != 0) {
            int pageSize = communityInfo.getPageSize();
            int pageNum = (communityInfo.getPageNum() - 1) * pageSize;
            communityInfo.setPageNum(pageNum);
        }
        return this.communityInfoDao.selectpage(communityInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CommunityInfo> selectall(CommunityInfo communityInfo) {
        return this.communityInfoDao.selectall(communityInfo);
    }
}
