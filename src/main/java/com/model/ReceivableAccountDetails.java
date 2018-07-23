package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractReceivableAccountDetails entity provides the base persistence definition of the ReceivableAccountDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RECEIVABLE_ACCOUNT_DETAILS")
public class ReceivableAccountDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String accountDetailId;
    private String entrustCompanyId;
    private String projectId;
    private String accountType;
    private String accountDate;
    private Double accountValue;
    private String contractCode;
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
    public ReceivableAccountDetails() {
    }

    /**
     * minimal constructor
     */
    public ReceivableAccountDetails(String accountDetailId) {
        this.accountDetailId = accountDetailId;
    }

    /**
     * full constructor
     */
    public ReceivableAccountDetails(String accountDetailId,
                                    String entrustCompanyId, String projectId, String accountType,
                                    String accountDate, Double accountValue, String contractCode,
                                    String remark, String inputer, String inputeTime, String updater,
                                    String updateTime, String receivableState) {
        super();
        this.accountDetailId = accountDetailId;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.accountType = accountType;
        this.accountDate = accountDate;
        this.accountValue = accountValue;
        this.contractCode = contractCode;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.receivableState = receivableState;
    }

    // Property accessors
    @Id

    @Column(name = "ACCOUNT_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getAccountDetailId() {
        return this.accountDetailId;
    }

    public void setAccountDetailId(String accountDetailId) {
        this.accountDetailId = accountDetailId;
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

    @Column(name = "ACCOUNT_TYPE", length = 6)

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Column(name = "ACCOUNT_DATE", length = 19)

    public String getAccountDate() {
        return this.accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    @Column(name = "ACCOUNT_VALUE", scale = 4)

    public Double getAccountValue() {
        return this.accountValue;
    }

    public void setAccountValue(Double accountValue) {
        this.accountValue = accountValue;
    }

    @Column(name = "CONTRACT_CODE", length = 30)

    public String getContractCode() {
        return this.contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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