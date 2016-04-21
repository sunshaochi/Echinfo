package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.PeopleAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.PeopleEntity;
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
 * Created by bitch-1 on 2016/4/7.
 * 主要成员界面
 */
public class PeopleAct extends BaseActivity{
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private int page=1;
    private int rows=10;

    private List<PeopleEntity> list;
    private PeopleAdapter adapter;
    String id=null;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_people);

    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("主要成员");
        id=getIntent().getStringExtra("id");
        setRight("纠错", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ErrorAct.class);
            }
        });

        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(false);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
        plv.getRefreshableView().setDivider(null);

        findpeoplePortsMsg(id, page, rows);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
                page = 1;
                findpeoplePortsMsg(id, page, rows);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                findpeoplePortsMsg(id, page, rows);
            }
        });
      //  pp_lv.setAdapter(new PeopleAdapter(PeopleAct.this,list));

    }

    private List<PeopleEntity> datas=new ArrayList<>();

    private void findpeoplePortsMsg(String companyId, final int page,int rows){

        RequestManager.getCommManager().findpeoplePortsMsg(companyId, page, rows, new CallBack() {
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
                                new TypeToken<List<PeopleEntity>>() {
                                }.getType());
                        if (list.size() > 0) {
                            if (page == 1) {
                                datas.clear();
                            }
                            datas.addAll(list);
                            if (datas != null && datas.size() > 0) {
                                if (adapter == null) {
                                    adapter = new PeopleAdapter(PeopleAct.this, datas);
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
                            MyToastUtils.showShortToast(PeopleAct.this, "暂无数据");
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
                MyToastUtils.showShortToast(PeopleAct.this, error);
            }
        });
    }

}
