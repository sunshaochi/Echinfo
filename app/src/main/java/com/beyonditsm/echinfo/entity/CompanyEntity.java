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
     * "lastUpdateTime":null,
     * "companyIntro":null,
     * "column20":null,
     * "legalRepPersion":"肖光富",
     * "companyCreatTime":"1990-02-07",
     * "registCapital":"10894.37 "
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
    private String lastUpdateTime;
    private String companyIntro;
    private String column20;
    private String companyCreatTime;
    private String registCapital;
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

    protected CompanyEntity(Parcel in) {
        lastUpdateTime = in.readString();
        companyIntro = in.readString();
        column20 = in.readString();
        companyCreatTime = in.readString();
        registCapital = in.readString();
        sts = in.readString();
        repPersion = in.readString();
        accountId = in.readString();
        editType = in.readString();
        editTime = in.readString();
        beforeEdit = in.readString();
        afterEdit = in.readString();
        companyId = in.readString();
        legalRepPersion = in.readString();
        recordStatus = in.readString();
        toCompanyId = in.readString();
        id = in.readString();
        companyName = in.readString();
        registNo = in.readString();
        companyPhoneNo = in.readString();
        postcode = in.readString();
        address = in.readString();
        email = in.readString();
        stockTransfer = in.readString();
        managementStatus = in.readString();
        isHaveWeb = in.readString();
        companyInverstment = in.readString();
        employeeCount = in.readString();
        focus = in.readString();
        level = in.readString();
        coords = in.readString();
        browseCount = in.readString();
        industry = in.readString();
        longitude = in.readFloat();
        latitude = in.readFloat();
    }

    public static final Creator<CompanyEntity> CREATOR = new Creator<CompanyEntity>() {
        @Override
        public CompanyEntity createFromParcel(Parcel in) {
            return new CompanyEntity(in);
        }

        @Override
        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public String getColumn20() {
        return column20;
    }

    public void setColumn20(String column20) {
        this.column20 = column20;
    }

    public String getCompanyCreatTime() {
        return companyCreatTime;
    }

    public void setCompanyCreatTime(String companyCreatTime) {
        this.companyCreatTime = companyCreatTime;
    }

    public String getRegistCapital() {
        return registCapital;
    }

    public void setRegistCapital(String registCapital) {
        this.registCapital = registCapital;
    }

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
        dest.writeString(lastUpdateTime);
        dest.writeString(companyIntro);
        dest.writeString(column20);
        dest.writeString(companyCreatTime);
        dest.writeString(registCapital);
        dest.writeString(sts);
        dest.writeString(repPersion);
        dest.writeString(accountId);
        dest.writeString(editType);
        dest.writeString(editTime);
        dest.writeString(beforeEdit);
        dest.writeString(afterEdit);
        dest.writeString(companyId);
        dest.writeString(legalRepPersion);
        dest.writeString(recordStatus);
        dest.writeString(toCompanyId);
        dest.writeString(id);
        dest.writeString(companyName);
        dest.writeString(registNo);
        dest.writeString(companyPhoneNo);
        dest.writeString(postcode);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(stockTransfer);
        dest.writeString(managementStatus);
        dest.writeString(isHaveWeb);
        dest.writeString(companyInverstment);
        dest.writeString(employeeCount);
        dest.writeString(focus);
        dest.writeString(level);
        dest.writeString(coords);
        dest.writeString(browseCount);
        dest.writeString(industry);
        dest.writeFloat(longitude);
        dest.writeFloat(latitude);
    }
}
