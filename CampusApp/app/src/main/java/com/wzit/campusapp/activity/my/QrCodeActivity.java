package com.wzit.campusapp.activity.my;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.utils.common.QRCodeUtil;
import com.wzit.campusapp.view.IconFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人二维码
 */
@SuppressLint("NonConstantResourceId")
public class QrCodeActivity extends BaseActivityWhite {

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
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.top2)
    RelativeLayout top2;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_qrcode;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        tvTitle.setText("二维码");
        icMore.setVisibility(View.GONE);
        Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        final Bitmap bitmap = QRCodeUtil.createQRImage(CommonUtils.getUserInfo().getUserName(), 300, 300, bmp, null);
        ivQrcode.setImageBitmap(bitmap);
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.ic_back)
    public void onClick() {
        finish();
    }
}
