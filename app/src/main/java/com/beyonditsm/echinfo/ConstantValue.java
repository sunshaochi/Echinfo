package com.beyonditsm.echinfo;

import com.beyonditsm.echinfo.entity.UserEntity;

/**
 * Created by wangbin on 16/4/5.
 */
public interface ConstantValue {

    public final boolean IS_SHOW_DEBUG=true;
    public final String ENCODING="utf-8";

    /**
     * 数据库版本
     */
    public final static int VERSION = 1;
    public final static Class<?>[] MODELS = {UserEntity.class};
    public final static String DB_NAME = "user.db";
}
