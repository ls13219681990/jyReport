package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysTablePk entity provides the base persistence definition of the SysTablePk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_TABLE_PK")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SysTablePk extends BaseBean implements java.io.Serializable {


    // Fields    

    private String tableName;
    private String currentPk;


    // Constructors

    /**
     * default constructor
     */
    public SysTablePk() {
    }

    /**
     * minimal constructor
     */
    public SysTablePk(String tableName) {
        this.tableName = tableName;
    }

    /**
     * full constructor
     */
    public SysTablePk(String tableName, String currentPk) {
        this.tableName = tableName;
        this.currentPk = currentPk;
    }


    // Property accessors
    @Id

    @Column(name = "TABLE_NAME", unique = true, nullable = false, length = 50)

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "CURRENT_PK", length = 20)

    public String getCurrentPk() {
        return this.currentPk;
    }

    public void setCurrentPk(String currentPk) {
        this.currentPk = currentPk;
    }


}