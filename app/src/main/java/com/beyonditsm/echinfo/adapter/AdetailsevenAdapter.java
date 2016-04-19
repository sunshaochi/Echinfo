package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * 修改记录
 * Created by gxy on 2016/4/19.
 */
public class AdetailsevenAdapter extends BaseAdapter {
    private Context context;
    public AdetailsevenAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 3;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.annual_list_seven, null);
            holder=new ViewHolder();
            holder.type= (TextView) convertView.findViewById(R.id.type);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.before= (TextView) convertView.findViewById(R.id.before);
            holder.after= (TextView) convertView.findViewById(R.id.after);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView time;
        private TextView type;
        private TextView before;
        private TextView after;
    }
}
