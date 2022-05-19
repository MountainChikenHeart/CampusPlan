package com.wzit.campusapp.activity.square;

import static com.wzit.campusapp.utils.common.CommonUtils.DelayClosed;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.TInfo2;
import static com.wzit.campusapp.utils.common.CommonUtils.ToPageAndSend;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.wzit.campusapp.R;
import com.wzit.campusapp.adapter.CmtDetailsListAdapter;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.CmtDetailsBean;
import com.wzit.campusapp.bean.GetPersonBean;
import com.wzit.campusapp.bean.InsertCmtBean;
import com.wzit.campusapp.fragment.OneFragment;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.interfaces.XPopupInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 圈子详细
 */
@SuppressLint("NonConstantResourceId")
public class DetailsActivity extends BaseActivityWhite {


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
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_wenzi)
    TextView tvWenzi;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.tv_person)
    TextView tvPerson;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_square_details;
    }

    /**
     * 初始化
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {
        activity = this;
        tvTitle.setText("圈子详细");
        String json = getIntent().getStringExtra("json");
        System.out.println(json);
        CmtDetailsBean bean = new Gson().fromJson(json, CmtDetailsBean.class);
        tvCode.setText("邀请码：" + bean.getCommunityCode());
        tvContent.setText("简介：" + bean.getCommunityIntro());
        tvName.setText("圈子名称：" + bean.getCommunityName());
        tvUsername.setText(bean.getUserName());
        Glide.with(activity).load(AppNetConfig.ImgApi + bean.getUserHeaderimg()).into(head);
        tvTime.setText(bean.getCreateTime());
        getData(bean.getCommunityId());
    }

    /**
     * 获取数据
     */
    private void getData(int cmtId) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("cmtId",cmtId);
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getCmtPersonInfo, new OkSuccessInterface() {
            @SuppressLint("SetTextI18n")
            @Override
            public void OnSuccess(String json) {
                //获取返回的json数据并解析
                GetPersonBean databean = new Gson().fromJson(json, GetPersonBean.class);
                int code = databean.getCode();
                if (code == 1) {
                    tvPerson.setText("成员人数：" + databean.getData().size());
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                    CmtDetailsListAdapter adapter = new CmtDetailsListAdapter(activity, databean.getData());
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }


    /**
     * 点击事件
     */
    @OnClick({R.id.ic_back, R.id.ic_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.ic_more:
                List<String> list = new ArrayList<>();
                list.add("发布任务");
                list.add("查看任务");
                list.add("删除圈子");
                BottomMenu.show((AppCompatActivity) activity, list, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        if (index == 0) {//发布任务
                            String json = getIntent().getStringExtra("json");
                            CmtDetailsBean bean = new Gson().fromJson(json, CmtDetailsBean.class);
                            if(!bean.getUserId().toString().equals(getUserInfo().getUserId().toString())){
                                CommonUtils.TWarning("抱歉，您没有发布任务权限！");
                                return;
                            }
                            ToPageAndSend(SquarePlanActivity.class,"json",getIntent().getStringExtra("json"));
                        } else if (index == 1) {//查看任务
                            ToPageAndSend(ComtInfoActivity.class,"json",getIntent().getStringExtra("json"));
                        } else if(index == 2){//删除圈子
                            String json = getIntent().getStringExtra("json");
                            CmtDetailsBean bean = new Gson().fromJson(json, CmtDetailsBean.class);
                            if(!bean.getUserId().toString().equals(getUserInfo().getUserId().toString())){
                                CommonUtils.TWarning("抱歉，您没有删除圈子权限！");
                                return;
                            }
                            CommonUtils.ConfirmDialogShow(activity, "删除圈子", "确定要删除吗？", new XPopupInterface() {
                                @Override
                                public void onConfirm() {
                                    HashMap<Object,Object> map = new HashMap<>();
                                    map.put("communityId",bean.getCommunityId());
                                    OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.DeleteCommunityInfo, new OkSuccessInterface() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void OnSuccess(String json) {
                                            //获取返回的json数据并解析
                                            InsertCmtBean databean = new Gson().fromJson(json, InsertCmtBean.class);
                                            int code = databean.getCode();
                                            if (code == 1) {
                                                TInfo2("删除成功");
                                                DelayClosed(activity);
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancel() {
                                    CommonUtils.TInfo2("已取消！");
                                }
                            });
                        }
                    }
                }, true);
                break;
        }
    }

    /**
     * 获取传递过来的参数
     */
    public static void start(Context context, String json) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("json", json);
        context.startActivity(intent);
    }
}
