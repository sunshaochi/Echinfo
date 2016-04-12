package com.beyonditsm.echinfo.http.impl;

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
     * 登陆
     * @param params
     * @param callback
     */
    public void toLogin(Map<String,String> params,CallBack callback){}
}
