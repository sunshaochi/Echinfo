package com.beyonditsm.echinfo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.SearchAct;
import com.beyonditsm.echinfo.base.BaseFragment;
import com.beyonditsm.echinfo.db.SearchDao;
import com.beyonditsm.echinfo.entity.SearchEntity;
import com.leaf.library.widget.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 搜索历史frgment
 * Created by wangbin on 16/4/8.
 */
public class SearchHisFrg extends BaseFragment {
    @ViewInject(R.id.lvHis)
    private MyListView plv;
    @ViewInject(R.id.llHasHis)
    private LinearLayout llHasHis;
    @ViewInject(R.id.llNoHis)
    private LinearLayout llNoHis;

    @ViewInject(R.id.tv_xinxi)
    private TextView tv_xinxi;//从主页查法人 查企业 查失信 进来后不同的无记录提示模块 （的企业名称 的法人或股东名称 的涉诉人名称）

//    @ViewInject(R.id.tv_shishi)
//    private TextView tv_shishi;//试试在上面的搜索框中输入您想要搜索

    @ViewInject(R.id.llClear)
    private LinearLayout llClear;

    private List<SearchEntity> listData;
    private MyAdapter adapter;
    private int key;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        key = arguments.getInt("key");

        setData();
        llClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDao.clearAll();
                setData();
            }
        });
        plv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((SearchAct)getActivity()).setText(listData.get(position).getContent(),listData.get(position).getType(),listData.get(position).getCountry());
            }
        });
    }

    private void setData(){
        listData= SearchDao.getSearchList();
        if(listData.size()==0){
            llHasHis.setVisibility(View.GONE);
            llNoHis.setVisibility(View.VISIBLE);
            llClear.setVisibility(View.GONE);
            switch (key){
                case 0:
                    tv_xinxi.setText("的企业名称，例如：“阿里巴巴");
                    break;
                case 1:
                    tv_xinxi.setText("的法人或股东名称，例如：“王明”");
                    break;
                case 2:
                    tv_xinxi.setText("的涉诉人名称，例如：“陈红”");
                    break;
            }

        }else{
            llHasHis.setVisibility(View.VISIBLE);
            llNoHis.setVisibility(View.GONE);
            llClear.setVisibility(View.VISIBLE);
        }
        if(adapter==null){
            adapter=new MyAdapter(listData);
            plv.setAdapter(adapter);
        }else{
            adapter.notifyDataChange(listData);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (receiver == null) {
            receiver = new MyBroadCastReceiver();
            getActivity().registerReceiver(receiver, new IntentFilter(SEARCH_HISTORY));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
            receiver = null;
        }
    }

    private MyBroadCastReceiver receiver;
    public final static String SEARCH_HISTORY = "com.searchhistory.receiver";

    /**
     * 接收参数
     */
    class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            setData();

        }
    }

    class MyAdapter extends BaseAdapter {
        private List<SearchEntity> list;

        public MyAdapter(List<SearchEntity> list) {
            this.list = list;
        }

        public void notifyDataChange(List<SearchEntity> list){
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
                convertView = View.inflate(getContext(), R.layout.layout_search_his, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tvContent.setText(list.get(position).getContent());
            holder.tvCountry.setText(list.get(position).getCountry());
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvContent;
            public final TextView tvCountry;
            public final View root;

            public ViewHolder(View root) {
                tvContent = (TextView) root.findViewById(R.id.tvContent);
                tvCountry = (TextView) root.findViewById(R.id.tvCountry);
                this.root = root;
            }
        }
    }
}
