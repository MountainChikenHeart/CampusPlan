package com.wzit.campusapi.entity;

import java.util.Date;

import com.wzit.campusapi.web.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 圈子表(CommunityInfo)实体类
 *
 * @author yyw
 * @since 2022-04-29 11:00:27
 */
public class CommunityInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -94733713372411455L;
    /**
     * 主键ID
     */
    private Long communityId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 圈子名称
     */
    private String communityName;
    /**
     * 圈子编号
     */
    private String communityNum;
    /**
     * 邀请码
     */
    private String communityCode;
    /**
     * 圈子封面
     */
    private String communityImg;
    /**
     * 圈子介绍
     */
    private String communityIntro;


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

}

