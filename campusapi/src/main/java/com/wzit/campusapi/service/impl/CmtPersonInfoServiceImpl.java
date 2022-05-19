package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.CmtPersonInfo;
import com.wzit.campusapi.dao.CmtPersonInfoDao;
import com.wzit.campusapi.service.CmtPersonInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 圈子成员表(CmtPersonInfo)表服务实现类
 *
 * @author yyw
 */
@Service("cmtPersonInfoService")
public class CmtPersonInfoServiceImpl implements CmtPersonInfoService {

    @Resource
    private CmtPersonInfoDao cmtPersonInfoDao;

    /**
     * 查询单条
     */
    @Override
    public CmtPersonInfo selectone(Long cmtPersonId) {
        return this.cmtPersonInfoDao.selectone(cmtPersonId);
    }

    /**
     * 新增
     */
    @Override
    public CmtPersonInfo insert(CmtPersonInfo cmtPersonInfo) {
        this.cmtPersonInfoDao.insert(cmtPersonInfo);
        return cmtPersonInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(CmtPersonInfo cmtPersonInfo) {
        if ((cmtPersonInfo.getCmtPersonId() + "").isEmpty()) {
            return false;
        } else {
            return this.cmtPersonInfoDao.update(cmtPersonInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(CmtPersonInfo cmtPersonInfo) {
        if ((cmtPersonInfo.getCmtPersonId() + "").isEmpty()) {
            return false;
        } else {
            return this.cmtPersonInfoDao.delete(cmtPersonInfo.getCmtPersonId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<CmtPersonInfo> selectpage(CmtPersonInfo cmtPersonInfo) {
        if (cmtPersonInfo.getPageNum() != 0) {
            int pageSize = cmtPersonInfo.getPageSize();
            int pageNum = (cmtPersonInfo.getPageNum() - 1) * pageSize;
            cmtPersonInfo.setPageNum(pageNum);
        }
        return this.cmtPersonInfoDao.selectpage(cmtPersonInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CmtPersonInfo> selectall(CmtPersonInfo cmtPersonInfo) {
        return this.cmtPersonInfoDao.selectall(cmtPersonInfo);
    }
}
