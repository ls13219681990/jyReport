package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TestTfSheetPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustId;
    private String entrustSn;
    private String entrustDate;
    private String sampleId;//样品ID
    private String sampleName;//样品名称
    private String testParameterId;//检测参数ID
    private String testParameterName;//检测参数名称
    private String sampleNo;//样品编号
    private String sampleSource;//样品来源
    private String projectPart;//工程部位
    private String moldingDate;//成型日期
    private Integer sampleAge;//龄期
    private String specificationsModels;//规格型号
    private String standards;//依据标准
    private String inputer;//登记人


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

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
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

    public String getTestParameterId() {
        return testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    public String getTestParameterName() {
        return testParameterName;
    }

    public void setTestParameterName(String testParameterName) {
        this.testParameterName = testParameterName;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleSource() {
        return sampleSource;
    }

    public void setSampleSource(String sampleSource) {
        this.sampleSource = sampleSource;
    }

    public String getProjectPart() {
        return projectPart;
    }

    public void setProjectPart(String projectPart) {
        this.projectPart = projectPart;
    }

    public String getMoldingDate() {
        return moldingDate;
    }

    public void setMoldingDate(String moldingDate) {
        this.moldingDate = moldingDate;
    }

    public Integer getSampleAge() {
        return sampleAge;
    }

    public void setSampleAge(Integer sampleAge) {
        this.sampleAge = sampleAge;
    }

    public String getSpecificationsModels() {
        return specificationsModels;
    }

    public void setSpecificationsModels(String specificationsModels) {
        this.specificationsModels = specificationsModels;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }
}
