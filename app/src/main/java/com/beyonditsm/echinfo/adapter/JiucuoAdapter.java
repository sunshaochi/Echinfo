package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.ErrorAct;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bitch-1 on 2016/4/5.
 */
public class JiucuoAdapter extends BaseAdapter {

    private Context context;
    private ErrorAct activity;
    private Map<Integer,String>map;


    private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
            "对外投资","股东信息","企业咨询","年报信息","分子机构","主要成员","变更记录"
    };

    public JiucuoAdapter(Context context,ErrorAct activity) {
        this.context = context;
        this.activity=activity;
        map=new HashMap<>();
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
    public View getView(  int position, View convertView, ViewGroup parent) {
        final int dex=position;
        View view=View.inflate(context, R.layout.gv_item,null);
          final CheckBox ck_xx= (CheckBox) view.findViewById(R.id.ck_xx);
        ck_xx.setText(TITLES[position]);
        ck_xx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    map.put(dex,ck_xx.getText().toString()+"");
                    activity.setData(map);
                } else {
                        map.remove(dex);
                    activity.setData(map);
                }
            }
        });
        return view;
    }


}
