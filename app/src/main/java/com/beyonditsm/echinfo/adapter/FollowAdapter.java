package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.CompanyEntity;

import java.util.List;

/**
 * Created by wangbin on 16/4/5.
 */
public class FollowAdapter extends BaseAdapter{
    private Context context;
    List<CompanyEntity> list;
    public FollowAdapter(Context context){
        this.context=context;
    }
    public FollowAdapter(Context context,List<CompanyEntity> list){
        this.context=context;
        this.list=list;
    }
    public void notify(List<CompanyEntity> list){
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
            convertView = View.inflate(context, R.layout.lv_item_follow, null);
            holder=new ViewHolder();
            holder.company= (TextView) convertView.findViewById(R.id.company);
            holder.lename= (TextView) convertView.findViewById(R.id.lename);
            holder.status= (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(list!=null){
            if(!TextUtils.isEmpty(list.get(position).getCompanyName()))
                holder.company.setText(list.get(position).getCompanyName());
            if(!TextUtils.isEmpty(list.get(position).getRepPersion()))
                holder.lename.setText("公司法人："+list.get(position).getRepPersion());
            if(!TextUtils.isEmpty(list.get(position).getRecordStatus()))
                holder.status.setText(list.get(position).getRecordStatus());
        }
        return convertView;
    }
    class ViewHolder{
        private TextView company;
        private TextView lename;
        private TextView status;
    }
}
