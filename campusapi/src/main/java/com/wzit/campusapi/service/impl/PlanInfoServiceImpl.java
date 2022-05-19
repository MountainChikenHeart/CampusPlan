package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.PlanInfo;
import com.wzit.campusapi.dao.PlanInfoDao;
import com.wzit.campusapi.service.PlanInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 任务计划表(PlanInfo)表服务实现类
 *
 * @author yyw
 */
@Service("planInfoService")
public class PlanInfoServiceImpl implements PlanInfoService {

    @Resource
    private PlanInfoDao planInfoDao;

    /**
     * 查询单条
     */
    @Override
    public PlanInfo selectone(Long planId) {
        return this.planInfoDao.selectone(planId);
    }

    /**
     * 新增
     */
    @Override
    public PlanInfo insert(PlanInfo planInfo) {
        this.planInfoDao.insert(planInfo);
        return planInfo;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(PlanInfo planInfo) {
        if ((planInfo.getPlanId() + "").isEmpty()) {
            return false;
        } else {
            return this.planInfoDao.update(planInfo) > 0;
        }
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(PlanInfo planInfo) {
        if ((planInfo.getPlanId() + "").isEmpty()) {
            return false;
        } else {
            return this.planInfoDao.delete(planInfo.getPlanId()) > 0;
        }
    }

    /**
     * 分页查询
     */
    @Override
    public List<PlanInfo> selectpage(PlanInfo planInfo) {
        if (planInfo.getPageNum() != 0) {
            int pageSize = planInfo.getPageSize();
            int pageNum = (planInfo.getPageNum() - 1) * pageSize;
            planInfo.setPageNum(pageNum);
        }
        return this.planInfoDao.selectpage(planInfo);
    }

    /**
     * 查询所有
     */
    @Override
    public List<PlanInfo> selectall(PlanInfo planInfo) {
        return this.planInfoDao.selectall(planInfo);
    }
}
