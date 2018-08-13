package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CapitalAccountPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String capitalAccountId;
    private String capitalAccountCode;
    private String entrustCompanyId;
    private String entrustCompanyName;
    private String entrustCompanyNo;
    private String projectId;
    private String projectName;
    private String linkMan;
    private String linkPhone;
    private String supervisionUnitId;
    private String supervisionUnitName;
    private String witness;
    private String accountType2;//类型
    private String accountKinds;//收款类别
    private String contractCode;//合同号
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String printNum;
    private String contractId;//合同ID
    private String entrustLinkMan;//委托方联系人
    private String entrustLinkPhone;//委托方联系电话
    private String projectRemark;//工程备注
    private int pageNo;//当前页数
    private int pageSize;//一页条数
    private int totalCount;//总条数

    List<CapitalAccountLinkmpPage> caLinkmpPageList = new ArrayList<CapitalAccountLinkmpPage>();

    public String getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    public String getCapitalAccountCode() {
        return capitalAccountCode;
    }

    public void setCapitalAccountCode(String capitalAccountCode) {
        this.capitalAccountCode = capitalAccountCode;
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

    public String getEntrustCompanyNo() {
        return entrustCompanyNo;
    }

    public void setEntrustCompanyNo(String entrustCompanyNo) {
        this.entrustCompanyNo = entrustCompanyNo;
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

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getAccountType2() {
        return accountType2;
    }

    public void setAccountType2(String accountType2) {
        this.accountType2 = accountType2;
    }

    public String getAccountKinds() {
        return accountKinds;
    }

    public void setAccountKinds(String accountKinds) {
        this.accountKinds = accountKinds;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getEntrustLinkMan() {
        return entrustLinkMan;
    }

    public void setEntrustLinkMan(String entrustLinkMan) {
        this.entrustLinkMan = entrustLinkMan;
    }

    public String getEntrustLinkPhone() {
        return entrustLinkPhone;
    }

    public void setEntrustLinkPhone(String entrustLinkPhone) {
        this.entrustLinkPhone = entrustLinkPhone;
    }

    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
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

    public List<CapitalAccountLinkmpPage> getCaLinkmpPageList() {
        return caLinkmpPageList;
    }

    public void setCaLinkmpPageList(List<CapitalAccountLinkmpPage> caLinkmpPageList) {
        this.caLinkmpPageList = caLinkmpPageList;
    }
}
