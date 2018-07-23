package com.model;

import com.common.dao.BaseBean;

import javax.persistence.*;


/**
 * AbstractContract entity provides the base persistence definition of the Contract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AGREEMENT")
public class Agreement extends BaseBean implements java.io.Serializable {


    // Fields    

    private String contractId;
    private String contractCode;
    private String entrustCompanyId;
    private String projectId;
    private String checkArea;
    private String paymentNode;
    private String contractChapter;
    private String linkMan;
    private String linkPhone;
    private String projectManager;
    private String contractIsReturn;
    private String managementDepartmentId;
    private String contractType;
    private String accountType;
    private Double receivedMoney;
    private Double contractMoney;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String copiesCount;
    private String projectNameTag;


    /****** Script for SelectTopNRows command from SSMS  ******/

    // Constructors

    /**
     * default constructor
     */
    public Agreement() {
    }

    /**
     * minimal constructor
     */
    public Agreement(String contractId) {
        this.contractId = contractId;
    }

    /**
     * full constructor
     */
    public Agreement(String contractId, String contractCode, String entrustCompanyId, String projectId, String checkArea, String paymentNode, String contractChapter, String linkMan, String linkPhone, String projectManager, String contractIsReturn, String managementDepartmentId, String contractType, String accountType, Double receivedMoney, Double contractMoney, String remark, String inputer, String inputeTime, String updater, String updateTime, String copiesCount, String projectNameTag) {
        this.contractId = contractId;
        this.contractCode = contractCode;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.checkArea = checkArea;
        this.paymentNode = paymentNode;
        this.contractChapter = contractChapter;
        this.linkMan = linkMan;
        this.linkPhone = linkPhone;
        this.projectManager = projectManager;
        this.contractIsReturn = contractIsReturn;
        this.managementDepartmentId = managementDepartmentId;
        this.contractType = contractType;
        this.accountType = accountType;
        this.receivedMoney = receivedMoney;
        this.contractMoney = contractMoney;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.copiesCount = copiesCount;
        this.projectNameTag = projectNameTag;
    }


    // Property accessors
    @Id

    @Column(name = "CONTRACT_ID", unique = true, nullable = false, length = 32)

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Column(name = "CONTRACT_CODE", length = 30)

    public String getContractCode() {
        return this.contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Column(name = "ENTRUST_COMPANY_ID", length = 500)

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

    @Column(name = "CHECK_AREA", length = 10)

    public String getCheckArea() {
        return this.checkArea;
    }

    public void setCheckArea(String checkArea) {
        this.checkArea = checkArea;
    }

    @Column(name = "PAYMENT_NODE", length = 11)

    public String getPaymentNode() {
        return this.paymentNode;
    }

    public void setPaymentNode(String paymentNode) {
        this.paymentNode = paymentNode;
    }

    @Column(name = "CONTRACT_CHAPTER", length = 20)

    public String getContractChapter() {
        return this.contractChapter;
    }

    public void setContractChapter(String contractChapter) {
        this.contractChapter = contractChapter;
    }

    @Column(name = "LINK_MAN", length = 30)

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "LINK_PHONE", length = 13)

    public String getLinkPhone() {
        return this.linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Column(name = "PROJECT_MANAGER", length = 30)

    public String getProjectManager() {
        return this.projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    @Column(name = "CONTRACT_IS_RETURN", length = 4)

    public String getContractIsReturn() {
        return this.contractIsReturn;
    }

    public void setContractIsReturn(String contractIsReturn) {
        this.contractIsReturn = contractIsReturn;
    }

    @Column(name = "MANAGEMENT_DEPARTMENT_ID", length = 32)

    public String getManagementDepartmentId() {
        return this.managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    @Column(name = "CONTRACT_TYPE", length = 32)

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Column(name = "ACCOUNT_TYPE", length = 32)

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Column(name = "RECEIVED_MONEY", scale = 4)

    public Double getReceivedMoney() {
        return this.receivedMoney;
    }

    public void setReceivedMoney(Double receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    @Column(name = "CONTRACT_MONEY", scale = 4)

    public Double getContractMoney() {
        return this.contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
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

    @Column(name = "COPIES_COUNT", length = 3)
    public String getCopiesCount() {
        return copiesCount;
    }

    public void setCopiesCount(String copiesCount) {
        this.copiesCount = copiesCount;
    }

    @Column(name = "PROJECT_NAME_TAG", length = 1000)
    public String getProjectNameTag() {
        return projectNameTag;
    }

    public void setProjectNameTag(String projectNameTag) {
        this.projectNameTag = projectNameTag;
    }


}