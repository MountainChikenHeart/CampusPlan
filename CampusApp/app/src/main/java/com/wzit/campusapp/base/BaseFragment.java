package com.wzit.campusapp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.xuexiang.xui.utils.StatusBarUtils;

import butterknife.ButterKnife;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        activity = getActivity();
        init();
        //设置底部导航栏颜色
        StatusBarUtils.setNavigationBarColor(getActivity(), Color.parseColor("#ffffff"));
        setHasOptionsMenu(true);
        return rootView;
    }

    /**
     *布局
     */
    protected abstract int setLayoutId();

    /**
     * 初始化
     */
    protected abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onResume() {
        super.onResume();
        init();
    }
}
