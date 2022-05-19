package com.wzit.campusapp.fragment;

import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.ToPage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.square.FabuActivity;
import com.wzit.campusapp.adapter.CmtListAdapter;
import com.wzit.campusapp.adapter.ImageAdapter;
import com.wzit.campusapp.base.BaseFragment;
import com.wzit.campusapp.bean.CountBean;
import com.wzit.campusapp.bean.DataBean;
import com.wzit.campusapp.bean.GetCmtBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;
import com.yanzhenjie.permission.util.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 圈子
 */
@SuppressLint("NonConstantResourceId")
public class TwoFragment extends BaseFragment {
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
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
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_fabu)
    ImageView ivFabu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.search_bar)
    SearchView searchView;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_two;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        icBack.setVisibility(View.GONE);
        tvTitle.setText("圈子");
        icMore.setVisibility(View.GONE);

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData(), activity);
        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(activity))//设置指示器
                .setOnBannerListener((data, position) -> {
                });

        refreshLayout.setEnableRefresh(true);
        refreshLayout.setHeaderHeight(100);
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        getData();

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getLimitData(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if("".equals(s)||s==null)
                    getData();
                return true;
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        HashMap<Object, Object> map = new HashMap<>();

        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCommunityInfoCount, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                CountBean countBean = new Gson().fromJson(json, CountBean.class);
                int code = countBean.getCode();
                if (code == 200) {
                    if(countBean.getData()>0){
                        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCommunityInfo, new OkSuccessInterface() {
                            @Override
                            public void OnSuccess(String json) {
                                if (refreshLayout != null) {
                                    refreshLayout.finishRefresh();
                                }
                                //获取返回的json数据并解析
                                GetCmtBean databean = new Gson().fromJson(json, GetCmtBean.class);
                                int code = databean.getCode();
                                if (code == 1) {
                                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                    CmtListAdapter adapter = new CmtListAdapter(activity, databean.getData());
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }else{
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        CmtListAdapter adapter = new CmtListAdapter(activity, new GetCmtBean().getData());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    /**
     * 根据条件获取数据
     */
    private void getLimitData(String limit) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("communityName",limit);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCommunityInfoLimitCount, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                CountBean countBean = new Gson().fromJson(json, CountBean.class);
                int code = countBean.getCode();
                if (code == 200) {
                    if(countBean.getData()>0){
                        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCommunityInfoLimit, new OkSuccessInterface() {
                            @Override
                            public void OnSuccess(String json) {
                                if (refreshLayout != null) {
                                    refreshLayout.finishRefresh();
                                }
                                //获取返回的json数据并解析
                                GetCmtBean databean = new Gson().fromJson(json, GetCmtBean.class);
                                int code = databean.getCode();
                                if (code == 1) {
                                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                    CmtListAdapter adapter = new CmtListAdapter(activity, databean.getData());
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }else{
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        CmtListAdapter adapter = new CmtListAdapter(activity, new GetCmtBean().getData());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.iv_fabu)
    public void onClick() {
        ToPage(FabuActivity.class);
    }
}
