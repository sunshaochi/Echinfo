package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

import java.util.List;

/**
 * Created by gxy on 2016/4/13.
 */
public class ExAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupData;
    private List<List<String>> childData;
    public ExAdapter(Context context){
        this.context=context;
    }
    public ExAdapter(Context context,List<String> groupData,List<List<String>> childData ){
        this.context=context;
        this.groupData=groupData;
        this.childData=childData;
    }
    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
//        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // 通过getSystemService方法实例化一个视图的填充器
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.detail_list_one, null);
        }
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(getGroup(groupPosition).toString());
//
        ImageView image=(ImageView) view.findViewById(R.id.image);
        //判断实例可以展开，如果可以则改变右侧的图标
        if(isExpanded)
            image.setBackgroundResource(R.mipmap.topup);
        else image.setBackgroundResource(R.mipmap.down);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        switch (groupPosition){
            case 0:
                //填充视图
                LayoutInflater inflater0 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater0.inflate(R.layout.detail_list_two, null);
                TextView title = (TextView) view.findViewById(R.id.name);
//                title.setText(childData.get(groupPosition).get(childPosition));
                ProgressBar progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
                switch (childPosition){
                    case 0:
                        progressBar.setProgress(80);
                        break;
                    case 1:
                        progressBar.setProgress(60);
                        break;
                    case 2:
                        progressBar.setProgress(50);
                        break;
                    case 3:
                        progressBar.setProgress(60);
                        break;
                    case 4:
                        progressBar.setProgress(80);
                        break;
                }
                TextView title2 = (TextView) view.findViewById(R.id.num);
//                title2.setText(childData.get(groupPosition).get(childPosition));
                break;
            case 2://个人失信
                //填充视图
                LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater2.inflate(R.layout.detail_list_four, null);
                TextView xh= (TextView) view.findViewById(R.id.xh);
                TextView name= (TextView) view.findViewById(R.id.name);
                TextView city= (TextView) view.findViewById(R.id.city);
                TextView num= (TextView) view.findViewById(R.id.num);
                TextView idCard= (TextView) view.findViewById(R.id.idCard);
                View view1=view.findViewById(R.id.view);
                if(childPosition==4){
                    view1.setVisibility(View.GONE);
                }
                break;
            case 3:
                //填充视图
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.detail_list_two, null);
                TextView title3 = (TextView) view.findViewById(R.id.name);
//                title3.setText(childData.get(groupPosition).get(childPosition).toString());
                ProgressBar progressBar3= (ProgressBar) view.findViewById(R.id.progressBar);
                TextView title4 = (TextView) view.findViewById(R.id.num);
//                title2.setText(childData.get(groupPosition).get(childPosition));
                break;
            default:
                //填充视图
                LayoutInflater inflaterd = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflaterd.inflate(R.layout.detail_list_three, null);
                TextView xhd= (TextView) view.findViewById(R.id.xh);
                TextView companyd= (TextView) view.findViewById(R.id.company);
                TextView numd= (TextView) view.findViewById(R.id.num);
                View view2=view.findViewById(R.id.view);
                if(childPosition==4){
                    view2.setVisibility(View.GONE);
                }
                break;
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
