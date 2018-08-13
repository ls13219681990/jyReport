package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustDetailReportPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustDetailId;//委托明细ID
    private String entrustId;//委托ID
    private String departmentId;//部门ID
    private String departmentName;//部门名称
    private String sampleId;//样品ID
    private String sampleName;//样品名称
    private String sampleSource;//样品来源
    private Integer reportNum;//报告分数
    private String processStatus;//流程状态
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String standards;//依据标准
    private String witness;//见证人
    private String sampleType;//补办委托样品类型
    private Double realTotalPrice;//真正的总价
    private String deleteFlag;//删除标识：01:删除，00:正常
    private String testResult;//检测结果
    //上委托金额明细List
    private List<EntrustDetailSavePage> enPaInfoPageList = new ArrayList<EntrustDetailSavePage>();

    //委托报告关系List
    private List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();


    public String getEntrustDetailId() {
        return entrustDetailId;
    }

    public void setEntrustDetailId(String entrustDetailId) {
        this.entrustDetailId = entrustDetailId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
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

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public List<EntrustDetailSavePage> getEnPaInfoPageList() {
        return enPaInfoPageList;
    }

    public void setEnPaInfoPageList(List<EntrustDetailSavePage> enPaInfoPageList) {
        this.enPaInfoPageList = enPaInfoPageList;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Double getRealTotalPrice() {
        return realTotalPrice;
    }

    public void setRealTotalPrice(Double realTotalPrice) {
        this.realTotalPrice = realTotalPrice;
    }

    public List<SampleReportPage> getSrPageList() {
        return srPageList;
    }

    public void setSrPageList(List<SampleReportPage> srPageList) {
        this.srPageList = srPageList;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
