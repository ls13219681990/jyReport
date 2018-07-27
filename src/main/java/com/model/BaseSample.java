package com.model;


import javax.persistence.*;


/**
 * AbstractBaseSample entity provides the base persistence definition of the BaseSample entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_SAMPLE")
public class BaseSample extends BaseBean implements java.io.Serializable {


    // Fields    

    private String sampleId;
    private String departmentId;
    private String sampleName;
    private String specificationsModels;
    private String standards;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String sampleLevel;
    private String testResult;
    private String snRule;
    private String status;


    // Constructors

    /**
     * default constructor
     */
    public BaseSample() {
    }

    /**
     * minimal constructor
     */
    public BaseSample(String sampleId) {
        this.sampleId = sampleId;
    }

    /**
     * full constructor
     */
    public BaseSample(String sampleId, String departmentId, String sampleName, String specificationsModels,
                      String standards, String remark, String inputer, String inputeTime, String updater,
                      String updateTime, String sampleLevel, String testResult, String snRule, String status) {
        this.sampleId = sampleId;
        this.departmentId = departmentId;
        this.sampleName = sampleName;
        this.specificationsModels = specificationsModels;
        this.standards = standards;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.sampleLevel = sampleLevel;
        this.testResult = testResult;
        this.snRule = snRule;
        this.status = status;
    }


    // Property accessors
    @Id

    @Column(name = "SAMPLE_ID", unique = true, nullable = false, length = 32)

    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "DEPARTMENT_ID", length = 32)

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "SAMPLE_NAME", length = 50)

    public String getSampleName() {
        return this.sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    @Column(name = "SPECIFICATIONS_MODELS", length = 50)

    public String getSpecificationsModels() {
        return this.specificationsModels;
    }

    public void setSpecificationsModels(String specificationsModels) {
        this.specificationsModels = specificationsModels;
    }

    @Column(name = "STANDARDS", length = 4000)

    public String getStandards() {
        return this.standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
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

    @Column(name = "SAMPLE_LEVEL", length = 120)
    public String getSampleLevel() {
        return sampleLevel;
    }

    public void setSampleLevel(String sampleLevel) {
        this.sampleLevel = sampleLevel;
    }

    @Column(name = "TEST_RESULT", length = 500)
    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "SN_RULE", length = 50)
    public String getSnRule() {
        return this.snRule;
    }

    public void setSnRule(String snRule) {
        this.snRule = snRule;
    }

    @Column(name = "STATUS", length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}