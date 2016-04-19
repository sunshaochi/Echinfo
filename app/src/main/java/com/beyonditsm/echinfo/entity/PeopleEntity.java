package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 主要成员实体类
 * Created by bitch-1 on 2016/4/19.
 */
public class PeopleEntity implements Parcelable {
    //{"status":200,"data":{"total":2,"rows":[{"id":"1","companyId":"12","companyName":"阿里巴巴","jobName":"主席","persionName":"马云","share":"1"},
    // {"id":"2","companyId":"12","companyName":"阿里巴巴","jobName":"副主席","persionName":"李建1","share":"2"}]},"message":"成功！"}

    private String id;
    private String companyId;
    private String companyName;
    private String jobName;
    private String persionName;
    private String share;

    protected PeopleEntity(Parcel in) {
        id = in.readString();
        companyId = in.readString();
        companyName = in.readString();
        jobName = in.readString();
        persionName = in.readString();
        share = in.readString();
    }

    public PeopleEntity() {
    }

    public static final Creator<PeopleEntity> CREATOR = new Creator<PeopleEntity>() {
        @Override
        public PeopleEntity createFromParcel(Parcel in) {
            return new PeopleEntity(in);
        }

        @Override
        public PeopleEntity[] newArray(int size) {
            return new PeopleEntity[size];
        }
    };

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPersionName() {
        return persionName;
    }

    public void setPersionName(String persionName) {
        this.persionName = persionName;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(companyId);
        dest.writeString(companyName);
        dest.writeString(jobName);
        dest.writeString(persionName);
        dest.writeString(share);
    }
}
