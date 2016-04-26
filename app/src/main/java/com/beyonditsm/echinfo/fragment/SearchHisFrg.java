package com.beyonditsm.echinfo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
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

    @ViewInject(R.id.llClear)
    private LinearLayout llClear;

    private List<SearchEntity> listData;
    private MyAdapter adapter;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frg_search, null);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setData();
        llClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDao.clearAll();
                setData();
            }
        });
    }

    private void setData(){
        listData= SearchDao.getSearchList();
        if(listData.size()==0){
            llHasHis.setVisibility(View.GONE);
            llNoHis.setVisibility(View.VISIBLE);
            llClear.setVisibility(View.GONE);
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
