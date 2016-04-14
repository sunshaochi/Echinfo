package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 上传附件返回
 * Created by wangbin on 16/1/24.
 */
public class BillImageEntity implements Parcelable {

    private List<String> bigImgPaths;
    private List<String> smallImgPaths;
    private String iconFilePath;
    public List<String> getBigImgPaths() {
        return bigImgPaths;
    }

    public void setBigImgPaths(List<String> bigImgPaths) {
        this.bigImgPaths = bigImgPaths;
    }

    public List<String> getSmallImgPaths() {
        return smallImgPaths;
    }

    public void setSmallImgPaths(List<String> smallImgPaths) {
        this.smallImgPaths = smallImgPaths;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.bigImgPaths);
        dest.writeStringList(this.smallImgPaths);
        dest.writeString(this.iconFilePath);
    }

    public BillImageEntity() {
    }

    protected BillImageEntity(Parcel in) {
        this.bigImgPaths = in.createStringArrayList();
        this.smallImgPaths = in.createStringArrayList();
        this.iconFilePath = in.readString();
    }

    public static final Creator<BillImageEntity> CREATOR = new Creator<BillImageEntity>() {
        public BillImageEntity createFromParcel(Parcel source) {
            return new BillImageEntity(source);
        }

        public BillImageEntity[] newArray(int size) {
            return new BillImageEntity[size];
        }
    };
}
