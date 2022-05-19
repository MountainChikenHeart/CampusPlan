package com.wzit.campusapp.bean;

import java.util.List;

public class GetPersonBean {

    private String msg;
    private Integer code;
    private List<DataDTO> data;
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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
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
        private Integer cmtPersonId;
        private Integer cmtId;
        private Integer userId;
        private String userNum;
        private String userName;
        private String userPassword;
        private Object userPhone;
        private String userHeaderimg;
        private Integer userSex;
        private String userSchool;
        private String userSignature;
        private Double userIntegral;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getCmtPersonId() {
            return cmtPersonId;
        }

        public void setCmtPersonId(Integer cmtPersonId) {
            this.cmtPersonId = cmtPersonId;
        }

        public Integer getCmtId() {
            return cmtId;
        }

        public void setCmtId(Integer cmtId) {
            this.cmtId = cmtId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
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

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public Object getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(Object userPhone) {
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
}
