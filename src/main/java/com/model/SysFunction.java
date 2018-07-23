package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysFunction entity provides the base persistence definition of the SysFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_FUNCTION")
public class SysFunction extends BaseBean implements java.io.Serializable {


    // Fields    

    private String functionId;
    private String parentFunctionId;
    private String functionCode;
    private String functionName;
    private String functionUrl;
    private String status;
    private String creatDate;
    private String isLast;


    // Constructors

    /**
     * default constructor
     */
    public SysFunction() {
    }

    /**
     * minimal constructor
     */
    public SysFunction(String functionId) {
        this.functionId = functionId;
    }

    /**
     * full constructor
     */
    public SysFunction(String functionId, String parentFunctionId, String functionCode, String functionName, String functionUrl, String status, String creatDate, String isLast) {
        this.functionId = functionId;
        this.parentFunctionId = parentFunctionId;
        this.functionCode = functionCode;
        this.functionName = functionName;
        this.functionUrl = functionUrl;
        this.status = status;
        this.creatDate = creatDate;
        this.isLast = isLast;
    }


    // Property accessors
    @Id

    @Column(name = "FUNCTION_ID", unique = true, nullable = false, length = 32)

    public String getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    @Column(name = "PARENT_FUNCTION_ID", length = 32)

    public String getParentFunctionId() {
        return this.parentFunctionId;
    }

    public void setParentFunctionId(String parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    @Column(name = "FUNCTION_CODE", length = 8)

    public String getFunctionCode() {
        return this.functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    @Column(name = "FUNCTION_NAME", length = 100)

    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Column(name = "FUNCTION_URL", length = 200)

    public String getFunctionUrl() {
        return this.functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    @Column(name = "STATUS", length = 2)

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CREAT_DATE", length = 10)

    public String getCreatDate() {
        return this.creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    @Column(name = "IS_LAST", length = 2)

    public String getIsLast() {
        return this.isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }


}