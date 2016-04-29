package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.AdetailsevenAdapter;
import com.beyonditsm.echinfo.adapter.AnnualExAdapter;
import com.beyonditsm.echinfo.adapter.EnterPAdapter;
import com.beyonditsm.echinfo.adapter.ExAdapter;
import com.beyonditsm.echinfo.adapter.GudonginfoAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.entity.ResultData;
import com.beyonditsm.echinfo.entity.StockMsg;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.GsonUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.leaf.library.widget.MyExpandableListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 年报详情列表
 * Created by gxy on 2016/4/19.
 */
public class AnnualDetaillistAct extends BaseActivity{

    @ViewInject(R.id.list)
    private ExpandableListView listView;
    private List<String> list;
    private Vector<Object> childlist;
    private AnnualExAdapter adapter;
    private CompanyEntity entity;
    private List<StockMsg> childList3;
    private List<CompanyEntity> childList4;
    private List<CompanyEntity> childList8;
    private List<Object> childList2;
    private List<Object> childList5;
    private List<Object> childList6;
    private List<Object> childList7;
    private String id=null;
    private String title=null;

    @Override
    public void setLayout() {
        setContentView(R.layout.annual_detail_list);
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        setTopTitle("2015年年报详情");
        id=getIntent().getStringExtra("id");
        title=getIntent().getStringExtra("title");
        setTopTitle(title);
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
        list=new ArrayList<>();
        childlist=new Vector<>();
        list.add("企业基本信息");
        list.add("网站或网店信息");
        list.add("股东信息");
        list.add("对外投资");
        list.add("企业资产状况信息");
        list.add("对外提供保证担保信息");
        list.add("股权变更信息");
        list.add("修改记录");
        entity=new CompanyEntity();
        childList2=new ArrayList<>();
        childList3=new ArrayList<>();
        childList4=new ArrayList<>();
        childList5=new ArrayList<>();
        childList6=new ArrayList<>();
        childList7=new ArrayList<>();
        childList8=new ArrayList<>();
//        findEnterpriseInfoMsgById("12");
//        findStockMsg("12",-1,-1);
//        findAbroadInvestment("12",-1,-1);
//        findAnnualPortsMsgChange("12",-1,-1);
        childlist.add(entity);
        childlist.add(childList2);
        childlist.add(childList3);
        childlist.add(childList4);
        childlist.add(childList5);
        childlist.add(childList6);
        childlist.add(childList7);
        childlist.add(childList8);


        adapter=new AnnualExAdapter(AnnualDetaillistAct.this,list,childlist,id);
        listView.setAdapter(adapter);
        listView.setChildDivider(new ColorDrawable(Color.WHITE));
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                switch (groupPosition){
                    case 0:

                        break;
                    case 1:
                        listView.collapseGroup(1);
                        break;
                    case 2:
                        if(adapter.getList()==null){
                            listView.collapseGroup(2);
                        }
                        break;
                    case 3:
                        if(adapter.getListInvestment()==null){
                            listView.collapseGroup(3);
                        }
                        break;
                    case 4:
                        listView.collapseGroup(4);
                        break;
                    case 5:
                        listView.collapseGroup(5);
                        break;
                    case 6:
                        listView.collapseGroup(6);
                        break;
                    case 7:
                        if(adapter.getListChange()==null){
                            listView.collapseGroup(7);
                        }
                        break;
                }
            }
        });
        //item点击事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long i) {

                return true;
            }
        });
        listView.expandGroup(0);

    }
    /**
     * 查询企业信息
     * @param id
     */
    private void findEnterpriseInfoMsgById(String id){
        RequestManager.getCommManager().findEnterpriseInfoMsgById(id, new CallBack() {
            @Override
            public void onSucess(String result) {
                ResultData<CompanyEntity> rd = (ResultData<CompanyEntity>) GsonUtils.json(result, CompanyEntity.class);
                entity = rd.getData();
                adapter.setChildData(childlist);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
    }


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
                        childList3 = gson.fromJson(rows.toString(),
                                new TypeToken<List<StockMsg>>() {
                                }.getType());

                        if (childList3 != null && childList3.size() > 0) {
                            adapter.setChildData(childlist);
                            adapter.notifyDataSetChanged();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(AnnualDetaillistAct.this, error);

            }
        });
    }

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
                        childList4 = gson.fromJson(rows.toString(),
                                new TypeToken<List<CompanyEntity>>() {
                                }.getType());
                        if (childList4 != null && childList4.size() > 0) {
                            adapter.setChildData(childlist);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
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
                        childList8 = gson.fromJson(rows.toString(),
                                new TypeToken<List<CompanyEntity>>() {
                                }.getType());
                        if (childList8 != null && childList8.size() > 0) {
                            adapter.setChildData(childlist);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
//                        MyToastUtils.showShortToast(AnnualDetaillistAct.this,"暂无数据");
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
