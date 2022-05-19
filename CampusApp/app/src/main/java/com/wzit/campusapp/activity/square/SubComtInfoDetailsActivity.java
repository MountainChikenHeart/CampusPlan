package com.wzit.campusapp.activity.square;

import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.GetEditStr;
import static com.wzit.campusapp.utils.common.CommonUtils.GetTextStr;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.SelectImage;
import static com.wzit.campusapp.utils.common.CommonUtils.TInfo2;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning2;
import static com.wzit.campusapp.utils.common.CommonUtils.uploadFile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lcw.library.imagepicker.ImagePicker;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.GetComtInfoBean;
import com.wzit.campusapp.bean.InsertCmtBean;
import com.wzit.campusapp.bean.SubComtInfoBean;
import com.wzit.campusapp.bean.UplaodBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交的圈子任务查看
 */
@SuppressLint("NonConstantResourceId")
public class SubComtInfoDetailsActivity extends BaseActivityWhite {


    private static final int REQUEST_SELECT_IMAGES_CODE = 0x01;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_top2)
    ImageView ivTop2;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ic_back)
    IconFontTextView icBack;
    @BindView(R.id.ic_more)
    IconFontTextView icMore;
    @BindView(R.id.show)
    ImageView show;
    @BindView(R.id.rl_tupian)
    RelativeLayout rlTupian;
    @BindView(R.id.finish)
    Button finish;
    private String path;
    private String imgurl = "";

    Integer userId;
    Integer comtId;
    String imgUrl;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_sub_comt_info_details;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        userId=Integer.parseInt(getIntent().getStringExtra("userId"));
        comtId=Integer.parseInt(getIntent().getStringExtra("comtId"));
        imgUrl=getIntent().getStringExtra("imgUrl");
        activity = this;
        icMore.setVisibility(View.GONE);
        tvTitle.setText("圈子任务查看");
        Glide.with(activity).load(AppNetConfig.ImgApi + imgUrl).into(show);
        boolean isFinish = Boolean.parseBoolean(getIntent().getStringExtra("finish"));
        if(isFinish)
            finish.setVisibility(View.GONE);
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.ic_back, R.id.finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.finish:
                FinishAction();
                break;
        }
    }

    /**
     * 确认完成
     */
    private void FinishAction() {

        //设置参数
        HashMap<Object, Object> map = new HashMap<>();
        map.put("isFinished",1);
        map.put("userId",userId);
        map.put("comtId",comtId);
        System.err.println(userId);
        System.err.println(comtId);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.updateSubComtInfo, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                InsertCmtBean bean = new Gson().fromJson(json, InsertCmtBean.class);
                if (bean.getCode() == 1){
                    TInfo2("确认成功");
                    DelayClosed(activity);
                }else {
                    TWarning2("确认失败");
                }
            }
        });
    }
}
