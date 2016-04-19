package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**分之机构实体类
 * Created by bitch-1 on 2016/4/18.
 */
public class FenziEntity implements Parcelable {
   // {"id":"10","companyName":"李建测试3公司","legalRepPersion":"李建3","recordStatus":"2","companyId":"12"}

    private String id;
    private String companyName;
    private String legalRepPersion;
    private String recordStatus;
    private String companyId;

    public FenziEntity() {
    }

    protected FenziEntity(Parcel in) {
        id = in.readString();
        companyName = in.readString();
        legalRepPersion = in.readString();
        recordStatus = in.readString();
        companyId = in.readString();
    }

    public static final Creator<FenziEntity> CREATOR = new Creator<FenziEntity>() {
        @Override
        public FenziEntity createFromParcel(Parcel in) {
            return new FenziEntity(in);
        }

        @Override
        public FenziEntity[] newArray(int size) {
            return new FenziEntity[size];
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(companyName);
        dest.writeString(legalRepPersion);
        dest.writeString(recordStatus);
        dest.writeString(companyId);
    }
}
