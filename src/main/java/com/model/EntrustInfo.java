package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustInfo entity provides the base persistence definition of the EntrustInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_INFO")
public class EntrustInfo extends BaseBean implements java.io.Serializable {


    // Fields    

    private String entrustId;
    private String entrustSn;
    private String entrustCompanyId;
    private String projectId;
    private String projectRemark;
    private String entrustDate;
    private String supervisionUnitId;
    private String projectAddress;
    private String linkPhone;
    private String inspectionMan;
    private String witnessMan;
    private String linkMan;
    private String accountType;
    private String accountKinds;
    private String contractCode;
    private Double accountValue;
    private String managementDepartmentId;
    private String isComplementally;
    private String entrustStatus;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private Integer printNum;
    private String entrustType;
    private String capitalAccountId;
    private Double involidMoney;
    private String contractId;
    private String specialRule;

    // Constructors

    /**
     * default constructor
     */
    public EntrustInfo() {
    }

    /**
     * minimal constructor
     */
    public EntrustInfo(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * full constructor
     */
    public EntrustInfo(String entrustId, String entrustSn, String entrustCompanyId, String projectId, String projectRemark, String entrustDate, String supervisionUnitId, String projectAddress, String linkPhone, String inspectionMan, String witnessMan, String linkMan, String accountType, String accountKinds, String contractCode, Double accountValue, String managementDepartmentId, String isComplementally, String entrustStatus, String remark, String inputer, String inputeTime, String updater, String updateTime, Integer printNum, String entrustType, String capitalAccountId, Double involidMoney, String contractId, String specialRule) {
        this.entrustId = entrustId;
        this.entrustSn = entrustSn;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.projectRemark = projectRemark;
        this.entrustDate = entrustDate;
        this.supervisionUnitId = supervisionUnitId;
        this.projectAddress = projectAddress;
        this.linkPhone = linkPhone;
        this.inspectionMan = inspectionMan;
        this.witnessMan = witnessMan;
        this.linkMan = linkMan;
        this.accountType = accountType;
        this.accountKinds = accountKinds;
        this.contractCode = contractCode;
        this.accountValue = accountValue;
        this.managementDepartmentId = managementDepartmentId;
        this.isComplementally = isComplementally;
        this.entrustStatus = entrustStatus;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.printNum = printNum;
        this.entrustType = entrustType;
        this.capitalAccountId = capitalAccountId;
        this.involidMoney = involidMoney;
        this.contractId = contractId;
        this.specialRule = specialRule;
    }


    // Property accessors
    @Id

    @Column(name = "ENTRUST_ID", unique = true, nullable = false, length = 32)

    public String getEntrustId() {
        return this.entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Column(name = "ENTRUST_SN", length = 20)

    public String getEntrustSn() {
        return this.entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
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

    @Column(name = "PROJECT_REMARK", length = 100)

    public String getProjectRemark() {
        return this.projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    @Column(name = "ENTRUST_DATE", length = 10)

    public String getEntrustDate() {
        return this.entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    @Column(name = "SUPERVISION_UNIT_ID", length = 32)

    public String getSupervisionUnitId() {
        return this.supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
    }

    @Column(name = "PROJECT_ADDRESS", length = 100)

    public String getProjectAddress() {
        return this.projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    @Column(name = "LINK_PHONE", length = 15)

    public String getLinkPhone() {
        return this.linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Column(name = "INSPECTION_MAN", length = 20)

    public String getInspectionMan() {
        return this.inspectionMan;
    }

    public void setInspectionMan(String inspectionMan) {
        this.inspectionMan = inspectionMan;
    }

    @Column(name = "WITNESS_MAN", length = 20)

    public String getWitnessMan() {
        return this.witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    @Column(name = "LINK_MAN", length = 20)

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "ACCOUNT_TYPE", length = 32)

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    @Column(name = "ACCOUNT_VALUE", scale = 4)

    public Double getAccountValue() {
        return this.accountValue;
    }

    public void setAccountValue(Double accountValue) {
        this.accountValue = accountValue;
    }

    @Column(name = "MANAGEMENT_DEPARTMENT_ID", length = 32)

    public String getManagementDepartmentId() {
        return this.managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    @Column(name = "IS_COMPLEMENTALLY", length = 50)

    public String getIsComplementally() {
        return this.isComplementally;
    }

    public void setIsComplementally(String isComplementally) {
        this.isComplementally = isComplementally;
    }

    @Column(name = "ENTRUST_STATUS", length = 2)

    public String getEntrustStatus() {
        return this.entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
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

    @Column(name = "ENTRUST_TYPE", length = 2)
    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    @Column(name = "CAPITAL_ACCOUNT_ID", length = 32)
    public String getCapitalAccountId() {
        return this.capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    @Column(name = "INVOLID_MONEY", scale = 4)
    public Double getInvolidMoney() {
        return involidMoney;
    }

    public void setInvolidMoney(Double involidMoney) {
        this.involidMoney = involidMoney;
    }

    @Column(name = "CONTRACT_ID", length = 32)
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Column(name = "SPECIAL_RULE", length = 2)
    public String getSpecialRule() {
        return specialRule;
    }

    public void setSpecialRule(String specialRule) {
        this.specialRule = specialRule;
    }


}