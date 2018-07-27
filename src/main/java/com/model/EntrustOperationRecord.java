package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractEntrustOperationRecord entity provides the base persistence definition of the EntrustOperationRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ENTRUST_OPERATION_RECORD")
public class EntrustOperationRecord extends BaseBean implements java.io.Serializable {


    // Fields    

    private String operationRecordId;
    private String entrustId;
    private String operation;
    private String inputer;
    private String inputeTime;


    // Constructors

    /**
     * default constructor
     */
    public EntrustOperationRecord() {
    }

    /**
     * minimal constructor
     */
    public EntrustOperationRecord(String operationRecordId) {
        this.operationRecordId = operationRecordId;
    }

    /**
     * full constructor
     */
    public EntrustOperationRecord(String operationRecordId, String entrustId, String operation, String inputer, String inputeTime) {
        this.operationRecordId = operationRecordId;
        this.entrustId = entrustId;
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

    @Column(name = "ENTRUST_ID", length = 32)

    public String getEntrustId() {
        return this.entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
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