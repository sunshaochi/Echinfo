package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 意见反馈
 * Created by wangbin on 16/4/5.
 */
public class FeedBackAct extends BaseActivity {
    @ViewInject(R.id.etFb)
    private EditText etFb;
    @ViewInject(R.id.etFbPhone)
    private EditText etFbPhone;
    String content;
    String phone;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_feed_back);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTopTitle("意见反馈");
        setRight("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValide()){
                    addMsg(content,phone);
                }
            }
        });
    }

    private boolean isValide(){
        content=etFb.getText().toString().trim();
        phone=etFbPhone.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            MyToastUtils.showShortToast(FeedBackAct.this,"请输入反馈内容");
            etFb.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            MyToastUtils.showShortToast(FeedBackAct.this, "请输入手机号码");
            etFbPhone.requestFocus();
            return false;
        }
        if(phone.length()!=11){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入正确的手机号");
            etFbPhone.requestFocus();
            etFbPhone.setSelection(phone.length());
            return false;
        }
        if(!EchinfoUtils.checkCellPhone(phone)){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入有效的手机号");
            return false;
        }
        return true;
    }

    private void addMsg(String content,String phone){
        RequestManager.getCommManager().addIdeaTicking(content, phone, new CallBack() {
            @Override
            public void onSucess(String result) {
                MyToastUtils.showShortToast(FeedBackAct.this,"提交成功");
                finish();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
