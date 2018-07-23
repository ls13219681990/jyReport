package com.dao.page;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class EntrustDetailSavePage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustMDetailId;//委托金额明细ID
    private String entrustDetailId;//委托明细ID
    private String testParameterId;//检测参数ID
    private String testParameterName;//检测参数名称
    private String unitPrice;//单价
    private String sampleSource;//价格来源
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private Integer testCount;


    public String getEntrustMDetailId() {
        return entrustMDetailId;
    }

    public void setEntrustMDetailId(String entrustMDetailId) {
        this.entrustMDetailId = entrustMDetailId;
    }

    public String getEntrustDetailId() {
        return entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    public String getTestParameterId() {
        return testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    public String getTestParameterName() {
        return testParameterName;
    }

    public void setTestParameterName(String testParameterName) {
        this.testParameterName = testParameterName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
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

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }
}
