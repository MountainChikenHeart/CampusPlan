package com.wzit.campusapp.activity.square;

import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.GetEditStr;
import static com.wzit.campusapp.utils.common.CommonUtils.GetTextStr;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning2;
import static com.wzit.campusapp.utils.common.CommonUtils.ToastShow;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.CmtDetailsBean;
import com.wzit.campusapp.bean.InsertComtBean;
import com.wzit.campusapp.bean.InsertPlanBean;
import com.wzit.campusapp.bean.UserLoginBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hutool.core.date.DateUtil;

@SuppressLint("NonConstantResourceId")
public class SquarePlanActivity extends BaseActivityWhite {

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
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.top5)
    RelativeLayout top5;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    private TimePickerView mDatePicker;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_square_plan;
    }

    @Override
    protected void init() {
        tvTitle.setText("发布圈子任务");
        icMore.setVisibility(View.GONE);
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.ic_back, R.id.et_content, R.id.submit, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.et_content:
                break;
            case R.id.submit:
                SaveAction();
                break;
            case R.id.tv_end:
                StartDatePicker();
                break;
        }
    }

    /**
     * 保存计划
     */
    private void SaveAction() {
        String json = getIntent().getStringExtra("json");
        CmtDetailsBean bean = new Gson().fromJson(json, CmtDetailsBean.class);
        UserLoginBean.DataDTO userInfo = getUserInfo();
        if(!userInfo.getUserId().equals(bean.getUserId())){
            CommonUtils.TWarning("抱歉，您不具有发布圈子任务权限！");
            return;
        }

        //获取文本框中的内容
        String titles = GetEditStr(title);
        String content = GetEditStr(etContent);
        String tvend = GetTextStr(tvEnd);

        //判断
        if (titles.isEmpty() || content.isEmpty() || tvend.equals("请选择截止时间")) {
            TWarning2("请完善信息");
        } else {
            //设置参数
            HashMap<Object, Object> map = new HashMap<>();
            map.put("comtTitle", titles);
            map.put("startTime", DateUtil.format(new Date(), "yyyy-MM-dd"));
            map.put("endTime", tvend);
            map.put("comtContent", content);
            map.put("userId",userInfo.getUserId());
            map.put("cmtId",bean.getCommunityId());
            OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.insertComtInfo, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    InsertComtBean bean = new Gson().fromJson(json, InsertComtBean.class);
                    if (bean.getCode() == 1){
                        CommonUtils.TInfo2("保存圈子任务成功");
                        DelayClosed(activity);
                    }else {
                        CommonUtils.TWarning2("保存圈子任务失败");
                    }
                }
            });
        }
    }

    /**
     * 选择时间
     */
    private void StartDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(activity, new OnTimeSelectListener() {
                @SuppressLint("SetTextI18n")
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onTimeSelected(Date date, View v) {
                    LocalDate localDate = LocalDate.now();
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDate start = date.toInstant().atZone(zoneId).toLocalDate();
                    if (start.isBefore(localDate)) {
                        ToastShow("不能小于当前时间");
                        return;
                    }
                    tvEnd.setText(DateUtil.format(date, "yyyy-MM-dd"));
                }
            }).setTimeSelectChangeListener(date -> Log.i("pvTime", "onTimeSelectChanged"))
                    .setTitleText("选择时间")
                    .setType(TimePickerType.DEFAULT)
                    .build();
            mDatePicker.show();
            mDatePicker = null;
        }
    }


}