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
    /*登录*/
    String LOGIN_URL=BASE_URL+"manager/unLogin/login.do";
    /*登出*/
    String UNLOGIN_URL=BASE_URL+"manager/unLogin/logout.do";
    /*校验推荐码*/
    String VERIFY_CODE_URL=BASE_URL+"manager/unLogin/verifyReferralCode.do";
    /*修改密码*/
    String MODIFY_PWD_URL=BASE_URL+"manager/login/modifyUserPassword.do";
    /*忘记密码*/
    String FORGET_PWD_URL=BASE_URL+"manager/unLogin/forgetPassword.do";
    /*修改用户信息*/
    String MODIFY_USER_URL=BASE_URL+"manager/login/modifyUserInfo.do";
    /*修改用户头像*/
    String MODIFY_USERHEAD_URL=BASE_URL+"manager/login/modifyUserIcon.do";
    /*检查更新*/
    String CHECK_UPDATE_URL=BASE_URL+"manager/unLogin/checkUpdrage.do";
    /*根据key获取类型*/
    String GETTYPEBYKEY_URL=BASE_URL+"manager/unLogin/findTypesByKey.do";

    /*企业列表信息*/
    String ENTERPRISELIST_URL=BASE_URL+"companyInfo/login/findEnterpriseInfo.do";
    /*企业基本信息*/
    String ENTERPRISEINFO_URL=BASE_URL+"companyInfo/login/findEnterpriseInfoMsgById.do";
    /*工商信息*/
    String BUSINESSINFO_URL=BASE_URL+"companyInfo/login/findVietinbanhInfoByCompanyId.do";
    /*纠错提交*/
    String CORRECTIONMANAGE_URL=BASE_URL+"companyInfo/addCorrectionManage.do";


    /*诉讼列表*/
    String LAWSUITMSG_URL=BASE_URL+"companyInfo/findLawsuitMsg.do";
    /*诉讼详情*/
    String LAWSUITDETAIL_URL=BASE_URL+"companyInfo/findLawsuitMsgById.do";
    /*股东信息*/
    String STOCKMSG_URL=BASE_URL+"companyInfo/findStockMsg.do";
    /*分之机构*/
    String INTERMSG_URL=BASE_URL+"sonEnterpriseInterMsg/findSonEnterpriseInterMsg.do";
    /*主要成员*/
    String FINDMAINMEMBERMSG_URL=BASE_URL+"mainMemberMsg/findMainMemberMsg.do";
    /*关注企业信息列表*/
    String MYATTENTTIONMSG_URL=BASE_URL+"myAttenttionMsg/findMyAttenttionMsg.do";







}
