package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * 搜索失信适配器
 * Created by wangbin on 16/4/7.
 */
public class BadCAdaper extends BaseAdapter{

    private Context context;
    public BadCAdaper(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView = View.inflate(context, R.layout.lv_badc_item, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.cid= (TextView) convertView.findViewById(R.id.cid);
            holder.location= (TextView) convertView.findViewById(R.id.location);
            holder.idname= (TextView) convertView.findViewById(R.id.idname);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView company;
        private TextView cid;
        private TextView location;
        private TextView idname;
    }
}
