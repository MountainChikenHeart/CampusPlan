package com.wzit.campusapp.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.square.DetailsActivity;
import com.wzit.campusapp.base.BaseRvAdapter;
import com.wzit.campusapp.base.BaseRvViewHolder;
import com.wzit.campusapp.bean.GetCmtBean;
import com.wzit.campusapp.bean.GetPersonBean;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.utils.common.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 圈子成员列表适配器
 */
@SuppressLint("NonConstantResourceId")
public class CmtDetailsListAdapter extends BaseRvAdapter<GetPersonBean.DataDTO, CmtDetailsListAdapter.ViewHolder> {

    private Activity activity;

    public CmtDetailsListAdapter(Activity context, List<GetPersonBean.DataDTO> datas) {
        super(context, datas);
        this.activity = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(ViewHolder holder, GetPersonBean.DataDTO dataDTO, int position) {
        holder.tvUsername.setText(dataDTO.getUserName());
        Glide.with(activity).load(AppNetConfig.ImgApi + dataDTO.getUserHeaderimg()).into(holder.head);
        holder.rlTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.TInfo2("已提醒");
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_square_details, parent, false);
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
            rlTip.setVisibility(View.GONE);
        }
    }
}
