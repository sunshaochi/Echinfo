package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.db.UserDao;
import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.EchinfoUtils;
import com.beyonditsm.echinfo.util.MyToastUtils;

/**
 * 修改密码
 * Created by wangbin on 16/4/6.
 */
public class UpdatePwdAct extends BaseActivity {
    private EditText etPwd;
    private EditText etNewP;
    private EditText etSP;

    private String pwd, nPwd, sureP,ypwd;

    private void assignViews() {
        etPwd = (EditText) findViewById(R.id.etPwd);
        etNewP = (EditText) findViewById(R.id.etNewP);
        etSP = (EditText) findViewById(R.id.etSP);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.act_update_pwd);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        UserEntity user= UserDao.getUser();
        ypwd=user.getPassword();
        setTopTitle("修改密码");
        assignViews();
        setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd= etPwd.getText().toString().trim();
                nPwd=etNewP.getText().toString().trim();
                if (isValidate()) {
                    updatePwd(pwd,nPwd);
                }
            }
        });
    }

    private boolean isValidate() {
        pwd = etPwd.getText().toString().trim();
        nPwd = etNewP.getText().toString().trim();
        sureP = etSP.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入原密码");
            etPwd.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(nPwd)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请输入新密码");
            etNewP.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(sureP)) {
            MyToastUtils.showShortToast(getApplicationContext(), "请确认新密码");
            etSP.requestFocus();
            return false;
        }
        if (!nPwd.equals(sureP)) {
            MyToastUtils.showShortToast(getApplicationContext(), "两次输入的密码不一致");
            etSP.requestFocus();
            return false;
        }

        if(nPwd.length()<6){
            MyToastUtils.showShortToast(getApplicationContext(),"请输入至少6位密码");
            etNewP.requestFocus();
            etNewP.setSelection(nPwd.length());
            return false;
        }
        if(nPwd.length()>20){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入不超过20位密码");
            etNewP.requestFocus();
            etNewP.setSelection(nPwd.length());
            return false;
        }
        if(!EchinfoUtils.checkPwd(pwd)){
            MyToastUtils.showShortToast(getApplicationContext(), "请输入6-20位字母或数字的原始密码");
            etPwd.requestFocus();
            return false;
        }
//        if(!(ypwd.equals(pwd))){
//            MyToastUtils.showShortToast(getApplicationContext(), "原密码输入错误");
//            return false;
//        }
        return true;


    }

    /**
     * 修改密码
     * @param password
     * @param newPassword
     */
    private void updatePwd(String password,String newPassword){
        RequestManager.getCommManager().updatePwd(password, newPassword, new CallBack() {
            @Override
            public void onSucess(String result) {
                MyToastUtils.showShortToast(getApplicationContext(), "修改密码成功");
                finish();
            }

            @Override
            public void onError(String error) {
                MyToastUtils.showShortToast(getApplicationContext(), error);
            }
        });
    }
}
