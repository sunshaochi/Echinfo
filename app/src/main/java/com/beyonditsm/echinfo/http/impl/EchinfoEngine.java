package com.beyonditsm.echinfo.http.impl;

import android.text.TextUtils;

import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.http.engine.RequestManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangbin on 16/4/12.
 */
public class EchinfoEngine extends RequestManager{

    /**
     * 发送验证码
     *
     * @param phoneNumber
     * @param callBack
     */
    public void toSendSms(String phoneNumber, String isRegister, CallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("phoneNumber",phoneNumber);
        map.put("isRegister",isRegister);
        doPost(IEchinfoUrl.SEND_SMS_URL,map,callBack);
    }

    /**
     * 注册
     *
     * @param captcha
     * @param callBack
     * @param password
     */
    public void toRegister( String phoneNumber, String captcha,String password, CallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("captcha", captcha);
        map.put("password", password);
        doPost(IEchinfoUrl.REGISTE_URL, map, callBack);
    }
    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param callBack
     */
    public void toLogin(String username, String password,String yzm,String key, CallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if(!TextUtils.isEmpty(yzm)){
            map.put("captcha", yzm);
        }
        if(!TextUtils.isEmpty(key)) {
            map.put("mobilePhoneKey", key);
        }
        doPost(IEchinfoUrl.LOGIN_URL, map, callBack);
    }

    /**
     * 登出
     *
     * @param callBack
     */
    public void unLogin(String userName,CallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("userName", userName);
        doPost(IEchinfoUrl.UNLOGIN_URL, map, callBack);
    }

}
