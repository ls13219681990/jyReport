package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysCode entity provides the base persistence definition of the SysCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_CODE")
public class SysCode extends BaseBean implements java.io.Serializable {


    // Fields    

    private String codeId;
    private String codeName;
    private String codeRelation;
    private String codeValue;
    private String status;


    // Constructors

    /**
     * default constructor
     */
    public SysCode() {
    }

    /**
     * minimal constructor
     */
    public SysCode(String codeId) {
        this.codeId = codeId;
    }

    /**
     * full constructor
     */
    public SysCode(String codeId, String codeName, String codeRelation, String codeValue, String status) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.codeRelation = codeRelation;
        this.codeValue = codeValue;
        this.status = status;
    }


    // Property accessors
    @Id

    @Column(name = "CODE_ID", unique = true, nullable = false, length = 32)

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    @Column(name = "CODE_NAME", length = 50)

    public String getCodeName() {
        return this.codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Column(name = "CODE_RELATION", length = 50)

    public String getCodeRelation() {
        return this.codeRelation;
    }

    public void setCodeRelation(String codeRelation) {
        this.codeRelation = codeRelation;
    }

    @Column(name = "CODE_VALUE", length = 20)

    public String getCodeValue() {
        return this.codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    @Column(name = "STATUS", length = 2)

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}