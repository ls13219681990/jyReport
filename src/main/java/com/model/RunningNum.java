package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractRunningNum entity provides the base persistence definition of the RunningNum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RUNNING_NUM")
public class RunningNum extends BaseBean implements java.io.Serializable {


    // Fields    

    private String item;
    private String currentKey;
    private String reportYear;


    // Constructors

    /**
     * default constructor
     */
    public RunningNum() {
    }

    /**
     * minimal constructor
     */
    public RunningNum(String item) {
        this.item = item;
    }

    /**
     * full constructor
     */
    public RunningNum(String item, String currentKey, String reportYear) {
        this.item = item;
        this.currentKey = currentKey;
        this.reportYear = reportYear;
    }


    // Property accessors
    @Id

    @Column(name = "ITEM", unique = true, nullable = false, length = 50)

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Column(name = "CURRENT_KEY", length = 50)

    public String getCurrentKey() {
        return this.currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    @Column(name = "REPORT_YEAR", length = 4)

    public String getReportYear() {
        return this.reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }


}