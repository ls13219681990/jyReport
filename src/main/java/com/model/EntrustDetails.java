package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustDetails entity provides the base persistence definition of the EntrustDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_DETAILS")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String entrustDetailId;
    private String entrustId;
    private String departmentId;
    private String sampleId;
    private Integer reportNum;
    private String processStatus;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String standards;
    private String witnessMan;
    private Double realTotalPrice;
    private String sampleSource;
    private String sampleType;


    // Constructors

    /**
     * default constructor
     */
    public EntrustDetails() {
    }

    /**
     * minimal constructor
     */
    public EntrustDetails(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    /**
     * full constructor
     */
    public EntrustDetails(String entrustDetailId, String entrustId, String departmentId, String sampleId, Integer reportNum, String processStatus, String remark, String inputer, String inputeTime, String updater, String updateTime, String standards, String witnessMan, Double realTotalPrice, String sampleSource, String sampleType) {
        this.entrustDetailId = entrustDetailId;
        this.entrustId = entrustId;
        this.departmentId = departmentId;
        this.sampleId = sampleId;
        this.reportNum = reportNum;
        this.processStatus = processStatus;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.standards = standards;
        this.witnessMan = witnessMan;
        this.realTotalPrice = realTotalPrice;
        this.sampleSource = sampleSource;
        this.sampleType = sampleType;
    }


    // Property accessors
    @Id

    @Column(name = "ENTRUST_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getEntrustDetailId() {
        return this.entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    @Column(name = "ENTRUST_ID", length = 32)

    public String getEntrustId() {
        return this.entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Column(name = "DEPARTMENT_ID", length = 32)

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "SAMPLE_ID", length = 32)

    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "REPORT_NUM")

    public Integer getReportNum() {
        return this.reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    @Column(name = "PROCESS_STATUS", length = 3)

    public String getProcessStatus() {
        return this.processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
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

    @Column(name = "STANDARDS", length = 4000)
    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    @Column(name = "WITNESS_MAN", length = 60)
    public String getWitnessMan() {
        return this.witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    @Column(name = "REAL_TOTAL_PRICE", scale = 4)
    public Double getRealTotalPrice() {
        return this.realTotalPrice;
    }

    public void setRealTotalPrice(Double realTotalPrice) {
        this.realTotalPrice = realTotalPrice;
    }

    @Column(name = "SAMPLE_SOURCE", length = 50)

    public String getSampleSource() {
        return this.sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    @Column(name = "SAMPLE_TYPE", length = 6)
    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

}