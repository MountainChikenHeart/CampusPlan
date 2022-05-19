package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 圈子成员表(CmtPersonInfo)实体类
 *
 * @author yyw
 * @since 2022-05-04 03:09:00
 */
public class CmtPersonInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -60377695447238527L;
    /**
     * 主键ID
     */
    private Long cmtPersonId;
    /**
     * 圈子ID
     */
    private Long cmtId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 加入时间
     */
    private Date createTime;


    public Long getCmtPersonId() {
        return cmtPersonId;
    }

    public void setCmtPersonId(Long cmtPersonId) {
        this.cmtPersonId = cmtPersonId;
    }

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

