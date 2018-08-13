package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AgreementPage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // Fields    

    private String contractCode;//合同编号
    private String contractType;//合同类型
    private String projectName;//项目名称
    private String entrustCompanyName; //委托单位
    private String receivable;//应收
    private String netReceipts;//实收
    private String uncollected;//未收
    private Integer totalCount;//总数
    private String contractPrice;//合同金额


    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }

    public String getNetReceipts() {
        return netReceipts;
    }

    public void setNetReceipts(String netReceipts) {
        this.netReceipts = netReceipts;
    }

    public String getUncollected() {
        return uncollected;
    }

    public void setUncollected(String uncollected) {
        this.uncollected = uncollected;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }


}