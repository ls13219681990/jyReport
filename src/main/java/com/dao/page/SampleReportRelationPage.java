package com.dao.page;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户画面用实体类
 */
public class SampleReportRelationPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String sampleReportRelationId;

    private List<SampleReportPage> sampleReportRelationList = new ArrayList<SampleReportPage>();

    private List<TestReportInfoPage> testReportInfoPageList = new ArrayList<TestReportInfoPage>();

    public String getSampleReportRelationId() {
        return sampleReportRelationId;
    }

    public void setSampleReportRelationId(String sampleReportRelationId) {
        this.sampleReportRelationId = sampleReportRelationId;
    }

    public List<TestReportInfoPage> getTestReportInfoPageList() {
        return testReportInfoPageList;
    }

    public void setTestReportInfoPageList(
            List<TestReportInfoPage> testReportInfoPageList) {
        this.testReportInfoPageList = testReportInfoPageList;
    }

    public List<SampleReportPage> getSampleReportRelationList() {
        return sampleReportRelationList;
    }

    public void setSampleReportRelationList(
            List<SampleReportPage> sampleReportRelationList) {
        this.sampleReportRelationList = sampleReportRelationList;
    }

}
