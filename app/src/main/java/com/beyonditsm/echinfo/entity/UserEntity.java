package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.leaf.library.db.annotation.Column;
import com.leaf.library.db.annotation.Table;

/**
 * 用户
 * Created by Administrator on 2016/1/24.
 */
@Table(name = "user_table")
public class UserEntity implements Parcelable {

    /**
     * id : a3ef1535c26611e5a130eca86ba4ba05
     * username : 13671667132
     * password : d888e54f01745031723e3660bb82f1ee
     * name : null
     * phone : 134
     * email : null
     * qq : 12314141
     * wechat : 141
     * icon : null
     * token : null
     * referralCode : null
     * myReferralCode : null
     * companyName : null
     * companyAddr : null
     * companyPhone : null
     * job : null
     * isValid : true
     * createPersonId : null
     * createTime : null
     * modifyPersonId : null
     * modifyTime : 1453630262000
     * remark : null
     */

    @Column
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String qq;
    @Column
    private String wechat;
    @Column
    private String icon;
    @Column
    private String token;
    @Column
    private String referralCode;
    @Column
    private String myReferralCode;
    @Column
    private String companyName;
    @Column
    private String companyAddr;
    @Column
    private String companyPhone;
    @Column
    private String job;
    @Column
    private boolean isValid;
    @Column
    private String createPersonId;
    @Column
    private String createTime;
    @Column
    private String modifyPersonId;
    @Column
    private long modifyTime;
    @Column
    private String remark;
    @Column
    private String accredit;//0只能使用我的，其他所有工具都不能用

    /**
     * qq登录
     * is_yellow_year_vip=0,
     * vip=0,
     * level=0,
     * province=,
     * yellow_vip_level=0,
     * is_yellow_vip=0,
     * gender=女,
     * openid=AF7F61CFB79518DA8B7230CDD73DD67D,
     * screen_name=所以愛┈━═☆,
     * msg=,
     * profile_image_url=http://q.qlogo.cn/qqapp/1105241351/AF7F61CFB79518DA8B7230CDD73DD67D/100,
     * city=
     * @return
     */
    /**
     * 微博登录
     * uid=3311587925,
     * favourites_count=0,
     * location=上海 长宁区,
     * description=,
     * verified=false,
     * friends_count=63,
     * gender=0,
     * screen_name=唯美似夏花_75039,
     * statuses_count=66,
     * followers_count=2,
     * profile_image_url=http://tp2.sinaimg.cn/3311587925/180/0/0,
     * access_token=2.00TSFHcDpHY4nB2257683e6f0VT98B
     * @return
     */


    public String getAccredit() {
        return accredit;
    }

    public void setAccredit(String accredit) {
        this.accredit = accredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getMyReferralCode() {
        return myReferralCode;
    }

    public void setMyReferralCode(String myReferralCode) {
        this.myReferralCode = myReferralCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyPersonId() {
        return modifyPersonId;
    }

    public void setModifyPersonId(String modifyPersonId) {
        this.modifyPersonId = modifyPersonId;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.qq);
        dest.writeString(this.wechat);
        dest.writeString(this.icon);
        dest.writeString(this.token);
        dest.writeString(this.referralCode);
        dest.writeString(this.myReferralCode);
        dest.writeString(this.companyName);
        dest.writeString(this.companyAddr);
        dest.writeString(this.companyPhone);
        dest.writeString(this.job);
        dest.writeByte(isValid ? (byte) 1 : (byte) 0);
        dest.writeString(this.createPersonId);
        dest.writeString(this.createTime);
        dest.writeString(this.modifyPersonId);
        dest.writeLong(this.modifyTime);
        dest.writeString(this.remark);
        dest.writeString(this.accredit);
    }

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        this.id = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.qq = in.readString();
        this.wechat = in.readString();
        this.icon = in.readString();
        this.token = in.readString();
        this.referralCode = in.readString();
        this.myReferralCode = in.readString();
        this.companyName = in.readString();
        this.companyAddr = in.readString();
        this.companyPhone = in.readString();
        this.job = in.readString();
        this.isValid = in.readByte() != 0;
        this.createPersonId = in.readString();
        this.createTime = in.readString();
        this.modifyPersonId = in.readString();
        this.modifyTime = in.readLong();
        this.remark = in.readString();
        this.accredit=in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", icon='" + icon + '\'' +
                ", token='" + token + '\'' +
                ", referralCode='" + referralCode + '\'' +
                ", myReferralCode='" + myReferralCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", job='" + job + '\'' +
                ", isValid=" + isValid +
                ", createPersonId='" + createPersonId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyPersonId='" + modifyPersonId + '\'' +
                ", modifyTime=" + modifyTime +
                ", remark='" + remark + '\'' +
                ", accredit='" + accredit + '\'' +
                '}';
    }
}