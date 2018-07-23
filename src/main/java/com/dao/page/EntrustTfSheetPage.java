package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class EntrustTfSheetPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustId;
    private String entrustSn;
    private String entrustCompanyId;
    private String entrustCompanyName;
    private String projectId;
    private String projectName;
    private String projectRemark;
    private String entrustDate;
    private String sampleName;//试件名称
    private String testNum;//试件数量
    private String accountValue;//收费金额
    private String linkPhone;
    private String linkMan;
    private String accountType;//类型
    private String accountTypeName;
    private String accountKinds;//收款类别
    private String accountKindsName;
    private String contractCode;
    private String contractMoney;//合同金额
    private String managementDepartmentId;//经营部门ID
    private String managementDepartmentName;//经营部门名称
    private String testDepartment;//检测部门名称
    private String entrustStatus;
    //委托传送单明细LIST
    private List<EntrustTfSheetDetailPage> eTfSheetList = new ArrayList<EntrustTfSheetDetailPage>();


    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    public String getEntrustCompanyId() {
        return entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getTestNum() {
        return testNum;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(String accountValue) {
        this.accountValue = accountValue;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getAccountKinds() {
        return accountKinds;
    }

    public void setAccountKinds(String accountKinds) {
        this.accountKinds = accountKinds;
    }

    public String getAccountKindsName() {
        return accountKindsName;
    }

    public void setAccountKindsName(String accountKindsName) {
        this.accountKindsName = accountKindsName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getManagementDepartmentId() {
        return managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    public String getManagementDepartmentName() {
        return managementDepartmentName;
    }

    public void setManagementDepartmentName(String managementDepartmentName) {
        this.managementDepartmentName = managementDepartmentName;
    }

    public String getTestDepartment() {
        return testDepartment;
    }

    public void setTestDepartment(String testDepartment) {
        this.testDepartment = testDepartment;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public List<EntrustTfSheetDetailPage> geteTfSheetList() {
        return eTfSheetList;
    }

    public void seteTfSheetList(List<EntrustTfSheetDetailPage> eTfSheetList) {
        this.eTfSheetList = eTfSheetList;
    }

}
