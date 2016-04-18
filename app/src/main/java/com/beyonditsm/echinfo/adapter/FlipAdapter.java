package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 *
 * Created by wangbin on 16/4/18.
 */
public class FlipAdapter extends BaseAdapter{
    private Context context;
    public FlipAdapter(Context context){
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
        convertView=View.inflate(context, R.layout.lv_flip_item,null);
        TextView tvNum= (TextView) convertView.findViewById(R.id.tvNum);
        tvNum.setText(position+"");
        return convertView;
    }
}
