package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractAmountReceivable entity provides the base persistence definition of the AmountReceivable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AMOUNT_RECEIVABLE")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AmountReceivable extends BaseBean implements java.io.Serializable {


    // Fields    

    private String amountReceivableId;
    private String entrustCompanyId;
    private String projectId;
    private Double receivableMoney;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public AmountReceivable() {
    }

    /**
     * minimal constructor
     */
    public AmountReceivable(String amountReceivableId) {
        this.amountReceivableId = amountReceivableId;
    }

    /**
     * full constructor
     */
    public AmountReceivable(String amountReceivableId, String entrustCompanyId, String projectId, Double receivableMoney, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.amountReceivableId = amountReceivableId;
        this.entrustCompanyId = entrustCompanyId;
        this.projectId = projectId;
        this.receivableMoney = receivableMoney;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "AMOUNT_RECEIVABLE_ID", unique = true, nullable = false, length = 32)

    public String getAmountReceivableId() {
        return this.amountReceivableId;
    }

    public void setAmountReceivableId(String amountReceivableId) {
        this.amountReceivableId = amountReceivableId;
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

    @Column(name = "RECEIVABLE_MONEY", scale = 4)

    public Double getReceivableMoney() {
        return this.receivableMoney;
    }

    public void setReceivableMoney(Double receivableMoney) {
        this.receivableMoney = receivableMoney;
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