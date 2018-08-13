package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustParameterPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //	private String capitalAccountId;//资金账号ID
    private String contractId;//合同ID
    private String departmentId;//部门ID
    private String departmentName;//部门名称
    private String sampleId;//样品ID
    private String sampleName;//样品名称
    private String testParameterId;//检测参数ID
    private String testParameterName;//检测参数名称
    private String unitPrice;//单价
    private String remark;//备注
    private String source;//来源:0:资金账号明细，1：参数明细

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
