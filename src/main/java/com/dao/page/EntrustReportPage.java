package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class EntrustReportPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustDetailId;//委托明细ID
    private String entrustSn;//委托编号
    private String entrustDate;//委托日期
    private String sampleName;//试件名称
    private String registrant;//登记人
    private String registrantName;//登记人姓名
    private String registeredTime;//登记时间
    private String inputer;//录入人
    private String inputeName;//录入人姓名
    private String inputeTime;//录入时间
    private String entrustStatus;//委托状态
    private String entrustStatusName;//委托状态名称
    private String entrustStartDate;//委托开始日期      查询用
    private String entrustEndDate;//委托结束日期      查询用
    private String registrantStartDate;//登记开始日期      查询用
    private String registrantEndDate;//登记结束日期      查询用
    private String inputeStartDate;//录入开始日期      查询用
    private String inputeEndDate;//录入结束日期      查询用
    private String departmentId;//部门ID
    private String isComplementally;//查询是否补报告  00：正办  01：补办
    private String testParameterId;//检测参数ID
    private Integer reportNum;//报告分数
    private String menuStatus;//画面状态：01：录入，编辑    02：审核   03：批准     04：发放
    private String witness;//见证人
    private String sampleLevel;//样品等级
    private Integer printNum;//打印份数
    private String projectName;//工程名称
    private String testResult;//检测结论
    private String entrustCompanyName;//单位名称

    private String inspectionNumber;

    private String inspectionSource;
    //委托报告关系List
    private List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();

    public String getEntrustDetailId() {
        return entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
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

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrantName() {
        return registrantName;
    }

    public void setRegistrantName(String registrantName) {
        this.registrantName = registrantName;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    public String getInputeName() {
        return inputeName;
    }

    public void setInputeName(String inputeName) {
        this.inputeName = inputeName;
    }

    public String getInputeTime() {
        return inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getEntrustStatusName() {
        return entrustStatusName;
    }

    public void setEntrustStatusName(String entrustStatusName) {
        this.entrustStatusName = entrustStatusName;
    }

    public String getEntrustStartDate() {
        return entrustStartDate;
    }

    public void setEntrustStartDate(String entrustStartDate) {
        this.entrustStartDate = entrustStartDate;
    }

    public String getEntrustEndDate() {
        return entrustEndDate;
    }

    public void setEntrustEndDate(String entrustEndDate) {
        this.entrustEndDate = entrustEndDate;
    }

    public String getRegistrantStartDate() {
        return registrantStartDate;
    }

    public void setRegistrantStartDate(String registrantStartDate) {
        this.registrantStartDate = registrantStartDate;
    }

    public String getRegistrantEndDate() {
        return registrantEndDate;
    }

    public void setRegistrantEndDate(String registrantEndDate) {
        this.registrantEndDate = registrantEndDate;
    }

    public String getInputeStartDate() {
        return inputeStartDate;
    }

    public void setInputeStartDate(String inputeStartDate) {
        this.inputeStartDate = inputeStartDate;
    }

    public String getInputeEndDate() {
        return inputeEndDate;
    }

    public void setInputeEndDate(String inputeEndDate) {
        this.inputeEndDate = inputeEndDate;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getIsComplementally() {
        return isComplementally;
    }

    public void setIsComplementally(String isComplementally) {
        this.isComplementally = isComplementally;
    }

    public String getTestParameterId() {
        return testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getSampleLevel() {
        return sampleLevel;
    }

    public void setSampleLevel(String sampleLevel) {
        this.sampleLevel = sampleLevel;
    }

    public List<SampleReportPage> getSrPageList() {
        return srPageList;
    }

    public void setSrPageList(List<SampleReportPage> srPageList) {
        this.srPageList = srPageList;
    }

    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(String inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public String getInspectionSource() {
        return inspectionSource;
    }

    public void setInspectionSource(String inspectionSource) {
        this.inspectionSource = inspectionSource;
    }
}
