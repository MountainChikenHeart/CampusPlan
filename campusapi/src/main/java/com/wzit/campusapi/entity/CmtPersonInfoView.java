package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * (CmtPersonInfoView)实体类
 *
 * @author yyw
 * @since 2022-05-04 03:09:00
 */
public class CmtPersonInfoView extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 720981160679307629L;

    private Long cmtPersonId;

    private Long cmtId;

    private Long userId;

    private Date createTime;

    private String userNum;

    private String userName;

    private String userPassword;

    private String userPhone;

    private String userHeaderimg;

    private Integer userSex;

    private String userSchool;

    private String userSignature;

    private Double userIntegral;


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

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserHeaderimg() {
        return userHeaderimg;
    }

    public void setUserHeaderimg(String userHeaderimg) {
        this.userHeaderimg = userHeaderimg;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public Double getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Double userIntegral) {
        this.userIntegral = userIntegral;
    }

}

