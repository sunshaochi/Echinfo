package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.beyonditsm.echinfo.R;


/**
 * 系统消息适配器
 * Created by Yang on 2016/2/18 0018.
 */
public class MsgAdp extends BaseAdapter {
    private LayoutInflater inflater;

    public MsgAdp(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
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
    public int getViewTypeCount() {
        // menu type count
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        // current menu type
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.lv_msg_item, null);
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.lv_msg_item, null);
//            new ViewHolder(convertView);
//        }
//        ViewHolder holder = (ViewHolder) convertView.getTag();
//        holder.swipe.setSwipeEnabled(false);
        return convertView;
    }

//    class ViewHolder {
//        ImageView iv_icon;
//        TextView msg_time;
//        TextView msg_content;
////        SwipeLayout swipe;
//
//        public ViewHolder(View view) {
//            iv_icon = (ImageView) view.findViewById(R.id.msg_icon);
//            msg_time = (TextView) view.findViewById(R.id.msg_time);
//            msg_content = (TextView) view.findViewById(R.id.msg_content);
////            swipe = (SwipeLayout) view.findViewById(R.id.swip);
//            view.setTag(this);
//        }
//    }
}