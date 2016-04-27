package com.beyonditsm.echinfo.http.impl;

import android.text.TextUtils;

import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.http.CallBack;
import com.beyonditsm.echinfo.http.IEchinfoUrl;
import com.beyonditsm.echinfo.http.engine.RequestManager;
import com.beyonditsm.echinfo.util.MyLogUtils;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.tandong.sa.json.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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

    /**
     * 查询企业详细信息
     * @param id
     * @param callBack
     */
    public void findEnterpriseInfoMsgById(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        doPost(IEchinfoUrl.ENTERPRISEINFO_URL,map,callBack);
    }
    /**
     * 查询企业工商信息
     * @param companyId
     * @param callBack
     */
    public void findVietinbanhInfoByCompanyId(String companyId,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        doPost(IEchinfoUrl.BUSINESSINFO_URL,map,callBack);
    }

    /**
     * 诉讼列表
     * @param page
     * @param rows
     * @param callBack
     */
    public void findLawsuitMsg(String companyId,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.LAWSUITMSG_URL, map, callBack);
    }

    /**
     * 查询诉讼详情
     * @param id
     * @param callBack
     */
    public void findLawsuitMsgById(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        doPost(IEchinfoUrl.LAWSUITDETAIL_URL,map,callBack);
    }
    /**
     * 股东列表
     * @param page
     * @param rows
     * @param callBack
     */
    public void findStockMsg(String companyId, int page,int rows,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("page", page + "");
        map.put("rows", rows + "");
        doPost(IEchinfoUrl.STOCKMSG_URL, map, callBack);
    }
    /**
     * 纠错
     * @param errorParts 错误部分
     * @param errorContent 错误内容
     * @param mobileNo 手机号码
     * @param callBack
     */
    public void comJiucuo(String errorParts,String errorContent,String mobileNo,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("errorParts", errorParts);
        map.put("errorContent", errorContent);
        map.put("mobileNo", mobileNo);
        doPost(IEchinfoUrl.CORRECTIONMANAGE_URL, map, callBack);
    }


    /**
     * 分之机构
     * @param
     * @param
     * @param
     * @param
     */
    public void findSonEnterpriseInterMsg(String companyId,int page,int rows,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("page", page + "");
        map.put("rows", rows + "");
        doPost(IEchinfoUrl.INTERMSG_URL, map, callBack);
    }


    /**
     * 主要成员
     * @param
     * @param
     * @param
     * @param
     */
    public void findpeoplePortsMsg(String companyId,Integer page, Integer rows,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("page", page + "");
        map.put("rows", rows + "");
        doPost(IEchinfoUrl.FINDMAINMEMBERMSG_URL, map, callBack);
    }

    /**
     * 关注列表
     * @param
     * @param
     * @param
     * @param
     */
    public void findgzPortsMsg(int page,int rows,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("rows", rows + "");
        doPost(IEchinfoUrl.MYATTENTTIONMSG_URL, map, callBack);
    }




    /**
     * 根据企业的id查询出该企业下所有的年报信息
     * @param companyId
     * @param page
     * @param rows
     * @param callBack
     */
    public void findAnnualPortsMsg(String companyId,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.ANNUALLIST_URL,map,callBack);
    }

    /**
     * 根据年报id查询年报详细信息
     * @param id
     * @param callBack
     */
    public void findEnterpriseInfoOfAnnual(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",id);
        doPost(IEchinfoUrl.ANNUALDETAIL_URL,map,callBack);
    }
    /**
     * 根据企业的id查询出该企业下所有的企业资讯
     * @param companyId
     * @param page
     * @param rows
     * @param callBack
     */
    public void findEnterpriseNewsList(String companyId,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.ENTERPRISENEWSLIST_URL,map,callBack);
    }

    /**
     * 查询资讯详情
     * @param id
     * @param callBack
     */
    public void findEnterpriseNewsById(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        doPost(IEchinfoUrl.ENTERPRISEBYID_URL,map,callBack);
    }

    /**
     * 意见反馈
     * @param content
     * @param phonenumber
     * @param callBack
     */
    public void addIdeaTicking(String content,String phonenumber,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("content",content);
        map.put("mobileNo",phonenumber);
        doPost(IEchinfoUrl.ADDMSG_URL,map,callBack);
    }

    /**
     * 关注企业
     * @param companyId
     * @param callBack
     */
    public void addMyAttention(String companyId,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        doPost(IEchinfoUrl.ATTENTION_URL,map,callBack);
    }

    /**
     * 取消关注
     * @param id
     * @param callBack
     */
    public void removeMyAttention(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",id);
        doPost(IEchinfoUrl.REMOVEATTENTION_URL,map,callBack);
    }

    /**
     * 对外投资
     * @param companyId
     * @param callBack
     */
    public void findAbroadInvestment(String companyId,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.INVESTMENT_URL,map,callBack);
    }

    /**
     * 变更记录
     * @param companyId
     * @param page
     * @param rows
     * @param callBack
     */
    public void findAnnualPortsMsgTest(String companyId,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.URL,map,callBack);
    }

    /**
     * 查询行业
     * @param fatherSectorId
     * @param callBack
     */
    public void findIndustryByFatherId(String fatherSectorId,CallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("fatherSectorId", fatherSectorId);
        doPost(IEchinfoUrl.INDUSTRY_URL, map, callBack);
    }
    /** 查询企业
     * @param companyName
     * @param page
     * @param rows
     */
    public void searchCompany(String companyName,String address,int page,int rows,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyName",companyName);
        if(!TextUtils.isEmpty(address)&&!"全国".equals(address)) {
            map.put("address", address);
        }
        map.put("page",page+"");
        map.put("rows",rows+"");
        doPost(IEchinfoUrl.SEARCH_CPMPANY_URL,map,callBack);
    }

    /**
     * 查询企业状态
     * @param companyId
     * @param callBack
     */
    public void selectStatus(String companyId,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("companyId",companyId);
        doPost(IEchinfoUrl.ENTEPRISESTS_URL,map,callBack);
    }

    /**
     * 根据职业的id查询出职业的信息 如果id为null 则查询出所有的根职业 如果id不为空则查询出该id下的子职业
     * @param id
     * @param callBack
     */
    public void findOccupationList(String id,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        if(!TextUtils.isEmpty(id)) {
            map.put("id", id);
        }
        doPost(IEchinfoUrl.OCCUPACTION_URL,map,callBack);
    }

    /**
     * 根据股东名称精确查询行业信息
     * @param name
     * @param callBack
     */
    public void findStockMsgByCompanyName(String name,String address,CallBack callBack ){
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        if(!TextUtils.isEmpty(address)&&!"全国".equals(address)) {
            map.put("address", address);
        }
        doPost(IEchinfoUrl.STOCKMSGBYNAME_URL,map,callBack);
    }

    /**
     * 失信列表
     * @param iname
     * @param callBack
     */
    public void findCourtitemList(String iname ,String areaname ,CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("iname",iname);
        if(!TextUtils.isEmpty(areaname)&&!"全国".equals(areaname)) {
            map.put("areaname", areaname);
        }
        doPost(IEchinfoUrl.COURTITEMMSG_URL,map,callBack);
    }

    /**
     * 热门企业列表
     * @param callBack
     */
    public void hotEnterprise(CallBack callBack){
//        Map<String,String> map=new HashMap<>();
        doGet(IEchinfoUrl.HOTENTERPRISE_URL,callBack);

    }



}
