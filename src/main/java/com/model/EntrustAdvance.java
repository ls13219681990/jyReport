package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustAdvance entity provides the base persistence definition of the EntrustAdvance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_ADVANCE")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustAdvance extends BaseBean implements java.io.Serializable {


    // Fields    

    private String entrustAdvanceId;
    private String entrustCompanyId;
    private String projectId;
    private Double advanceMoney;
    private String advanceStatus;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public EntrustAdvance() {
    }

    /**
     * minimal constructor
     */
    public EntrustAdvance(String entrustAdvanceId) {
        this.entrustAdvanceId = entrustAdvanceId;
    }

    /**
     * full constructor
     */
    public EntrustAdvance(String entrustAdvanceId, String entrustCompanyId, String projectId, Double advanceMoney, String advanceStatus, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.entrustAdvanceId = entrustAdvanceId;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.advanceMoney = advanceMoney;
        this.advanceStatus = advanceStatus;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "ENTRUST_ADVANCE_ID", unique = true, nullable = false, length = 32)

    public String getEntrustAdvanceId() {
        return this.entrustAdvanceId;
    }

    public void setEntrustAdvanceId(String entrustAdvanceId) {
        this.entrustAdvanceId = entrustAdvanceId;
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

    @Column(name = "ADVANCE_MONEY", scale = 4)

    public Double getAdvanceMoney() {
        return this.advanceMoney;
    }

    public void setAdvanceMoney(Double advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    @Column(name = "ADVANCE_STATUS", length = 2)

    public String getAdvanceStatus() {
        return this.advanceStatus;
    }

    public void setAdvanceStatus(String advanceStatus) {
        this.advanceStatus = advanceStatus;
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