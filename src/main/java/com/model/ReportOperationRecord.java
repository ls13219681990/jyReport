package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractReportOperationRecord entity provides the base persistence definition of the ReportOperationRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REPORT_OPERATION_RECORD")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ReportOperationRecord extends BaseBean implements java.io.Serializable {


    // Fields    

    private String operationRecordId;
    private String reportId;
    private String operation;
    private String inputer;
    private String inputeTime;


    // Constructors

    /**
     * default constructor
     */
    public ReportOperationRecord() {
    }

    /**
     * minimal constructor
     */
    public ReportOperationRecord(String operationRecordId) {
        this.operationRecordId = operationRecordId;
    }

    /**
     * full constructor
     */
    public ReportOperationRecord(String operationRecordId, String reportId, String operation, String inputer, String inputeTime) {
        this.operationRecordId = operationRecordId;
        this.reportId = reportId;
        this.operation = operation;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
    }


    // Property accessors
    @Id

    @Column(name = "OPERATION_RECORD_ID", unique = true, nullable = false, length = 32)

    public String getOperationRecordId() {
        return this.operationRecordId;
    }

    public void setOperationRecordId(String operationRecordId) {
        this.operationRecordId = operationRecordId;
    }

    @Column(name = "REPORT_ID", length = 32)

    public String getReportId() {
        return this.reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Column(name = "OPERATION", length = 200)

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Column(name = "INPUTER", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "INPUTE_TIME", length = 19)

    public String getInputeTime() {
        return this.inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }


}