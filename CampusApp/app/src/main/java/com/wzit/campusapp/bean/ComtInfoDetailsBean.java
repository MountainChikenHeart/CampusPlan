package com.wzit.campusapp.bean;

import com.google.gson.annotations.SerializedName;

public class ComtInfoDetailsBean {

    @SerializedName("ComtContent")
    private String comtContent;
    @SerializedName("ComtTitle")
    private String comtTitle;
    @SerializedName("ComtTime")
    private String comtTime;
    @SerializedName("UserId")
    private Integer userId;
    @SerializedName("CmtId")
    private Integer cmtId;
    private Integer id;


    public String getComtContent() {
        return comtContent;
    }

    public void setComtContent(String comtContent) {
        this.comtContent = comtContent;
    }

    public String getComtTitle() {
        return comtTitle;
    }

    public void setComtTitle(String comtTitle) {
        this.comtTitle = comtTitle;
    }

    public String getComtTime() {
        return comtTime;
    }

    public void setComtTime(String comtTime) {
        this.comtTime = comtTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCmtId() {
        return cmtId;
    }

    public void setCmtId(Integer cmtId) {
        this.cmtId = cmtId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
