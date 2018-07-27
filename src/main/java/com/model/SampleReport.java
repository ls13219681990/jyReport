package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysRoleFunction entity provides the base persistence definition of the SysRoleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SAMPLE_REPORT")
public class SampleReport extends BaseBean implements java.io.Serializable {


    // Fields    

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
    private String sampleState;
    private String sampleTestDate;


    // Constructors

    /**
     * default constructor
     */
    public SampleReport() {
    }

    /**
     * minimal constructor
     */
    public SampleReport(String sampleReportId) {
        this.sampleReportId = sampleReportId;
    }

    /**
     * full constructor
     */
    public SampleReport(String sampleReportId, String reportId, String entrustDetailId, String sampleNo, String invalidStatus, String sampleId, String sampleName, String projectPart, String moldingDate, String sampleAge, String sampleLevel, String sampleSource, String sampleType, String entrustId, String sampleState, String sampleTestDate) {
        this.sampleReportId = sampleReportId;
        this.reportId = reportId;
        this.entrustDetailId = entrustDetailId;
        this.sampleNo = sampleNo;
        this.invalidStatus = invalidStatus;
        this.sampleId = sampleId;
        this.sampleName = sampleName;
        this.projectPart = projectPart;
        this.moldingDate = moldingDate;
        this.sampleAge = sampleAge;
        this.sampleLevel = sampleLevel;
        this.sampleSource = sampleSource;
        this.sampleType = sampleType;
        this.entrustId = entrustId;
        this.sampleState = sampleState;
        this.sampleTestDate = sampleTestDate;
    }


    // Property accessors
    @Id

    @Column(name = "SAMPLE_REPORT_ID", unique = true, nullable = false, length = 32)

    public String getSampleReportId() {
        return this.sampleReportId;
    }

    public void setSampleReportId(String sampleReportId) {
        this.sampleReportId = sampleReportId;
    }

    @Column(name = "REPORT_ID", length = 32)
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Column(name = "ENTRUST_DETAIL_ID", length = 32)
    public String getEntrustDetailId() {
        return entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    @Column(name = "SAMPLE_NO", length = 32)
    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "INVALID_STATUS", length = 6)
    public String getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(String invalidStatus) {
        this.invalidStatus = invalidStatus;
    }

    @Column(name = "SAMPLE_ID", length = 32)

    public String getSampleId() {
        return this.sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "SAMPLE_NAME", length = 50)
    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    @Column(name = "PROJECT_PART", length = 1000)

    public String getProjectPart() {
        return this.projectPart;
    }

    public void setProjectPart(String projectPart) {
        this.projectPart = projectPart;
    }

    @Column(name = "MOLDING_DATE", length = 11)

    public String getMoldingDate() {
        return this.moldingDate;
    }

    public void setMoldingDate(String moldingDate) {
        this.moldingDate = moldingDate;
    }

    @Column(name = "SAMPLE_AGE", length = 20)

    public String getSampleAge() {
        return this.sampleAge;
    }

    public void setSampleAge(String sampleAge) {
        this.sampleAge = sampleAge;
    }

    @Column(name = "SAMPLE_LEVEL", length = 120)
    public String getSampleLevel() {
        return sampleLevel;
    }

    public void setSampleLevel(String sampleLevel) {
        this.sampleLevel = sampleLevel;
    }

    @Column(name = "SAMPLE_TYPE", length = 6)
    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    @Column(name = "SAMPLE_SOURCE", length = 50)

    public String getSampleSource() {
        return this.sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    @Column(name = "ENTRUST_ID", length = 32)
    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Column(name = "SAMPLE_STATE", length = 6)
    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
    }

    @Column(name = "SAMPLE_TEST_DATE", length = 11)
    public String getSampleTestDate() {
        return sampleTestDate;
    }

    public void setSampleTestDate(String sampleTestDate) {
        this.sampleTestDate = sampleTestDate;
    }


}