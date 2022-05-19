package com.wzit.campusapp.bean;

import java.util.List;

public class GetSxxBean {

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
        private List<FourDTO> four;
        private List<OneDTO> one;
        private List<TwoDTO> two;
        private List<ThreeDTO> three;

        public List<FourDTO> getFour() {
            return four;
        }

        public void setFour(List<FourDTO> four) {
            this.four = four;
        }

        public List<OneDTO> getOne() {
            return one;
        }

        public void setOne(List<OneDTO> one) {
            this.one = one;
        }

        public List<TwoDTO> getTwo() {
            return two;
        }

        public void setTwo(List<TwoDTO> two) {
            this.two = two;
        }

        public List<ThreeDTO> getThree() {
            return three;
        }

        public void setThree(List<ThreeDTO> three) {
            this.three = three;
        }

        public static class FourDTO {
            private Object createTime;
            private Integer planId;
            private String planNum;
            private String planName;
            private Object startTime;
            private String endTime;
            private String planContent;
            private Object planReplay;
            private Integer planDegree;
            private Integer planIsFinished;
            private Integer userId;
            private String day;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Integer getPlanId() {
                return planId;
            }

            public void setPlanId(Integer planId) {
                this.planId = planId;
            }

            public String getPlanNum() {
                return planNum;
            }

            public void setPlanNum(String planNum) {
                this.planNum = planNum;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
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

            public String getPlanContent() {
                return planContent;
            }

            public void setPlanContent(String planContent) {
                this.planContent = planContent;
            }

            public Object getPlanReplay() {
                return planReplay;
            }

            public void setPlanReplay(Object planReplay) {
                this.planReplay = planReplay;
            }

            public Integer getPlanDegree() {
                return planDegree;
            }

            public void setPlanDegree(Integer planDegree) {
                this.planDegree = planDegree;
            }

            public Integer getPlanIsFinished() {
                return planIsFinished;
            }

            public void setPlanIsFinished(Integer planIsFinished) {
                this.planIsFinished = planIsFinished;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }

        public static class OneDTO {
            private Object createTime;
            private Integer planId;
            private String planNum;
            private String planName;
            private Object startTime;
            private String endTime;
            private String planContent;
            private Object planReplay;
            private Integer planDegree;
            private Integer planIsFinished;
            private Integer userId;
            private String day;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Integer getPlanId() {
                return planId;
            }

            public void setPlanId(Integer planId) {
                this.planId = planId;
            }

            public String getPlanNum() {
                return planNum;
            }

            public void setPlanNum(String planNum) {
                this.planNum = planNum;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
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

            public String getPlanContent() {
                return planContent;
            }

            public void setPlanContent(String planContent) {
                this.planContent = planContent;
            }

            public Object getPlanReplay() {
                return planReplay;
            }

            public void setPlanReplay(Object planReplay) {
                this.planReplay = planReplay;
            }

            public Integer getPlanDegree() {
                return planDegree;
            }

            public void setPlanDegree(Integer planDegree) {
                this.planDegree = planDegree;
            }

            public Integer getPlanIsFinished() {
                return planIsFinished;
            }

            public void setPlanIsFinished(Integer planIsFinished) {
                this.planIsFinished = planIsFinished;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }

        public static class TwoDTO {
            private Object createTime;
            private Integer planId;
            private String planNum;
            private String planName;
            private Object startTime;
            private String endTime;
            private String planContent;
            private Object planReplay;
            private Integer planDegree;
            private Integer planIsFinished;
            private Integer userId;
            private String day;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Integer getPlanId() {
                return planId;
            }

            public void setPlanId(Integer planId) {
                this.planId = planId;
            }

            public String getPlanNum() {
                return planNum;
            }

            public void setPlanNum(String planNum) {
                this.planNum = planNum;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
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

            public String getPlanContent() {
                return planContent;
            }

            public void setPlanContent(String planContent) {
                this.planContent = planContent;
            }

            public Object getPlanReplay() {
                return planReplay;
            }

            public void setPlanReplay(Object planReplay) {
                this.planReplay = planReplay;
            }

            public Integer getPlanDegree() {
                return planDegree;
            }

            public void setPlanDegree(Integer planDegree) {
                this.planDegree = planDegree;
            }

            public Integer getPlanIsFinished() {
                return planIsFinished;
            }

            public void setPlanIsFinished(Integer planIsFinished) {
                this.planIsFinished = planIsFinished;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }

        public static class ThreeDTO {
            private Object createTime;
            private Integer planId;
            private String planNum;
            private String planName;
            private Object startTime;
            private String endTime;
            private String planContent;
            private Object planReplay;
            private Integer planDegree;
            private Integer planIsFinished;
            private Integer userId;
            private String day;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Integer getPlanId() {
                return planId;
            }

            public void setPlanId(Integer planId) {
                this.planId = planId;
            }

            public String getPlanNum() {
                return planNum;
            }

            public void setPlanNum(String planNum) {
                this.planNum = planNum;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
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

            public String getPlanContent() {
                return planContent;
            }

            public void setPlanContent(String planContent) {
                this.planContent = planContent;
            }

            public Object getPlanReplay() {
                return planReplay;
            }

            public void setPlanReplay(Object planReplay) {
                this.planReplay = planReplay;
            }

            public Integer getPlanDegree() {
                return planDegree;
            }

            public void setPlanDegree(Integer planDegree) {
                this.planDegree = planDegree;
            }

            public Integer getPlanIsFinished() {
                return planIsFinished;
            }

            public void setPlanIsFinished(Integer planIsFinished) {
                this.planIsFinished = planIsFinished;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
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
