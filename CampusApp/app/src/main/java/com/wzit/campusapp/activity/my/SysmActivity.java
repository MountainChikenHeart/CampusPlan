package com.wzit.campusapp.activity.my;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.view.IconFontTextView;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 软件协议
 */
@SuppressLint("NonConstantResourceId")
public class SysmActivity extends BaseActivityWhite {
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_top2)
    ImageView ivTop2;
    @BindView(R.id.ic_back)
    IconFontTextView icBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.agreement_content)
    TextView agreementContent;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_sysm;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        tvTitle.setText("软件协议");
        try {
            InputStream is = getResources().openRawResource(R.raw.agreement);
            byte[] bytes = new byte[is.available()];
            int len = is.read(bytes);
            if (len != 0) {
                String rawTxt = new String(bytes);
                agreementContent.setText(rawTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.ic_back)
    public void onClick() {
        finish();
    }
}
