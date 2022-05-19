package com.wzit.campusapp.bean;

import java.util.List;

public class GetComtInfoBean {
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

    public static class DataDTO{
        private List<List<ChildrenDTO>> children;
        private List<String> group;

        public List<List<ChildrenDTO>> getChildren() {
            return children;
        }

        public void setChildren(List<List<ChildrenDTO>> children) {
            this.children = children;
        }

        public List<String> getGroup() {
            return group;
        }

        public void setGroup(List<String> group) {
            this.group = group;
        }

        public static class ChildrenDTO{
            private Object createTime;
            private Integer comtId;
            private String comtTitle;
            private String comtContent;
            private Object startTime;
            private String endTime;
            private String comtImg;
            private Integer userId;
            private Integer cmtId;
            private String day;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

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

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
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

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }

    }
}
