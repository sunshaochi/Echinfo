package com.beyonditsm.echinfo.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.beyonditsm.echinfo.entity.UserEntity;
import com.beyonditsm.echinfo.util.ShUtils;
import com.leaf.library.db.TemplateDAO;

import java.util.List;


/**
 * Created by wangbin on 16/1/25.
 */
public class UserDao extends TemplateDAO<UserEntity, String> {
    public UserDao() {
        super(ShUtils.getDbhelper());
    }

    private static UserDao dao;

    private static UserDao getDao() {
        if (dao == null) {
            dao = new UserDao();
        }
        return dao;
    }

    /**
     * 插入用户
     *
     */
    public static void saveUser(UserEntity userEntity) {
        getDao().insert(userEntity);
    }

    /**
     * 获取用户
     *
     * @return
     */
    public static UserEntity getUser() {
        List<UserEntity> list = getDao().find();
        if (list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 更新用户
     *
     * @param userEntity
     */
    public static void updateUser(UserEntity userEntity) {
        SQLiteDatabase db=getDao().getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",userEntity.getName());
        values.put("email",userEntity.getEmail());
        values.put("qq",userEntity.getQq());
        values.put("wechat",userEntity.getWechat());
        values.put("company_name",userEntity.getCompanyName());
        values.put("company_addr",userEntity.getCompanyAddr());
        values.put("job",userEntity.getJob());
        values.put("icon",userEntity.getIcon());
        db.update(getDao().getTableName(), values, "username=?", new String[]{userEntity.getUsername()});
    }

    /**
     * 删除用户
     *
     */
    public static void deleteUser() {
        getDao().deleteAll();
    }
}
