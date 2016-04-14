package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangbin on 16/1/25.
 */
public class UserDataEntity implements Parcelable {

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, 0);
    }

    public UserDataEntity() {
    }

    protected UserDataEntity(Parcel in) {
        this.user = in.readParcelable(UserEntity.class.getClassLoader());
    }

    public static final Creator<UserDataEntity> CREATOR = new Creator<UserDataEntity>() {
        public UserDataEntity createFromParcel(Parcel source) {
            return new UserDataEntity(source);
        }

        public UserDataEntity[] newArray(int size) {
            return new UserDataEntity[size];
        }
    };
}
