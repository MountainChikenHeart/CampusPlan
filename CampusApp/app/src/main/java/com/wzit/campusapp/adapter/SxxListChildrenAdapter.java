package com.wzit.campusapp.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseRvAdapter;
import com.wzit.campusapp.base.BaseRvViewHolder;
import com.wzit.campusapp.bean.GetSxxBean;
import com.wzit.campusapp.bean.SxxChildrenBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 四象限子列表适配器
 */
@SuppressLint("NonConstantResourceId")
public class SxxListChildrenAdapter extends BaseRvAdapter<SxxChildrenBean, SxxListChildrenAdapter.ViewHolder> {

    private Activity activity;
    private int mark;

    public SxxListChildrenAdapter(Activity context, List<SxxChildrenBean> datas,int mark) {
        super(context, datas);
        this.activity = context;
        this.mark = mark;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(ViewHolder holder, SxxChildrenBean dataDTO, int position) {
        holder.ivDegree1.setVisibility(View.GONE);
        holder.ivDegree2.setVisibility(View.GONE);
        holder.ivDegree3.setVisibility(View.GONE);
        holder.ivDegree4.setVisibility(View.GONE);
        if (mark == 1){
            holder.ivDegree1.setVisibility(View.VISIBLE);
            holder.tvName.setText(dataDTO.getPlanName());
            holder.tvTime.setText(dataDTO.getDay());
        }
        if (mark == 2){
            holder.ivDegree2.setVisibility(View.VISIBLE);
            holder.tvName.setText(dataDTO.getPlanName());
            holder.tvTime.setText(dataDTO.getDay());
        }
        if (mark == 3){
            holder.ivDegree3.setVisibility(View.VISIBLE);
            holder.tvName.setText(dataDTO.getPlanName());
            holder.tvTime.setText(dataDTO.getDay());
        }
        if (mark == 4){
            holder.ivDegree4.setVisibility(View.VISIBLE);
            holder.tvName.setText(dataDTO.getPlanName());
            holder.tvTime.setText(dataDTO.getDay());
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sxx_children, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_degree1)
        CheckBox ivDegree1;
        @BindView(R.id.iv_degree2)
        CheckBox ivDegree2;
        @BindView(R.id.iv_degree3)
        CheckBox ivDegree3;
        @BindView(R.id.iv_degree4)
        CheckBox ivDegree4;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.top)
        RelativeLayout top;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
