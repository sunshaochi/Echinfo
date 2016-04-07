package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;

/**
 * Created by bitch-1 on 2016/4/6.
 * 公司详情页面
 */
public class CompanyAdapter extends BaseAdapter {

    private final int IMAGES[] = {R.mipmap.gongshang, R.mipmap.qytp, R.mipmap.hyfx,R.mipmap.sxxx,
            R.mipmap.susong, R.mipmap.dwtz, R.mipmap.gdxx, R.mipmap.qyzx,
            R.mipmap.nbxx,R.mipmap.fzjg,R.mipmap.zycy,R.mipmap.bgjl};
    private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失业信息", "诉讼信息",
            "对外投资","股东信息","企业咨询","年报信息","分子机构","主要成员","变更记录"
    };
    private Context context;

    public CompanyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return IMAGES.length;
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
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView = View.inflate(context, R.layout.gvqiye_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.ivQy.setImageResource(IMAGES[position]);
        viewHolder.tvQy.setText(TITLES[position]);

        return convertView;



    }

    public class ViewHolder {
        public final ImageView ivQy;
        public final TextView tvQy;
        public final View root;

        public ViewHolder(View root) {
            ivQy = (ImageView) root.findViewById(R.id.ivQy);
            tvQy = (TextView) root.findViewById(R.id.tvQy);
            this.root = root;
        }
    }
}
