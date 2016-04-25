package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.BadCAdaper;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.BadCreditEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyLogUtils;
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
 * 失信信息
 * Created by gxy on 2016/4/15.
 */
public class DishonestyInfoAct extends BaseActivity {
    @ViewInject(R.id.plv)
    private PullToRefreshListView plv;
    String companyId=null;
    String iname=null;
    private int page=1;
    private int rows=10;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_my_follow);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("失信榜单");
        companyId=getIntent().getStringExtra(CompanyxqAct.COMPANYID);
        iname=getIntent().getStringExtra("iname");
//        iname="1234";
        findCourtitemList(iname,null);
        plv.setPullRefreshEnabled(true);//下拉刷新
        plv.setScrollLoadEnabled(true);//滑动加载
        plv.setPullLoadEnabled(false);//上拉刷新
        plv.setHasMoreData(true);//是否有更多数据
        plv.getRefreshableView().setVerticalScrollBarEnabled(false);//设置右侧滑动
        plv.getRefreshableView().setSelector(new ColorDrawable(Color.TRANSPARENT));
        plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
        plv.getRefreshableView().setDivider(null);

        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv.setLastUpdatedLabel(EchinfoUtils.getCurrentTime());
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
//        plv.getRefreshableView().setAdapter(new BadCAdaper(this));
        plv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DishonestyInfoAct.this,DisinfodetailAct.class);
                intent.putExtra("entity",badCreditEntityList.get(position));
                startActivity(intent);
//                openActivity(DisinfodetailAct.class);
            }
        });
    }

    private List<BadCreditEntity> badCreditEntityList;
    /**
     * 失信列表
     * @param iname
     */
    private void findCourtitemList(String iname,String address){
        MyLogUtils.degug(iname);
        RequestManager.getCommManager().findCourtitemList(iname,address, new CallBack() {
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
                        badCreditEntityList = gson.fromJson(rows.toString(),
                                new TypeToken<List<BadCreditEntity>>() {
                                }.getType());
                        plv.getRefreshableView().setAdapter(new BadCAdaper(DishonestyInfoAct.this,badCreditEntityList));

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
