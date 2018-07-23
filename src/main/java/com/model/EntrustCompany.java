package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustCompany entity provides the base persistence definition of the EntrustCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_COMPANY")
public class EntrustCompany extends BaseBean implements java.io.Serializable {


    // Fields    

    private String entrustCompanyId;
    private String entrustCompanyName;
    private String entrustCompanyNo;
    private Double advancesReceived;
    private String address;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public EntrustCompany() {
    }

    /**
     * minimal constructor
     */
    public EntrustCompany(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    /**
     * full constructor
     */
    public EntrustCompany(String entrustCompanyId, String entrustCompanyName, String entrustCompanyNo, Double advancesReceived, String address, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.entrustCompanyId = entrustCompanyId;
        this.entrustCompanyName = entrustCompanyName;
        this.entrustCompanyNo = entrustCompanyNo;
        this.advancesReceived = advancesReceived;
        this.address = address;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "ENTRUST_COMPANY_ID", unique = true, nullable = false, length = 32)

    public String getEntrustCompanyId() {
        return this.entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    @Column(name = "ENTRUST_COMPANY_NAME", length = 80)

    public String getEntrustCompanyName() {
        return this.entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    @Column(name = "ENTRUST_COMPANY_NO", length = 10)

    public String getEntrustCompanyNo() {
        return this.entrustCompanyNo;
    }

    public void setEntrustCompanyNo(String entrustCompanyNo) {
        this.entrustCompanyNo = entrustCompanyNo;
    }

    @Column(name = "ADVANCES_RECEIVED", scale = 4)

    public Double getAdvancesReceived() {
        return this.advancesReceived;
    }

    public void setAdvancesReceived(Double advancesReceived) {
        this.advancesReceived = advancesReceived;
    }

    @Column(name = "ADDRESS", length = 100)

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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