package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractManagementDepartment entity provides the base persistence definition of the ManagementDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MANAGEMENT_DEPARTMENT")
public class ManagementDepartment extends BaseBean implements java.io.Serializable {


    // Fields    

    private String managementDepartmentId;
    private String managementDepartmentName;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public ManagementDepartment() {
    }

    /**
     * minimal constructor
     */
    public ManagementDepartment(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    /**
     * full constructor
     */
    public ManagementDepartment(String managementDepartmentId, String managementDepartmentName, String remark, String inputer, String inputeTime, String updater, String updateTime) {
        this.managementDepartmentId = managementDepartmentId;
        this.managementDepartmentName = managementDepartmentName;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "MANAGEMENT_DEPARTMENT_ID", unique = true, nullable = false, length = 32)

    public String getManagementDepartmentId() {
        return this.managementDepartmentId;
    }

    public void setManagementDepartmentId(String managementDepartmentId) {
        this.managementDepartmentId = managementDepartmentId;
    }

    @Column(name = "MANAGEMENT_DEPARTMENT_NAME", length = 50)

    public String getManagementDepartmentName() {
        return this.managementDepartmentName;
    }

    public void setManagementDepartmentName(String managementDepartmentName) {
        this.managementDepartmentName = managementDepartmentName;
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