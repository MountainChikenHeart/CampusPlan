package com.wzit.campusapp.bean;

public class InsertCmtPerson {

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
        private Integer cmtPersonId;
        private Object cmtId;
        private Object userId;

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

        public Object getCmtId() {
            return cmtId;
        }

        public void setCmtId(Object cmtId) {
            this.cmtId = cmtId;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }
    }
}
