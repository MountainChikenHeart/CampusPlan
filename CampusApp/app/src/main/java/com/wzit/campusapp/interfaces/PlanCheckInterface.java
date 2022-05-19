package com.wzit.campusapp.interfaces;

import android.widget.CompoundButton;

import com.wzit.campusapp.bean.NewPlanBean;

public interface PlanCheckInterface {
    /**
     * 红色选中
     */
    void IsRedCheck(CompoundButton compoundButton,NewPlanBean planBean,boolean b);

    /**
     * 黄色选中
     */
    void IsYellowCheck(CompoundButton compoundButton,NewPlanBean planBean, boolean b);

    /**
     * 蓝色选中
     */
    void IsBlueCheck(CompoundButton compoundButton,NewPlanBean planBean,boolean b);
}
