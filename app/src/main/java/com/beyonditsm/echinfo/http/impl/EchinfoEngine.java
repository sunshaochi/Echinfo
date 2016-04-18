package com.beyonditsm.echinfo.http.impl;

import android.text.TextUtils;

import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.tandong.sa.json.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 16/4/12.
 */
public class EchinfoEngine extends RequestManager {

    /**
     * 发送验证码
     *
     * @param phoneNumber
     * @param callBack
     */
    public void toSendSms(String phoneNumber, String isRegister, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("isRegister", isRegister);
        doPost(IEchinfoUrl.SEND_SMS_URL, map, callBack);
    }

    /**
     * 注册
     *
     * @param captcha
     * @param callBack
     * @param password
     */
    public void toRegister(String phoneNumber, String captcha, String password, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("username", phoneNumber);
        map.put("phoneNumber", phoneNumber);
        map.put("captcha", captcha);
//        map.put("password", password);
        doPost(IEchinfoUrl.REGISTE_URL, map, callBack);
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param callBack
     */
    public void toLogin(String username, String password, String yzm, String key, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        if (!TextUtils.isEmpty(yzm)) {
            map.put("captcha", yzm);
        }
        if (!TextUtils.isEmpty(key)) {
            map.put("mobilePhoneKey", key);
        }
        doPost(IEchinfoUrl.LOGIN_URL, map, callBack);
    }

    /**
     * 登出
     *
     * @param callBack
     */
    public void unLogin(String userName, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(userName)) {
            map.put("userName", userName);
        }
        doPost(IEchinfoUrl.UNLOGIN_URL, map, callBack);
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @param callBack
     */
    public void updatePwd(String password, String newPassword, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("password", password);
        map.put("newPassword", newPassword);
        doPost(IEchinfoUrl.MODIFY_PWD_URL, map, callBack);
    }

    /**
     * 忘记密码
     *
     * @param phoneNumber
     * @param captcha
     * @param newPassword
     * @param callBack
     */
    public void forgetPwd(String phoneNumber, String captcha, String newPassword, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("captcha", captcha);
        map.put("newPassword", newPassword);
        doPost(IEchinfoUrl.FORGET_PWD_URL, map, callBack);
    }

    /**
     * 修改用户信息
     *
     * @param userEntitty
     * @param callBack
     */
    public void modifyUserInfo(UserEntity userEntitty, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        Gson gson = new Gson();
        String json = gson.toJson(userEntitty);
        try {
            JSONObject obj = new JSONObject(json);
            if ((obj.toString()).indexOf("createTime") != -1) {
                obj.remove("createTime");
            }
            if ((obj.toString()).indexOf("createPersonId") != -1) {
                obj.remove("createPersonId");
            }
            if ((obj.toString()).indexOf("modifyTime") != -1) {
                obj.remove("modifyTime");
            }
            if ((obj.toString()).indexOf("modifyPersonId") != -1) {
                obj.remove("modifyPersonId");
            }
            Iterator<String> it = obj.keys();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, String.valueOf(obj.get(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        doPost(IEchinfoUrl.MODIFY_USER_URL, map, callBack);
    }


    /**
     * 更新用户头像
     *
     * @param fileMaps
     * @param callBack
     */
    public void updateUserIcon(final Map<String, FileBody> fileMaps, CallBack callBack) {
        loadImage(IEchinfoUrl.MODIFY_USERHEAD_URL, fileMaps, callBack);
    }

    /**
     * 版本更新
     *
     * @param currentVersion
     * @param platform
     * @param callBack
     */
    public void findVersion(int currentVersion, String platform, CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("currentVersion", currentVersion + "");
        map.put("platform", platform);
        doPost(IEchinfoUrl.CHECK_UPDATE_URL, map, callBack);
    }

    //获取机构列表
    public void getTypes(CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "ep_institution_001");
        doPost(IEchinfoUrl.GETTYPEBYKEY_URL, map, callBack);
    }

}
