package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProjectTestMoneyPage implements java.io.Serializable {

    /**
     * 工程检测费用统计
     */
    private static final long serialVersionUID = 1L;

    private String projectId;//工程ID
    private String projectName;//工程名称
    private String testMoney;//检测费用
    private String invoiceMoney;//发票费用
    private String receiptMoney;//收据费用
    private String receivedMoney;//已收费用
    private String uncollectedMoney;//未收费用
    private String amountReceivable;
    //工程检测费用明细
    List<ProjectTestMoneyDetailPage> ptmDetailPageList = new ArrayList<ProjectTestMoneyDetailPage>();

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

    public String getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(String invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public String getReceiptMoney() {
        return receiptMoney;
    }

    public void setReceiptMoney(String receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    public String getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(String receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public String getUncollectedMoney() {
        return uncollectedMoney;
    }

    public void setUncollectedMoney(String uncollectedMoney) {
        this.uncollectedMoney = uncollectedMoney;
    }

    public List<ProjectTestMoneyDetailPage> getPtmDetailPageList() {
        return ptmDetailPageList;
    }

    public void setPtmDetailPageList(
            List<ProjectTestMoneyDetailPage> ptmDetailPageList) {
        this.ptmDetailPageList = ptmDetailPageList;
    }

    public String getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(String amountReceivable) {
        this.amountReceivable = amountReceivable;
    }
}
