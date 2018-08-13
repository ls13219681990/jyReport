package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysRole entity provides the base persistence definition of the SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLE")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SysRole extends BaseBean implements java.io.Serializable {


    // Fields    

    private String roleId;
    private String roleCode;
    private String roleName;
    private String roleStatus;
    private String inputer;
    private String inputTime;


    // Constructors

    /**
     * default constructor
     */
    public SysRole() {
    }

    /**
     * minimal constructor
     */
    public SysRole(String roleId) {
        this.roleId = roleId;
    }

    /**
     * full constructor
     */
    public SysRole(String roleId, String roleCode, String roleName, String roleStatus, String inputer, String inputTime) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleStatus = roleStatus;
        this.inputer = inputer;
        this.inputTime = inputTime;
    }


    // Property accessors
    @Id

    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "ROLE_CODE", length = 3)

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Column(name = "ROLE_NAME", length = 100)

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "ROLE_STATUS", length = 2)

    public String getRoleStatus() {
        return this.roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Column(name = "INPUTER", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "INPUT_TIME", length = 19)

    public String getInputTime() {
        return this.inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }


}