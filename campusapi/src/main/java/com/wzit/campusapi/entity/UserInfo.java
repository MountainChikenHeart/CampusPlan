package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(UserInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 11:00:29
 */
public class UserInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 427030628033955198L;
    /**
     * 主键ID
     */
    private Long userId;
    /**
     * 账号
     */
    private String userNum;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 头像
     */
    private String userHeaderimg;
    /**
     * 性别
     */
    private Integer userSex;
    /**
     * 个性签名
     */
    private String userSignature;
    /**
     * 学校
     */
    private String userSchool;
    /**
     * 积分
     */
    private Double userIntegral;
    /**
     * 是否vip
     */
    private Integer isVip;
    /**
     * token
     */
    private String userToken;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public Double getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Double userIntegral) {
        this.userIntegral = userIntegral;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}

