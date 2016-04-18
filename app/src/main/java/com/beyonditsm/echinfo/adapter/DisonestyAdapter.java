package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * Created by gxy on 2016/4/15.
 * 失信榜单适配器
 */
public class DisonestyAdapter extends BaseAdapter {
    private Context context;
    public DisonestyAdapter(Context context){
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
        ViewHolder holder=null;
        if(convertView==null) {
            convertView = View.inflate(context, R.layout.dishoeny_item, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.cid= (TextView) convertView.findViewById(R.id.cid);
            holder.location= (TextView) convertView.findViewById(R.id.location);
            holder.idname= (TextView) convertView.findViewById(R.id.idname);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{
        private TextView company;
        private TextView cid;
        private TextView location;
        private TextView idname;
    }
}
