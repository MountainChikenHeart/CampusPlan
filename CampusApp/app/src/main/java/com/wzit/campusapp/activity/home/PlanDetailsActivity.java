package com.wzit.campusapp.activity.home;

import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.InsertPlanBean;
import com.wzit.campusapp.bean.PlanDetailsBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.interfaces.XPopupInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 详细
 */
@SuppressLint("NonConstantResourceId")
public class PlanDetailsActivity extends BaseActivityWhite {

    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_top2)
    ImageView ivTop2;
    @BindView(R.id.ic_back)
    IconFontTextView icBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ic_more)
    IconFontTextView icMore;
    @BindView(R.id.iv_degree1)
    CheckBox ivDegree1;
    @BindView(R.id.iv_degree2)
    CheckBox ivDegree2;
    @BindView(R.id.iv_degree3)
    CheckBox ivDegree3;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.top1)
    RelativeLayout top1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;

    Integer id;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_plan_details;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {

        ivDegree1.setVisibility(View.GONE);
        ivDegree2.setVisibility(View.GONE);
        ivDegree3.setVisibility(View.GONE);

        icMore.setVisibility(View.GONE);
        tvTitle.setText("详细");
        String json = getIntent().getStringExtra("json");
        System.out.println(json);
        PlanDetailsBean bean = new Gson().fromJson(json, PlanDetailsBean.class);
        tvName.setText(bean.getPlanName());
        tvTime.setText(bean.getPlanTime());
        tvContent.setText(bean.getPlanContent());
        id= bean.getId();

        if (bean.getRank() == 0){
            ivDegree1.setVisibility(View.VISIBLE);
        }else if (bean.getRank() == 1){
            ivDegree2.setVisibility(View.VISIBLE);
        }else if (bean.getRank() == 2){
            ivDegree3.setVisibility(View.VISIBLE);
        }


    }

    /**
     * 点击事件
     */
    @OnClick(R.id.ic_back)
    public void onClick() {
        finish();
    }

    @OnClick(R.id.delete)
    public void delete(){
        CommonUtils.ConfirmDialogShow(this, "删除", "确定要删除这项计划吗？", new XPopupInterface() {
            @Override
            public void onConfirm() {
                HashMap<Object,Object> map = new HashMap<>();
                map.put("planId",id);
                OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.DeletePlan, new OkSuccessInterface() {
                    @Override
                    public void OnSuccess(String json) {
                        InsertPlanBean bean = new Gson().fromJson(json, InsertPlanBean.class);
                        if(bean.getCode() == 1){
                            CommonUtils.TInfo2("删除成功");
                            DelayClosed(activity);
                        }else{
                            CommonUtils.TWarning2("删除失败");
                        }
                    }
                });
            }
            @Override
            public void onCancel() {
                CommonUtils.TInfo2("已取消");
            }
        });
    }

    /**
     * 获取传递过来的参数
     */
    public static void start(Context context, String json) {
        Intent intent = new Intent(context, PlanDetailsActivity.class);
        intent.putExtra("json", json);
        context.startActivity(intent);
    }
}
