package com.wzit.campusapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.wzit.campusapp.R;
import com.wzit.campusapp.view.DYLoadingView;


/**
 * 仿抖音加载
 */
public class DYLoading extends Dialog {

    private DYLoadingView dy;

    public DYLoading(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyloading);
        dy= findViewById(R.id.dyloading);
    }

    /**
     * 显示加载框
     */
    @Override
    public void show() {
        super.show();
        dy.setVisibility(View.VISIBLE);
        dy.start();
    }


    /**
     * 隐藏加载框
     */
    @Override
    public void dismiss() {
        super.dismiss();
        dy.setVisibility(View.GONE);
        dy.stop();
    }

    /**
     * 是否显示背景阴影效果,默认显示阴影
     * false ------> 不显示
     * true ------> 显示
     */
    public DYLoading isShadow(boolean show){
        if (!show){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        return this;
    }
}
