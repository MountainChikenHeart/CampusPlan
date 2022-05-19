package com.wzit.campusapp.adapter;


import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.ToastShow2;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kongzue.dialog.listener.InputDialogOkButtonClickListener;
import com.kongzue.dialog.v2.InputDialog;
import com.shehuan.niv.NiceImageView;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.square.DetailsActivity;
import com.wzit.campusapp.base.BaseRvAdapter;
import com.wzit.campusapp.base.BaseRvViewHolder;
import com.wzit.campusapp.bean.GetCmtBean;
import com.wzit.campusapp.bean.InsertCmtPerson;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.EditDialog;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 圈子列表适配器
 */
@SuppressLint("NonConstantResourceId")
public class CmtListAdapter extends BaseRvAdapter<GetCmtBean.DataDTO, CmtListAdapter.ViewHolder> {

    private Activity activity;

    public CmtListAdapter(Activity context, List<GetCmtBean.DataDTO> datas) {
        super(context, datas);
        this.activity = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(ViewHolder holder, GetCmtBean.DataDTO dataDTO, int position) {
        holder.tvTime.setText(dataDTO.getCreateTime());
        Glide.with(activity).load(AppNetConfig.ImgApi + dataDTO.getCommunityImg()).into(holder.niImg);
        holder.tvContent.setText(dataDTO.getCommunityIntro());
        holder.tvTitle.setText(dataDTO.getCommunityName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //是自己的圈子
                if (dataDTO.getUserId().equals(getUserInfo().getUserId())){
                    DetailsActivity.start(activity,new Gson().toJson(dataDTO));
                    return;
                }
                //是否加入过
                if (dataDTO.getIsJoin() == 1){
                    DetailsActivity.start(activity,new Gson().toJson(dataDTO));
                    return;
                }
                EditDialog editDialog = new EditDialog(activity);
                editDialog.setTitle("加入圈子");
                editDialog.setYesOnclickListener("确定", new EditDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick(String msg) {
                        if (msg.equals(dataDTO.getCommunityCode())){
                            editDialog.dismiss();
                            insertCmt(dataDTO);
                        }else {
                            CommonUtils.TWarning2("邀请码错误");
                        }
                    }
                });
                editDialog.setNoOnclickListener("取消", new EditDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        editDialog.dismiss();
                    }
                });
                editDialog.show();
            }
        });
    }

    /**
     * 加入圈子
     */
    private void insertCmt(GetCmtBean.DataDTO dataDTO) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("cmtId",dataDTO.getCommunityId());
        OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.InsertCmtPersonInfo, new OkSuccessInterface() {
            @SuppressLint("SetTextI18n")
            @Override
            public void OnSuccess(String json) {
                //获取返回的json数据并解析
                InsertCmtPerson databean = new Gson().fromJson(json, InsertCmtPerson.class);
                int code = databean.getCode();
                if (code == 1) {
                    InputDialog.unloadAllDialog();
                    DetailsActivity.start(activity,new Gson().toJson(dataDTO));
                }else {
                    ToastShow2("加入失败");
                }
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cmt, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseRvViewHolder {
        @BindView(R.id.ni_img)
        NiceImageView niImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
