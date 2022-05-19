package com.wzit.campusapi.service.impl;

import com.wzit.campusapi.entity.CmtPersonInfoView;
import com.wzit.campusapi.dao.CmtPersonInfoViewDao;
import com.wzit.campusapi.service.CmtPersonInfoViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (CmtPersonInfoView)表服务实现类
 *
 * @author yyw
 */
@Service("cmtPersonInfoViewService")
public class CmtPersonInfoViewServiceImpl implements CmtPersonInfoViewService {

    @Resource
    private CmtPersonInfoViewDao cmtPersonInfoViewDao;

    /**
     * 查询单条
     */
    @Override
    public CmtPersonInfoView selectone() {
        return this.cmtPersonInfoViewDao.selectone();
    }

    /**
     * 新增
     */
    @Override
    public CmtPersonInfoView insert(CmtPersonInfoView cmtPersonInfoView) {
        this.cmtPersonInfoViewDao.insert(cmtPersonInfoView);
        return cmtPersonInfoView;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(CmtPersonInfoView cmtPersonInfoView) {
        return false;
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(CmtPersonInfoView cmtPersonInfoView) {
        return false;
    }

    /**
     * 分页查询
     */
    @Override
    public List<CmtPersonInfoView> selectpage(CmtPersonInfoView cmtPersonInfoView) {
        if (cmtPersonInfoView.getPageNum() != 0) {
            int pageSize = cmtPersonInfoView.getPageSize();
            int pageNum = (cmtPersonInfoView.getPageNum() - 1) * pageSize;
            cmtPersonInfoView.setPageNum(pageNum);
        }
        return this.cmtPersonInfoViewDao.selectpage(cmtPersonInfoView);
    }

    /**
     * 查询所有
     */
    @Override
    public List<CmtPersonInfoView> selectall(CmtPersonInfoView cmtPersonInfoView) {
        return this.cmtPersonInfoViewDao.selectall(cmtPersonInfoView);
    }
}
