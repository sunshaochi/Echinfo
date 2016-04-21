package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.AnnualEntity;

import java.util.List;

/**
 * Created by gxy on 2016/4/18.
 */
public class AnnualAdapter extends BaseAdapter {
    private Context context;
    private List<AnnualEntity> list;
    public AnnualAdapter(Context context,List<AnnualEntity> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.annual_item, null);
            holder=new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.company= (TextView) convertView.findViewById(R.id.company);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getYear()))
            holder.name.setText(list.get(position).getYear()+"年年报");
        if(!TextUtils.isEmpty(list.get(position).getCompanyName()))
            holder.company.setText(list.get(position).getCompanyName());
        return convertView;
    }

    class ViewHolder{
        private TextView name;
        private TextView company;
    }
}
