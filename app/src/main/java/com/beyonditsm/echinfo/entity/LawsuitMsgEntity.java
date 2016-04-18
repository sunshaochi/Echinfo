package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 诉讼信息
 * Created by gxy on 2016/4/18.
 */
public class LawsuitMsgEntity implements Parcelable{
    /**
     * "id":"1",
     * "companyId":"1",
     * "name":"1",
     * "cardNo":"1",
     * "court":"1",
     * "registrineTime":"1",
     * "caseNo":"1",
     * "zxbd":"1"
     */
    private String id;
    private String companyId;
    private String name;
    private String cardNo;
    private String court;
    private String registrineTime;
    private String caseNo;
    private String zxbd;

    protected LawsuitMsgEntity(Parcel in) {
        id = in.readString();
        companyId = in.readString();
        name = in.readString();
        cardNo = in.readString();
        court = in.readString();
        registrineTime = in.readString();
        caseNo = in.readString();
        zxbd = in.readString();
    }

    public static final Creator<LawsuitMsgEntity> CREATOR = new Creator<LawsuitMsgEntity>() {
        @Override
        public LawsuitMsgEntity createFromParcel(Parcel in) {
            return new LawsuitMsgEntity(in);
        }

        @Override
        public LawsuitMsgEntity[] newArray(int size) {
            return new LawsuitMsgEntity[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getRegistrineTime() {
        return registrineTime;
    }

    public void setRegistrineTime(String registrineTime) {
        this.registrineTime = registrineTime;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getZxbd() {
        return zxbd;
    }

    public void setZxbd(String zxbd) {
        this.zxbd = zxbd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(companyId);
        dest.writeString(name);
        dest.writeString(cardNo);
        dest.writeString(court);
        dest.writeString(registrineTime);
        dest.writeString(caseNo);
        dest.writeString(zxbd);
    }
}
