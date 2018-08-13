package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TemplateLocationPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String locationId;
    private String templateInfoId;
    private String reportNo;
    private String qrCode;
    private String reportName;
    private String entrustSn;
    private String entrustCompanyName;
    private String projectName;
    private String entrustDate;
    private String projectParts;
    private String testDate;
    private String supervisionUnitName;
    private String witnessMan;
    private String parameterName;
    private String sampleName;
    private String sampleSourceName;
    private String reportDate;
    private String standards;
    private String sampleNo;
    private String sampleState;
    private String entrustLinkMan;
    private String testAge;
    private String moldingDate;
    private String smapleLevel;
    private String testResult;
    private String tester;
    private String auditor;
    private String approver;
    private String testData;
    private String qcNumber;
    private String multiSample;
    private String cellSpan;
    private String qrCellSpan;
    private String testResultArrayMode;
    private String updateFlag;//模板更新状态
    private String excludeConfig;//区分检测日期
    private String pageRowCount;

    private List<TestDataInfoPage> testDataInfoList = new ArrayList<TestDataInfoPage>();

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getTemplateInfoId() {
        return templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getProjectParts() {
        return projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getSupervisionUnitName() {
        return supervisionUnitName;
    }

    public void setSupervisionUnitName(String supervisionUnitName) {
        this.supervisionUnitName = supervisionUnitName;
    }

    public String getWitnessMan() {
        return witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleSourceName() {
        return sampleSourceName;
    }

    public void setSampleSourceName(String sampleSourceName) {
        this.sampleSourceName = sampleSourceName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
    }

    public String getEntrustLinkMan() {
        return entrustLinkMan;
    }

    public void setEntrustLinkMan(String entrustLinkMan) {
        this.entrustLinkMan = entrustLinkMan;
    }

    public String getTestAge() {
        return testAge;
    }

    public void setTestAge(String testAge) {
        this.testAge = testAge;
    }

    public String getMoldingDate() {
        return moldingDate;
    }

    public void setMoldingDate(String moldingDate) {
        this.moldingDate = moldingDate;
    }

    public String getSmapleLevel() {
        return smapleLevel;
    }

    public void setSmapleLevel(String smapleLevel) {
        this.smapleLevel = smapleLevel;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public List<TestDataInfoPage> getTestDataInfoList() {
        return testDataInfoList;
    }

    public void setTestDataInfoList(List<TestDataInfoPage> testDataInfoList) {
        this.testDataInfoList = testDataInfoList;
    }

    public String getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(String qcNumber) {
        this.qcNumber = qcNumber;
    }

    public String getMultiSample() {
        return multiSample;
    }

    public void setMultiSample(String multiSample) {
        this.multiSample = multiSample;
    }

    public String getCellSpan() {
        return cellSpan;
    }

    public void setCellSpan(String cellSpan) {
        this.cellSpan = cellSpan;
    }

    public String getQrCellSpan() {
        return qrCellSpan;
    }

    public void setQrCellSpan(String qrCellSpan) {
        this.qrCellSpan = qrCellSpan;
    }

    public String getTestResultArrayMode() {
        return testResultArrayMode;
    }

    public void setTestResultArrayMode(String testResultArrayMode) {
        this.testResultArrayMode = testResultArrayMode;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getExcludeConfig() {
        return excludeConfig;
    }

    public void setExcludeConfig(String excludeConfig) {
        this.excludeConfig = excludeConfig;
    }

    public String getPageRowCount() {
        return pageRowCount;
    }

    public void setPageRowCount(String pageRowCount) {
        this.pageRowCount = pageRowCount;
    }

}
