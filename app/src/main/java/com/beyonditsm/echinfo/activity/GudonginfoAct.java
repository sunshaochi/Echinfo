package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.GudonginfoAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.StockMsg;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bitch-1 on 2016/4/6.
 * 股东信息界面
 */
public class GudonginfoAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    private List<StockMsg> list;
    private int page=1;
    private int rows=10;
    private GudonginfoAdapter adapter;
    String companyId=null;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_gudonginfo);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("股东信息");
        companyId=getIntent().getStringExtra(CompanyxqAct.COMPANYID);
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(true);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
        plv.getRefreshableView().setDivider(null);

        findStockMsg(companyId,page,rows);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
                page = 1;
                findStockMsg(companyId,page, rows);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                findStockMsg(companyId,page, rows);
            }
        });
    }


    private List<StockMsg> datas=new ArrayList<>();

    /**
     * 股东信息
     * @param page
     * @param rows
     */
    private void findStockMsg(String companyId,final int page,int rows){
        RequestManager.getCommManager().findStockMsg(companyId,page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        list = gson.fromJson(rows.toString(),
                                new TypeToken<List<StockMsg>>() {
                                }.getType());
                        if (list.size() > 0) {
                            if (page == 1) {
                                datas.clear();
                            }
                            datas.addAll(list);
                            if (datas != null && datas.size() > 0) {
                                if (adapter == null) {
                                    adapter = new GudonginfoAdapter(GudonginfoAct.this, datas);
                                    plv.getRefreshableView().setAdapter(adapter);
                                } else {
                                    adapter.notify(datas);
                                }
                            } else {

                            }
                        } else {
                            plv.setHasMoreData(false);
                        }

                    } else {
                        if(page==1) {
                            MyToastUtils.showShortToast(GudonginfoAct.this, "暂无数据");
                        }else {
                            plv.setHasMoreData(false);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                MyToastUtils.showShortToast(GudonginfoAct.this, error);

            }
        });
    }
}

