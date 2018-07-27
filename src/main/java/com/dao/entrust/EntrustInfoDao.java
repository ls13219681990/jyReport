package com.dao.entrust;

import com.dao.common.BaseDao;
import com.model.BaseDepartment;
import com.model.EntrustInfo;
import com.dao.page.*;

import java.util.List;

public interface EntrustInfoDao extends BaseDao<EntrustInfo> {

    public List<EntrustSavePage> findEntrustInfoListSQL(EntrustSavePage esPage);

    public List<EntrustSavePage> findEMDInfoListSQL(String strEntrustId);

    public List<EntrustSavePage> findERDInfoListSQL(EntrustSavePage esPage);

    public List<ProjectMoneyPage> findProjectMoney(ProjectMoneyPage pMoneyPage);

    public List<String> findMaxSampleNo(String strEntrustId);

    public List<EntrustReportPage> findERDetailInfoListSQL(EntrustReportPage erPage);

    public ProjectTestMoneyPage findProjectTestMoney(String strProjectId);

    public List<ProjectTestMoneyDetailPage> findPTMDetailList(String strProjectId);

    public List<EntrustReportPage> findDepartmentDetailInfo(EntrustReportPage esPage, List<BaseDepartment> wtDepartmentId);

    public List<OutputValueCountPage> findOutputValueCount(String strOutputValueYear);

    public List<EntrustReportPage> findDepartmentDetailInputInfo(EntrustReportPage esPage, List<BaseDepartment> wtDepartmentId);

    public List<EntrustSavePage> findEntrustInfoListSQLByAll(final EntrustSavePage esPage);

    public List<TestReportInfoPage> findDepartmentReportInfo(Integer pageNo,
                                                             Integer pageSize, String wtDepartmentId, String entrustSn, String entrustCompanyName, String projectName, String startDate, String endDate, String inputTime, String entrustDate, String projectPart, String sampleAge, String reportStatus, String reportNo, String inputerName, String auditorName, String apprvoerName, String printUserName);

    public List<AgreementPage> findReceivablesStatistics(Integer pageNo, Integer pageSize, String accountType, String contractNo, String entrustCompanyName, String projectName);

    List<WaterAccountStatisticsPage> entrustWaterAccountStatisticsSql(Integer pageNo, Integer pageSize, String entrustSn, String startDate, String endDate, String entrustDate, String inputTime, String capitalAccountNo, String inputerName, String entrustCompanyName, String projectName, String entrustStatus, String accountKinds, String testDepartment, String managmentDepartment, String acceptanceMan, String sampleName, String accountType);

}