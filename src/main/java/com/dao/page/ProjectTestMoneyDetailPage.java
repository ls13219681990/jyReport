package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProjectTestMoneyDetailPage implements java.io.Serializable {

    /**
     * 工程检测费用统计明细
     */
    private static final long serialVersionUID = 1L;

    private String entrustCompanyId;//委托单位ID
    private String entrustCompanyName;//委托单位名称
    private String entrustCompanyNo;//委托单位编号
    private String entrustYear;//委托年份
    //委托单位检测费用按月集计
    List<ProjectTestMoneyYearPage> ptmYearPageLidt = new ArrayList<ProjectTestMoneyYearPage>();

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

    public String getEntrustYear() {
        return entrustYear;
    }

    public void setEntrustYear(String entrustYear) {
        this.entrustYear = entrustYear;
    }

    public List<ProjectTestMoneyYearPage> getPtmYearPageLidt() {
        return ptmYearPageLidt;
    }

    public void setPtmYearPageLidt(List<ProjectTestMoneyYearPage> ptmYearPageLidt) {
        this.ptmYearPageLidt = ptmYearPageLidt;
    }
}
