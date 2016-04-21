package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * Created by bitch-1 on 2016/4/7.
 */
public class ReqyAdapter extends BaseAdapter {
    private Context context;
    public ReqyAdapter(Context context){
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
            convertView = View.inflate(context, R.layout.lv_item_rmqy, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.ratingBar= (RatingBar) convertView.findViewById(R.id.rating);
            holder.number= (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView company;
        private RatingBar ratingBar;
        private TextView number;
    }
}

