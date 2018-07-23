package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class EntrustSavePage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustId;
    private String entrustSn;
    private String entrustCompanyId;
    private String entrustCompanyName;
    private String projectId;
    private String projectName;
    private String projectRemark;
    private String entrustDate;
    private String supervisionUnitId;
    private String supervisionUnitName;
    private String projectAddress;
    private String linkPhone;
    private String inspectionMan;
    private String witnessMan;
    private String linkMan;
    private String accountType;
    private String accountTypeName;
    private String accountKinds;
    private String accountKindsName;
    private String contractCode;
    private Double accountValue;
    private String managementDepartmentId;
    private String managementDepartmentName;
    private String isComplementally;
    private String entrustStatus;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String entrustStartDate;//委托开始日期      查询用
    private String entrustEndDate;//委托结束日期      查询用
    private String entrustCompanyNo;//委托单位编号      查询用
    private String inputStartDate;//录入开始日期      查询用
    private String inputEndDate;//录入结束日期      查询用
    private String entrustStatusName;//委托状态名称
    private String inputerName;//录入人名称
    private Double involidMoney;//作废后金额
    private int pageNo;//当前页数
    private int pageSize;//一页条数
    private int totalCount;//总条数
    private String contractId;//合同ID
    private String specialRule;//特殊规则，00一般部门，01市政部门
    private String invalid;//是否作废

    //上委托明细LIST
    private List<EntrustDetailReportPage> edReport = new ArrayList<EntrustDetailReportPage>();

    private String printNum;//打印次数

    private String entrustType;//00：不往质监站传，01：往质监站传

    private String isCollections;//00：未收款，01：已收款

    private String isInvoice;//00：未开票，01：已开票

    private String capitalAccountId;//资金账号ID


    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
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

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
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

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getInspectionMan() {
        return inspectionMan;
    }

    public void setInspectionMan(String inspectionMan) {
        this.inspectionMan = inspectionMan;
    }

    public String getWitnessMan() {
        return witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getAccountKinds() {
        return accountKinds;
    }

    public void setAccountKinds(String accountKinds) {
        this.accountKinds = accountKinds;
    }

    public String getAccountKindsName() {
        return accountKindsName;
    }

    public void setAccountKindsName(String accountKindsName) {
        this.accountKindsName = accountKindsName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Double getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(Double accountValue) {
        this.accountValue = accountValue;
    }

    public String getManagementDepartmentId() {
        return managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    public String getManagementDepartmentName() {
        return managementDepartmentName;
    }

    public void setManagementDepartmentName(String managementDepartmentName) {
        this.managementDepartmentName = managementDepartmentName;
    }

    public String getIsComplementally() {
        return isComplementally;
    }

    public void setIsComplementally(String isComplementally) {
        this.isComplementally = isComplementally;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
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

    public String getEntrustCompanyNo() {
        return entrustCompanyNo;
    }

    public void setEntrustCompanyNo(String entrustCompanyNo) {
        this.entrustCompanyNo = entrustCompanyNo;
    }

    public String getInputStartDate() {
        return inputStartDate;
    }

    public void setInputStartDate(String inputStartDate) {
        this.inputStartDate = inputStartDate;
    }

    public String getInputEndDate() {
        return inputEndDate;
    }

    public void setInputEndDate(String inputEndDate) {
        this.inputEndDate = inputEndDate;
    }

    public List<EntrustDetailReportPage> getEdReport() {
        return edReport;
    }

    public void setEdReport(List<EntrustDetailReportPage> edReport) {
        this.edReport = edReport;
    }

    public String getEntrustStatusName() {
        return entrustStatusName;
    }

    public void setEntrustStatusName(String entrustStatusName) {
        this.entrustStatusName = entrustStatusName;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public String getIsCollections() {
        return isCollections;
    }

    public void setIsCollections(String isCollections) {
        this.isCollections = isCollections;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    public Double getInvolidMoney() {
        return involidMoney;
    }

    public void setInvolidMoney(Double involidMoney) {
        this.involidMoney = involidMoney;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSpecialRule() {
        return specialRule;
    }

    public void setSpecialRule(String specialRule) {
        this.specialRule = specialRule;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}
