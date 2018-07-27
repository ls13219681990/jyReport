package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractTestReportInfo entity provides the base persistence definition of the TestReportInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEMPLATE_LOCATION")
public class TemplateLocation extends BaseBean implements java.io.Serializable {


    // Fields    

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
    private String excludeConfig;
    private String pageRowCount;

    // Constructors

    /**
     * default constructor
     */
    public TemplateLocation() {
    }

    /**
     * minimal constructor
     */
    public TemplateLocation(String locationId) {
        this.locationId = locationId;
    }

    /**
     * full constructor
     */
    public TemplateLocation(String locationId, String templateInfoId, String reportNo, String qrCode,
                            String reportName, String entrustSn, String entrustCompanyName, String projectName,
                            String entrustDate, String projectParts, String testDate, String supervisionUnitName,
                            String witnessMan, String parameterName, String sampleName, String sampleSourceName,
                            String reportDate, String standards, String sampleNo, String sampleState, String entrustLinkMan,
                            String testAge, String moldingDate, String smapleLevel, String testResult, String tester,
                            String auditor, String approver, String testData, String qcNumber, String multiSample,
                            String cellSpan, String qrCellSpan, String testResultArrayMode, String excludeConfig, String pageRowCount) {
        this.locationId = locationId;
        this.templateInfoId = templateInfoId;
        this.reportNo = reportNo;
        this.qrCode = qrCode;
        this.reportName = reportName;
        this.entrustSn = entrustSn;
        this.entrustCompanyName = entrustCompanyName;
        this.projectName = projectName;
        this.entrustDate = entrustDate;
        this.projectParts = projectParts;
        this.testDate = testDate;
        this.supervisionUnitName = supervisionUnitName;
        this.witnessMan = witnessMan;
        this.parameterName = parameterName;
        this.sampleName = sampleName;
        this.sampleSourceName = sampleSourceName;
        this.reportDate = reportDate;
        this.standards = standards;
        this.sampleNo = sampleNo;
        this.sampleState = sampleState;
        this.entrustLinkMan = entrustLinkMan;
        this.testAge = testAge;
        this.moldingDate = moldingDate;
        this.smapleLevel = smapleLevel;
        this.testResult = testResult;
        this.tester = tester;
        this.auditor = auditor;
        this.approver = approver;
        this.testData = testData;
        this.qcNumber = qcNumber;
        this.multiSample = multiSample;
        this.cellSpan = cellSpan;
        this.qrCellSpan = qrCellSpan;
        this.testResultArrayMode = testResultArrayMode;
        this.excludeConfig = excludeConfig;
        this.pageRowCount = pageRowCount;
    }


    // Property accessors
    @Id

    @Column(name = "LOCATION_ID", unique = true, nullable = false, length = 32)

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Column(name = "TEMPLATE_INFO_ID", length = 32)
    public String getTemplateInfoId() {
        return templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    @Column(name = "REPORT_NO", length = 40)
    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    @Column(name = "QR_CODE", length = 40)
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Column(name = "REPORT_NAME", length = 40)
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Column(name = "ENTRUST_SN", length = 40)
    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    @Column(name = "ENTRUST_COMPANY_NAME", length = 40)
    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    @Column(name = "PROJECT_NAME", length = 40)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "ENTRUST_DATE", length = 100)
    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    @Column(name = "PROJECT_PART", length = 40)
    public String getProjectParts() {
        return projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    @Column(name = "TEST_DATE", length = 40)
    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    @Column(name = "SUPERVISION_UNIT_NAME", length = 40)
    public String getSupervisionUnitName() {
        return supervisionUnitName;
    }

    public void setSupervisionUnitName(String supervisionUnitName) {
        this.supervisionUnitName = supervisionUnitName;
    }

    @Column(name = "WITNESSES", length = 40)
    public String getWitnessMan() {
        return witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    @Column(name = "PARAMETER_NAME", length = 120)
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Column(name = "SAMPLE_NAME", length = 120)
    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    @Column(name = "SAMPLE_SOURCE_NAME", length = 120)
    public String getSampleSourceName() {
        return sampleSourceName;
    }

    public void setSampleSourceName(String sampleSourceName) {
        this.sampleSourceName = sampleSourceName;
    }

    @Column(name = "REPORT_DATE", length = 40)
    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @Column(name = "STANDARDS", length = 40)
    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    @Column(name = "SAMPLE_NO", length = 120)
    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_STATE", length = 40)
    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
    }

    @Column(name = "ENTRUST_LINK_MAN", length = 40)
    public String getEntrustLinkMan() {
        return entrustLinkMan;
    }

    public void setEntrustLinkMan(String entrustLinkMan) {
        this.entrustLinkMan = entrustLinkMan;
    }

    @Column(name = "TEST_AGE", length = 40)
    public String getTestAge() {
        return testAge;
    }

    public void setTestAge(String testAge) {
        this.testAge = testAge;
    }

    @Column(name = "MOLDING_DATE", length = 40)
    public String getMoldingDate() {
        return moldingDate;
    }

    public void setMoldingDate(String moldingDate) {
        this.moldingDate = moldingDate;
    }

    @Column(name = "SAMPLE_LEVEL", length = 40)
    public String getSmapleLevel() {
        return smapleLevel;
    }

    public void setSmapleLevel(String smapleLevel) {
        this.smapleLevel = smapleLevel;
    }

    @Column(name = "TEST_RESULT", length = 40)
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "TESTER", length = 40)
    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    @Column(name = "AUDITOR", length = 40)
    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    @Column(name = "APPROVER", length = 40)
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Column(name = "TEST_DATA", length = 400)
    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    @Column(name = "QC_NUMBER", length = 40)
    public String getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(String qcNumber) {
        this.qcNumber = qcNumber;
    }

    @Column(name = "MULTI_SAMPLE", length = 4)
    public String getMultiSample() {
        return multiSample;
    }

    public void setMultiSample(String multiSample) {
        this.multiSample = multiSample;
    }

    @Column(name = "CELL_SPAN", length = 100)
    public String getCellSpan() {
        return cellSpan;
    }

    public void setCellSpan(String cellSpan) {
        this.cellSpan = cellSpan;
    }

    @Column(name = "QR_CELL_SPAN", length = 200)
    public String getQrCellSpan() {
        return qrCellSpan;
    }

    public void setQrCellSpan(String qrCellSpan) {
        this.qrCellSpan = qrCellSpan;
    }

    @Column(name = "TEST_RESULT_ARRAY_MODE", length = 4)
    public String getTestResultArrayMode() {
        return testResultArrayMode;
    }

    public void setTestResultArrayMode(String testResultArrayMode) {
        this.testResultArrayMode = testResultArrayMode;
    }

    @Column(name = "EXCLUDE_CONFIG", length = 300)
    public String getExcludeConfig() {
        return excludeConfig;
    }

    public void setExcludeConfig(String excludeConfig) {
        this.excludeConfig = excludeConfig;
    }

    @Column(name = "PAGE_ROW_COUNT", length = 150)
    public String getPageRowCount() {
        return pageRowCount;
    }

    public void setPageRowCount(String pageRowCount) {
        this.pageRowCount = pageRowCount;
    }

}