package com.wzit.campusapp.activity.home;

import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.adapter.SxxListAdapter;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.CountBean;
import com.wzit.campusapp.bean.GetSxxBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 四象限
 */
@SuppressLint("NonConstantResourceId")
public class SXXActivity extends BaseActivityWhite {
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_sxx;
    }

    @Override
    protected void init() {
        tvTitle.setText("四象限");
        icMore.setVisibility(View.GONE);
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HashMap<Object, Object> map = new HashMap<>();
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSxx, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                GetSxxBean bean = new Gson().fromJson(json, GetSxxBean.class);
                if (bean.getCode() == 1){
                    //设置布局管理器
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    SxxListAdapter adapter = new SxxListAdapter(activity, bean.getData());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    /**
     * 返回
     */
    @OnClick(R.id.ic_back)
    public void onClick() {
        finish();
    }
}
