package com.beyonditsm.echinfo.entity;

/**
 * 公司详情
 * Created by gxy on 2016/4/19.
 */
public class CompanyEntity {
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
     */
    /**
     * "id":"1",
     * "editType":"1",
     * "editTime":"2016-04-16 00：00：00",
     * "companyId":"12",
     * "beforeEdit":"变更前的你",
     * "afterEdit":"变更后的我"
     */
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
}
