package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.InformationAct;

/**
 * Created by bitch-1 on 2016/4/6.
 */
public class InformationAdapter extends BaseAdapter {
    private Context context;
    public InformationAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return 8;
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
        convertView=View.inflate(context, R.layout.lv_item_info,null);
        return convertView;
    }
}
