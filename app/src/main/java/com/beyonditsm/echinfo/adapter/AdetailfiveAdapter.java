package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * 对外提供担保信息
 * Created by gxy on 2016/4/19.
 */
public class AdetailfiveAdapter extends BaseAdapter {
    private Context context;
    public AdetailfiveAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.annual_list_five, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.money= (TextView) convertView.findViewById(R.id.money);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView company;
        private TextView money;
        private TextView time;
    }
}
