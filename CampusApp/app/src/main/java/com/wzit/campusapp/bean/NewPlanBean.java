package com.wzit.campusapp.bean;

public class NewPlanBean {
    private int id ;

    private String PlanName;

    private String PlanTime;

    private int Rank;

    private String PlanContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getPlanTime() {
        return PlanTime;
    }

    public void setPlanTime(String planTime) {
        PlanTime = planTime;
    }

    public String getPlanContent() {
        return PlanContent;
    }

    public void setPlanContent(String planContent) {
        PlanContent = planContent;
    }
}
