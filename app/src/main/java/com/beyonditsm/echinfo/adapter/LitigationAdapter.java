package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.LawsuitMsgEntity;

import java.util.List;

/**
 * 诉讼列表适配器
 * Created by bitch-1 on 2016/4/6.
 */
public class LitigationAdapter extends BaseAdapter {
    private Context context;
    private List<LawsuitMsgEntity> list;
    public LitigationAdapter(Context context){
        this.context=context;
    }
    public LitigationAdapter(Context context,List<LawsuitMsgEntity> list){
        this.context=context;
        this.list=list;
    }

    public void notify(List<LawsuitMsgEntity> list){
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
            convertView = View.inflate(context, R.layout.lv_item_litigation, null);
            holder=new ViewHolder();
            holder.fyname= (TextView) convertView.findViewById(R.id.fyname);
            holder.qiyehao= (TextView) convertView.findViewById(R.id.qiyehao);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.fyname.setText(list.get(position).getCourt());
        holder.qiyehao.setText(list.get(position).getCaseNo());
        holder.time.setText(list.get(position).getRegistrineTime());
        return convertView;
    }

    class ViewHolder{
        private TextView fyname;
        private TextView qiyehao;
        private TextView time;
    }
}
