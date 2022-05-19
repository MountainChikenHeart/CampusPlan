package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * (CommunityInfoView)实体类
 *
 * @author yyw
 * @since 2022-05-04 02:45:01
 */
public class CommunityInfoView extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 469601962596828621L;

    private Long communityId;

    private Long userId;

    private String userName;

    private String userPhone;

    private String userHeaderimg;

    private Integer userSex;

    private String communityName;

    private String communityNum;

    private String communityCode;

    private String communityImg;

    private String communityIntro;

    private String userNum;

    private String userPassword;

    private String userSignature;

    private String userSchool;

    private Double userIntegral;

    private Integer isVip;

    private Integer isJoin;


    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(String communityNum) {
        this.communityNum = communityNum;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getCommunityImg() {
        return communityImg;
    }

    public void setCommunityImg(String communityImg) {
        this.communityImg = communityImg;
    }

    public String getCommunityIntro() {
        return communityIntro;
    }

    public void setCommunityIntro(String communityIntro) {
        this.communityIntro = communityIntro;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }
}

