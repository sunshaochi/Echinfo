package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.AnnualEntity;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/6.
 * 企业资讯适配器
 */
public class InformationAdapter extends BaseAdapter {
    private Context context;
    private List<AnnualEntity> list;
    public InformationAdapter(Context context){
        this.context=context;
    }
    public InformationAdapter(Context context,List<AnnualEntity> list){
        this.context=context;
        this.list=list;
    }

    public void notify(List<AnnualEntity> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null) {
            convertView = View.inflate(context, R.layout.lv_item_info, null);
            holder=new ViewHolder();
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.come= (TextView) convertView.findViewById(R.id.come);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getNewsName()))
            holder.title.setText(list.get(position).getNewsName());
        if(!TextUtils.isEmpty(list.get(position).getNewsFrom()))
            holder.come.setText("来源："+list.get(position).getNewsFrom());
        if(!TextUtils.isEmpty(list.get(position).getNewsTime()))
            holder.time.setText(list.get(position).getNewsTime());
        return convertView;
    }
    class ViewHolder{
        private TextView title;
        private TextView come;
        private TextView time;
    }
}
