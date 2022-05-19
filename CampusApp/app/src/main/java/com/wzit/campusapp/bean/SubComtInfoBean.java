package com.wzit.campusapp.bean;

import java.util.List;

public class SubComtInfoBean {

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

    public static class DataDTO{
        private Integer subComtId;
        private Integer comtId;
        private Integer userId;
        private String isUrge;
        private String subComtImg;
        private String isFinished;

        public Integer getSubComtId() {
            return subComtId;
        }

        public void setSubComtId(Integer subComtId) {
            this.subComtId = subComtId;
        }

        public Integer getComtId() {
            return comtId;
        }

        public void setComtId(Integer comtId) {
            this.comtId = comtId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getIsUrge() {
            return isUrge;
        }

        public void setIsUrge(String isUrge) {
            this.isUrge = isUrge;
        }

        public String getSubComtImg() {
            return subComtImg;
        }

        public void setSubComtImg(String subComtImg) {
            this.subComtImg = subComtImg;
        }

        public String getIsFinished() {
            return isFinished;
        }

        public void setIsFinished(String isFinished) {
            this.isFinished = isFinished;
        }
    }

}
