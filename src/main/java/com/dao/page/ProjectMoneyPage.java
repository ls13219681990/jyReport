package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProjectMoneyPage implements java.io.Serializable {

    /**
     * 工程应收
     */
    private static final long serialVersionUID = 1L;

    private String entrustCompanyId;//委托单位ID
    private String entrustCompanyName;//委托单位名称
    private String entrustCompanyNo;//委托单位编号
    private String projectId;//工程ID
    private String projectName;//工程名称
    private String testMoney;//检测费用
    private String agMoney;//合同应收
    private String arMoney;//以前应收

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

    public String getTestMoney() {
        return testMoney;
    }

    public void setTestMoney(String testMoney) {
        this.testMoney = testMoney;
    }

    public String getAgMoney() {
        return agMoney;
    }

    public void setAgMoney(String agMoney) {
        this.agMoney = agMoney;
    }

    public String getArMoney() {
        return arMoney;
    }

    public void setArMoney(String arMoney) {
        this.arMoney = arMoney;
    }
}
