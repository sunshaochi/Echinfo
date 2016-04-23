package com.beyonditsm.echinfo.entity;

/**
 * 股东实体
 * Created by gxy on 2016/4/18.
 */
public class StockMsg {
    private String	cardNo;//证件号码
    private String	cardType;//证件类型
    private String	companyId;//企业id
    private String	id;//ID(主键ID)
    private String	name;//姓名/企业名称（股东）
    private Double	realSubcribe;//实缴出资
    private String	realSubcribeTime;//实缴时间
    private String	realSubcribeType;//实缴出资方式
    private String	stockType;//股东类型
    private Double	subcribe;//认缴出资
    private String	subcribeTime;//出资时间
    private String	subcribeType;//认缴出资方式
    private String legalRepPersion;//企业法人
    private String recordStatus;//状态

    public String getLegalRepPersion() {
        return legalRepPersion;
    }

    public void setLegalRepPersion(String legalRepPersion) {
        this.legalRepPersion = legalRepPersion;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRealSubcribe() {
        return realSubcribe;
    }

    public void setRealSubcribe(Double realSubcribe) {
        this.realSubcribe = realSubcribe;
    }

    public String getRealSubcribeTime() {
        return realSubcribeTime;
    }

    public void setRealSubcribeTime(String realSubcribeTime) {
        this.realSubcribeTime = realSubcribeTime;
    }

    public String getRealSubcribeType() {
        return realSubcribeType;
    }

    public void setRealSubcribeType(String realSubcribeType) {
        this.realSubcribeType = realSubcribeType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getSubcribeTime() {
        return subcribeTime;
    }

    public void setSubcribeTime(String subcribeTime) {
        this.subcribeTime = subcribeTime;
    }

    public Double getSubcribe() {
        return subcribe;
    }

    public void setSubcribe(Double subcribe) {
        this.subcribe = subcribe;
    }

    public String getSubcribeType() {
        return subcribeType;
    }

    public void setSubcribeType(String subcribeType) {
        this.subcribeType = subcribeType;
    }
}
