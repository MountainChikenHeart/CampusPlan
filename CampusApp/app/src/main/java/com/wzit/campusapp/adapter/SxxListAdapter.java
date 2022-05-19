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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseRvAdapter;
import com.wzit.campusapp.base.BaseRvViewHolder;
import com.wzit.campusapp.bean.GetSxxBean;
import com.wzit.campusapp.bean.SxxChildrenBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 四象限列表适配器
 */
@SuppressLint("NonConstantResourceId")
public class SxxListAdapter extends BaseRvAdapter<GetSxxBean.DataDTO, SxxListAdapter.ViewHolder> {

    private Activity activity;

    public SxxListAdapter(Activity context, List<GetSxxBean.DataDTO> datas) {
        super(context,datas);
        this.activity = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindData(ViewHolder holder, GetSxxBean.DataDTO dataDTO, int position) {
//        holder.ivDegree1.setVisibility(View.GONE);
//        holder.ivDegree2.setVisibility(View.GONE);
//        holder.ivDegree3.setVisibility(View.GONE);
//        holder.ivDegree4.setVisibility(View.GONE);
        if (position == 0) {
//            holder.ivDegree1.setVisibility(View.VISIBLE);
            holder.tvMark.setText("重要且紧急");
            holder.tvMark.setTextColor(Color.parseColor("#ff0000"));
            holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            List<SxxChildrenBean> list = new ArrayList<>();
            if(dataDTO.getOne()!=null){
                for (int i = 0; i < dataDTO.getOne().size(); i++) {
                    SxxChildrenBean bean = new SxxChildrenBean();
                    bean.setDay(dataDTO.getOne().get(i).getDay());
                    bean.setPlanName(dataDTO.getOne().get(i).getPlanName());
                    bean.setPlanId(dataDTO.getOne().get(i).getPlanId());
                    list.add(bean);
                }
            }
            SxxListChildrenAdapter adapter = new SxxListChildrenAdapter(activity,list,1);
            holder.recyclerView.setAdapter(adapter);
        }
        if (position == 1) {
//            holder.ivDegree2.setVisibility(View.VISIBLE);
            holder.tvMark.setText("重要不紧急");
            holder.tvMark.setTextColor(Color.parseColor("#ffa000"));
            holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            List<SxxChildrenBean> list = new ArrayList<>();
            if(dataDTO.getTwo()!=null){
                for (int i = 0; i < dataDTO.getTwo().size(); i++) {
                    SxxChildrenBean bean = new SxxChildrenBean();
                    bean.setDay(dataDTO.getTwo().get(i).getDay());
                    bean.setPlanName(dataDTO.getTwo().get(i).getPlanName());
                    bean.setPlanId(dataDTO.getTwo().get(i).getPlanId());
                    list.add(bean);
                }
            }
            SxxListChildrenAdapter adapter = new SxxListChildrenAdapter(activity,list,2);
            holder.recyclerView.setAdapter(adapter);
        }
        if (position == 2) {
//            holder.ivDegree3.setVisibility(View.VISIBLE);
            holder.tvMark.setText("不重要但紧急");
            holder.tvMark.setTextColor(Color.parseColor("#1B88EE"));
            holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            List<SxxChildrenBean> list = new ArrayList<>();
            if(dataDTO.getThree()!=null){
                for (int i = 0; i < dataDTO.getThree().size(); i++) {
                    SxxChildrenBean bean = new SxxChildrenBean();
                    bean.setDay(dataDTO.getThree().get(i).getDay());
                    bean.setPlanName(dataDTO.getThree().get(i).getPlanName());
                    bean.setPlanId(dataDTO.getThree().get(i).getPlanId());
                    list.add(bean);
                }
            }
            SxxListChildrenAdapter adapter = new SxxListChildrenAdapter(activity,list,3);
            holder.recyclerView.setAdapter(adapter);
        }
        if (position == 3) {
//            holder.ivDegree4.setVisibility(View.VISIBLE);
            holder.tvMark.setText("不重要不紧急");
            holder.tvMark.setTextColor(Color.parseColor("#03dac5"));
            holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            List<SxxChildrenBean> list = new ArrayList<>();
            if(dataDTO.getFour()!=null){
                for (int i = 0; i < dataDTO.getFour().size(); i++) {
                    SxxChildrenBean bean = new SxxChildrenBean();
                    bean.setDay(dataDTO.getFour().get(i).getDay());
                    bean.setPlanName(dataDTO.getFour().get(i).getPlanName());
                    bean.setPlanId(dataDTO.getFour().get(i).getPlanId());
                    list.add(bean);
                }
            }
            SxxListChildrenAdapter adapter = new SxxListChildrenAdapter(activity,list,4);
            holder.recyclerView.setAdapter(adapter);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sxx, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseRvViewHolder {
//        @BindView(R.id.iv_degree1)
//        CheckBox ivDegree1;
//        @BindView(R.id.iv_degree2)
//        CheckBox ivDegree2;
//        @BindView(R.id.iv_degree3)
//        CheckBox ivDegree3;
//        @BindView(R.id.iv_degree4)
//        CheckBox ivDegree4;
        @BindView(R.id.tv_mark)
        TextView tvMark;
        @BindView(R.id.top)
        RelativeLayout top;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
