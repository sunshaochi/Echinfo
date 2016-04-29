package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.CompanyEntity;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/7.
 */
public class ReqyAdapter extends BaseAdapter {
    private Context context;
    private List<CompanyEntity>list;
    public ReqyAdapter(Context context,List<CompanyEntity>list){
        this.context=context;
        this.list=list;
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
            convertView = View.inflate(context, R.layout.lv_item_rmqy, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.ratingBar= (RatingBar) convertView.findViewById(R.id.rating);
            holder.number= (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getCompanyName())) {
            holder.company.setText(list.get(position).getCompanyName());
        }
        if(!TextUtils.isEmpty(list.get(position).getLevel())) {
            holder.ratingBar.setRating(Float.parseFloat(list.get(position).getLevel()));
        }else {
            holder.ratingBar.setProgress(0);
        }
        if (!TextUtils.isEmpty(list.get(position).getFocus())) {
            holder.number.setText(list.get(position).getFocus());
        }else {
            holder.number.setText("0");
        }

        return convertView;
    }
    class ViewHolder{
        private TextView company;
        private RatingBar ratingBar;
        private TextView number;
    }
}

