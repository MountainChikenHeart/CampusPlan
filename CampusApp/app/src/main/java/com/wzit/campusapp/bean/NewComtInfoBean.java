package com.wzit.campusapp.bean;

public class NewComtInfoBean {
    private int id ;
    private String ComtTitle;
    private String ComtTime;
    private String ComtContent;
    private int UserId;
    private int CmtId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComtTitle() {
        return ComtTitle;
    }

    public void setComtTitle(String comtTitle) {
        ComtTitle = comtTitle;
    }

    public String getComtTime() {
        return ComtTime;
    }

    public void setComtTime(String comtTime) {
        ComtTime = comtTime;
    }

    public String getComtContent() {
        return ComtContent;
    }

    public void setComtContent(String comtContent) {
        ComtContent = comtContent;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCmtId() {
        return CmtId;
    }

    public void setCmtId(int cmtId) {
        CmtId = cmtId;
    }
}
