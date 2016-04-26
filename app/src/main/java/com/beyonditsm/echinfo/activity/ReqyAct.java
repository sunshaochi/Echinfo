package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.ReqyAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.CompanyEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshBase;
import com.beyonditsm.echinfo.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by bitch-1 on 2016/4/7.
 * 热门企业列表
 */
public class ReqyAct extends BaseActivity {

    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private ReqyAdapter adapter;

    private int page=1;
    private int rows=10;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_rmqy);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("热门企业");
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(false);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());

        hotEnterprise();
        plv.getRefreshableView().setDivider(null);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
                hotEnterprise();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
//        plv.getRefreshableView().setAdapter(new ReqyAdapter(this));
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ReqyAct.this,CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID,list.get(position).getId());
                startActivity(intent);
            }
        });
    }
    private List<CompanyEntity>list;


    public void hotEnterprise(){
        RequestManager.getCommManager().hotEnterprise(new CallBack() {
            @Override
            public void onSucess(String result) {
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                Gson gson = new Gson();
                JSONObject json =null;

                try {
                    json = new JSONObject(result);
                     JSONObject data=json.getJSONObject("data");
                     JSONArray rows = data.getJSONArray("rows");
                     if(rows.length()>0){
                         list=gson.fromJson(rows.toString(),new TypeToken<List<CompanyEntity>>(){}.getType());
                         if(list.size()>0){
                             plv.getRefreshableView().setAdapter(new ReqyAdapter(ReqyAct.this,list));

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

            }
        });

    }

    }

