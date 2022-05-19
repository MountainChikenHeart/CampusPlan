package com.wzit.campusapi.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzit.campusapi.web.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 圈子任务表(ComtInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 11:00:32
 */
public class ComtInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 413324368117519930L;
    /**
     * 主键ID
     */
    private Long comtId;
    /**
     * 子任务标题
     */
    private String comtTitle;
    /**
     * 子任务内容
     */
    private String comtContent;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
    /**
     * 子任务图片
     */
    private String comtImg;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 圈子ID
     */
    private Long cmtId;
    /**
     * 相差天数
     */
    private String day;


    public Long getComtId() {
        return comtId;
    }

    public void setComtId(Long comtId) {
        this.comtId = comtId;
    }

    public String getComtTitle() {
        return comtTitle;
    }

    public void setComtTitle(String comtTitle) {
        this.comtTitle = comtTitle;
    }

    public String getComtContent() {
        return comtContent;
    }

    public void setComtContent(String comtContent) {
        this.comtContent = comtContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getComtImg() {
        return comtImg;
    }

    public void setComtImg(String comtImg) {
        this.comtImg = comtImg;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

