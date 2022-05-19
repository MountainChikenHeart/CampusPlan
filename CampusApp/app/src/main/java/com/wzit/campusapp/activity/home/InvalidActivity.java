package com.wzit.campusapp.activity.home;

import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wzit.campusapp.R;
import com.wzit.campusapp.adapter.IsFinishedExpandableListviewAdapter;
import com.wzit.campusapp.adapter.NotFinishedExpandableListviewAdapter;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.GetPlanBean;
import com.wzit.campusapp.bean.NewPlanBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;
import com.wzit.campusapp.view.NestedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已失效
 */
@SuppressLint("NonConstantResourceId")
public class InvalidActivity extends BaseActivityWhite {

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
    @BindView(R.id.iv_fabu)
    ImageView ivFabu;
    @BindView(R.id.expandlistview)
    NestedExpandableListView expandlistview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_one;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        icMore.setVisibility(View.GONE);
        ivFabu.setVisibility(View.GONE);
        tvTitle.setText("已失效");
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
     * 点击事件
     */
    @OnClick({R.id.ic_back})
    public void onClick(View view) {
        if (view.getId() == R.id.ic_back) {//返回
            finish();
        }
    }

    /**
     * 获取数据
     */
    private void getData() {
        List<List<NewPlanBean>> list3 = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getPlan, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                if (refreshLayout != null) {
                    refreshLayout.finishRefresh();
                }
                GetPlanBean bean = new Gson().fromJson(json, GetPlanBean.class);
                if (bean.getCode() == 1) {
                    List<List<GetPlanBean.DataDTO.ChildrenDTO>> listList = bean.getData().getChildren();
                    for (int i = 0; i < listList.size(); i++) {
                        List<GetPlanBean.DataDTO.ChildrenDTO> children = bean.getData().getChildren().get(i);
                        List<NewPlanBean> list1 = new ArrayList<>();
                        for (int j = 0; j < children.size(); j++) {
                            NewPlanBean bean1 = new NewPlanBean();
                            bean1.setId(children.get(j).getPlanId());
                            bean1.setPlanName(children.get(j).getPlanName());
                            bean1.setRank(children.get(j).getPlanDegree());
                            bean1.setPlanTime(children.get(j).getDay());
                            bean1.setPlanContent(children.get(j).getPlanContent());
                            list1.add(bean1);
                        }
                        list3.add(list1);
                    }
                    IsFinishedExpandableListviewAdapter adapter = new IsFinishedExpandableListviewAdapter(activity, bean.getData().getGroup(), list3);
                    expandlistview.setGroupIndicator(null);
                    expandlistview.setAdapter(adapter);
                } else {
                    expandlistview.setAdapter(new NotFinishedExpandableListviewAdapter(activity, new ArrayList<>(),  new ArrayList<>()));

                }

            }
        });
    }
}
