package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.dao.CmtPersonInfoDao;
import com.wzit.campusapi.dao.SubComtInfoDao;
import com.wzit.campusapi.entity.CmtPersonInfo;
import com.wzit.campusapi.entity.ComtInfo;
import com.wzit.campusapi.dao.ComtInfoDao;
import com.wzit.campusapi.entity.UserComtUrge;
import com.wzit.campusapi.service.ComtInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 圈子任务表(ComtInfo)表服务实现类
 *
 * @author yyw
 */
@Service("comtInfoService")
public class ComtInfoServiceImpl implements ComtInfoService {

    @Resource
    private ComtInfoDao comtInfoDao;

    @Resource
    private CmtPersonInfoDao cmtPersonInfoDao;

    /**
     * 查询单条
     */
    @Override
    public ComtInfo selectone(Long comtId) {
        return this.comtInfoDao.selectone(comtId);
    }

    /**
     * 新增
     */
    @Override
    public ComtInfo insert(ComtInfo comtInfo) {
        this.comtInfoDao.insert(comtInfo);
        CmtPersonInfo cmtPersonInfo = new CmtPersonInfo();
        cmtPersonInfo.setCmtId(comtInfo.getCmtId());
        List<CmtPersonInfo> cmtPersonInfoList = cmtPersonInfoDao.selectall(cmtPersonInfo);
        for (CmtPersonInfo cmtPersonInfo1:cmtPersonInfoList){
            UserComtUrge userComtUrge = new UserComtUrge();
            userComtUrge.setUserId(cmtPersonInfo1.getUserId());
            userComtUrge.setComtId(comtInfo.getComtId());
            userComtUrge.setIsUrge(0);
            userComtUrge.setCreateTime(new Date());
            comtInfoDao.insertUrge(userComtUrge);
        }
        return comtInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(ComtInfo comtInfo) {
        if ((comtInfo.getComtId() + "").isEmpty()) {
            return false;
        } else {
            return this.comtInfoDao.update(comtInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(ComtInfo comtInfo) {
        if ((comtInfo.getComtId() + "").isEmpty()) {
            return false;
        } else {
            return (this.comtInfoDao.delete(comtInfo.getComtId()) > 0);
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<ComtInfo> selectpage(ComtInfo comtInfo) {
        if (comtInfo.getPageNum() != 0) {
            int pageSize = comtInfo.getPageSize();
            int pageNum = (comtInfo.getPageNum() - 1) * pageSize;
            comtInfo.setPageNum(pageNum);
        }
        return this.comtInfoDao.selectpage(comtInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<ComtInfo> selectall(ComtInfo comtInfo) {
        return this.comtInfoDao.selectall(comtInfo);
    }

    @Override
    public boolean updateUrge(UserComtUrge userComtUrge) {
        return this.comtInfoDao.updateUrge(userComtUrge);
    }

    @Override
    public boolean selectUrge(UserComtUrge userComtUrge) {
        return (this.comtInfoDao.selectUrge(userComtUrge)!=null);
    }
}
