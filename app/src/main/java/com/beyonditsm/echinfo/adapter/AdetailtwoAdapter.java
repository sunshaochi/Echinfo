package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * 网站网店
 * Created by gxy on 2016/4/19.
 */
public class AdetailtwoAdapter extends BaseAdapter {
    private Context context;
    public AdetailtwoAdapter(Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.annual_detail_two, null);
            holder=new ViewHolder();
            holder.website= (TextView) convertView.findViewById(R.id.website);
            holder.intro= (TextView) convertView.findViewById(R.id.intro);
            holder.iv= (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder{
        private TextView website;
        private TextView intro;
        private ImageView iv;
    }
}
