package com.beyonditsm.echinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beyonditsm.echinfo.AppManager;
import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.OccuAdapter;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.entity.OccuaptionEntity;
import com.beyonditsm.echinfo.event.OccuEvent;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.tandong.sa.eventbus.EventBus;
import com.tandong.sa.json.Gson;
import com.tandong.sa.json.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 职业选择
 * Created by wangbin on 16/4/6.
 */
public class OccupationAct extends BaseActivity {
    @ViewInject(R.id.lv)
    private ListView lv;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_occupation);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(OccupationAct.this);
        setTopTitle("职业");
        findOccupationList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EventBus.getDefault().post(new OccuEvent("客户服务助理"));
                Intent intent=new Intent(OccupationAct.this,OccupationChildAct.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private List<OccuaptionEntity> list;

    //查询职业列表
    private void findOccupationList(){
        RequestManager.getCommManager().findOccupationList(null, new CallBack() {
            @Override
            public void onSucess(String result) {
                Gson gson=new Gson();
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    JSONObject data=json.getJSONObject("data");
                    JSONArray rows = data.getJSONArray("rows");
                    list = gson.fromJson(rows.toString(), new TypeToken<List<OccuaptionEntity>>() {}.getType());
                    lv.setAdapter(new OccuAdapter(OccupationAct.this,list,0));

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
