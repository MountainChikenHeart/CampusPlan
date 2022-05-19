package com.wzit.campusapp.activity;



import static com.wzit.campusapp.utils.common.CommonUtils.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.UserLoginBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.CustomVideoView;


import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hutool.crypto.SecureUtil;


/**
 * 登录页
 */
@SuppressLint("NonConstantResourceId")
public class LoginActivity extends BaseActivityWhite {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.yzm)
    TextView yzm;
    @BindView(R.id.wjmm)
    TextView wjmm;
    @BindView(R.id.btnVideo)
    CustomVideoView btnVideo;
    @BindView(R.id.xian1)
    ImageView xian1;
    @BindView(R.id.xian2)
    ImageView xian2;

    private Activity that;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        that = this;
        login.setAlpha(0.7f);
        login.setClickable(false);
        SetSystemBarColor(that, R.color.white);
        FullScreen(this);
        KeepScreenOn(this);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String names = name.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (pwd.length() > 0 && names.length() > 0) {
                    login.setAlpha(1.0f);
                    login.setClickable(true);
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String names = name.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (pwd.length() > 0 && names.length() > 0) {
                    login.setAlpha(1.0f);
                    login.setClickable(true);
                }
            }
        });
        String path = getPackageResourcePath();
        LoginVideo(btnVideo,"android.resource://"+ getPackageName() +"/" + R.raw.video);
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.login, R.id.yzm, R.id.wjmm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login://登录
                LoginAction();
                break;
            case R.id.yzm://验证码
                break;
            case R.id.wjmm://注册
                ToPage(RegisterActivity.class);
                break;
        }
    }

    /**
     * 登录
     */
    private void LoginAction() {
        String counts = GetEditStr(name);
        String pwd = GetEditStr(password);
        if (counts.isEmpty() || pwd.isEmpty()) {
            TWarning("请填写完整信息");
        }else {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("userName", counts);
            map.put("userPassword", pwd);
            OKJsonPost(that, new Gson().toJson(map), AppNetConfig.Login, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    System.out.println(json);
                    //获取返回的json数据并解析
                    UserLoginBean databean = new Gson().fromJson(json, UserLoginBean.class);
                    int code = databean.getCode();
                    if (code == 1) {
                        TInfo2("登录成功");
                        SaveKey("USER", "user", json);
                        DelayTwo(that, MainActivity.class);
                    } else {
                        TWarning2("用户名/密码错误");
                    }
                }
            });
        }
    }

    /**
     * 生命周期
     */
    @Override
    protected void onResume() {
        super.onResume();
        LoginVideo(btnVideo,"android.resource://"+ getPackageName() +"/" + R.raw.video);
    }
}
