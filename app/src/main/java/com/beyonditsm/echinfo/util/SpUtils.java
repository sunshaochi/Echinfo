package com.beyonditsm.echinfo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangbin on 15/11/19.
 */
public class SpUtils {

    private static final String SP_NAME="sp_name";
    private static final String ECHINFO_SP="echinfo_sp";
    private final static String ISFIRST = "echinfo_first";

    private static final String COOKIE="cookie";


    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }


    public static SharedPreferences getFinancialSp(Context context){
        return context.getSharedPreferences(ECHINFO_SP, Context.MODE_PRIVATE);
    }

    /**
     * 是否第一次进入app
     *
     * @param context
     * @param isFirst
     */
    public static void setIsFirst(Context context, boolean isFirst) {
        getFinancialSp(context).edit().putBoolean(ISFIRST, isFirst).commit();
    }

    public static boolean getIsFirst(Context context) {
        return getFinancialSp(context).getBoolean(ISFIRST, true);
    }



    /**
     * 保存cookie
     * @param context
     * @param cookie
     */
    public static void setCooike(Context context,String cookie){
        getSp(context).edit().putString(COOKIE,cookie).commit();
    }

    /**
     * 获取cookie
     * @param context
     * @return
     */
    public static String getCookie(Context context){
        return getSp(context).getString(COOKIE, "");
    }




    /**
     * 清除
     * @param context
     */
    public static void clearSp(Context context){
        getSp(context).edit().clear().commit();
    }




}
