package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.CompanyEntity;

import java.util.List;

/**
 * 修改记录
 * Created by gxy on 2016/4/19.
 */
public class AdetailsevenAdapter extends BaseAdapter {
    private Context context;
    private List<CompanyEntity> list;
    public AdetailsevenAdapter(Context context){
        this.context=context;
    }
    public AdetailsevenAdapter(Context context,List<CompanyEntity> list){
        this.context=context;
        this.list=list;
    }
    public void notify(List<CompanyEntity> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }else {
            return 3;
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.annual_list_seven, null);
            holder=new ViewHolder();
            holder.view=convertView.findViewById(R.id.view);
            holder.type= (TextView) convertView.findViewById(R.id.type);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.before= (TextView) convertView.findViewById(R.id.before);
            holder.after= (TextView) convertView.findViewById(R.id.after);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(list!=null){
            holder.time.setText(list.get(position).getEditTime());
            holder.type.setText(list.get(position).getEditType());
            holder.before.setText(list.get(position).getBeforeEdit());
            holder.after.setText(list.get(position).getAfterEdit());
        }
        if(position==0){
            holder.view.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
    class ViewHolder{
        private View view;
        private TextView time;
        private TextView type;
        private TextView before;
        private TextView after;
    }
}
