package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.StockMsg;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/6.
 */
public class GudonginfoAdapter  extends BaseAdapter {
    private Context context;
    private List<StockMsg> list;
    public GudonginfoAdapter(Context context){
        this.context=context;
    }
    public GudonginfoAdapter(Context context,List<StockMsg> list){
        this.context=context;
        this.list=list;
    }

    public void notify(List<StockMsg> list){
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
            convertView = View.inflate(context, R.layout.lv_item_gudong, null);
            holder=new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.identity= (TextView) convertView.findViewById(R.id.identity);
            holder.outtime= (TextView) convertView.findViewById(R.id.outtime);
            holder.outmoney= (TextView) convertView.findViewById(R.id.outmoney);
            holder.realtime= (TextView) convertView.findViewById(R.id.realtime);
            holder.realmoney= (TextView) convertView.findViewById(R.id.realmoney);
            holder.payway= (TextView) convertView.findViewById(R.id.payway);
            holder.view=convertView.findViewById(R.id.view);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getName()))
            holder.name.setText(list.get(position).getName());
        if(!TextUtils.isEmpty(list.get(position).getStockType()))
            holder.identity.setText(list.get(position).getStockType());
        if(!TextUtils.isEmpty(list.get(position).getSubcribeTime()))
            holder.outtime.setText(list.get(position).getSubcribeTime());
        if(!TextUtils.isEmpty(list.get(position).getSubcribe()+""))
            holder.outmoney.setText(list.get(position).getSubcribe() + "");
        if(!TextUtils.isEmpty(list.get(position).getRealSubcribeTime()))
            holder.realtime.setText(list.get(position).getRealSubcribeTime());
        if(!TextUtils.isEmpty(list.get(position).getRealSubcribe() + ""))
            holder.realmoney.setText(list.get(position).getRealSubcribe() + "");
        if(!TextUtils.isEmpty(list.get(position).getRealSubcribeType()))
            holder.payway.setText(list.get(position).getRealSubcribeType());
        return convertView;
    }

    class ViewHolder{
        private TextView name;
        private TextView identity;
        private TextView outtime;
        private TextView outmoney;
        private TextView realtime;
        private TextView realmoney;
        private TextView payway;
        private View view;

    }

}