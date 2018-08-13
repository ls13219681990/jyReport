package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractProjectInfo entity provides the base persistence definition of the ProjectInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_INFO")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProjectInfo extends BaseBean implements java.io.Serializable {


    // Fields    

    private String projectId;
    private String projectName;
    private String projectAddress;
    private String linkMan;
    private String linkPhone;
    private Double amountReceivable;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public ProjectInfo() {
    }

    /**
     * minimal constructor
     */
    public ProjectInfo(String projectId) {
        this.projectId = projectId;
    }

    /**
     * full constructor
     */
    public ProjectInfo(String projectId, String projectName, String projectAddress, String linkMan, String linkPhone, Double amountReceivable, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.linkMan = linkMan;
        this.linkPhone = linkPhone;
        this.amountReceivable = amountReceivable;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "PROJECT_ID", unique = true, nullable = false, length = 32)

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "PROJECT_NAME", length = 500)

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "PROJECT_ADDRESS", length = 500)

    public String getProjectAddress() {
        return this.projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    @Column(name = "LINK_MAN", length = 20)

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "LINK_PHONE", length = 15)

    public String getLinkPhone() {
        return this.linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    @Column(name = "AMOUNT_RECEIVABLE", scale = 4)

    public Double getAmountReceivable() {
        return this.amountReceivable;
    }

    public void setAmountReceivable(Double amountReceivable) {
        this.amountReceivable = amountReceivable;
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