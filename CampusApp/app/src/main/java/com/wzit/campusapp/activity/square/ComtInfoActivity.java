package com.wzit.campusapp.activity.square;

import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wzit.campusapp.R;
import com.wzit.campusapp.adapter.ComtInfoExpandableListviewAdapter;
import com.wzit.campusapp.adapter.NotFinishedExpandableListviewAdapter;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.CmtDetailsBean;
import com.wzit.campusapp.bean.GetComtInfoBean;
import com.wzit.campusapp.bean.NewComtInfoBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;
import com.wzit.campusapp.view.NestedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class ComtInfoActivity extends BaseActivityWhite {

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
    @BindView(R.id.expandlistview)
    NestedExpandableListView expandlistview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_comt_info;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        tvTitle.setText("圈子任务列表");
        icMore.setVisibility(View.GONE);
        getData();

        refreshLayout.setEnableRefresh(true);
        refreshLayout.setHeaderHeight(100);
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        List<List<NewComtInfoBean>> list3 = new ArrayList<>();
        String json = getIntent().getStringExtra("json");
        CmtDetailsBean bean = new Gson().fromJson(json, CmtDetailsBean.class);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("cmtId",bean.getCommunityId());
        Integer communityId = bean.getCommunityId();
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getComtInfo, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                if (refreshLayout != null) {
                    refreshLayout.finishRefresh();
                }
                GetComtInfoBean bean = new Gson().fromJson(json, GetComtInfoBean.class);
                if (bean.getCode() == 1) {
                    List<List<GetComtInfoBean.DataDTO.ChildrenDTO>> listList = bean.getData().getChildren();
                    for (int i = 0; i < listList.size(); i++) {
                        List<GetComtInfoBean.DataDTO.ChildrenDTO> children = bean.getData().getChildren().get(i);
                        List<NewComtInfoBean> list1 = new ArrayList<>();
                        for (int j = 0; j < children.size(); j++) {
                            NewComtInfoBean bean1 = new NewComtInfoBean();
                            bean1.setId(children.get(j).getComtId());
                            bean1.setComtTitle(children.get(j).getComtTitle());
                            bean1.setComtTime(children.get(j).getDay());
                            bean1.setComtContent(children.get(j).getComtContent());
                            bean1.setUserId(children.get(j).getUserId());
                            bean1.setCmtId(children.get(j).getCmtId());
                            list1.add(bean1);
                        }
                        list3.add(list1);
                    }
                    ComtInfoExpandableListviewAdapter adapter = new ComtInfoExpandableListviewAdapter(activity, bean.getData().getGroup(), list3, communityId);
                    expandlistview.setGroupIndicator(null);
                    expandlistview.setAdapter(adapter);
                } else {
                    expandlistview.setAdapter(new NotFinishedExpandableListviewAdapter(activity, new ArrayList<>(),  new ArrayList<>()));
                }

            }
        });
    }

    @OnClick(R.id.ic_back)
    public void onClick(){
        finish();
    }

    /**
     * 刷新
     */
    @Override
    public void onResume() {
        init();
        super.onResume();
    }
}