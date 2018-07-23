package com.dao.page;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class ProjectReceivablePage implements java.io.Serializable {

    /**
     * 工程应收预览表
     */
    private static final long serialVersionUID = 1L;

    private String entrustCompanyId;//委托单位ID
    private String entrustCompanyNo;//委托单位编号
    private String entrustCompanyName;//委托单位名称
    private String projectId;
    private String projectName;//工程名称
    private String testMoney;//检测费用
    private String contractMoney;//合同金额
    private String receivableMoney;//以前应收

    public String getEntrustCompanyId() {
        return entrustCompanyId;
    }

    public void setEntrustCompanyId(String entrustCompanyId) {
        this.entrustCompanyId = entrustCompanyId;
    }

    public String getEntrustCompanyNo() {
        return entrustCompanyNo;
    }

    public void setEntrustCompanyNo(String entrustCompanyNo) {
        this.entrustCompanyNo = entrustCompanyNo;
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

    public String getTestMoney() {
        return testMoney;
    }

    public void setTestMoney(String testMoney) {
        this.testMoney = testMoney;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getReceivableMoney() {
        return receivableMoney;
    }

    public void setReceivableMoney(String receivableMoney) {
        this.receivableMoney = receivableMoney;
    }

}
