package com.model;


import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractBaseDepartment entity provides the base persistence definition of the BaseDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_DEPARTMENT")
public class BaseDepartment extends BaseBean implements java.io.Serializable {


    // Fields    

    private String departmentId;
    private String departmentName;
    private String parentDepartmentId;
    private String isTestDept;
    private String isManagementRight;
    private String remark;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;
    private String specialRule;


    // Constructors

    /**
     * default constructor
     */
    public BaseDepartment() {
    }

    /**
     * minimal constructor
     */
    public BaseDepartment(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * full constructor
     */
    public BaseDepartment(String departmentId, String departmentName, String parentDepartmentId, String isTestDept, String isManagementRight, String remark, String inputer, String inputeTime, String updater, String updateTime, String specialRule) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.parentDepartmentId = parentDepartmentId;
        this.isTestDept = isTestDept;
        this.isManagementRight = isManagementRight;
        this.remark = remark;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.specialRule = specialRule;
    }


    // Property accessors
    @Id

    @Column(name = "DEPARTMENT_ID", unique = true, nullable = false, length = 32)

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "DEPARTMENT_NAME", length = 50)

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "PARENT_DEPARTMENT_ID", length = 32)

    public String getParentDepartmentId() {
        return this.parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    @Column(name = "IS_TEST_DEPT", length = 2)

    public String getIsTestDept() {
        return this.isTestDept;
    }

    public void setIsTestDept(String isTestDept) {
        this.isTestDept = isTestDept;
    }

    @Column(name = "IS_MANAGEMENT_RIGHT", length = 2)

    public String getIsManagementRight() {
        return this.isManagementRight;
    }

    public void setIsManagementRight(String isManagementRight) {
        this.isManagementRight = isManagementRight;
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

    @Column(name = "SPECIAL_RULE", length = 2)
    public String getSpecialRule() {
        return specialRule;
    }

    public void setSpecialRule(String specialRule) {
        this.specialRule = specialRule;
    }


}