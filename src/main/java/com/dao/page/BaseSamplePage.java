package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseSamplePage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

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

    private List<TemplateInfoPage> tInfoPage = new ArrayList<TemplateInfoPage>();

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSpecificationsModels() {
        return specificationsModels;
    }

    public void setSpecificationsModels(String specificationsModels) {
        this.specificationsModels = specificationsModels;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
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

    public String getSampleLevel() {
        return sampleLevel;
    }

    public void setSampleLevel(String sampleLevel) {
        this.sampleLevel = sampleLevel;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getSnRule() {
        return snRule;
    }

    public void setSnRule(String snRule) {
        this.snRule = snRule;
    }

    public List<TemplateInfoPage> gettInfoPage() {
        return tInfoPage;
    }

    public void settInfoPage(List<TemplateInfoPage> tInfoPage) {
        this.tInfoPage = tInfoPage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
