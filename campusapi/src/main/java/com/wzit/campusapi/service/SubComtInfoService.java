package com.wzit.campusapi.service;

import com.wzit.campusapi.entity.SubComtInfo;
import com.wzit.campusapi.entity.UserInfo;

import java.util.List;

/**
 * 圈子子任务提交表(SubComtInfo)表服务接口
 *
 * @author yyw
 */
public interface SubComtInfoService {

    /**
     * 查询单条
     */
    SubComtInfo selectone(Long subComtId);

    /**
     * 新增
     */
    SubComtInfo insert(SubComtInfo subComtInfo);

    /**
     * 修改
     */
    boolean update(SubComtInfo subComtInfo);

    /**
     * 删除
     */
    boolean delete(SubComtInfo subComtInfo);

    /**
     * 分页查询
     */
    List<SubComtInfo> selectpage(SubComtInfo subComtInfo);

    /**
     * 查询所有
     */
    List<SubComtInfo> selectall(SubComtInfo subComtInfo);

    Integer selectcount(SubComtInfo subComtInfo);
}
