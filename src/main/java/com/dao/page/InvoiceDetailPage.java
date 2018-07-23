package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class InvoiceDetailPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String invoiceDetailId;
    private String entrustCompanyId;
    private String projectId;
    private String invoiceDate;//开票日期
    private String invoiceStartDate;//开票开始日期（查询用）
    private String invoiceEndDate;//开票结束日期（查询用）
    private String invoiceType;
    private String invoiceTypeName;//票据类型名称
    private Double invoiceValue;
    private String invoiceNo;
    private String remark;
    private String isReceivable;//是否已收款
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String receivableState;

    private List<EntrustInfoPage> entrustIdList = new ArrayList<EntrustInfoPage>();

    public String getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceStartDate() {
        return invoiceStartDate;
    }

    public void setInvoiceStartDate(String invoiceStartDate) {
        this.invoiceStartDate = invoiceStartDate;
    }

    public String getInvoiceEndDate() {
        return invoiceEndDate;
    }

    public void setInvoiceEndDate(String invoiceEndDate) {
        this.invoiceEndDate = invoiceEndDate;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    public Double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(Double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsReceivable() {
        return isReceivable;
    }

    public void setIsReceivable(String isReceivable) {
        this.isReceivable = isReceivable;
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
