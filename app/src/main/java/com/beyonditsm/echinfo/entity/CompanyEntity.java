package com.beyonditsm.echinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 公司详情
 * Created by gxy on 2016/4/19.
 */
public class CompanyEntity implements Parcelable {
    /**
     * "id":"12",
     * "companyName":"李建的1公司",
     * "registNo":"",
     * "companyPhoneNo":null,
     * "postcode":null,
     * "address":"长灵区",
     * "email":"qq.com",
     * "stockTransfer":null,
     * "managementStatus":null,
     * "isHaveWeb":null,
     * "companyInverstment":null,
     * "employeeCount":null,
     * "focus":null,
     * "level":null,
     * "coords":null,
     * "browseCount":null,
     * "industry":null
     * "sts"
     */
    /**
     * "id":"1",
     * "editType":"1",
     * "editTime":"2016-04-16 00：00：00",
     * "companyId":"12",
     * "beforeEdit":"变更前的你",
     * "afterEdit":"变更后的我"
     */
    /**
     * "id":null,
     * "companyId":null,
     * "accountId":null,
     * "companyName":"上海鸿日投资管理有限公司",
     * "repPersion":"李启孟",
     * "recordStatus":"存续"
     */
    private String sts;
    private String repPersion;
    private String accountId;
    private String editType;
    private String editTime;
    private String beforeEdit;
    private String afterEdit;
    private String	companyId;//企业ID
    private String	legalRepPersion;//法定代表人
    private String	recordStatus;//登记状态
    private String	toCompanyId;//被投资企业ID
    private String id;
    private String companyName;
    private String registNo;
    private String companyPhoneNo;
    private String postcode;
    private String address;
    private String email;
    private String stockTransfer;
    private String managementStatus;
    private String isHaveWeb;
    private String companyInverstment;
    private String employeeCount;
    private String focus;
    private String level;
    private String coords;
    private String browseCount;
    private String industry;
    private float longitude;//经度
    private float latitude;// 纬度

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getRepPersion() {
        return repPersion;
    }

    public void setRepPersion(String repPersion) {
        this.repPersion = repPersion;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getBeforeEdit() {
        return beforeEdit;
    }

    public void setBeforeEdit(String beforeEdit) {
        this.beforeEdit = beforeEdit;
    }

    public String getAfterEdit() {
        return afterEdit;
    }

    public void setAfterEdit(String afterEdit) {
        this.afterEdit = afterEdit;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getToCompanyId() {
        return toCompanyId;
    }

    public void setToCompanyId(String toCompanyId) {
        this.toCompanyId = toCompanyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistNo() {
        return registNo;
    }

    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    public String getCompanyPhoneNo() {
        return companyPhoneNo;
    }

    public void setCompanyPhoneNo(String companyPhoneNo) {
        this.companyPhoneNo = companyPhoneNo;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStockTransfer() {
        return stockTransfer;
    }

    public void setStockTransfer(String stockTransfer) {
        this.stockTransfer = stockTransfer;
    }

    public String getManagementStatus() {
        return managementStatus;
    }

    public void setManagementStatus(String managementStatus) {
        this.managementStatus = managementStatus;
    }

    public String getIsHaveWeb() {
        return isHaveWeb;
    }

    public void setIsHaveWeb(String isHaveWeb) {
        this.isHaveWeb = isHaveWeb;
    }

    public String getCompanyInverstment() {
        return companyInverstment;
    }

    public void setCompanyInverstment(String companyInverstment) {
        this.companyInverstment = companyInverstment;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(String browseCount) {
        this.browseCount = browseCount;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public CompanyEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sts);
        dest.writeString(this.repPersion);
        dest.writeString(this.accountId);
        dest.writeString(this.editType);
        dest.writeString(this.editTime);
        dest.writeString(this.beforeEdit);
        dest.writeString(this.afterEdit);
        dest.writeString(this.companyId);
        dest.writeString(this.legalRepPersion);
        dest.writeString(this.recordStatus);
        dest.writeString(this.toCompanyId);
        dest.writeString(this.id);
        dest.writeString(this.companyName);
        dest.writeString(this.registNo);
        dest.writeString(this.companyPhoneNo);
        dest.writeString(this.postcode);
        dest.writeString(this.address);
        dest.writeString(this.email);
        dest.writeString(this.stockTransfer);
        dest.writeString(this.managementStatus);
        dest.writeString(this.isHaveWeb);
        dest.writeString(this.companyInverstment);
        dest.writeString(this.employeeCount);
        dest.writeString(this.focus);
        dest.writeString(this.level);
        dest.writeString(this.coords);
        dest.writeString(this.browseCount);
        dest.writeString(this.industry);
        dest.writeFloat(this.longitude);
        dest.writeFloat(this.latitude);
    }

    protected CompanyEntity(Parcel in) {
        this.sts = in.readString();
        this.repPersion = in.readString();
        this.accountId = in.readString();
        this.editType = in.readString();
        this.editTime = in.readString();
        this.beforeEdit = in.readString();
        this.afterEdit = in.readString();
        this.companyId = in.readString();
        this.legalRepPersion = in.readString();
        this.recordStatus = in.readString();
        this.toCompanyId = in.readString();
        this.id = in.readString();
        this.companyName = in.readString();
        this.registNo = in.readString();
        this.companyPhoneNo = in.readString();
        this.postcode = in.readString();
        this.address = in.readString();
        this.email = in.readString();
        this.stockTransfer = in.readString();
        this.managementStatus = in.readString();
        this.isHaveWeb = in.readString();
        this.companyInverstment = in.readString();
        this.employeeCount = in.readString();
        this.focus = in.readString();
        this.level = in.readString();
        this.coords = in.readString();
        this.browseCount = in.readString();
        this.industry = in.readString();
        this.longitude = in.readFloat();
        this.latitude = in.readFloat();
    }

    public static final Creator<CompanyEntity> CREATOR = new Creator<CompanyEntity>() {
        public CompanyEntity createFromParcel(Parcel source) {
            return new CompanyEntity(source);
        }

        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };
}
