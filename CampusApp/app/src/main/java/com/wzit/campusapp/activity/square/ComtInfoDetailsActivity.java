package com.wzit.campusapp.activity.square;

import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.adapter.CmtDetailsListAdapter;
import com.wzit.campusapp.adapter.ComtInfoUserListAdapter;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.ComtInfoDetailsBean;
import com.wzit.campusapp.bean.CountBean;
import com.wzit.campusapp.bean.GetPersonBean;
import com.wzit.campusapp.bean.InsertComtBean;
import com.wzit.campusapp.bean.InsertPlanBean;
import com.wzit.campusapp.bean.SubComtInfoBean;
import com.wzit.campusapp.bean.TotalBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.interfaces.XPopupInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class ComtInfoDetailsActivity extends BaseActivityWhite {

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
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.top1)
    RelativeLayout top1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_count1)
    TextView tvCount1;
    @BindView(R.id.tv_total1)
    TextView tvTotal1;
    @BindView(R.id.tv_count2)
    TextView tvCount2;
    @BindView(R.id.tv_total2)
    TextView tvTotal2;
    @BindView(R.id.tv_notCommit)
    TextView tvNotCommit;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.commit)
    RecyclerView commitRecyclerView;
    @BindView(R.id.not_commit)
    RecyclerView notCommitRecyclerView;
    @BindView(R.id.finish)
    RecyclerView finishRecyclerView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.submit)
    Button finish;

    Integer comtId;
    Integer userId;
    boolean isUrge;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_comt_info_details;
    }

    /**
     * 初始化
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {

        icMore.setVisibility(View.GONE);
        tvTitle.setText("圈子任务详细");
        String json = getIntent().getStringExtra("json");
        System.out.println(json);
        ComtInfoDetailsBean bean = new Gson().fromJson(json, ComtInfoDetailsBean.class);
        tvName.setText(bean.getComtTitle());
        tvTime.setText(bean.getComtTime());
        tvContent.setText(bean.getComtContent());
        comtId = bean.getId();
        userId = bean.getUserId();
        button.setVisibility(View.VISIBLE);
        finish.setVisibility(View.GONE);

        //查询是否被提醒
        HashMap<Object, Object> map = new HashMap<>();
        map.put("userId",getUserInfo().getUserId());
        map.put("comtId",comtId);
        map.put("isUrge",1);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getComtInfoUrge, new OkSuccessInterface() {
            @SuppressLint("SetTextI18n")
            @Override
            public void OnSuccess(String json) {
                //获取返回的json数据并解析
                InsertComtBean databean = new Gson().fromJson(json, InsertComtBean.class);
                int code = databean.getCode();
                if (code == 1) {
                    isUrge=true;
                }else{
                    isUrge = false;
                }
            }
        });

        //获得圈子总成员数
        map = new HashMap<>();
        map.put("cmtId",bean.getCmtId());
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCmtPersonInfo, new OkSuccessInterface() {
            @SuppressLint("SetTextI18n")
            @Override
            public void OnSuccess(String json) {
                //获取返回的json数据并解析
                GetPersonBean databean = new Gson().fromJson(json, GetPersonBean.class);
                int code = databean.getCode();
                if (code == 1) {
                    tvTotal1.setText(databean.getData().size()+"人提交该任务");
                    tvTotal2.setText(databean.getData().size()+"人完成该任务");
                }
            }
        });

        //获得已经完成任务的成员
        map = new HashMap<>();
        map.put("isFinished",1);
        map.put("comtId",comtId);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfoCount, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                CountBean countBean = new Gson().fromJson(json, CountBean.class);
                if(countBean.getCode()==200){
                    if(countBean.getData()==0){
                        tvCount2.setText("已有0/");
                        return;
                    }
                }
                HashMap<Object, Object> map = new HashMap<>();
                map.put("isFinished",1);
                map.put("comtId",comtId);
                OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfo, new OkSuccessInterface() {
                    @Override
                    public void OnSuccess(String json) {
                        SubComtInfoBean bean = new Gson().fromJson(json, SubComtInfoBean.class);
                        if(bean.getCode() == 1){
                            tvCount2.setText("已有"+bean.getData().size()+"/");
                        }else{
                            CommonUtils.TWarning2("系统错误，请稍后重试！");
                            DelayClosed(activity);
                        }
                    }
                });
            }
        });

        //获得已经提交任务的成员
        map = new HashMap<>();
        map.put("comtId",comtId);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfoCount, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                CountBean countBean = new Gson().fromJson(json, CountBean.class);
                if(countBean.getCode()==200){
                    if(countBean.getData()==0){
                        tvCount1.setText("已有0/");
                        if (isUrge){
                            CommonUtils.TInfo2("您已经被圈主提醒，请尽快提交该任务！");
                        }
                        return;
                    }
                }
                HashMap<Object, Object> map = new HashMap<>();
                map.put("comtId",comtId);
                OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfo, new OkSuccessInterface() {
                    @Override
                    public void OnSuccess(String json) {
                        SubComtInfoBean bean = new Gson().fromJson(json, SubComtInfoBean.class);
                        if(bean.getCode() == 1){
                            tvCount1.setText("已有"+bean.getData().size()+"/");
                            boolean flag = true;
                            for(SubComtInfoBean.DataDTO dataDTO:bean.getData()){
                                if(dataDTO.getComtId().toString().equals(comtId.toString())
                                        &&dataDTO.getUserId().toString().equals(getUserInfo().getUserId().toString())){
                                    button.setVisibility(View.GONE);
                                    finish.setVisibility(View.VISIBLE);
                                    finish.setText("您已提交该任务");
                                    flag = false;
                                }
                            }
                            if(flag && isUrge){
                                CommonUtils.TInfo2("您已经被圈主提醒，请尽快提交该任务！");
                            }
                        }else{
                            CommonUtils.TWarning2("系统错误，请稍后重试！");
                            DelayClosed(activity);
                        }
                    }
                });
            }
        });

        if(Objects.requireNonNull(getUserInfo()).getUserId().equals(userId)){
            button.setText("删除任务");
            button.setBackgroundColor(Color.parseColor("#ee5252"));
        }else{
            button.setText("提交任务");
            button.setBackgroundColor(Color.parseColor("#2488C6"));
        }

        if(getUserInfo().getUserId().equals(userId)){
            getData(bean.getCmtId());
        }else{
            tvNotCommit.setVisibility(View.GONE);
            tvCommit.setVisibility(View.GONE);
            tvFinish.setVisibility(View.GONE);
        }

    }

    /**
     * 获取未提交和已提交任务的成员
     */
    private void getData(int cmtId) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("cmtId",cmtId);

        GetPersonBean notCommit = new GetPersonBean();
        notCommit.setData(new ArrayList<>());
        GetPersonBean commit = new GetPersonBean();
        commit.setData(new ArrayList<>());
        GetPersonBean finish = new GetPersonBean();
        finish.setData(new ArrayList<>());

        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCmtPersonInfo, new OkSuccessInterface() {
            @Override
            public void OnSuccess(String json) {
                //获取返回的json数据并解析
                GetPersonBean databean = new Gson().fromJson(json, GetPersonBean.class);
                int code = databean.getCode();
                if (code == 1) {
                    HashMap<Object, Object> map = new HashMap<>();
                    map.put("comtId",comtId);
                    OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfoCount, new OkSuccessInterface() {
                        @Override
                        public void OnSuccess(String json) {
                            //获取返回的json数据并解析
                            CountBean count = new Gson().fromJson(json, CountBean.class);
                            int code = count.getCode();
                            if (code == 200) {
                                if(count.getData()>0){
                                    HashMap<Object, Object> map = new HashMap<>();
                                    map.put("comtId",comtId);
                                    OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getSubComtInfo, new OkSuccessInterface() {
                                        @Override
                                        public void OnSuccess(String json) {
                                            //获取返回的json数据并解析
                                            SubComtInfoBean subComtInfoBean = new Gson().fromJson(json, SubComtInfoBean.class);
                                            int code = subComtInfoBean.getCode();
                                            if (code == 1) {
                                                for(GetPersonBean.DataDTO dataGetPersonBean:databean.getData()){
                                                    boolean flag = true;
                                                    for(SubComtInfoBean.DataDTO dataSubComtInfoBean: subComtInfoBean.getData()){
                                                        if(dataGetPersonBean.getUserId().equals(dataSubComtInfoBean.getUserId())){
                                                            if(dataSubComtInfoBean.getIsFinished()!=null &&
                                                                    dataSubComtInfoBean.getIsFinished().equals("1"))
                                                                finish.getData().add(dataGetPersonBean);
                                                            else
                                                                commit.getData().add(dataGetPersonBean);
                                                            flag=false;
                                                            break;
                                                        }
                                                    }
                                                    if(flag)
                                                        notCommit.getData().add(dataGetPersonBean);
                                                }
                                                commitRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                                ComtInfoUserListAdapter adapter1 = new ComtInfoUserListAdapter(activity, commit.getData(), subComtInfoBean.getData(), true, false, comtId);
                                                commitRecyclerView.setAdapter(adapter1);
                                                finishRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                                ComtInfoUserListAdapter adapter2 = new ComtInfoUserListAdapter(activity, finish.getData(), subComtInfoBean.getData(), true, true, comtId);
                                                finishRecyclerView.setAdapter(adapter2);
                                                notCommitRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                                ComtInfoUserListAdapter adapter3 = new ComtInfoUserListAdapter(activity, notCommit.getData(), subComtInfoBean.getData(), false, false, comtId);
                                                notCommitRecyclerView.setAdapter(adapter3);
                                            }
                                        }
                                    });
                                }else{
                                    //全部都未提交
                                    notCommitRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                                    ComtInfoUserListAdapter adapter2 = new ComtInfoUserListAdapter(activity, databean.getData(), null,false, false, comtId);
                                    notCommitRecyclerView.setAdapter(adapter2);
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.ic_back)
    public void onFinish() {
        finish();
    }

    @OnClick(R.id.button)
    public void onClick(){

        if(Objects.requireNonNull(getUserInfo()).getUserId().equals(userId)){
            CommonUtils.ConfirmDialogShow(this, "删除", "确定要删除这项圈子任务吗？", new XPopupInterface() {
                @Override
                public void onConfirm() {
                    HashMap<Object,Object> map = new HashMap<>();
                    map.put("comtId",comtId);
                    OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.DeleteComtInfo, new OkSuccessInterface() {
                        @Override
                        public void OnSuccess(String json) {
                            InsertPlanBean bean = new Gson().fromJson(json, InsertPlanBean.class);
                            if(bean.getCode() == 1){
                                CommonUtils.TInfo2("删除成功");
                                DelayClosed(activity);
                            }else{
                                CommonUtils.TWarning2("删除失败");
                            }
                        }
                    });
                }
                @Override
                public void onCancel() {
                    CommonUtils.TInfo2("已取消");
                }
            });
        }else{
            CommonUtils.ToPageAndSend(SubComtInfoActivity.class,"comtId",comtId.toString());
        }
    }

    /**
     * 获取传递过来的参数
     */
    public static void start(Context context, String json) {
        Intent intent = new Intent(context, ComtInfoDetailsActivity.class);
        intent.putExtra("json", json);
        context.startActivity(intent);
    }

}