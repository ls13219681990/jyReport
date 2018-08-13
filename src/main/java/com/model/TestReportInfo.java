package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AbstractTestReportInfo entity provides the base persistence definition of the
 * TestReportInfo entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "TEST_REPORT_INFO")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TestReportInfo extends BaseBean
        implements java.io.Serializable {

    // Fields

    private String reportId;
    private String reportNo;
    private String reportDate;
    private String reportName;
    private String reportPath;
    private String reportStatus;
    private String sampleNo;
    private String projectParts;
    private String testResult;
    private String reportConclusion;
    private Integer printNum;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String auditor;
    private String auditTime;
    private String approver;
    private String approveTime;
    private String distribute;
    private String distributeTime;
    private String tester;
    private String sampleTestDate;
    private String qcNumber;
    private String reportCompanyName;
    private String reportProjectName;
    private String reportTestData;
    private String templateInfoId;
    private String reportRemark;
    private String sourcesReportId;


    // Constructors

    /**
     * default constructor
     */
    public TestReportInfo() {
    }

    /**
     * minimal constructor
     */
    public TestReportInfo(String reportId) {
        this.reportId = reportId;
    }


    public TestReportInfo(String reportId, String reportNo,
                          String reportDate, String reportName, String reportPath,
                          String reportStatus, String sampleNo, String projectParts,
                          String testResult, String reportConclusion, Integer printNum,
                          String inputer, String inputeTime, String updater,
                          String updateTime, String auditor, String auditTime,
                          String approver, String approveTime, String distribute,
                          String distributeTime, String tester, String sampleTestDate,
                          String qcNumber, String reportCompanyName,
                          String reportProjectName, String reportTestData,
                          String templateInfoId, String reportRemark, String sourcesReportId) {
        super();
        this.reportId = reportId;
        this.reportNo = reportNo;
        this.reportDate = reportDate;
        this.reportName = reportName;
        this.reportPath = reportPath;
        this.reportStatus = reportStatus;
        this.sampleNo = sampleNo;
        this.projectParts = projectParts;
        this.testResult = testResult;
        this.reportConclusion = reportConclusion;
        this.printNum = printNum;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.auditor = auditor;
        this.auditTime = auditTime;
        this.approver = approver;
        this.approveTime = approveTime;
        this.distribute = distribute;
        this.distributeTime = distributeTime;
        this.tester = tester;
        this.sampleTestDate = sampleTestDate;
        this.qcNumber = qcNumber;
        this.reportCompanyName = reportCompanyName;
        this.reportProjectName = reportProjectName;
        this.reportTestData = reportTestData;
        this.templateInfoId = templateInfoId;
        this.reportRemark = reportRemark;
        this.sourcesReportId = sourcesReportId;
    }

    // Property accessors
    @Id
    @Column(name = "REPORT_ID", unique = true, nullable = false, length = 32)
    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Column(name = "REPORT_NO", length = 50)
    public String getReportNo() {
        return this.reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    @Column(name = "REPORT_DATE", length = 10)
    public String getReportDate() {
        return this.reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @Column(name = "REPORT_NAME", length = 100)
    public String getReportName() {
        return this.reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Column(name = "REPORT_PATH", length = 200)
    public String getReportPath() {
        return this.reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Column(name = "REPORT_STATUS", length = 2)
    public String getReportStatus() {
        return this.reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Column(name = "SAMPLE_NO", length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "PROJECT_PARTS", length = 200)
    public String getProjectParts() {
        return this.projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    @Column(name = "TEST_RESULT", length = 500)
    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "REPORT_CONCLUSION", length = 800)
    public String getReportConclusion() {
        return this.reportConclusion;
    }

    public void setReportConclusion(String reportConclusion) {
        this.reportConclusion = reportConclusion;
    }

    @Column(name = "PRINT_NUM")
    public Integer getPrintNum() {
        return this.printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
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

    @Column(name = "AUDITOR", length = 32)
    public String getAuditor() {
        return this.auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    @Column(name = "AUDIT_TIME", length = 19)
    public String getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    @Column(name = "APPROVER", length = 32)
    public String getApprover() {
        return this.approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Column(name = "APPROVE_TIME", length = 19)
    public String getApproveTime() {
        return this.approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    @Column(name = "DISTRIBUTE", length = 32)
    public String getDistribute() {
        return this.distribute;
    }

    public void setDistribute(String distribute) {
        this.distribute = distribute;
    }

    @Column(name = "DISTRIBUTE_TIME", length = 19)
    public String getDistributeTime() {
        return this.distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    @Column(name = "TESTER", length = 32)
    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    @Column(name = "SAMPLE_TEST_DATE", length = 11)
    public String getSampleTestDate() {
        return sampleTestDate;
    }

    public void setSampleTestDate(String sampleTestDate) {
        this.sampleTestDate = sampleTestDate;
    }

    @Column(name = "QC_NUMBER", length = 160)
    public String getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(String qcNumber) {
        this.qcNumber = qcNumber;
    }

    @Column(name = "REPORT_COMPANY_NAME", length = 200)
    public String getReportCompanyName() {
        return reportCompanyName;
    }

    public void setReportCompanyName(String reportCompanyName) {
        this.reportCompanyName = reportCompanyName;
    }

    @Column(name = "REPORT_PROJECT_NAME", length = 500)
    public String getReportProjectName() {
        return reportProjectName;
    }

    public void setReportProjectName(String reportProjectName) {
        this.reportProjectName = reportProjectName;
    }

    @Column(name = "REPORT_TEST_DATA", length = 1000)
    public String getReportTestData() {
        return reportTestData;
    }

    public void setReportTestData(String reportTestData) {
        this.reportTestData = reportTestData;
    }

    @Column(name = "TEMPLATE_INFO_ID", length = 32)
    public String getTemplateInfoId() {
        return templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    @Column(name = "REPORT_REMARK", length = 2000)
    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    @Column(name = "SOURCES_REPORT_ID", length = 32)
    public String getSourcesReportId() {
        return sourcesReportId;
    }

    public void setSourcesReportId(String sourcesReportId) {
        this.sourcesReportId = sourcesReportId;
    }

}