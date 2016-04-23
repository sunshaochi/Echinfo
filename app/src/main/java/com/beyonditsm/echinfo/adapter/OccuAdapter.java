package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.OccuaptionEntity;
import com.beyonditsm.echinfo.http.CallBack;

import java.util.List;

/**
 * 职业选择适配器
 * Created by wangbin on 16/4/6.
 */
public class OccuAdapter extends BaseAdapter{
    private Context context;
    private List<OccuaptionEntity> list;
    public OccuAdapter(Context context){
        this.context=context;
    }
    public OccuAdapter(Context context,List<OccuaptionEntity> list){
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
        convertView=View.inflate(context, R.layout.lv_occu_item,null);
        TextView job= (TextView) convertView.findViewById(R.id.job);
        job.setText(list.get(position).getOccupationName());
        return convertView;
    }
}
