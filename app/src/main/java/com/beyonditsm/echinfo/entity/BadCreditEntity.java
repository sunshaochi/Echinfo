package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 失信
 * Created by gxy on 2016/4/23.
 */
public class BadCreditEntity implements Parcelable{
    /**
     * "duty":"要求被执行人履行生效法律文书所确定的义务",
     * "sType":"CourtPerson",
     * "gistunit":"江苏省阜宁县人民法院",
     * "iname":"顾海林",
     * "url":"http://shixin.court.gov.cn/detail?id=1509363",
     * "disrupttypename":"违反财产报告制度,违反限制高消费令,被执行人无正当理由拒不履行执行和解协议,其他有履行能力而拒不履行生效法律文书确定义务",
     * "age":46,
     * "casecode":"(2015)阜执字第00690号",
     * "regdate":"2015-03-28 00:00:00",
     * "publishdate":"2015-04-28 00:00:00",
     * "partytypename":"580",
     * "courtname":"江苏省阜宁县人民法院",
     * "cardnum":"3209231969****1513",
     * "key":"1509363",
     * "sexy":"男",
     * "focusnumber":0,
     * "performance":"全部未履行",
     * "sId":"5550a5f9e138231700b9328e",
     * "gistid":"(2013)阜城民初字第1244号",
     * "areaname":"江苏",
     * "isBlacklist":"True",
     * "buildTime":1431348729.957686
     */

    private String duty;
    private String sType;
    private String gistunit;
    private String iname;
    private String url;
    private String disrupttypename;
    private String age;
    private String casecode;
    private String regdate;
    private String publishdate;
    private String partytypename;
    private String courtname;
    private String key;
    private String cardnum;
    private String sexy;
    private String focusnumber;
    private String performance;
    private String sId;
    private String gistid;
    private String areaname;
    private String isBlacklist;
    private String buildTime;

    protected BadCreditEntity(Parcel in) {
        duty = in.readString();
        sType = in.readString();
        gistunit = in.readString();
        iname = in.readString();
        url = in.readString();
        disrupttypename = in.readString();
        age = in.readString();
        casecode = in.readString();
        regdate = in.readString();
        publishdate = in.readString();
        partytypename = in.readString();
        courtname = in.readString();
        cardnum=in.readString();
        key = in.readString();
        sexy = in.readString();
        focusnumber = in.readString();
        performance = in.readString();
        sId = in.readString();
        gistid = in.readString();
        areaname = in.readString();
        isBlacklist = in.readString();
        buildTime = in.readString();
    }

    public static final Creator<BadCreditEntity> CREATOR = new Creator<BadCreditEntity>() {
        @Override
        public BadCreditEntity createFromParcel(Parcel in) {
            return new BadCreditEntity(in);
        }

        @Override
        public BadCreditEntity[] newArray(int size) {
            return new BadCreditEntity[size];
        }
    };

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getGistunit() {
        return gistunit;
    }

    public void setGistunit(String gistunit) {
        this.gistunit = gistunit;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisrupttypename() {
        return disrupttypename;
    }

    public void setDisrupttypename(String disrupttypename) {
        this.disrupttypename = disrupttypename;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCasecode() {
        return casecode;
    }

    public void setCasecode(String casecode) {
        this.casecode = casecode;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getPartytypename() {
        return partytypename;
    }

    public void setPartytypename(String partytypename) {
        this.partytypename = partytypename;
    }

    public String getCourtname() {
        return courtname;
    }

    public void setCourtname(String courtname) {
        this.courtname = courtname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public String getFocusnumber() {
        return focusnumber;
    }

    public void setFocusnumber(String focusnumber) {
        this.focusnumber = focusnumber;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getGistid() {
        return gistid;
    }

    public void setGistid(String gistid) {
        this.gistid = gistid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(String isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(duty);
        dest.writeString(sType);
        dest.writeString(gistunit);
        dest.writeString(iname);
        dest.writeString(url);
        dest.writeString(disrupttypename);
        dest.writeString(age);
        dest.writeString(casecode);
        dest.writeString(regdate);
        dest.writeString(publishdate);
        dest.writeString(partytypename);
        dest.writeString(courtname);
        dest.writeString(cardnum);
        dest.writeString(key);
        dest.writeString(sexy);
        dest.writeString(focusnumber);
        dest.writeString(performance);
        dest.writeString(sId);
        dest.writeString(gistid);
        dest.writeString(areaname);
        dest.writeString(isBlacklist);
        dest.writeString(buildTime);
    }
}
