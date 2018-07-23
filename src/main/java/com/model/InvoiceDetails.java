package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractInvoiceDetails entity provides the base persistence definition of the InvoiceDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INVOICE_DETAILS")
public class InvoiceDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String invoiceDetailId;
    private String entrustCompanyId;
    private String projectId;
    private String invoiceDate;
    private String invoiceType;
    private Double invoiceValue;
    private String invoiceNo;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String receivableState;


    // Constructors

    /**
     * default constructor
     */
    public InvoiceDetails() {
    }

    /**
     * minimal constructor
     */
    public InvoiceDetails(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    /**
     * full constructor
     */
    public InvoiceDetails(String invoiceDetailId,
                          String entrustCompanyId, String projectId, String invoiceDate,
                          String invoiceType, Double invoiceValue, String invoiceNo,
                          String remark, String inputer, String inputeTime, String updater,
                          String updateTime, String receivableState) {
        super();
        this.invoiceDetailId = invoiceDetailId;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.invoiceValue = invoiceValue;
        this.invoiceNo = invoiceNo;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.receivableState = receivableState;
    }


    // Property accessors
    @Id

    @Column(name = "INVOICE_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getInvoiceDetailId() {
        return this.invoiceDetailId;
    }


    public void setInvoiceDetailId(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Column(name = "ENTRUST_COMPANY_ID", length = 32)

    public String getEntrustCompanyId() {
        return this.entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    @Column(name = "PROJECT_ID", length = 32)

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "INVOICE_DATE", length = 19)

    public String getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Column(name = "INVOICE_TYPE", length = 6)

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Column(name = "INVOICE_VALUE", scale = 4)

    public Double getInvoiceValue() {
        return this.invoiceValue;
    }

    public void setInvoiceValue(Double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    @Column(name = "INVOICE_NO", length = 20)

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Column(name = "REMARK", length = 200)

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "INPUTER", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "INPUTE_TIME", length = 19)

    public String getInputeTime() {
        return this.inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    @Column(name = "UPDATER", length = 32)

    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Column(name = "UPDATE_TIME", length = 19)

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "RECEIVABLE_STATE", length = 2)

    public String getReceivableState() {
        return receivableState;
    }

    public void setReceivableState(String receivableState) {
        this.receivableState = receivableState;
    }


}