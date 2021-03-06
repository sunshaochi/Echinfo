package com.beyonditsm.echinfo.http;

/**
 * Created by wangbin on 16/4/12.
 */
public interface IEchinfoUrl {
    /*基础URL*/
//    public String BASE_URL = "http://139.196.50.38:8088/yiqicha/";

    public String BASE_URL="http://139.196.50.242:8082/yiqicha/";
//    public String BASE_URL="http://172.16.6.184:8080/yiqicha/";

    /*注册地址*/
    String REGISTE_URL = BASE_URL + "manager/unLogin/register.do";
    /*发送验证码*/
    String SEND_SMS_URL = BASE_URL + "manager/unLogin/sendSmsCaptcha.do";
    /*登录*/
    String LOGIN_URL = BASE_URL + "manager/unLogin/login.do";
    /*登出*/
    String UNLOGIN_URL = BASE_URL + "manager/unLogin/logout.do";
    /*校验推荐码*/
    String VERIFY_CODE_URL = BASE_URL + "manager/unLogin/verifyReferralCode.do";
    /*修改密码*/
    String MODIFY_PWD_URL = BASE_URL + "manager/login/modifyUserPassword.do";
    /*忘记密码*/
    String FORGET_PWD_URL = BASE_URL + "manager/unLogin/forgetPassword.do";
    /*修改用户信息*/
    String MODIFY_USER_URL = BASE_URL + "manager/login/modifyUserInfo.do";
    /*修改用户头像*/
    String MODIFY_USERHEAD_URL = BASE_URL + "manager/login/modifyUserIcon.do";
    /*检查更新*/
    String CHECK_UPDATE_URL = BASE_URL + "manager/unLogin/checkUpdrage.do";
    /*根据key获取类型*/
    String GETTYPEBYKEY_URL = BASE_URL + "manager/unLogin/findTypesByKey.do";

    /*企业列表信息*/
//    String ENTERPRISELIST_URL = BASE_URL + "companyInfo/login/findEnterpriseInfo.do";
    /*企业基本信息*/
    String ENTERPRISEINFO_URL = BASE_URL + "companyInfo/unLogin/findEnterpriseInfoMsgById.do";
    /*已经登录*/
    String ENTERINFO_LOGIN_URL=BASE_URL+"companyInfo/findEnterpriseInfoMsgById.do";
    /*工商信息*/
    String BUSINESSINFO_URL = BASE_URL + "companyInfo/unLogin/findVietinbanhInfoByCompanyId.do";
    /*纠错提交*/
    String CORRECTIONMANAGE_URL = BASE_URL + "companyInfo/unLogin/addCorrectionManage.do";
    /*诉讼列表*/
    String LAWSUITMSG_URL = BASE_URL + "companyInfo/unLogin/findLawsuitMsg.do";
    /*诉讼详情*/
    String LAWSUITDETAIL_URL = BASE_URL + "companyInfo/unLogin/findLawsuitMsgById.do";
    /*股东信息*/
    String STOCKMSG_URL = BASE_URL + "companyInfo/unLogin/findStockMsg.do";
    /*分之机构*/
    String INTERMSG_URL = BASE_URL + "sonEnterpriseInterMsg/unLogin/findSonEnterpriseInterMsg.do";
    /*主要成员*/
    String FINDMAINMEMBERMSG_URL = BASE_URL + "mainMemberMsg/unLogin/findMainMemberMsg.do";
    /*关注企业信息列表*/
    String MYATTENTTIONMSG_URL = BASE_URL + "myAttenttionMsg/findMyAttenttionMsg.do";

    /*查询企业*/
    String SEARCH_CPMPANY_URL = BASE_URL + "companyInfo/unLogin/findEnterpriseInfo.do";


    /*年报列表*/
    String ANNUALLIST_URL = BASE_URL + "annualPortsMsg/unLogin/findAnnualPortsMsg.do";
    /*年报详细*/
    String ANNUALDETAIL_URL = BASE_URL + "annualPortsMsg/unLogin/findEnterpriseInfoOfAnnual.do";
    /*企业资讯*/
    String ENTERPRISENEWSLIST_URL = BASE_URL + "enterpriseNews/unLogin/findEnterpriseNewsList.do";
    /*企业资讯详情*/
    String ENTERPRISEBYID_URL = BASE_URL + "enterpriseNews/unLogin/findEnterpriseNewsById.do";
    /*意见反馈*/
    String ADDMSG_URL = BASE_URL + "ideaTicking/addIdeaTicking.do";

    /*关注企业*/
    String ATTENTION_URL = BASE_URL + "companyInfo/addMyAttention.do";
    /*取消关注*/
    String REMOVEATTENTION_URL = BASE_URL + "companyInfo/removeMyAttention.do";
    /*对外投资企业列表*/
    String INVESTMENT_URL = BASE_URL + "companyInfo/unLogin/findAbroadInvestment.do";
    /*变更记录*/
    String URL=BASE_URL+"editRecordMsg/unLogin/findEditRecord.do";

    /*行业*/
    String INDUSTRY_URL=BASE_URL+"industryMsg/unLogin/findIndustryByFatherId.do";

    /*企业查询状态，企业详情gridview是否可点击*/
    String ENTEPRISESTS_URL=BASE_URL+"companyInfo/unLogin/selectStatus.do";
    /*职业类别*/
    String OCCUPACTION_URL=BASE_URL+"occupationMsg/unLogin/findOccupationList.do";
    /*常见问题*/
    String PROBLEM_URL="http://139.196.50.38:8081/questions?hideHeader=1";
    /*行业分析*/
    String ANALY_URL="http://139.196.50.38:8081/indana?hideHeader=1";
    /*根据股东名称精确查询行业信息*/
    String STOCKMSGBYNAME_URL=BASE_URL+"companyInfo/unLogin/findStockMsgByName.do";
    /*失信列表*/
    String COURTITEMMSG_URL=BASE_URL+"courtitemMsg/unLogin/findOccupationList.do";
    /*热门企业列表*/
    String HOTENTERPRISE_URL=BASE_URL+"companyInfo/unLogin/hotEnterprise.do";
    /*企业图谱*/
    String TUPU_URL="http://139.196.50.38:8081/businessMap?hideHeader=1";

    /*统计企业总数*/
    String ENTER_COUNT_URL=BASE_URL+"companyInfo/unLogin/countEnterpriseInfo.do";

}
