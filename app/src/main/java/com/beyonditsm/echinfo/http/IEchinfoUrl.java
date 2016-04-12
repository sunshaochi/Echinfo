package com.beyonditsm.echinfo.http;

/**
 * Created by wangbin on 16/4/12.
 */
public interface IEchinfoUrl {
    /*基础URL*/
    public String BASE_URL="http://139.196.50.38:8088/yiqicha/";

    /*注册地址*/
    String REGISTE_URL=BASE_URL+"manager/unLogin/register.do";
    /*发送验证码*/
    String SEND_SMS_URL=BASE_URL+"manager/unLogin/sendSmsCaptcha.do";


}
