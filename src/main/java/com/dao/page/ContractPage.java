package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model.EntrustCompany;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ContractPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String contractId;
    private String contractCode;
    private String entrustCompanyId;
    private String entrustCompanyName;//委托单位名称
    private String projectId;
    private String projectName;//工程名称
    private String checkArea;
    //    private String imageProgress;
    private String paymentNode;
    private String contractChapter;
    private String linkMan;
    private String linkPhone;
    private String projectManager;
    private String contractIsReturn;
    private String managementDepartmentId;
    private String mDepartmentName;//经营部门名称
    private String contractType;
    private String contractTypeName;//类型名称
    private String accountType;
    private String accountTypeName;//收费方式名称
    private Double receivedMoney;
    private Double contractMoney;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private int pageNo;//当前页数
    private int pageSize;//一页条数
    private int totalCount;//总条数
    private String projectNameTag;//工程名称备注
    private String copiesCount;//合同份数
    private Double receivedPrice;//合同已收款（统计）
    private Double remainderPrice;//合同未收款（统计）

    private List<EntrustCompany> ecList = new ArrayList<EntrustCompany>();

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getEntrustCompanyId() {
        return entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCheckArea() {
        return checkArea;
    }

    public void setCheckArea(String checkArea) {
        this.checkArea = checkArea;
    }

    public String getPaymentNode() {
        return paymentNode;
    }

    public void setPaymentNode(String paymentNode) {
        this.paymentNode = paymentNode;
    }

    public String getContractChapter() {
        return contractChapter;
    }

    public void setContractChapter(String contractChapter) {
        this.contractChapter = contractChapter;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getContractIsReturn() {
        return contractIsReturn;
    }

    public void setContractIsReturn(String contractIsReturn) {
        this.contractIsReturn = contractIsReturn;
    }

    public String getManagementDepartmentId() {
        return managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(Double receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
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

    public String getmDepartmentName() {
        return mDepartmentName;
    }

    public void setmDepartmentName(String mDepartmentName) {
        this.mDepartmentName = mDepartmentName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
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

    public String getProjectNameTag() {
        return projectNameTag;
    }

    public void setProjectNameTag(String projectNameTag) {
        this.projectNameTag = projectNameTag;
    }

    public String getCopiesCount() {
        return copiesCount;
    }

    public void setCopiesCount(String copiesCount) {
        this.copiesCount = copiesCount;
    }

    public Double getReceivedPrice() {
        return receivedPrice;
    }

    public void setReceivedPrice(Double receivedPrice) {
        this.receivedPrice = receivedPrice;
    }

    public Double getRemainderPrice() {
        return remainderPrice;
    }

    public void setRemainderPrice(Double remainderPrice) {
        this.remainderPrice = remainderPrice;
    }

    public List<EntrustCompany> getEcList() {
        return ecList;
    }

    public void setEcList(List<EntrustCompany> ecList) {
        this.ecList = ecList;
    }

}
