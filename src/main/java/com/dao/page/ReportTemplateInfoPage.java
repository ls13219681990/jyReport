package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ReportTemplateInfoPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String reportId;//报告ID
    private String reportNo;//报告编号
    private String reportName;//报告名称
    private String entrustCompanyId;//委托单位ID
    private String entrustCompanyName;//委托单位名称
    private String projectId;//工程ID
    private String projectName;//工程名称
    private String entrustSn;//委托编号
    private String entrustDate;//委托日期
    private String supervisionUnitId;//见证单位ID
    private String supervisionUnitName;//见证单位名称
    private String witnessMan;//见证人
    private String projectParts;//工程部位
    private String sampleSource;//样品来源
    private String sampleSourceName;//样品来源名称
    private String sampleId;//样品ID
    private String sampleName;//样品名称
    private String sampleNo;//样品编号
    private String specificationsModels;//规格型号
    private String standards;//依据标准
    private String sampleTestDate;//检测日期
    private String sampleFeeder;//送样人
    private String testResult;//检测结果
    private String testParameter;//检测项目
    private String reportDate;//报告日期
    private String qcNumber;//质量监督号

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
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

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getSupervisionUnitId() {
        return supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
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

    public String getProjectParts() {
        return projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
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

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
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

    public String getSampleSourceName() {
        return sampleSourceName;
    }

    public void setSampleSourceName(String sampleSourceName) {
        this.sampleSourceName = sampleSourceName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getSampleTestDate() {
        return sampleTestDate;
    }

    public void setSampleTestDate(String sampleTestDate) {
        this.sampleTestDate = sampleTestDate;
    }

    public String getSampleFeeder() {
        return sampleFeeder;
    }

    public void setSampleFeeder(String sampleFeeder) {
        this.sampleFeeder = sampleFeeder;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestParameter() {
        return testParameter;
    }

    public void setTestParameter(String testParameter) {
        this.testParameter = testParameter;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(String qcNumber) {
        this.qcNumber = qcNumber;
    }
}
