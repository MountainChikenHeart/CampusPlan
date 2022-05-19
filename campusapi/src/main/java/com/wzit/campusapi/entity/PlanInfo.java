package com.wzit.campusapi.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzit.campusapi.web.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 任务计划表(PlanInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 18:51:14
 */
public class PlanInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -48263707584686896L;
    /**
     * 主键ID
     */
    private Long planId;
    /**
     * 任务编号
     */
    private String planNum;
    /**
     * 任务名称
     */
    private String planName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 任务内容
     */
    private String planContent;
    /**
     * 重复周期
     */
    private Integer planReplay;
    /**
     * 任务等级
     */
    private Integer planDegree;
    /**
     * 是否完成
     */
    private Integer planIsFinished;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 相差天数
     */
    private String day;


    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Integer getPlanReplay() {
        return planReplay;
    }

    public void setPlanReplay(Integer planReplay) {
        this.planReplay = planReplay;
    }

    public Integer getPlanDegree() {
        return planDegree;
    }

    public void setPlanDegree(Integer planDegree) {
        this.planDegree = planDegree;
    }

    public Integer getPlanIsFinished() {
        return planIsFinished;
    }

    public void setPlanIsFinished(Integer planIsFinished) {
        this.planIsFinished = planIsFinished;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "PlanInfo{" +
                "planId=" + planId +
                ", planNum='" + planNum + '\'' +
                ", planName='" + planName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", planContent='" + planContent + '\'' +
                ", planReplay=" + planReplay +
                ", planDegree=" + planDegree +
                ", planIsFinished=" + planIsFinished +
                ", userId=" + userId +
                ", day='" + day + '\'' +
                '}';
    }
}

