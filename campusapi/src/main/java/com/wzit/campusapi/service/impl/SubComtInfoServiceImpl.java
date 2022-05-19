package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.SubComtInfo;
import com.wzit.campusapi.dao.SubComtInfoDao;
import com.wzit.campusapi.entity.UserInfo;
import com.wzit.campusapi.service.SubComtInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 圈子子任务提交表(SubComtInfo)表服务实现类
 *
 * @author yyw
 */
@Service("subComtInfoService")
public class SubComtInfoServiceImpl implements SubComtInfoService {

    @Resource
    private SubComtInfoDao subComtInfoDao;

    /**
     * 查询单条
     */
    @Override
    public SubComtInfo selectone(Long subComtId) {
        return this.subComtInfoDao.selectone(subComtId);
    }

    /**
     * 新增
     */
    @Override
    public SubComtInfo insert(SubComtInfo subComtInfo) {
        this.subComtInfoDao.insert(subComtInfo);
        return subComtInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(SubComtInfo subComtInfo) {
        if ((subComtInfo.getSubComtId() + "").isEmpty()) {
            return false;
        } else {
            return this.subComtInfoDao.update(subComtInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(SubComtInfo subComtInfo) {
        if ((subComtInfo.getSubComtId() + "").isEmpty()) {
            return false;
        } else {
            return this.subComtInfoDao.delete(subComtInfo.getSubComtId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<SubComtInfo> selectpage(SubComtInfo subComtInfo) {
        if (subComtInfo.getPageNum() != 0) {
            int pageSize = subComtInfo.getPageSize();
            int pageNum = (subComtInfo.getPageNum() - 1) * pageSize;
            subComtInfo.setPageNum(pageNum);
        }
        return this.subComtInfoDao.selectpage(subComtInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<SubComtInfo> selectall(SubComtInfo subComtInfo) {
        return this.subComtInfoDao.selectall(subComtInfo);
    }

    @Override
    public Integer selectcount(SubComtInfo subComtInfo) {
        return this.subComtInfoDao.selectcount(subComtInfo);
    }
}
