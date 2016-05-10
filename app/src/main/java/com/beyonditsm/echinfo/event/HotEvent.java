package com.beyonditsm.echinfo.event;

import com.beyonditsm.echinfo.entity.CompanyEntity;

import java.util.List;

/**
 * Created by wangbin on 16/5/9.
 */
public class HotEvent {

    public List<CompanyEntity> list;

    public HotEvent(List<CompanyEntity> list){
        this.list=list;
    }
}
