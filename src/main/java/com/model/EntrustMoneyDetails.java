package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustMoneyDetails entity provides the base persistence definition of the EntrustMoneyDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_MONEY_DETAILS")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustMoneyDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String entrustMoneyDetailId;
    private String testParameterId;
    private Double realUnitPrice;
    private String sampleSource;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String entrustDetailId;
    private Integer testCount;


    // Constructors

    /**
     * default constructor
     */
    public EntrustMoneyDetails() {
    }

    /**
     * minimal constructor
     */
    public EntrustMoneyDetails(String entrustMoneyDetailId) {
        this.entrustMoneyDetailId = entrustMoneyDetailId;
    }

    /**
     * full constructor
     */
    public EntrustMoneyDetails(String entrustMoneyDetailId, String testParameterId, Double realUnitPrice, String sampleSource, String remark, String inputer, String inputeTime, String updater, String updateTime, String entrustDetailId, Integer testCount) {
        this.entrustMoneyDetailId = entrustMoneyDetailId;
        this.testParameterId = testParameterId;
        this.realUnitPrice = realUnitPrice;
        this.sampleSource = sampleSource;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.entrustDetailId = entrustDetailId;
        this.testCount = testCount;
    }


    // Property accessors
    @Id

    @Column(name = "ENTRUST_MONEY_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getEntrustMoneyDetailId() {
        return this.entrustMoneyDetailId;
    }

    public void setEntrustMoneyDetailId(String entrustMoneyDetailId) {
        this.entrustMoneyDetailId = entrustMoneyDetailId;
    }

    @Column(name = "TEST_PARAMETER_ID", length = 32)

    public String getTestParameterId() {
        return this.testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    @Column(name = "REAL_UNIT_PRICE", scale = 4)

    public Double getRealUnitPrice() {
        return this.realUnitPrice;
    }

    public void setRealUnitPrice(Double realUnitPrice) {
        this.realUnitPrice = realUnitPrice;
    }

    @Column(name = "SAMPLE_SOURCE", length = 50)

    public String getSampleSource() {
        return this.sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
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

    @Column(name = "ENTRUST_DETAIL_ID", length = 32)
    public String getEntrustDetailId() {
        return this.entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    @Column(name = "TEST_COUNT")
    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }
}