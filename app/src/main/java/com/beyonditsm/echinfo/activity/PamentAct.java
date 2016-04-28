package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.PamentAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.FenziEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.LoadingView;
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
 * Created by bitch-1 on 2016/4/5.
 * 分之机构界面
 */
public class PamentAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;

    private int page=1;
    private int rows=10;
    private String companyId=null;
    private List<FenziEntity>list;
    private PamentAdapter adapter;
    @ViewInject(R.id.loadingView)
    private LoadingView loadView;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_my_follow);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("分支机构");
        loadView.setNoContentTxt("暂无结果");
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

        findSonEnterpriseInterMsg(companyId, page, rows);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
                page = 1;
                loadView.loading();
                findSonEnterpriseInterMsg(companyId, page, rows);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                loadView.loading();
                findSonEnterpriseInterMsg(companyId, page, rows);
            }
        });
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PamentAct.this, CompanyxqAct.class);
                intent.putExtra(CompanyxqAct.ID, datas.get(position).getCompanyId());
                startActivity(intent);
            }
        });
        loadView.setOnRetryListener(new LoadingView.OnRetryListener() {
            @Override
            public void OnRetry() {
                findSonEnterpriseInterMsg(companyId, page, rows);
            }
        });
    }

    private List<FenziEntity> datas=new ArrayList<>();
    private void findSonEnterpriseInterMsg(String companyId, final int page,int rows){
        RequestManager.getCommManager().findSonEnterpriseInterMsg(companyId, page, rows, new CallBack() {
            @Override
            public void onSucess(String result) {
                loadView.loadComplete();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    if (rows.length() > 0) {
                        list = gson.fromJson(rows.toString(),
                                new TypeToken<List<FenziEntity>>() {
                                }.getType());
                        if (list.size() > 0) {
                            if (page == 1) {
                                datas.clear();
                            }
                            datas.addAll(list);
                            if (datas != null && datas.size() > 0) {
                                if (adapter == null) {
                                    adapter = new PamentAdapter(PamentAct.this, datas);
                                    plv.getRefreshableView().setAdapter(adapter);
                                } else {
                                    adapter.notify(datas);
                                }
                            } else {
                                if(page==1) {
                                    loadView.noContent();
                                }else {
                                    plv.setHasMoreData(false);
                                }
                            }
                        } else {
                            plv.setHasMoreData(false);
                        }

                    } else {
                        if(page==1) {
                            loadView.noContent();
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
                loadView.loadError();
                plv.onPullUpRefreshComplete();
                plv.onPullDownRefreshComplete();
                MyToastUtils.showShortToast(PamentAct.this, error);
            }
        });
    }

}

