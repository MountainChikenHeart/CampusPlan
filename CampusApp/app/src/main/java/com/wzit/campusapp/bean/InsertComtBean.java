package com.wzit.campusapp.bean;

public class InsertComtBean {

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
        private Integer comtId;
        private String comtTitle;
        private String comtContent;
        private String startTime;
        private String endTime;
        private String comtImg;
        private String createTime;
        private Integer userId;
        private Integer cmtId;

        public Integer getComtId() {
            return comtId;
        }

        public void setComtId(Integer comtId) {
            this.comtId = comtId;
        }

        public String getComtTitle() {
            return comtTitle;
        }

        public void setComtTitle(String comtTitle) {
            this.comtTitle = comtTitle;
        }

        public String getComtContent() {
            return comtContent;
        }

        public void setComtContent(String comtContent) {
            this.comtContent = comtContent;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getComtImg() {
            return comtImg;
        }

        public void setComtImg(String comtImg) {
            this.comtImg = comtImg;
        }

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

        public Integer getCmtId() {
            return cmtId;
        }

        public void setCmtId(Integer cmtId) {
            this.cmtId = cmtId;
        }
    }
}
