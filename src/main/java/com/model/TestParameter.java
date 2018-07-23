package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractTestParameter entity provides the base persistence definition of the TestParameter entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "TEST_PARAMETER")
public class TestParameter extends BaseBean implements java.io.Serializable {


    // Fields    

    private String testParameterId;
    private String sampleId;
    private String testParameterName;
    private String unit;
    private Double unitPrice;
    private String parentParameterId;
    private String templateName;
    private String templatePath;
    private String parameterType;
    private String snRule;
    private String parameterStatus;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public TestParameter() {
    }

    /**
     * minimal constructor
     */
    public TestParameter(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    /**
     * full constructor
     */
    public TestParameter(String testParameterId, String sampleId, String testParameterName, String unit, Double unitPrice, String parentParameterId, String templateName, String templatePath, String parameterType, String snRule, String parameterStatus, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.testParameterId = testParameterId;
        this.sampleId = sampleId;
        this.testParameterName = testParameterName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.parentParameterId = parentParameterId;
        this.templateName = templateName;
        this.templatePath = templatePath;
        this.parameterType = parameterType;
        this.snRule = snRule;
        this.parameterStatus = parameterStatus;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "TEST_PARAMETER_ID", unique = true, nullable = false, length = 32)

    public String getTestParameterId() {
        return this.testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    @Column(name = "SAMPLE_ID", length = 32)

    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "TEST_PARAMETER_NAME", length = 200)

    public String getTestParameterName() {
        return this.testParameterName;
    }

    public void setTestParameterName(String testParameterName) {
        this.testParameterName = testParameterName;
    }

    @Column(name = "UNIT", length = 6)

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "UNIT_PRICE", scale = 4)

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "PARENT_PARAMETER_ID", length = 32)

    public String getParentParameterId() {
        return this.parentParameterId;
    }

    public void setParentParameterId(String parentParameterId) {
        this.parentParameterId = parentParameterId;
    }

    @Column(name = "TEMPLATE_NAME", length = 100)

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Column(name = "TEMPLATE_PATH", length = 200)

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Column(name = "PARAMETER_TYPE", length = 2)

    public String getParameterType() {
        return this.parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @Column(name = "SN_RULE", length = 50)

    public String getSnRule() {
        return this.snRule;
    }

    public void setSnRule(String snRule) {
        this.snRule = snRule;
    }

    @Column(name = "PARAMETER_STATUS", length = 2)

    public String getParameterStatus() {
        return this.parameterStatus;
    }

    public void setParameterStatus(String parameterStatus) {
        this.parameterStatus = parameterStatus;
    }

    @Column(name = "REMARK", length = 100)

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