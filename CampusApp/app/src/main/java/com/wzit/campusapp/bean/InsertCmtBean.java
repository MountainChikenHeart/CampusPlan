package com.wzit.campusapp.bean;

public class InsertCmtBean {

    private String msg;
    private Integer code;
    private DataDTO data;
    private Integer pagesum;
    private Long timestamp;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Integer getPagesum() {
        return pagesum;
    }

    public void setPagesum(Integer pagesum) {
        this.pagesum = pagesum;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataDTO {
        private String createTime;
        private Integer communityId;
        private Integer userId;
        private String communityName;
        private String communityNum;
        private String communityCode;
        private String communityImg;
        private String communityIntro;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getCommunityId() {
            return communityId;
        }

        public void setCommunityId(Integer communityId) {
            this.communityId = communityId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
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
}
