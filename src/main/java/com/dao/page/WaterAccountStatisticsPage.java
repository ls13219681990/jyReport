package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WaterAccountStatisticsPage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String entrustSn;//委托编号
    private String entrustDate;//委托日期
    private String capitalAccountCode;//资金账号
    private String entrustCompanyName;//委托单位
    private String projectName;//工程名称
    private String sampleName;//样品名称
    private String testParameterName;//检测参数
    private String reportNum;//样品组数
    private String realTotalPrice;//金额
    private String realUnitPrice; //单价
    private String received;//已收
    private String linkman;//委托人
    private String linkPhone;//联系电话
    private String acceptanceMan;//受理人
    private String accountType;//收费类别
    private String managementDepartmentName;//经营部门
    private String departmentName;//检测科室
    private String inputName;//委托录入人
    private Integer totalCount;//总数
    private String entrustStatus;//委托状态
    private String isComplementally;//是否补办
    private String sampleNameRemark;    //样品代名称
    private String inputTime;

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getCapitalAccountCode() {
        return capitalAccountCode;
    }

    public void setCapitalAccountCode(String capitalAccountCode) {
        this.capitalAccountCode = capitalAccountCode;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getTestParameterName() {
        return testParameterName;
    }

    public void setTestParameterName(String testParameterName) {
        this.testParameterName = testParameterName;
    }

    public String getRealTotalPrice() {
        return realTotalPrice;
    }

    public void setRealTotalPrice(String realTotalPrice) {
        this.realTotalPrice = realTotalPrice;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getManagementDepartmentName() {
        return managementDepartmentName;
    }

    public void setManagementDepartmentName(String managementDepartmentName) {
        this.managementDepartmentName = managementDepartmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum;
    }

    public String getRealUnitPrice() {
        return realUnitPrice;
    }

    public void setRealUnitPrice(String realUnitPrice) {
        this.realUnitPrice = realUnitPrice;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getIsComplementally() {
        return isComplementally;
    }

    public void setIsComplementally(String isComplementally) {
        this.isComplementally = isComplementally;
    }

    public String getSampleNameRemark() {
        return sampleNameRemark;
    }

    public void setSampleNameRemark(String sampleNameRemark) {
        this.sampleNameRemark = sampleNameRemark;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

}