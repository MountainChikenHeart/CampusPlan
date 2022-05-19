package com.wzit.campusapp.activity;


import static com.wzit.campusapp.utils.common.CommonUtils.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * APP广告页
 */
@SuppressLint("NonConstantResourceId")
public class AdvertActivity extends BaseActivityWhite {
    @BindView(R.id.bj)
    ImageView bj;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.btn_tojump)
    TextView btnTojump;
    @BindView(R.id.top)
    RelativeLayout top;
    private Context context;
    private Activity activity;
    private CountDownTimer mTimer;
    private int adtime = 4;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_advert;
    }

    /**
     * 初始化
     */
    @SuppressLint("WrongConstant")
    @Override
    protected void init() {
        context = this;
        activity = this;
        PermissionAction();
    }

    /**
     * 权限申请
     */
    @SuppressLint("WrongConstant")
    private void PermissionAction() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                    top.setVisibility(View.VISIBLE);
                    countDownTime();
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                    new XPopup.Builder(context).asConfirm("提示", "在使用前，请允许权限？",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    PermissionAction();
                                }
                            }, new OnCancelListener() {
                                @Override
                                public void onCancel() {
                                    System.exit(0);
                                }
                            })
                            .show();
                })
                .start();
    }


    /**
     * 倒计时
     */
    private void countDownTime() {
        //用安卓自带的CountDownTimer实现
        mTimer = new CountDownTimer(adtime * 1000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                tvWenzi.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                mTimer.cancel();
                tvWenzi.setText("进入");
                ToPage(getUserInfo() == null ? LoginActivity.class : MainActivity.class);
                finishAffinity();
            }
        };
        mTimer.start();
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.tv_wenzi})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_wenzi) {//点击文字跳转
            mTimer.cancel();
            ToPage(getUserInfo() == null ? LoginActivity.class : MainActivity.class);
            finishAffinity();
        }
    }

    /**
     * 重新倒计时
     */
    @Override
    protected void onRestart() {
        countDownTime();
        super.onRestart();
    }

    /**
     * 跳转链接，清除计时器
     */
    @Override
    protected void onStop() {
        mTimer.cancel();
        super.onStop();
    }
}