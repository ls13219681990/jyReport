package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户画面用实体类
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SampleReportPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String sampleReportId;
    private String reportId;
    private String entrustDetailId;
    private String sampleNo;
    private String invalidStatus;
    private String sampleId;
    private String sampleName;
    private String projectPart;
    private String moldingDate;
    private String sampleAge;
    private String sampleLevel;
    private String sampleSource;
    private String sampleType;
    private String entrustId;
    private String sampleState;//样品状态
    private String sampleTestDate;//检测日期

    public String getSampleReportId() {
        return sampleReportId;
    }

    public void setSampleReportId(String sampleReportId) {
        this.sampleReportId = sampleReportId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEntrustDetailId() {
        return entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(String invalidStatus) {
        this.invalidStatus = invalidStatus;
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

    public String getProjectPart() {
        return projectPart;
    }

    public void setProjectPart(String projectPart) {
        this.projectPart = projectPart;
    }

    public String getMoldingDate() {
        return moldingDate;
    }

    public void setMoldingDate(String moldingDate) {
        this.moldingDate = moldingDate;
    }

    public String getSampleAge() {
        return sampleAge;
    }

    public void setSampleAge(String sampleAge) {
        this.sampleAge = sampleAge;
    }

    public String getSampleLevel() {
        return sampleLevel;
    }

    public void setSampleLevel(String sampleLevel) {
        this.sampleLevel = sampleLevel;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
    }

    public String getSampleTestDate() {
        return sampleTestDate;
    }

    public void setSampleTestDate(String sampleTestDate) {
        this.sampleTestDate = sampleTestDate;
    }

}
