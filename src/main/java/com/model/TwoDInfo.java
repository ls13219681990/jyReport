package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractTwoDInfo entity provides the base persistence definition of the TwoDInfo entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "TWO_D_INFO")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TwoDInfo extends BaseBean implements java.io.Serializable {


    // Fields    

    private String twodInfoId;
    private String reportId;
    private String testAgencyName;
    private String testCategories;
    private String projectName;
    private String projectAddress;
    private String projectParts;
    private String reportNo;
    private String entrustCompanyName;
    private String reportDate;
    private String testResult;
    private String reportConclusion;
    private String inspectionMan;
    private String witnessMan;
    private String isNew;
    private String standards;
    private String testName;


    // Constructors

    /**
     * default constructor
     */
    public TwoDInfo() {
    }

    /**
     * minimal constructor
     */
    public TwoDInfo(String twodInfoId) {
        this.twodInfoId = twodInfoId;
    }

    /**
     * full constructor
     */
    public TwoDInfo(String twodInfoId, String reportId, String testAgencyName, String testCategories, String projectName, String projectAddress, String projectParts, String reportNo, String entrustCompanyName, String reportDate, String testResult, String reportConclusion, String inspectionMan, String witnessMan, String isNew, String standards, String testName) {
        this.twodInfoId = twodInfoId;
        this.reportId = reportId;
        this.testAgencyName = testAgencyName;
        this.testCategories = testCategories;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.projectParts = projectParts;
        this.reportNo = reportNo;
        this.entrustCompanyName = entrustCompanyName;
        this.reportDate = reportDate;
        this.testResult = testResult;
        this.reportConclusion = reportConclusion;
        this.inspectionMan = inspectionMan;
        this.witnessMan = witnessMan;
        this.isNew = isNew;
        this.standards = standards;
        this.testName = testName;
    }


    // Property accessors
    @Id

    @Column(name = "TWOD_INFO_ID", unique = true, nullable = false, length = 32)

    public String getTwodInfoId() {
        return this.twodInfoId;
    }

    public void setTwodInfoId(String twodInfoId) {
        this.twodInfoId = twodInfoId;
    }

    @Column(name = "REPORT_ID", length = 32)

    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Column(name = "TEST_AGENCY_NAME", length = 100)

    public String getTestAgencyName() {
        return this.testAgencyName;
    }

    public void setTestAgencyName(String testAgencyName) {
        this.testAgencyName = testAgencyName;
    }

    @Column(name = "TEST_CATEGORIES", length = 80)

    public String getTestCategories() {
        return this.testCategories;
    }

    public void setTestCategories(String testCategories) {
        this.testCategories = testCategories;
    }

    @Column(name = "PROJECT_NAME", length = 100)

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "PROJECT_ADDRESS", length = 200)

    public String getProjectAddress() {
        return this.projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    @Column(name = "PROJECT_PARTS", length = 200)

    public String getProjectParts() {
        return this.projectParts;
    }

    public void setProjectParts(String projectParts) {
        this.projectParts = projectParts;
    }

    @Column(name = "REPORT_NO", length = 50)

    public String getReportNo() {
        return this.reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    @Column(name = "ENTRUST_COMPANY_NAME", length = 200)

    public String getEntrustCompanyName() {
        return this.entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    @Column(name = "REPORT_DATE", length = 10)

    public String getReportDate() {
        return this.reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @Column(name = "TEST_RESULT", length = 500)

    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Column(name = "REPORT_CONCLUSION", length = 800)

    public String getReportConclusion() {
        return this.reportConclusion;
    }

    public void setReportConclusion(String reportConclusion) {
        this.reportConclusion = reportConclusion;
    }

    @Column(name = "INSPECTION_MAN", length = 20)

    public String getInspectionMan() {
        return this.inspectionMan;
    }

    public void setInspectionMan(String inspectionMan) {
        this.inspectionMan = inspectionMan;
    }

    @Column(name = "WITNESS_MAN", length = 20)

    public String getWitnessMan() {
        return this.witnessMan;
    }

    public void setWitnessMan(String witnessMan) {
        this.witnessMan = witnessMan;
    }

    @Column(name = "IS_NEW", length = 2)

    public String getIsNew() {
        return this.isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    @Column(name = "STANDARDS", length = 4000)
    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    @Column(name = "TEST_NAME", length = 200)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


}