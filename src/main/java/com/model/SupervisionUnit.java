package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSupervisionUnit entity provides the base persistence definition of the SupervisionUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPERVISION_UNIT")
public class SupervisionUnit extends BaseBean implements java.io.Serializable {


    // Fields    

    private String supervisionUnitId;
    private String supervisionUnitName;
    private String witnesses;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public SupervisionUnit() {
    }

    /**
     * minimal constructor
     */
    public SupervisionUnit(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
    }

    /**
     * full constructor
     */
    public SupervisionUnit(String supervisionUnitId, String supervisionUnitName, String witness, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.supervisionUnitId = supervisionUnitId;
        this.supervisionUnitName = supervisionUnitName;
        this.witnesses = witnesses;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "SUPERVISION_UNIT_ID", unique = true, nullable = false, length = 32)

    public String getSupervisionUnitId() {
        return this.supervisionUnitId;
    }

    public void setSupervisionUnitId(String supervisionUnitId) {
        this.supervisionUnitId = supervisionUnitId;
    }

    @Column(name = "SUPERVISION_UNIT_NAME", length = 100)

    public String getSupervisionUnitName() {
        return this.supervisionUnitName;
    }

    public void setSupervisionUnitName(String supervisionUnitName) {
        this.supervisionUnitName = supervisionUnitName;
    }

    @Column(name = "WITNESSES", length = 120)

    public String getWitnesses() {
        return this.witnesses;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
    }

    @Column(name = "REMARK", length = 200)

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Column(name = "UPDATER", length = 32)

    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Column(name = "UPDATE_TIME", length = 19)

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}