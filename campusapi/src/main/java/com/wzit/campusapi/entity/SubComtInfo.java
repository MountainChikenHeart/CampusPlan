package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 圈子子任务提交表(SubComtInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 11:00:30
 */
public class SubComtInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 441894213649681865L;
    /**
     * 主键ID
     */
    private Long subComtId;
    /**
     * 圈子子任务ID
     */
    private Long comtId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 是否催促
     */
    private Integer isUrge;
    /**
     * 图片提交
     */
    private String subComtImg;
    /**
     * 是否完成
     */
    private Integer isFinished;


    public Long getSubComtId() {
        return subComtId;
    }

    public void setSubComtId(Long subComtId) {
        this.subComtId = subComtId;
    }

    public Long getComtId() {
        return comtId;
    }

    public void setComtId(Long comtId) {
        this.comtId = comtId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsUrge() {
        return isUrge;
    }

    public void setIsUrge(Integer isUrge) {
        this.isUrge = isUrge;
    }

    public String getSubComtImg() {
        return subComtImg;
    }

    public void setSubComtImg(String subComtImg) {
        this.subComtImg = subComtImg;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

}

