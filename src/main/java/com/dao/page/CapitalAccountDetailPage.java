package com.dao.page;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class CapitalAccountDetailPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String capitalAccountDetailId;
    //    private String capitalAccountId;
    private String contractId;//合同ID
    private String testProject;//样品ID
    private String testProjectName;//样品名称
    private String testType;//参数ID
    private String testTypeName;//参数名称
    private String computingUnit;//计量单位
    private String price;
    private Double realPrice;//价格
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String department;
    private String sampleId;//样品ID
    private String sampleName;//样品名称
    private String unit;//计量单位
    private String contractPrice;//合同执行价格


    public String getCapitalAccountDetailId() {
        return capitalAccountDetailId;
    }

    public void setCapitalAccountDetailId(String capitalAccountDetailId) {
        this.capitalAccountDetailId = capitalAccountDetailId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTestProject() {
        return testProject;
    }

    public void setTestProject(String testProject) {
        this.testProject = testProject;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getComputingUnit() {
        return computingUnit;
    }

    public void setComputingUnit(String computingUnit) {
        this.computingUnit = computingUnit;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
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

    public String getTestProjectName() {
        return testProjectName;
    }

    public void setTestProjectName(String testProjectName) {
        this.testProjectName = testProjectName;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }


}
