package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * Created by bitch-1 on 2016/4/5.
 */
public class JiucuoAdapter extends BaseAdapter {

    private Context context;

    private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
            "对外投资","股东信息","企业咨询","年报信息","分子机构","主要成员","变更记录"
    };

    public JiucuoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Object getItem(int position) {
        return TITLES[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.gv_item,null);
        TextView tv_xx= (TextView) view.findViewById(R.id.tv_xx);
        tv_xx.setText(TITLES[position]);
        return view;
    }
}
