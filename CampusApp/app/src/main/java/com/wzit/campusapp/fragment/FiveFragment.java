package com.wzit.campusapp.fragment;

import static com.wzit.campusapp.utils.common.CommonUtils.LoginOutShow;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.LoginActivity;
import com.wzit.campusapp.activity.my.QrCodeActivity;
import com.wzit.campusapp.activity.my.UserInfoActivity;
import com.wzit.campusapp.activity.my.SysmActivity;
import com.wzit.campusapp.activity.my.XgmmActivity;
import com.wzit.campusapp.base.BaseFragment;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */
@SuppressLint("NonConstantResourceId")
public class FiveFragment extends BaseFragment {

    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.shezhi)
    IconFontTextView shezhi;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.ic_ewm)
    IconFontTextView icEwm;
    @BindView(R.id.tv_ewm)
    TextView tvEwm;
    @BindView(R.id.ic_right1)
    IconFontTextView icRight1;
    @BindView(R.id.top1)
    RelativeLayout top1;
    @BindView(R.id.line1)
    ImageView line1;
    @BindView(R.id.ic_grxx)
    IconFontTextView icGrxx;
    @BindView(R.id.tv_dqsj)
    TextView tvDqsj;
    @BindView(R.id.ic_right2)
    IconFontTextView icRight2;
    @BindView(R.id.top2)
    RelativeLayout top2;
    @BindView(R.id.line2)
    ImageView line2;
    @BindView(R.id.iv_xgmm)
    IconFontTextView ivXgmm;
    @BindView(R.id.tv_sbbh)
    TextView tvSbbh;
    @BindView(R.id.ic_right3)
    IconFontTextView icRight3;
    @BindView(R.id.top3)
    RelativeLayout top3;
    @BindView(R.id.line3)
    ImageView line3;
    @BindView(R.id.line5)
    ImageView line5;
    @BindView(R.id.ic_fkjy)
    IconFontTextView icFkjy;
    @BindView(R.id.tv_xtbb)
    TextView tvXtbb;
    @BindView(R.id.ic_right6)
    IconFontTextView icRight6;
    @BindView(R.id.top6)
    RelativeLayout top6;
    @BindView(R.id.line6)
    ImageView line6;
    @BindView(R.id.ic_gywm)
    IconFontTextView icGywm;
    @BindView(R.id.tv_jywl)
    TextView tvJywl;
    @BindView(R.id.ic_right8)
    IconFontTextView icRight8;
    @BindView(R.id.top7)
    RelativeLayout top7;
    @BindView(R.id.line8)
    ImageView line8;
    @BindView(R.id.ic_sysm)
    IconFontTextView icSysm;
    @BindView(R.id.tv_sysm)
    TextView tvSysm;
    @BindView(R.id.top8)
    RelativeLayout top8;
    @BindView(R.id.top11)
    RelativeLayout top11;
    @BindView(R.id.rl_tuichu)
    RelativeLayout rlTuichu;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_five;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        Glide.with(activity).load(AppNetConfig.ImgApi + getUserInfo().getUserHeaderimg()).into(head);
        name.setText(getUserInfo().getUserName());
        name.setText(getUserInfo().getUserNum());
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.top, R.id.top1, R.id.top2, R.id.top3, R.id.top6, R.id.top7, R.id.top8, R.id.rl_tuichu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top:
                break;
            case R.id.top1://二维码
                CommonUtils.ToPage(QrCodeActivity.class);
                break;
            case R.id.top2://个人信息
                CommonUtils.ToPage(UserInfoActivity.class);
                break;
            case R.id.top3://修改密码
                CommonUtils.ToPage(XgmmActivity.class);
                break;
            case R.id.top6:
                break;
            case R.id.top7://关于我们
                new XPopup.Builder(activity).asConfirm("关于我们", "联系电话：18703628548",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {//确认
                            }
                        }, new OnCancelListener() {//取消
                            @Override
                            public void onCancel() {
                            }
                        })
                        .show();
                break;
            case R.id.top8://软件协议
                CommonUtils.ToPage(SysmActivity.class);
                break;
            case R.id.rl_tuichu://退出登录
                LoginOutShow(activity, LoginActivity.class);
                break;
        }
    }
}
