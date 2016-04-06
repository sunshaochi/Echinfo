package com.beyonditsm.echinfo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.adapter.MsgAdp;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.beyonditsm.echinfo.view.MyAlertDialog;
import com.beyonditsm.echinfo.view.swipemenulistview.SwipeMenu;
import com.beyonditsm.echinfo.view.swipemenulistview.SwipeMenuCreator;
import com.beyonditsm.echinfo.view.swipemenulistview.SwipeMenuItem;
import com.beyonditsm.echinfo.view.swipemenulistview.SwipeMenuListView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 系统消息
 * Created by wangbin on 16/4/5.
 */
public class MsgAct extends BaseActivity {
    private MsgAdp adapter;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_msg);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("系统消息");
        setRight("一键清空", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new MyAlertDialog(MsgAct.this).builder().setTitle("提示").setMsg("您确定清空消息吗？",true).setPositiveButton("确定",
                       new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                           }
                       },true).setNegativeButton("取消",null).show();
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 0:
                        SwipeMenuItem deleteItem = new SwipeMenuItem(
                                getApplicationContext());
                        deleteItem.setBackground(R.color.delete_red);
                        deleteItem.setWidth(EchinfoUtils.dip2px(MsgAct.this, 80));
                        deleteItem.setTitle("删除");
                        deleteItem.setTitleSize(14);
                        deleteItem.setTitleColor(Color.parseColor("#ffffff"));
                        menu.addMenuItem(deleteItem);
                        break;
                }

            }
        };
        plv.setMenuCreator(creator);

        plv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        MyToastUtils.showShortToast(MsgAct.this, position + "");
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        plv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        adapter = new MsgAdp(MsgAct.this);
//        adapter.setMode(SwipeItemMangerImpl.Mode.Single);
        plv.setAdapter(adapter);
    }

    @ViewInject(R.id.plv)
    private SwipeMenuListView plv;

}
