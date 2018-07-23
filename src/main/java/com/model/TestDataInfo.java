package com.model;

import com.common.dao.BaseBean;

import javax.persistence.*;


/**
 * AbstractEntrustAdvance entity provides the base persistence definition of the EntrustAdvance entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "TEST_DATA_INFO")
public class TestDataInfo extends BaseBean implements java.io.Serializable {


    // Fields    
    private String testDataId;
    private String locationId;
    private String testLocation;
    private String testName;
    private String testUnit;
    private String arrayMode;
    private String redundancy;

    // Constructors

    /**
     * default constructor
     */
    public TestDataInfo() {
    }

    /**
     * minimal constructor
     */
    public TestDataInfo(String testDataId) {
        this.testDataId = testDataId;
    }

    /**
     * full constructor
     */
    public TestDataInfo(String testDataId, String locationId, String testLocation, String testName, String testUnit, String arrayMode, String redundancy) {
        this.testDataId = testDataId;
        this.locationId = locationId;
        this.testLocation = testLocation;
        this.testName = testName;
        this.testUnit = testUnit;
        this.arrayMode = arrayMode;
        this.redundancy = redundancy;
    }


    // Property accessors
    @Id

    @Column(name = "TEST_DATA_ID", unique = true, nullable = false, length = 32)

    public String getTestDataId() {
        return this.testDataId;
    }

    public void setTestDataId(String testDataId) {
        this.testDataId = testDataId;
    }

    @Column(name = "LOCATION_ID", length = 32)
    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Column(name = "TEST_LOCATION", length = 400)
    public String getTestLocation() {
        return testLocation;
    }

    public void setTestLocation(String testLocation) {
        this.testLocation = testLocation;
    }

    @Column(name = "TEST_NAME", length = 50)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Column(name = "TEST_UNIT", length = 20)
    public String getTestUnit() {
        return testUnit;
    }

    public void setTestUnit(String testUnit) {
        this.testUnit = testUnit;
    }

    @Column(name = "ARRAY_MODE", length = 2)
    public String getArrayMode() {
        return arrayMode;
    }

    public void setArrayMode(String arrayMode) {
        this.arrayMode = arrayMode;
    }

    @Column(name = "REDUNDANCY", length = 50)
    public String getRedundancy() {
        return redundancy;
    }

    public void setRedundancy(String redundancy) {
        this.redundancy = redundancy;
    }

}