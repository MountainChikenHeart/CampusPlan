package com.wzit.campusapp.bean;

public class UserLoginBean {

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
        private Integer userId;
        private String userNum;
        private String userName;
        private String userPhone;
        private String userPassword;
        private String userHeaderimg;
        private Integer userSex;
        private String userSignature;
        private Object userSchool;
        private Object userIntegral;
        private Object isVip;
        private String userToken;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public Object getUserSchool() {
            return userSchool;
        }

        public void setUserSchool(Object userSchool) {
            this.userSchool = userSchool;
        }

        public Object getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(Object userIntegral) {
            this.userIntegral = userIntegral;
        }

        public Object getIsVip() {
            return isVip;
        }

        public void setIsVip(Object isVip) {
            this.isVip = isVip;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }
}
