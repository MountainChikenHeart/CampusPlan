package com.wzit.campusapp.activity.my;



import static com.wzit.campusapp.utils.common.CommonUtils.Delay3s;
import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.GetEditStr;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.TSuccess2;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.UpdateUserBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改密码
 */
@SuppressLint("NonConstantResourceId")
public class XgmmActivity extends BaseActivityWhite {

    @BindView(R.id.cha)
    ImageView cha;
    @BindView(R.id.wenzi1)
    TextView wenzi1;
    @BindView(R.id.wenzi2)
    TextView wenzi2;
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
    private Activity activity;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_xgmm;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        activity = this;
    }

    /**
     * 修改密码判断
     */
    private void XgmmAction() {

        //获取文本框中的内容
        String passwords = GetEditStr(querenmima);
        String password2s = GetEditStr(mima);

        //判断
        if (passwords.isEmpty() || password2s.isEmpty()) {
            TWarning2("请填写完整信息");
        } else if (!passwords.equals(password2s)) {
            TWarning2("密码不一致");
        } else {
            //设置参数
            HashMap<String, String> map = new HashMap<>();
            map.put("userPassword", password2s);
            OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getUserUpdate, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    UpdateUserBean databean = new Gson().fromJson(json, UpdateUserBean.class);
                    int code = databean.getCode();
                    if (code == -1) {
                        TWarning2("修改失败");
                    } else {
                        TSuccess2("修改成功");
                        DelayClosed(activity);
                    }
                }
            });
        }
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.cha, R.id.rl_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cha:
                finish();
                break;
            case R.id.rl_zhuce:
                XgmmAction();
                break;
        }
    }
}
