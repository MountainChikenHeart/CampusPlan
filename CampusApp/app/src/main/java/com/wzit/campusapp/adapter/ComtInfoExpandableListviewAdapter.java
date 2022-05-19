package com.wzit.campusapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.square.ComtInfoDetailsActivity;
import com.wzit.campusapp.bean.NewComtInfoBean;
import com.wzit.campusapp.utils.common.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务适配器
 */
public class ComtInfoExpandableListviewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<String> list1 = new ArrayList<>();
    private List<List<NewComtInfoBean>> list3 = new ArrayList<>();

    private Integer communityId;

    public ComtInfoExpandableListviewAdapter(Context context, List<String> list1, List<List<NewComtInfoBean>> list3, Integer communityId){
        this.context=context;
        this.list1=list1;
        this.list3=list3;
        this.communityId=communityId;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_chidren_item_comt,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.key = (TextView)convertView.findViewById(R.id.tv_name);//任务名称
            childViewHolder.value = (TextView)convertView.findViewById(R.id.tv_time);//时间
            childViewHolder.button = (CheckBox) convertView.findViewById(R.id.iv_button);//等级
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        NewComtInfoBean bean = list3.get(groupPosition).get(childPosition);
        //子布局赋值
        childViewHolder.key.setText(bean.getComtTitle());
        childViewHolder.value.setText(bean.getComtTime());

        //点击详细
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComtInfoDetailsActivity.start(context,new Gson().toJson(bean));
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
        Button button;
    }
}
