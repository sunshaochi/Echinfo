package com.beyonditsm.echinfo.http;

/**
 * Created by wangbin on 16/4/12.
 */
public interface CallBack {

    /**
     * 成功有数据
     */
    public void onSucess(String result);

    /**
     * 失败
     */
    public void onError(String error);
}
