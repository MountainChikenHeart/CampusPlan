package com.wzit.campusapp.adapter;


import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.square.SubComtInfoDetailsActivity;
import com.wzit.campusapp.base.BaseRvAdapter;
import com.wzit.campusapp.base.BaseRvViewHolder;
import com.wzit.campusapp.bean.GetPersonBean;
import com.wzit.campusapp.bean.InsertCmtBean;
import com.wzit.campusapp.bean.InsertComtBean;
import com.wzit.campusapp.bean.SubComtInfoBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 圈子任务提交成员列表适配器
 */
@SuppressLint("NonConstantResourceId")
public class ComtInfoUserListAdapter extends BaseRvAdapter<GetPersonBean.DataDTO, ComtInfoUserListAdapter.ViewHolder> {

    private Activity activity;
    private List<SubComtInfoBean.DataDTO> SubComtInfoData;
    private boolean commit;
    private boolean finish;
    private int comtId;

    public ComtInfoUserListAdapter(Activity context, List<GetPersonBean.DataDTO> datas,List<SubComtInfoBean.DataDTO> SubComtInfoData, boolean commit, boolean finish, int comtId) {
        super(context, datas);
        this.activity = context;
        this.SubComtInfoData = SubComtInfoData;
        this.commit = commit;
        this.finish = finish;
        this.comtId = comtId;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(ViewHolder holder, GetPersonBean.DataDTO dataDTO, int position) {
        holder.tvUsername.setText(dataDTO.getUserName());
        Glide.with(activity).load(AppNetConfig.ImgApi + dataDTO.getUserHeaderimg()).into(holder.head);
        holder.rlTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commit){
                    Intent intent = new Intent(context, SubComtInfoDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    for(SubComtInfoBean.DataDTO SubComtInfoDataDTO:SubComtInfoData){
                        if(dataDTO.getUserId().equals(SubComtInfoDataDTO.getUserId())){
                            intent.putExtra("imgUrl", SubComtInfoDataDTO.getSubComtImg());
                            intent.putExtra("comtId",SubComtInfoDataDTO.getComtId().toString());
                            intent.putExtra("finish",String.valueOf(finish));
                        }
                    }
                    intent.putExtra("userId",dataDTO.getUserId().toString());
                    context.startActivity(intent);
                }
                else{
                    HashMap<Object,Object> map = new HashMap<>();
                    map.put("comtId",comtId);
                    map.put("userId",dataDTO.getUserId());
                    map.put("isUrge",1);
                    System.out.println(dataDTO.getUserId());
                    System.out.println(comtId);
                    OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.updateComtInfoUrge, new OkSuccessInterface() {
                        @Override
                        public void OnSuccess(String json) {
                            //获取返回的json数据并解析
                            InsertComtBean insertComtBean = new Gson().fromJson(json, InsertComtBean.class);
                            int code = insertComtBean.getCode();
                            if (code == 1) {
                                CommonUtils.TInfo2("提醒成功！");
                            }
                        }
                    });
                }
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(commit)
            view = LayoutInflater.from(context).inflate(R.layout.item_comt_user_commit, parent, false);
        else
            view = LayoutInflater.from(context).inflate(R.layout.item_comt_user_not_commit, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseRvViewHolder {
        @BindView(R.id.head)
        CircleImageView head;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.rl_tip)
        RelativeLayout rlTip;
        @BindView(R.id.top)
        RelativeLayout top;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
