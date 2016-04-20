package com.beyonditsm.echinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.activity.GudonginfoAct;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.entity.StockMsg;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.leaf.library.widget.MyListView;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gxy on 2016/4/19.
 */
public class AnnualExAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> groupData;
    private List<List<String>> childData;
    private MyListView lv1,lv2,lv3,lv5,lv6,lv7;
    public AnnualExAdapter(Context context){
        this.context=context;
    }
    public AnnualExAdapter(Context context, List<String> groupData, List<List<String>> childData){
        this.context=context;
        this.groupData=groupData;
        this.childData=childData;
    }
    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
//        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // 通过getSystemService方法实例化一个视图的填充器
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.detail_list_one, null);
        }
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(getGroup(groupPosition).toString());
//
        ImageView image=(ImageView) view.findViewById(R.id.image);
        //判断实例可以展开，如果可以则改变右侧的图标
        if(isExpanded)
            image.setBackgroundResource(R.mipmap.topup);
        else image.setBackgroundResource(R.mipmap.down);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        switch (groupPosition){
            case 0://企业基本信息
                //填充视图
                LayoutInflater inflater0 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater0.inflate(R.layout.annual_list_one, null);
                TextView company= (TextView) view.findViewById(R.id.company);//企业名称
                TextView regId= (TextView) view.findViewById(R.id.regId);//注册号
                TextView phone= (TextView) view.findViewById(R.id.phone);//企业联系电话
                TextView zipCode= (TextView) view.findViewById(R.id.zipCode);//邮政编码
                TextView address= (TextView) view.findViewById(R.id.address);//企业通信地址
                TextView email= (TextView) view.findViewById(R.id.email);//电子邮箱
                TextView gqzr= (TextView) view.findViewById(R.id.gqzr);//有限责任公司本年度是否发生股权转让
                TextView state= (TextView) view.findViewById(R.id.state);//营业状况
                TextView online= (TextView) view.findViewById(R.id.online);//是否有网站或网店
                TextView tzxx= (TextView) view.findViewById(R.id.tzxx);//企业是否有投资信息或购买其它公司股权
                TextView number= (TextView) view.findViewById(R.id.number);//从业人数
                break;
            case 1://网站或网店信息
                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater1.inflate(R.layout.listview_item, null);
                lv1= (MyListView) view.findViewById(R.id.lv);
                lv1.setAdapter(new AdetailtwoAdapter(context));
                break;
            case 2://股东信息
                //填充视图
                LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater2.inflate(R.layout.listview_item, null);
                lv2= (MyListView) view.findViewById(R.id.lv);
                findStockMsg("12",-1,-1);
                break;
            case 3://对外投资
                //填充视图
                LayoutInflater inflater3 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater3.inflate(R.layout.listview_item, null);
                lv3= (MyListView) view.findViewById(R.id.lv);
                findAbroadInvestment("12",-1,-1);
//                lv3.setAdapter(new FollowAdapter(context));
                break;
            case 4://企业资产状况信息
                LayoutInflater inflater4 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater4.inflate(R.layout.annual_list_four, null);
                TextView money= (TextView) view.findViewById(R.id.money);//资产总额
                TextView qyAll= (TextView) view.findViewById(R.id.qyAll);//所有者权益合计
                TextView moneyAll= (TextView) view.findViewById(R.id.moneyAll);//营收总收入
                TextView profit= (TextView) view.findViewById(R.id.profit);//利润总额
                TextView moneyMain= (TextView) view.findViewById(R.id.moneyMain);//营业总收入中主营业务收入
                TextView netProfit= (TextView) view.findViewById(R.id.netProfit);//净利润
                TextView paytaxes= (TextView) view.findViewById(R.id.paytaxes);//纳税总额
                TextView liabilities= (TextView) view.findViewById(R.id.liabilities);//负债总额
                break;
            case 5://对外提供保证担保信息
                LayoutInflater inflater5 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater5.inflate(R.layout.listview_item, null);
                lv5= (MyListView) view.findViewById(R.id.lv);
                lv5.setAdapter(new AdetailfiveAdapter(context));
                break;
            case 6://股权变更信息
                LayoutInflater inflater6 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater6.inflate(R.layout.listview_item, null);
                lv6= (MyListView) view.findViewById(R.id.lv);
                lv6.setAdapter(new AdetailsixAdapter(context));
                break;
            case 7://修改记录
                LayoutInflater inflater7 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater7.inflate(R.layout.listview_item, null);
                lv7= (MyListView) view.findViewById(R.id.lv);
                findAnnualPortsMsgChange("12",-1,-1);
//                lv7.setAdapter(new AdetailsevenAdapter(context));
                break;
            default:
                break;
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private GudonginfoAdapter adapter;
    private List<StockMsg> list;
    /**
     * 股东信息
     * @param page
     * @param rows
     */
    private void findStockMsg(String id,final int page,int rows){
        RequestManager.getCommManager().findStockMsg(id, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        list = gson.fromJson(rows.toString(),
                                new TypeToken<List<StockMsg>>() {
                                }.getType());

                        if (list != null && list.size() > 0) {
                            adapter = new GudonginfoAdapter(context, list);
                            lv2.setAdapter(adapter);
                        }

                    } else {
                        MyToastUtils.showShortToast(context, "暂无数据");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(context, error);

            }
        });
    }
    private List<CompanyEntity> listInvestment;
    private FollowAdapter adapterInvestment;
    /**
     * 对外投资
     * @param companyId
     */
    private void findAbroadInvestment(String companyId, final int page,int rows){
        RequestManager.getCommManager().findAbroadInvestment(companyId, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        listInvestment = gson.fromJson(rows.toString(),
                                new TypeToken<List<CompanyEntity>>() {
                                }.getType());
                        if (listInvestment != null && listInvestment.size() > 0) {
                            adapterInvestment = new FollowAdapter(context, listInvestment);
                                lv3.setAdapter(adapterInvestment);
                        }
                    } else {
                        MyToastUtils.showShortToast(context,"暂无数据");
                    }
                }

            catch(JSONException e){
                e.printStackTrace();
            }
        }

        @Override
            public void onError(String error) {
            }
        });
    }

    private List<CompanyEntity> listChange;
    private AdetailsevenAdapter adapterChange;
    /**
     * 修改记录
     * @param id
     * @param page
     * @param rows
     */
    private void findAnnualPortsMsgChange(String id,int page,int rows){
        RequestManager.getCommManager().findAnnualPortsMsgTest(id, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        listChange = gson.fromJson(rows.toString(),
                                new TypeToken<List<CompanyEntity>>() {
                                }.getType());
                        if (listChange != null && listChange.size() > 0) {
                            adapterChange = new AdetailsevenAdapter(context, listChange);
                            lv7.setAdapter(adapterChange);
                        }
                    } else {
                        MyToastUtils.showShortToast(context,"暂无数据");
                    }
                } catch (JSONException e) {
                e.printStackTrace();
            }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
