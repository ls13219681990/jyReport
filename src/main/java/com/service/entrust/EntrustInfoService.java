package com.service.entrust;

import com.dao.page.*;
import com.model.EntrustInfo;
import com.service.common.BaseService;

import java.util.Collection;
import java.util.List;

public interface EntrustInfoService extends BaseService<EntrustInfo> {

    List<EntrustInfo> findIsEntrust(String strECompanyId, String strProjectId, String strSUnitId);

    String saveEntrustInfo(EntrustSavePage esPage, String userId);

    void updateEntrustInfo(EntrustSavePage esPage, String userId);

    void updateEntrustMDetail(EntrustSavePage esPage, String userId);

    List<EntrustSavePage> findEntrustInfoByCondition(EntrustSavePage esPage);

    List<EntrustSavePage> findEntrustDetailInfoById(String strEntrustId);

    List<EntrustSavePage> findERDetailInfoById(EntrustSavePage esPage);

    List<EntrustTfSheetPage> findEntrustEfSheet(String strEntrustId);

    List<TestTfSheetPage> findTestEfSheet(String strEntrustId);

    void updateEntrustStatus(String strEntrustId, String strEntrustStatus, String userId);

    List<ProjectMoneyPage> findProjectMoney(ProjectMoneyPage pMoneyPage);

    List<ProjectTestMoneyPage> findProjectTestMoney(String strProjectId);

    List<EntrustReportPage> findERDetailInfo(EntrustReportPage erPage);

    List<EntrustReportPage> findDepartmentDetailInfo(EntrustReportPage esPage, String userId);

    void saveAllReportStatus(Collection<EntrustReportPage> coll, String userId);

    List<OutputValueCountPage> findOutputValueCount(String strOutputValueYear);

    void saveEntrustMDetail(EntrustSavePage esPage, String userId);

    List<EntrustReportPage> findDepartmentDetailInputInfo(EntrustReportPage esPage, String userId);

    void updateEntrustInfoBySn(EntrustSavePage esPage, String userId);

    EntrustInfo findEntrust(String entrustDetailId);

    List<String> ifAllReportStatus(Collection<EntrustReportPage> coll);

    List<EntrustSavePage> findEntrustInfoByConditionByAll(EntrustSavePage esPage);

    List<TestReportInfoPage> findDepartmentReportInfo(Integer pageNo,
                                                      Integer pageSize, String departmentId, String userId, String entrustSn, String entrustCompanyName, String projectName, String startDate, String endDate, String inputTime, String entrustDate, String projectPart, String sampleAge, String reportStatus, String reportNo, String inputerName, String auditorName, String apprvoerName, String printUserName);

    List<AgreementPage> findReceivablesStatistics(Integer pageNo, Integer pageSize, String accountType, String contractNo, String entrustCompanyName, String projectName);

    List<WaterAccountStatisticsPage> findentrustWaterAccountStatistics(
            Integer pageNo, Integer pageSize, String entrustSn,
            String startDate, String endDate, String entrustDate,
            String inputTime, String capitalAccountNo, String inputerName,
            String entrustCompanyName, String projectName,
            String entrustStatus, String accountKinds, String testDepartment,
            String managmentDepartment, String acceptanceMan, String sampleName, String accountType);

}