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
 * 企业适配器
 * Created by wangbin on 16/4/7.
 */
public class EnterPAdapter extends BaseAdapter {

    private Context context;
    private List<CompanyEntity> list;


    public EnterPAdapter(Context context, List<CompanyEntity> list) {
        this.context = context;
        this.list = list;
    }

    public void notifyDataChange(List<CompanyEntity> list){
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.lv_item_evterp, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        if(!TextUtils.isEmpty(list.get(position).getCompanyName()))
            holder.tvComName.setText(list.get(position).getCompanyName());
        if(!TextUtils.isEmpty(list.get(position).getLegalRepPersion()))
            holder.tvPerson.setText("公司法人:"+list.get(position).getLegalRepPersion());
        if(!TextUtils.isEmpty(list.get(position).getManagementStatus()))
            holder.tvState.setText(list.get(position).getManagementStatus());

        return convertView;
    }

    public class ViewHolder {
        public final TextView tvComName;
        public final TextView tvPerson;
        public final TextView tvState;
        public final View root;

        public ViewHolder(View root) {
            tvComName = (TextView) root.findViewById(R.id.tvComName);
            tvPerson = (TextView) root.findViewById(R.id.tvPerson);
            tvState = (TextView) root.findViewById(R.id.tvState);
            this.root = root;
        }
    }
}
