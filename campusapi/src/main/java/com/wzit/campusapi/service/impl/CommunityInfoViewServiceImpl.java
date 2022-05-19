package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.CommunityInfoView;
import com.wzit.campusapi.dao.CommunityInfoViewDao;
import com.wzit.campusapi.service.CommunityInfoViewService;
import com.wzit.campusapi.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (CommunityInfoView)表服务实现类
 *
 * @author yyw
 */
@Service("communityInfoViewService")
public class CommunityInfoViewServiceImpl implements CommunityInfoViewService {

    @Resource
    private CommunityInfoViewDao communityInfoViewDao;

    /**
     * 查询单条
     */
    @Override
    public CommunityInfoView selectone() {
        return this.communityInfoViewDao.selectone();
    }

    /**
     * 新增
     */
    @Override
    public CommunityInfoView insert(CommunityInfoView communityInfoView) {
        this.communityInfoViewDao.insert(communityInfoView);
        return communityInfoView;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(CommunityInfoView communityInfoView) {
        return false;
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(CommunityInfoView communityInfoView) {
        return false;
    }

    /**
     * 分页查询
     */
    @Override
    public List<CommunityInfoView> selectpage(CommunityInfoView communityInfoView) {
        if (communityInfoView.getPageNum() != 0) {
            int pageSize = communityInfoView.getPageSize();
            int pageNum = (communityInfoView.getPageNum() - 1) * pageSize;
            communityInfoView.setPageNum(pageNum);
        }
        return this.communityInfoViewDao.selectpage(communityInfoView);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CommunityInfoView> selectall(CommunityInfoView communityInfoView) {
        return this.communityInfoViewDao.selectall(communityInfoView);
    }

    @Override
    public List<CommunityInfoView> selectallLimit(CommunityInfoView communityInfoView) {
        if(StringUtils.isNotEmpty(communityInfoView.getCommunityName()))
            communityInfoView.setCommunityName("%"+communityInfoView.getCommunityName()+"%");
        else
            communityInfoView.setCommunityName(null);
        return this.communityInfoViewDao.selectallLimit(communityInfoView);
    }
}
