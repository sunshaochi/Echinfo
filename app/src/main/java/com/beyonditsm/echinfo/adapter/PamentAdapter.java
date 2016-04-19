package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.FenziEntity;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/18.
 */
public class PamentAdapter extends BaseAdapter {
    private Context context;
    private List<FenziEntity> list;

    public PamentAdapter(Context context,List<FenziEntity> list){
        this.context=context;
        this.list=list;
    }

    public void notify(List<FenziEntity> list){
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
            convertView = View.inflate(context, R.layout.lv_item_pament, null);
            holder=new ViewHolder();
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_faren= (TextView) convertView.findViewById(R.id.tv_faren);
            holder.tv_zhuantai= (TextView) convertView.findViewById(R.id.tv_zhuantai);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getCompanyName());
        holder.tv_faren.setText(list.get(position).getLegalRepPersion());
        holder.tv_zhuantai.setText(list.get(position).getRecordStatus());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name;
        private TextView tv_faren;
        private TextView tv_zhuantai;
    }
}