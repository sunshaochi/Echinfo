package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.PeopleEntity;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/7.
 */
public class PeopleAdapter  extends BaseAdapter {
    private Context context;
    private List<PeopleEntity>list;

    public PeopleAdapter(Context context,List<PeopleEntity>list) {
        this.context = context;
        this.list=list;
    }
    public void notify(List<PeopleEntity> list){
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
            convertView = View.inflate(context, R.layout.lv_item_people, null);
            holder=new ViewHolder();
            holder.tv_nm= (TextView) convertView.findViewById(R.id.tv_nm);
            holder.tv_jobnm= (TextView) convertView.findViewById(R.id.tv_jobnm);
            holder.iv_pp= (ImageView) convertView.findViewById(R.id.iv_pp);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getPersionName()))
            holder.tv_nm.setText(list.get(position).getPersionName());
        if(!TextUtils.isEmpty(list.get(position).getJobName()))
            holder.tv_jobnm.setText(list.get(position).getJobName());
      //  holder.iv_pp.setText(list.get(position).getRecordStatus());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_nm;
        private TextView tv_jobnm;
        private ImageView iv_pp;
    }
}