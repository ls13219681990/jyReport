package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class TestReportInfoPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String reportId;
    private String entrustDetailId;
    private String reportNo;
    private String reportDate;
    private String reportName;
    private String reportPath;
    private String reportStatus;
    private String reportStatusName;//报告状态名称
    private String sampleNo;
    private String projectParts;
    private String testResult;
    private String reportConclusion;
    private Integer printNum;
    private String inputer;
    private String inputeName;//录入人姓名
    private String inputeTime;
    private String updater;
    private String updateName;//更新人姓名
    private String updateTime;
    private String auditor;
    private String auditorName;//审核人姓名
    private String auditTime;
    private String approver;
    private String approverName;//批准人姓名
    private String approveTime;
    private String distribute;
    private String distributeName;//发放人姓名
    private String distributeTime;
    private String templateName;//模板名称
    private String templatePath;//模板路径
    private String twoDInfoUrl;//二维码路径
    private String approverSignPath;//批准人签名路径
    private String auditSignPath;//审核人签名路径
    private String inputerSignPath;//录入人签名路径
    private String tester;//检测人
    private String testerName;//检测人姓名
    private String testerSignPath;//检测人签名路径
    private String qcNumber;//质量监督号
    private String reportCompanyName;//报告单位名称
    private String reportProjectName;//报告工程名称
    private String reportTestData;//报告检测数据
    private String templateInfoId;//模板信息ID
    private String reportRemark;//备注信息
    private String EntrustNumber;
    private String entrustStatus;//委托状态
    private String isComplementally;//是否补办
    private String entrustStatusName;//委托状态名称


    private Integer pageNo;

    private Integer pageSize;

    private Integer totalCount;

    private String sourcesReportId;


    private String sampleTestDate;


    private List<TemplateInfoPage> templateInfoList;//样品模板

    private List<SampleReportPage> sampleReportList = new ArrayList<SampleReportPage>();


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

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportStatusName() {
        return reportStatusName;
    }

    public void setReportStatusName(String reportStatusName) {
        this.reportStatusName = reportStatusName;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getProjectParts() {
        return projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getReportConclusion() {
        return reportConclusion;
    }

    public void setReportConclusion(String reportConclusion) {
        this.reportConclusion = reportConclusion;
    }

    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
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

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getDistribute() {
        return distribute;
    }

    public void setDistribute(String distribute) {
        this.distribute = distribute;
    }

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTwoDInfoUrl() {
        return twoDInfoUrl;
    }

    public void setTwoDInfoUrl(String twoDInfoUrl) {
        this.twoDInfoUrl = twoDInfoUrl;
    }

    public String getApproverSignPath() {
        return approverSignPath;
    }

    public void setApproverSignPath(String approverSignPath) {
        this.approverSignPath = approverSignPath;
    }

    public String getAuditSignPath() {
        return auditSignPath;
    }

    public void setAuditSignPath(String auditSignPath) {
        this.auditSignPath = auditSignPath;
    }

    public String getInputeName() {
        return inputeName;
    }

    public void setInputeName(String inputeName) {
        this.inputeName = inputeName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getDistributeName() {
        return distributeName;
    }

    public void setDistributeName(String distributeName) {
        this.distributeName = distributeName;
    }

    public String getInputerSignPath() {
        return inputerSignPath;
    }

    public void setInputerSignPath(String inputerSignPath) {
        this.inputerSignPath = inputerSignPath;
    }

    public List<SampleReportPage> getSampleReportList() {
        return sampleReportList;
    }

    public void setSampleReportList(List<SampleReportPage> sampleReportList) {
        this.sampleReportList = sampleReportList;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public String getTesterSignPath() {
        return testerSignPath;
    }

    public void setTesterSignPath(String testerSignPath) {
        this.testerSignPath = testerSignPath;
    }

    public String getQcNumber() {
        return qcNumber;
    }

    public void setQcNumber(String qcNumber) {
        this.qcNumber = qcNumber;
    }

    public String getReportCompanyName() {
        return reportCompanyName;
    }

    public void setReportCompanyName(String reportCompanyName) {
        this.reportCompanyName = reportCompanyName;
    }

    public String getReportProjectName() {
        return reportProjectName;
    }

    public void setReportProjectName(String reportProjectName) {
        this.reportProjectName = reportProjectName;
    }

    public String getReportTestData() {
        return reportTestData;
    }

    public void setReportTestData(String reportTestData) {
        this.reportTestData = reportTestData;
    }

    public List<TemplateInfoPage> getTemplateInfoList() {
        return templateInfoList;
    }

    public void setTemplateInfoList(List<TemplateInfoPage> templateInfoList) {
        this.templateInfoList = templateInfoList;
    }

    public String getTemplateInfoId() {
        return templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    public String getEntrustNumber() {
        return EntrustNumber;
    }

    public void setEntrustNumber(String entrustNumber) {
        EntrustNumber = entrustNumber;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getEntrustStatusName() {
        return entrustStatusName;
    }

    public void setEntrustStatusName(String entrustStatusName) {
        this.entrustStatusName = entrustStatusName;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getIsComplementally() {
        return isComplementally;
    }

    public void setIsComplementally(String isComplementally) {
        this.isComplementally = isComplementally;
    }

    public String getSourcesReportId() {
        return sourcesReportId;
    }

    public void setSourcesReportId(String sourcesReportId) {
        this.sourcesReportId = sourcesReportId;
    }

    public String getSampleTestDate() {
        return sampleTestDate;
    }

    public void setSampleTestDate(String sampleTestDate) {
        this.sampleTestDate = sampleTestDate;
    }

}
