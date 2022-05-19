package com.wzit.campusapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.home.PlanDetailsActivity;
import com.wzit.campusapp.bean.NewPlanBean;
import com.wzit.campusapp.interfaces.PlanCheckInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 已完成任务适配器
 */
public class IsFinishedExpandableListviewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<String> list1 = new ArrayList<>();
    private List<List<NewPlanBean>> list3 = new ArrayList<>();
    private PlanCheckInterface planCheckInterface;

    public PlanCheckInterface getPlanCheckInterface() {
        return planCheckInterface;
    }

    public void setPlanCheckInterface(PlanCheckInterface planCheckInterface) {
        this.planCheckInterface = planCheckInterface;
    }

    public IsFinishedExpandableListviewAdapter(Context context, List<String> list1, List<List<NewPlanBean>> list3){
        this.context=context;
        this.list1=list1;
        this.list3=list3;
    }

    @Override
    public int getGroupCount() {
        return list1.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list3.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return list3.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list3.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_parent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title = convertView.findViewById(R.id.title);
            groupViewHolder.tv_state = convertView.findViewById(R.id.tv_state);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        //主布局赋值标题
        groupViewHolder.tv_title.setText(list1.get(groupPosition));
        //如果是展开状态，
        if (isExpanded){
            groupViewHolder.tv_state.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.xl2));
        }else{
            groupViewHolder.tv_state.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.xl1));
        }
        return convertView;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_chidren_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.key = (TextView)convertView.findViewById(R.id.tv_name);//任务名称
            childViewHolder.value = (TextView)convertView.findViewById(R.id.tv_time);//时间
            childViewHolder.degree1 = (CheckBox) convertView.findViewById(R.id.iv_degree1);//等级
            childViewHolder.degree2 = (CheckBox) convertView.findViewById(R.id.iv_degree2);//等级
            childViewHolder.degree3 = (CheckBox) convertView.findViewById(R.id.iv_degree3);//等级
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.degree1.setVisibility(View.GONE);
        childViewHolder.degree2.setVisibility(View.GONE);
        childViewHolder.degree3.setVisibility(View.GONE);

        NewPlanBean bean = list3.get(groupPosition).get(childPosition);
        //子布局赋值
        childViewHolder.key.setText(bean.getPlanName());
        childViewHolder.value.setText(bean.getPlanTime());

        //等级
        if (bean.getRank() == 0){
             childViewHolder.degree1.setVisibility(View.VISIBLE);
        }else if (bean.getRank() == 1){
             childViewHolder.degree2.setVisibility(View.VISIBLE);
        }if (bean.getRank() == 2){
            childViewHolder.degree3.setVisibility(View.VISIBLE);
        }

        childViewHolder.degree1.setClickable(false);
        childViewHolder.degree2.setClickable(false);
        childViewHolder.degree3.setClickable(false);

        //点击详细
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanDetailsActivity.start(context,new Gson().toJson(bean));
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private static class GroupViewHolder {
        TextView tv_title;
        ImageView tv_state;
    }

    private static class ChildViewHolder {
        TextView key,value;
        CheckBox degree1,degree2,degree3;
    }
}
