package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.leaf.library.db.annotation.Column;
import com.leaf.library.db.annotation.Table;

/**
 * 搜索历史
 * Created by wangbin on 16/4/23.
 */
@Table(name = "search_table")
public class SearchEntity implements Parcelable {
    @Column
    private int type;//0企业 1法人 2失信
    @Column
    private String content;//搜索内容
    @Column
    private String country;//选择城市
    @Column
    private String time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.content);
        dest.writeString(this.country);
        dest.writeString(this.time);
    }

    public SearchEntity() {
    }

    protected SearchEntity(Parcel in) {
        this.type = in.readInt();
        this.content = in.readString();
        this.country = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<SearchEntity> CREATOR = new Parcelable.Creator<SearchEntity>() {
        public SearchEntity createFromParcel(Parcel source) {
            return new SearchEntity(source);
        }

        public SearchEntity[] newArray(int size) {
            return new SearchEntity[size];
        }
    };
}
