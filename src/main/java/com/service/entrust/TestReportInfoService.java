package com.service.entrust;

import com.common.service.BaseService;
import com.dao.page.ReportTemplateInfoPage;
import com.dao.page.SampleReportRelationPage;
import com.dao.page.TestReportInfoPage;
import com.model.TestReportInfo;

import java.util.Collection;
import java.util.List;

public interface TestReportInfoService extends BaseService<TestReportInfo> {

    List<TestReportInfoPage> findReportInfo(String strEDetailId, String strStatus);

    void saveTReportInfo(TestReportInfoPage triPage, String userId);

    void saveAllReportApAndAu(Collection<TestReportInfoPage> coll, String userId);

    void saveReportPrintInfo(String reportId, String printNum, String distributeTime, String reportStatus, String userId);

    void saveReportPrintNum(Collection<TestReportInfoPage> coll, String userId);

    List<ReportTemplateInfoPage> findReportTitleInfo(String strReportId);

    void saveSampleReportInfo(SampleReportRelationPage srrPage, String userId);

    void updateSampleReportInfo(SampleReportRelationPage srrPage, String userId);

    void updateSampleReportByReportId(SampleReportRelationPage srrPage, String userId);

    void updateTestReportByReportStatus(TestReportInfoPage triPage, String userId, String backStatus);

    void updateTestReport(TestReportInfoPage triPage, String userId);

    List<TestReportInfoPage> sampleReportList(TestReportInfoPage triPage);

    void updTestReport(TestReportInfoPage triPage, String userId);

    List<TestReportInfoPage> saveTwoReportList(TestReportInfoPage triPage);
}