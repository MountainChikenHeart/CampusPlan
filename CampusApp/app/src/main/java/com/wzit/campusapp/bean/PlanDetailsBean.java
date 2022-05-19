package com.wzit.campusapp.bean;

import com.google.gson.annotations.SerializedName;

public class PlanDetailsBean {

    @SerializedName("PlanContent")
    private String planContent;
    @SerializedName("PlanName")
    private String planName;
    @SerializedName("PlanTime")
    private String planTime;
    @SerializedName("Rank")
    private Integer rank;
    private Integer id;

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
