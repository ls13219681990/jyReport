package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractCapitalAccountDetail entity provides the base persistence definition of the CapitalAccountDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAPITAL_ACCOUNT_DETAIL")
public class CapitalAccountDetail extends BaseBean implements java.io.Serializable {


    // Fields    

    private String capitalAccountDetailId;
    private String contractId;
    private String testProject;
    private String testType;
    private String computingUnit;
    private Double price;
    private Double realPrice;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public CapitalAccountDetail() {
    }

    /**
     * minimal constructor
     */
    public CapitalAccountDetail(String capitalAccountDetailId) {
        this.capitalAccountDetailId = capitalAccountDetailId;
    }

    /**
     * full constructor
     */
    public CapitalAccountDetail(String capitalAccountDetailId, String contractId, String testProject, String testType, String computingUnit, Double price, Double realPrice, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.capitalAccountDetailId = capitalAccountDetailId;
        this.contractId = contractId;
        this.testProject = testProject;
        this.testType = testType;
        this.computingUnit = computingUnit;
        this.price = price;
        this.realPrice = realPrice;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "CAPITAL_ACCOUNT_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getCapitalAccountDetailId() {
        return this.capitalAccountDetailId;
    }

    public void setCapitalAccountDetailId(String capitalAccountDetailId) {
        this.capitalAccountDetailId = capitalAccountDetailId;
    }

    @Column(name = "CONTRACT_ID", length = 32)

    public String getContractId() {
        return this.contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Column(name = "TEST_PROJECT", length = 32)

    public String getTestProject() {
        return this.testProject;
    }

    public void setTestProject(String testProject) {
        this.testProject = testProject;
    }

    @Column(name = "TEST_TYPE", length = 32)

    public String getTestType() {
        return this.testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Column(name = "COMPUTING_UNIT", length = 6)

    public String getComputingUnit() {
        return this.computingUnit;
    }

    public void setComputingUnit(String computingUnit) {
        this.computingUnit = computingUnit;
    }

    @Column(name = "PRICE", scale = 4)

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "REAL_PRICE", scale = 4)

    public Double getRealPrice() {
        return this.realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
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


}