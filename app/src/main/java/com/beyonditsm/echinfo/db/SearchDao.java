package com.beyonditsm.echinfo.db;

import com.beyonditsm.echinfo.entity.SearchEntity;
import com.beyonditsm.echinfo.util.ShUtils;
import com.leaf.library.db.TemplateDAO;

import java.util.List;

/**
 * Created by wangbin on 16/4/23.
 */
public class SearchDao extends TemplateDAO<SearchEntity, String> {

    public SearchDao() {
        super(ShUtils.getDbhelper());
    }

    private static SearchDao dao;

    private static SearchDao getDao() {
        if (dao == null) {
            dao = new SearchDao();
        }
        return dao;
    }

    /**
     * 插入搜索
     *
     * @param searchEntity
     */
    public static void addSearch(SearchEntity searchEntity) {
        getDao().insert(searchEntity);
    }

    /**
     * 查询搜索历史
     * @return
     */
    public static List<SearchEntity> getSearchList() {
        List<SearchEntity> searchList = getDao().find(null, null, null, null, null, "time desc", null);
        return searchList;
    }

    public static void clearAll(){
        getDao().deleteAll();
    }


}
