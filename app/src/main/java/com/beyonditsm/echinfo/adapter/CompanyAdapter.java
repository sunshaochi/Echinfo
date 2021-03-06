package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.entity.StatusEntity;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/6.
 * 公司详情页面
 */
public class CompanyAdapter extends BaseAdapter {
    private StatusEntity entity;
    private List<Integer> list;

    private final int IMAGES[] = {R.mipmap.gongshang, R.mipmap.qytp, R.mipmap.hyfx,R.mipmap.sxxx,
            R.mipmap.susong, R.mipmap.dwtz, R.mipmap.gdxx, R.mipmap.qyzx,
            R.mipmap.nbxx,R.mipmap.fzjg,R.mipmap.zycy,R.mipmap.bgjl};
    private final String TITLES[] = {"工商信息", "企业图谱", "行业分析", "失信信息", "诉讼信息",
            "对外投资","股东信息","企业资讯","年报信息","分支机构","主要成员","变更记录"
    };
    private Context context;

    public CompanyAdapter(Context context) {
        this.context = context;
    }
    public CompanyAdapter(Context context,StatusEntity entity) {
        this.context = context;
        this.entity=entity;

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

        switch (position){
            case 0:
                if("1".equals(entity.getGongshangStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.gongshangw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 1:
                if("1".equals(entity.getTupuStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.qytpw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 2:
                if("1".equals(entity.getHangyeStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.hyfxw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 3:
                if ("1".equals(entity.getCourtitemStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.sxxxw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 4:
                if("1".equals(entity.getLawsuitmsgStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.susongw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 5:
                if("1".equals(entity.getAbroadinvestmenttatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.dwtzw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 6:
                if("1".equals(entity.getStockmsgStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.gdxxw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 7:
                if("1".equals(entity.getEnenewterprissStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.qyzxw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 8:
                if("1".equals(entity.getAnnualportsmsgStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.nbxxw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 9:
                if("1".equals(entity.getSonenterpriseStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.fzjgw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 10:
                if("1".equals(entity.getMainmemberStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.zycyw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
            case 11:
                if("1".equals(entity.getEditrecordStatus())){
                    convertView.setBackgroundResource(R.drawable.qygvitem_back);
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.black));
                }else{
                    viewHolder.ivQy.setImageResource(R.mipmap.bgjlw);
//                    convertView.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    viewHolder.tvQy.setTextColor(context.getResources().getColor(R.color.gray));
                }
                break;
        }

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
