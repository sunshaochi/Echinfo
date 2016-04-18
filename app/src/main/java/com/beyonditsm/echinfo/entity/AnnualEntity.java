package com.beyonditsm.echinfo.entity;

/**
 * 年报实体,企业资讯
 * Created by gxy on 2016/4/18.
 */
public class AnnualEntity {
    /**
     * "id":"2","companyId":"12","year":"2016","companyName":"李建的1公司"
     * "id":"1","newsName":"马云独家内幕","newsFrom":"淘宝网","newsTime":"2016-04-18 00：00：00","companyName":"阿里巴巴","companyId":"12","content":"马云的成功秘诀"
     */
    private String id;
    private String companyId;
    private String year;
    private String companyName;
    private String newsName;
    private String newsFrom;
    private String newsTime;
    private String content;

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsFrom() {
        return newsFrom;
    }

    public void setNewsFrom(String newsFrom) {
        this.newsFrom = newsFrom;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
