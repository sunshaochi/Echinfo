package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

import java.util.List;

/**
 * 选择区域
 * Created by wangbin on 16/4/7.
 */
public class CountryAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public CountryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.lv_country_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvCoun.setText(list.get(position));
        return convertView;
    }

    public class ViewHolder {
        public final TextView tvCoun;
        public final View root;

        public ViewHolder(View root) {
            tvCoun = (TextView) root.findViewById(R.id.tvCoun);
            this.root = root;
        }
    }
}
