package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysUserRole entity provides the base persistence definition of the SysUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_ROLE")
public class SysUserRole extends BaseBean implements java.io.Serializable {


    // Fields    

    private String userRoleId;
    private String sysUserId;
    private String roleId;


    // Constructors

    /**
     * default constructor
     */
    public SysUserRole() {
    }

    /**
     * minimal constructor
     */
    public SysUserRole(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * full constructor
     */
    public SysUserRole(String userRoleId, String sysUserId, String roleId) {
        this.userRoleId = userRoleId;
        this.sysUserId = sysUserId;
        this.roleId = roleId;
    }


    // Property accessors
    @Id

    @Column(name = "USER_ROLE_ID", unique = true, nullable = false, length = 32)

    public String getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "SYS_USER_ID", length = 32)

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Column(name = "ROLE_ID", length = 32)

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}