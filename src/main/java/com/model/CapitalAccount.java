package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractCapitalAccount entity provides the base persistence definition of the CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAPITAL_ACCOUNT")
public class CapitalAccount extends BaseBean implements java.io.Serializable {


    // Fields    

    private String capitalAccountId;
    private String capitalAccountCode;
    private String entrustCompanyId;
    private String projectId;
    private String supervisionUnitId;
    private String accountType2;
    private String accountKinds;
    private String contractCode;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private Integer printNum;
    private String contractId;
    private String linkMan;
    private String linkPhone;
    private String projectRemark;


    // Constructors

    /**
     * default constructor
     */
    public CapitalAccount() {
    }

    /**
     * minimal constructor
     */
    public CapitalAccount(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    /**
     * full constructor
     */
    public CapitalAccount(String capitalAccountId, String capitalAccountCode, String entrustCompanyId, String projectId, String supervisionUnitId, String accountType2, String accountKinds, String contractCode, String remark, String inputer, String inputeTime, String updater, String updateTime, Integer printNum, String contractId, String linkMan, String linkPhone, String projectRemark) {
        this.capitalAccountId = capitalAccountId;
        this.capitalAccountCode = capitalAccountCode;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.supervisionUnitId = supervisionUnitId;
        this.accountType2 = accountType2;
        this.accountKinds = accountKinds;
        this.contractCode = contractCode;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.printNum = printNum;
        this.contractId = contractId;
        this.linkMan = linkMan;
        this.linkPhone = linkPhone;
        this.projectRemark = projectRemark;
    }


    // Property accessors
    @Id

    @Column(name = "CAPITAL_ACCOUNT_ID", unique = true, nullable = false, length = 32)

    public String getCapitalAccountId() {
        return this.capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    @Column(name = "CAPITAL_ACCOUNT_CODE", length = 30)

    public String getCapitalAccountCode() {
        return this.capitalAccountCode;
    }

    public void setCapitalAccountCode(String capitalAccountCode) {
        this.capitalAccountCode = capitalAccountCode;
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

    @Column(name = "SUPERVISION_UNIT_ID", length = 32)

    public String getSupervisionUnitId() {
        return this.supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
    }

    @Column(name = "ACCOUNT_TYPE2", length = 32)

    public String getAccountType2() {
        return this.accountType2;
    }

    public void setAccountType2(String accountType2) {
        this.accountType2 = accountType2;
    }

    @Column(name = "ACCOUNT_KINDS", length = 32)

    public String getAccountKinds() {
        return this.accountKinds;
    }

    public void setAccountKinds(String accountKinds) {
        this.accountKinds = accountKinds;
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

    @Column(name = "PRINT_NUM")
    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    @Column(name = "CONTRACT_ID", length = 32)
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Column(name = "LINK_MAN", length = 120)

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "LINK_PHONE", length = 140)

    public String getLinkPhone() {
        return this.linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Column(name = "PROJECT_REMARK", length = 20)

    public String getProjectRemark() {
        return this.projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }


}