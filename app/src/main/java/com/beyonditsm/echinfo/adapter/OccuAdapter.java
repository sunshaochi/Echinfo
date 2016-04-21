package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * 职业选择适配器
 * Created by wangbin on 16/4/6.
 */
public class OccuAdapter extends BaseAdapter{
    private Context context;
    public OccuAdapter(Context context){
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
        convertView=View.inflate(context, R.layout.lv_occu_item,null);
        TextView job= (TextView) convertView.findViewById(R.id.job);
        return convertView;
    }
}
