package com.wzit.campusapi.entity;

import com.wzit.campusapi.web.BaseEntity;

import java.io.Serializable;

public class UserComtUrge  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 38743277514632842L;

    private Long urgeId;
    private Long userId;
    private Long comtId;
    private Integer isUrge;

    public Long getUrgeId() {
        return urgeId;
    }

    public void setUrgeId(Long urgeId) {
        this.urgeId = urgeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComtId() {
        return comtId;
    }

    public void setComtId(Long comtId) {
        this.comtId = comtId;
    }

    public Integer getIsUrge() {
        return isUrge;
    }

    public void setIsUrge(Integer isUrge) {
        this.isUrge = isUrge;
    }
}
