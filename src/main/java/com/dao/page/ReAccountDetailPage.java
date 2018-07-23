package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class ReAccountDetailPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String accountDetailId;
    private String entrustCompanyId;
    private String projectId;
    private String accountType;
    private String accountTypeName;//收款方式名称
    private String accountDate;//收款日期
    private String accountStartDate;//收款开始日期（查询用）
    private String accountEndDate;//收款结束日期（查询用）
    private String invoiceNo;//票据号码（查询用）
    private String allInvoiceNo;//相关票据号码
    private Double accountValue;
    private String contractCode;//合同号
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String receivableState;

    private List<EntrustInfoPage> entrustIdList = new ArrayList<EntrustInfoPage>();

    public String getAccountDetailId() {
        return accountDetailId;
    }

    public void setAccountDetailId(String accountDetailId) {
        this.accountDetailId = accountDetailId;
    }

    public String getEntrustCompanyId() {
        return entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getAccountStartDate() {
        return accountStartDate;
    }

    public void setAccountStartDate(String accountStartDate) {
        this.accountStartDate = accountStartDate;
    }

    public String getAccountEndDate() {
        return accountEndDate;
    }

    public void setAccountEndDate(String accountEndDate) {
        this.accountEndDate = accountEndDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getAllInvoiceNo() {
        return allInvoiceNo;
    }

    public void setAllInvoiceNo(String allInvoiceNo) {
        this.allInvoiceNo = allInvoiceNo;
    }

    public Double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(Double accountValue) {
        this.accountValue = accountValue;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    public String getInputeTime() {
        return inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<EntrustInfoPage> getEntrustIdList() {
        return entrustIdList;
    }

    public void setEntrustIdList(List<EntrustInfoPage> entrustIdList) {
        this.entrustIdList = entrustIdList;
    }

    public String getReceivableState() {
        return receivableState;
    }

    public void setReceivableState(String receivableState) {
        this.receivableState = receivableState;
    }

}
