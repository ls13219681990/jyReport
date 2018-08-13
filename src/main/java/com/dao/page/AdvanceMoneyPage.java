package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdvanceMoneyPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustAdvanceId;
    private String entrustCompanyId;//委托单位ID
    private String entrustCompanyName;//委托单位名称
    private String entrustCompanyNo;//委托单位编号
    private String projectId;//工程ID
    private String projectName;//工程名称
    private Double advanceMoney;//金额
    private String advanceStatus;//预收款状态
    private String advanceStatusName;//预收款状态名称
    private String remark;//备注
    private String inputer;//录入人
    private String inputerName;//录入人姓名
    private String inputeTime;
    private String updater;
    private String updateTime;


    public String getEntrustAdvanceId() {
        return entrustAdvanceId;
    }

    public void setEntrustAdvanceId(String entrustAdvanceId) {
        this.entrustAdvanceId = entrustAdvanceId;
    }

    public String getEntrustCompanyId() {
        return entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getEntrustCompanyNo() {
        return entrustCompanyNo;
    }

    public void setEntrustCompanyNo(String entrustCompanyNo) {
        this.entrustCompanyNo = entrustCompanyNo;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(Double advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getAdvanceStatus() {
        return advanceStatus;
    }

    public void setAdvanceStatus(String advanceStatus) {
        this.advanceStatus = advanceStatus;
    }

    public String getAdvanceStatusName() {
        return advanceStatusName;
    }

    public void setAdvanceStatusName(String advanceStatusName) {
        this.advanceStatusName = advanceStatusName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getInputeTime() {
        return inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
