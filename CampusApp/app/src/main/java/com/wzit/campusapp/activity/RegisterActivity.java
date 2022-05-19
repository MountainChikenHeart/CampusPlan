package com.wzit.campusapp.activity;


import static com.wzit.campusapp.utils.common.CommonUtils.ClearAllInfo;
import static com.wzit.campusapp.utils.common.CommonUtils.Delay3s;
import static com.wzit.campusapp.utils.common.CommonUtils.DialogShow;
import static com.wzit.campusapp.utils.common.CommonUtils.GetEditStr;
import static com.wzit.campusapp.utils.common.CommonUtils.GetTextStr;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning2;
import static com.wzit.campusapp.utils.common.CommonUtils.ToPage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.RegisterBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户注册
 */
@SuppressLint("NonConstantResourceId")
public class RegisterActivity extends BaseActivityWhite {


    @BindView(R.id.cha)
    ImageView cha;
    @BindView(R.id.wenzi1)
    TextView wenzi1;
    @BindView(R.id.wenzi2)
    TextView wenzi2;
    @BindView(R.id.yonghuming)
    EditText yonghuming;
    @BindView(R.id.rl_yonghuming)
    RelativeLayout rlYonghuming;
    @BindView(R.id.mima)
    EditText mima;
    @BindView(R.id.rl_mima)
    RelativeLayout rlMima;
    @BindView(R.id.querenmima)
    EditText querenmima;
    @BindView(R.id.rl_querenmima)
    RelativeLayout rlQuerenmima;
    @BindView(R.id.rl_zhuce)
    RelativeLayout rlZhuce;
    @BindView(R.id.denglu)
    TextView denglu;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.school)
    EditText school;
    @BindView(R.id.rl_school)
    RelativeLayout rlSchool;
    private Context context;
    private Activity activity;
    private int sex_mark = 4;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        context = this;
        activity = this;
    }

    /**
     * 用户注册判断
     */
    private void RegisterAction() {

        //获取文本框中的内容
        String passwords = GetEditStr(querenmima);
        String password2s = GetEditStr(mima);
        String names = GetEditStr(yonghuming);
        String sexs = GetTextStr(sex);
        String schools = GetEditStr(school);

        //判断
        if (schools.isEmpty() || names.isEmpty() || passwords.isEmpty() || password2s.isEmpty() || sex_mark == 4) {
            TWarning2("请完善信息");
        } else if (!passwords.equals(password2s)) {
            TWarning2("密码不一致");
        } else {
            //设置参数
            HashMap<Object, Object> map = new HashMap<>();
            map.put("userName", names);
            map.put("userPassword", passwords);
            map.put("userSex", sex_mark);
            map.put("userSchool", schools);
            OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.Register, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    RegisterBean databean = new Gson().fromJson(json, RegisterBean.class);
                    int code = databean.getCode();
                    if (code == -1) {
                        TWarning("注册失败");
                    } else if (code == -2) {
                        TWarning("该用户已存在");
                    } else {
                        //清除保存的信息
                        ClearAllInfo();
                        DialogShow(context, "注册成功");
                        Delay3s(activity);
                    }
                }
            });
        }
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.cha, R.id.rl_zhuce, R.id.denglu,R.id.sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cha:
                finish();
                break;
            case R.id.rl_zhuce:
                RegisterAction();
                break;
            case R.id.denglu:
                ToPage(LoginActivity.class);
                break;
            case R.id.sex:
                getSex();
                break;
        }
    }

    /**
     * 性别选择
     */
    private void getSex() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("未知");
        BottomMenu.show((AppCompatActivity) activity, list, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                sex.setText(text);
                sex_mark = index;
            }
        }, true);
    }
}
