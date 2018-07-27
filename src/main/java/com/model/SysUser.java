package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysUser entity provides the base persistence definition of the SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseBean implements java.io.Serializable {


    // Fields    

    private String sysUserId;
    private String departmentId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String mailbox;
    private String phoneNumber;
    private String userType;
    private String userStatus;
    private String isAudit;
    private String inputer;
    private String inputeTime;
    private String updater;
    private String updateTime;


    // Constructors

    /**
     * default constructor
     */
    public SysUser() {
    }

    /**
     * minimal constructor
     */
    public SysUser(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * full constructor
     */
    public SysUser(String sysUserId, String departmentId, String userCode, String userName, String userPassword, String mailbox, String phoneNumber, String userType, String userStatus, String isAudit, String inputer, String inputeTime, String updater, String updateTime) {
        this.sysUserId = sysUserId;
        this.departmentId = departmentId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.mailbox = mailbox;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.userStatus = userStatus;
        this.isAudit = isAudit;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.updater = updater;
        this.updateTime = updateTime;
    }


    // Property accessors
    @Id

    @Column(name = "SYS_USER_ID", unique = true, nullable = false, length = 32)

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Column(name = "DEPARTMENT_ID", length = 32)

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "USER_CODE", length = 20)

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name = "USER_NAME", length = 50)

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "USER_PASSWORD", length = 10)

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "MAILBOX", length = 100)

    public String getMailbox() {
        return this.mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    @Column(name = "PHONE_NUMBER", length = 15)

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "USER_TYPE", length = 2)

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "USER_STATUS", length = 2)

    public String getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Column(name = "IS_AUDIT", length = 2)

    public String getIsAudit() {
        return this.isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    @Column(name = "inputer", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "inpute_time", length = 19)

    public String getInputeTime() {
        return this.inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    @Column(name = "updater", length = 32)

    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Column(name = "update_time", length = 19)

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}