package com.service.entrust.impl;

import com.common.*;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.common.BaseSampleDao;
import com.dao.common.SupervisionUnitDao;
import com.dao.common.TemplateInfoDao;
import com.dao.common.TestParameterDao;
import com.dao.entrust.*;
import com.dao.page.*;
import com.dao.sys.SysUserDao;
import com.model.*;
import com.service.common.RunningNumService;
import com.service.common.SignNameService;
import com.service.entrust.TestReportInfoService;
import com.service.sys.SysCodeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service("testReportInfoService")
public class TestReportInfoServiceImpl extends BaseServiceImpl<TestReportInfo>
        implements TestReportInfoService {


    @Autowired
    private TestReportInfoDao testReportInfoDao;
    @Autowired
    private ROperationRecordDao rOperationRecordDao;
    @Autowired
    private SysCodeService sysCodeService;
    @Autowired
    private SignNameService signNameService;
    @Autowired
    private EntrustDetailDao entrustDetailDao;
    @Autowired
    private EntrustInfoDao entrustInfoDao;
    @Autowired
    private TwoDInfoDao twoDInfoDao;
    @Autowired
    private EOperationRecordDao eOperationRecordDao;
    @Autowired
    private BaseSampleDao baseSampleDao;
    @Autowired
    private SupervisionUnitDao supervisionUnitDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RunningNumService runningNumService;
    @Autowired
    private SampleReportDao sampleReportDao;
    @Autowired
    private EntrustMoDetailDao entrustMoDetailDao;
    @Autowired
    private TestParameterDao testParameterDao;
    @Autowired
    private TemplateInfoDao templateInfoDao;

	/*@Override
	@Resource(name = "testReportInfoDao")
	protected void initBaseDAO(BaseDao<TestReportInfo> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<TestReportInfo> findJsonPageByCriteria(
			JsonPager<TestReportInfo> jp, TestReportInfo t) {
		return null;
	}*/

    @Override
    public List<TestReportInfoPage> findReportInfo(String strEDetailId,
                                                   String strStatus) {
        List<String> reportList = new ArrayList<String>();
        List<SampleReport> srList = sampleReportDao.findByProperty(
                "entrustDetailId", strEDetailId);
        for (SampleReport sr : srList) {
            if (reportList.contains(sr.getReportId())) {
                continue;
            } else {
                reportList.add(sr.getReportId());
            }
        }
        // 报告信息
        List<TestReportInfoPage> triLPageist = new ArrayList<TestReportInfoPage>();
        DetachedCriteria dcTri = DetachedCriteria
                .forClass(TestReportInfo.class);
        if (reportList != null && reportList.size() > 0) {
            dcTri.add(Property.forName("reportId").in(reportList));
        }
        if ("01".equals(strStatus) || "03".equals(strStatus)) {
            List<String> strStatusList = new ArrayList<String>();
            strStatusList.add("01");
            strStatusList.add("03");
            strStatusList.add("08");
            dcTri.add(Property.forName("reportStatus").in(strStatusList));
        }
        if ("02".equals(strStatus) || "06".equals(strStatus)) {
            List<String> strStatusList = new ArrayList<String>();
            strStatusList.add("02");
            strStatusList.add("06");
            dcTri.add(Property.forName("reportStatus").in(strStatusList));
        }
        if ("04".equals(strStatus) || "05".equals(strStatus)
                || "07".equals(strStatus)) {
            dcTri.add(Property.forName("reportStatus").eq(strStatus));
        }
        dcTri.addOrder(Order.desc("inputeTime"));

        List<TestReportInfo> triList = testReportInfoDao.findByCriteria(dcTri);

        for (String sourcesReportId : reportList) {
            List<TestReportInfo> report = testReportInfoDao.findByProperty(
                    "sourcesReportId", sourcesReportId);
            if (report.size() > 0) {
                for (TestReportInfo testReportInfo : report) {
                    triList.add(testReportInfo);
                }
            }
        }

        for (TestReportInfo tri : triList) {
            TestReportInfoPage triPage = new TestReportInfoPage();
            triPage.setReportId(tri.getReportId());
            triPage.setReportNo(tri.getReportNo());
            triPage.setReportDate(tri.getReportDate());
            triPage.setReportName(tri.getReportName());
            triPage.setReportProjectName(tri.getReportProjectName());
            triPage.setReportCompanyName(tri.getReportCompanyName());
            triPage.setQcNumber(tri.getQcNumber());
            triPage.setReportPath(tri.getReportPath());
            triPage.setReportStatus(tri.getReportStatus());
            triPage.setReportStatusName(sysCodeService.findCodeName(
                    "REPORT_STATUS", tri.getReportStatus()));
            triPage.setSampleNo(tri.getSampleNo());
            triPage.setProjectParts(tri.getProjectParts());
            triPage.setTestResult(tri.getTestResult());
            triPage.setReportConclusion(tri.getReportConclusion());
            triPage.setPrintNum(tri.getPrintNum());
            triPage.setInputer(tri.getInputer());
            if (tri.getUpdater() != null && !"".equals(tri.getUpdater())) {
                SysUser su = sysUserDao.findById(tri.getUpdater());
                triPage.setInputeName(su.getUserName());
            }
            if (tri.getInputer() != null && !"".equals(tri.getInputer())) {
                SysUser su = sysUserDao.findById(tri.getInputer());
                triPage.setInputeName(su.getUserName());
            }
            triPage.setInputeTime(tri.getInputeTime());
            triPage.setTemplateInfoId(tri.getTemplateInfoId());
            triPage.setReportRemark(tri.getReportRemark());
            if (!CommonMethod.isNull(tri.getUpdater())) {
                List<SignName> inSnList = signNameService.findByProperty(
                        "userId", tri.getUpdater());
                String inputerSignPath = "";
                if (inSnList != null && inSnList.size() > 0) {
                    inputerSignPath = inSnList.get(0).getPathSupply() + "/"
                            + inSnList.get(0).getSignatureName();
                }
                triPage.setInputerSignPath(inputerSignPath);
                if (tri.getUpdater() != null && !"".equals(tri.getUpdater())) {
                    SysUser suUpdater = sysUserDao.findById(tri.getUpdater());
                    triPage.setUpdateName(suUpdater.getUserName());
                }
            }
            triPage.setUpdater(tri.getUpdater());
            triPage.setUpdateTime(tri.getUpdateTime());
            triPage.setAuditor(tri.getAuditor());
            triPage.setAuditTime(tri.getAuditTime());
            triPage.setApprover(tri.getApprover());
            triPage.setApproveTime(tri.getApproveTime());
            triPage.setDistribute(tri.getDistribute());
            triPage.setDistributeTime(tri.getDistributeTime());
            if (!CommonMethod.isNull(tri.getDistribute())) {
                SysUser suDistribute = sysUserDao.findById(tri.getDistribute());
                triPage.setDistributeName(suDistribute.getUserName());
            }
            // 获取批准人签名
            if (!CommonMethod.isNull(tri.getApprover())) {
                List<SignName> snList = signNameService.findByProperty(
                        "userId", tri.getApprover());
                String approverSignPath = "";
                if (snList != null && snList.size() > 0) {
                    approverSignPath = snList.get(0).getPathSupply() + "/"
                            + snList.get(0).getSignatureName();
                }
                triPage.setApproverSignPath(approverSignPath);
                SysUser suApprover = sysUserDao.findById(tri.getApprover());
                triPage.setApproverName(suApprover.getUserName());
            }
            // 获取审核人签名
            if (!CommonMethod.isNull(tri.getAuditor())) {
                List<SignName> snList = signNameService.findByProperty(
                        "userId", tri.getAuditor());
                String auditSignPath = "";
                if (snList != null && snList.size() > 0) {
                    auditSignPath = snList.get(0).getPathSupply() + "/"
                            + snList.get(0).getSignatureName();
                }
                triPage.setAuditSignPath(auditSignPath);
                SysUser suAuditor = sysUserDao.findById(tri.getAuditor());
                triPage.setAuditorName(suAuditor.getUserName());

            }
            // 获取检测人签名
            if (!CommonMethod.isNull(tri.getTester())) {
                List<SignName> snList = signNameService.findByProperty(
                        "userId", tri.getTester());
                String testSignPath = "";
                if (snList != null && snList.size() > 0) {
                    testSignPath = snList.get(0).getPathSupply() + "/"
                            + snList.get(0).getSignatureName();
                }
                triPage.setTesterSignPath(testSignPath);
                SysUser suTester = sysUserDao.findById(tri.getTester());
                triPage.setTesterName(suTester.getUserName());
                triPage.setTester(tri.getTester());
            }
            // 获取二维码路径
            List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId",
                    tri.getReportId());
            String twodInfoId = "";
            if (tdList != null && tdList.size() > 0) {
                twodInfoId = tdList.get(0).getTwodInfoId();
            }
            // 正式库地址
            // String twoDInfoUrl =
            // "http://171.221.206.40:8089/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;
            // 测试库地址
            // String twoDInfoUrl =
            // "http://218.6.168.103:9000/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;

            // String twoDInfoUrl = this.getTwoDInfoUrl(twodInfoId);

            String twoDInfoUrl = BookingConfig.getInstance().getValue(
                    "twoDInfoUrl");
            triPage.setTwoDInfoUrl(twoDInfoUrl + twodInfoId);

            // 查询样品报告关系
            List<SampleReport> sReportList = sampleReportDao.findByProperty(
                    "reportId", tri.getReportId());
            List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
            for (SampleReport sRepor : sReportList) {
                SampleReportPage sRPage = new SampleReportPage();
                sRPage.setSampleReportId(sRepor.getSampleReportId());
                sRPage.setReportId(sRepor.getReportId());
                sRPage.setEntrustDetailId(sRepor.getEntrustDetailId());
                sRPage.setSampleNo(sRepor.getSampleNo());
                sRPage.setInvalidStatus(sRepor.getInvalidStatus());
                sRPage.setProjectPart(sRepor.getProjectPart());
                sRPage.setMoldingDate(sRepor.getMoldingDate());
                sRPage.setSampleAge(sRepor.getSampleAge());
                sRPage.setSampleId(sRepor.getSampleId());
                sRPage.setSampleName(sRepor.getSampleName());
                sRPage.setSampleLevel(sRepor.getSampleLevel());
                sRPage.setSampleSource(sRepor.getSampleSource());
                sRPage.setSampleType(sRepor.getSampleType());
                sRPage.setEntrustId(sRepor.getEntrustId());
                sRPage.setSampleState(sRepor.getSampleState());
                sRPage.setSampleTestDate(sRepor.getSampleTestDate());
                srPageList.add(sRPage);
            }
            triPage.setSampleReportList(srPageList);

            // if("01".equals(tri.getReportStatus())){//报告状态为：报告未录入时获取模板信息
            // 委托报告明细信息
            EntrustDetails eDetails = entrustDetailDao.findById(strEDetailId);
            // 样品信息
            List<TemplateInfo> ti = templateInfoDao.findByProperty("sampleId",
                    eDetails.getSampleId());
            List<TemplateInfoPage> tipage = new ArrayList<TemplateInfoPage>();
            for (TemplateInfo temp : ti) {
                TemplateInfoPage tpage = new TemplateInfoPage();
                tpage.setTemplateName(temp.getTemplateName() == null ? ""
                        : temp.getTemplateName());
                tpage.setTemplatePath(temp.getTemplatePath() == null ? ""
                        : temp.getTemplatePath());
                tpage.setInputer(temp.getInputer() == null ? "" : temp
                        .getInputer());
                tpage.setInputeTime(temp.getInputeTime() == null ? "" : temp
                        .getInputeTime());
                tpage.setSampleId(temp.getSampleId() == null ? "" : temp
                        .getSampleId());
                tpage.setSnRule(temp.getSnRule() == null ? "" : temp
                        .getSnRule());
                tpage.setTemplateInfoId(temp.getTemplateInfoId() == null ? ""
                        : temp.getTemplateInfoId());
                tpage.setTemplateViewName(temp.getTemplateViewName() == null ? ""
                        : temp.getTemplateViewName());
                tipage.add(tpage);
            }
            triPage.setTemplateInfoList(tipage);
            /*
             * //获取模板名称
             * triPage.setTemplateName(temp.getTemplateName()==null?"":temp
             * .getTemplateName()); //获取模板路径
             * triPage.setTemplatePath(temp.getTemplatePath
             * ()==null?"":temp.getTemplatePath());
             */

            // testReportInfoDao.findByProperty("sourecs", tr)
            triLPageist.add(triPage);
            // }
        }

        return triLPageist;
    }

    @Override
    public void saveTReportInfo(TestReportInfoPage triPage, String userId) {

        // 报告数据
        TestReportInfo tri = testReportInfoDao.findById(triPage.getReportId());
        tri.setReportPath(triPage.getReportPath());
        // tri.setReportDate(triPage.getReportDate());
        tri.setReportStatus(triPage.getReportStatus());
        // tri.setTestResult(triPage.getTestResult());
        // tri.setReportConclusion(triPage.getReportConclusion());
        tri.setReportTestData(triPage.getReportTestData());
        tri.setTemplateInfoId(triPage.getTemplateInfoId());
        if ("02".equals(triPage.getReportStatus())) {
            tri.setUpdater(userId);
            tri.setUpdateTime(CommonMethod.getDate());
        } else if ("05".equals(triPage.getReportStatus())) {
            String reportNo = "";
            if (CommonMethod.isNull(tri.getReportNo())) {
                // 报告数据
                List<SampleReport> srList = sampleReportDao.findByProperty(
                        "reportId", tri.getReportId());
                // 样品ID
                String sampleId = srList.get(0).getSampleId();
                // 样品信息
                BaseSample bs = baseSampleDao.findById(sampleId);
                reportNo = getNextReportSn(bs.getSnRule());
            } else {
                reportNo = triPage.getReportNo();
            }
            tri.setReportNo(reportNo);
            tri.setApprover(userId);
            tri.setApproveTime(CommonMethod.getDate());
        } else if ("04".equals(triPage.getReportStatus())) {
            tri.setAuditor(userId);
            tri.setAuditTime(CommonMethod.getDate());
        } else if ("07".equals(triPage.getReportStatus())) {
            tri.setPrintNum(tri.getPrintNum() + 1);
            tri.setDistribute(userId);
            tri.setDistributeTime(CommonMethod.getDate());

            saveEntrustStatus(tri, userId);
        }
        testReportInfoDao.update(tri);

        ReportOperationRecord roRecord = new ReportOperationRecord();
        roRecord.setOperationRecordId(CommonMethod.getNewKey());
        roRecord.setReportId(triPage.getReportId());
        roRecord.setOperation(triPage.getReportStatus());// 报告操作状态
        roRecord.setInputer(userId);
        roRecord.setInputeTime(CommonMethod.getDate());
        rOperationRecordDao.save(roRecord);

        // "02"待批准"03"批准"05"审核时修改二维码信息
        if ("02".equals(triPage.getReportStatus())
                || "04".equals(triPage.getReportStatus())
                || "05".equals(triPage.getReportStatus())) {
            TwoDInfo td = new TwoDInfo();
            td.setReportDate(triPage.getReportDate());
            td.setTestResult(triPage.getTestResult());
            td.setReportConclusion(triPage.getReportConclusion());

            if ("02".equals(triPage.getReportStatus())) {// 报告录入时，生成二维码数据
                // 委托报告明细信息
                EntrustDetails eDetails = entrustDetailDao.findById(triPage
                        .getEntrustDetailId());

                EntrustSavePage esPage = new EntrustSavePage();
                esPage.setEntrustId(eDetails.getEntrustId());
                // List<EntrustSavePage> esPageList =
                // entrustInfoDao.findEntrustInfoListSQL(esPage);
                // EntrustSavePage esp = esPageList.get(0);
                td.setTwodInfoId(CommonMethod.getNewKey());
                td.setReportId(triPage.getReportId());
                //超过。。。。时间之后就更改
                String thisDate = "2018-06-21 00:00";
                String entrustDate = null;
                List<SampleReport> findByProperty = sampleReportDao.findByProperty("reportId", triPage.getReportId());
                for (SampleReport sampleReport : findByProperty) {
                    EntrustInfo entrustInfo = entrustInfoDao.findById(sampleReport.getEntrustId());
                    entrustDate = entrustInfo.getEntrustDate();
                }
                boolean compare_date = date.compare_date(entrustDate + " 00:01", thisDate);
                if (compare_date) {
                    td.setTestAgencyName("四川省建业工程质量检测有限公司");
                } else {
                    td.setTestAgencyName("四川省建业检验检测股份有限公司");
                    //td.setTestAgencyName("四川省建业工程质量检测有限公司");
                }
                td.setTestCategories(eDetails.getSampleSource());
                // td.setProjectName(esp.getProjectName());
                // td.setProjectAddress(esp.getProjectAddress());
                td.setProjectParts(tri.getProjectParts().replace("OO", "#"));
                td.setProjectParts(tri.getProjectParts().replace("PLUS", "+"));
                td.setReportNo(tri.getReportNo());
                td.setStandards(eDetails.getStandards());
                // td.setEntrustCompanyName(esp.getEntrustCompanyName());
                td.setTestName(tri.getReportName());
                // td.setInspectionMan(esp.getInspectionMan());
                // td.setWitnessMan(esp.getWitnessMan());
                td.setIsNew("01");// 新系统
                twoDInfoDao.save(td);
            }
            List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId",
                    triPage.getReportId());
            if (tdList != null && tdList.size() > 0) {
                List<TwoDInfo> tdList1 = twoDInfoDao.findByProperty(
                        "twodInfoId", td.getTwodInfoId());

                if (tdList1 != null && tdList1.size() > 0) {
                    twoDInfoDao.update(td);
                }
                /*
                 * TwoDInfo tdInfo =tdList.get(0);
                 * tdInfo.setReportDate(td.getReportDate());
                 * tdInfo.setTestResult(td.getTestResult());
                 * tdInfo.setReportConclusion(td.getReportConclusion());
                 * twoDInfoDao.update(td);
                 */
            } else {
                twoDInfoDao.save(td);
            }
        }
    }

    /**
     * 全部审核或者批准或者打印发放
     */
    @Override
    public void updateTestReportByReportStatus(TestReportInfoPage triPage,
                                               String userId, String backStatus) {
        String uuid = UuidUtil.getUUID();
        if (CommonMethod.isNull(triPage.getReportId())) {
            throw new BusinessException("fail,报告ID不能为空", "");
        }
        if ("03".equals(backStatus) || "06".equals(backStatus)) {
            // 报告数据
            TestReportInfo tri = testReportInfoDao.findById(triPage
                    .getReportId());
            tri.setReportStatus(backStatus);
            tri.setReportRemark(triPage.getReportRemark());
            tri.setUpdater(userId);
            tri.setUpdateTime(CommonMethod.getDate());
        } else {
            // 拿到项目的绝对路径
            String as = CopyFile.class.getResource("").getPath()
                    .replaceAll("%20", " ");
            String path = as.substring(1, as.indexOf("jyReport") + 19);
            String file1 = path + "/"
                    + triPage.getReportPath().replace("\\", "/");// 源文件
            String reportIdPath = null;
            String repath = null;
            if (triPage.getReportPath()
                    .substring(triPage.getReportPath().length() - 4)
                    .equalsIgnoreCase("xlsx")) {
                reportIdPath = file1.substring(file1.length() - 37,
                        file1.length() - 5);
                reportIdPath = file1.replace(reportIdPath,
                        triPage.getReportId());
                repath = triPage.getReportPath().substring(
                        triPage.getReportPath().length() - 37,
                        triPage.getReportPath().length() - 5);
                repath = triPage.getReportPath().replace(repath,
                        triPage.getReportId());
            } else {
                reportIdPath = file1.substring(file1.length() - 36,
                        file1.length() - 4);
                reportIdPath = file1.replace(reportIdPath,
                        triPage.getReportId());

                repath = triPage.getReportPath().substring(
                        triPage.getReportPath().length() - 36,
                        triPage.getReportPath().length() - 4);
                repath = triPage.getReportPath().replace(repath,
                        triPage.getReportId());
            }
            // 复制excel文件
            CopyFile.copyeExcel(file1, reportIdPath);
            // 添加一条新的数据
            TestReportInfo tr = new TestReportInfo();
            tr.setReportId(uuid);
            tr.setReportNo(triPage.getReportNo());
            tr.setReportDate(triPage.getReportDate());
            tr.setReportName(triPage.getReportName());
            tr.setInputer(userId);
            tr.setInputeTime(CommonMethod.getDate());
            tr.setReportPath(repath);
            tr.setReportStatus(backStatus);
            tr.setSampleNo(triPage.getSampleNo());
            tr.setTestResult(triPage.getTestResult());
            tr.setReportConclusion(triPage.getReportConclusion());
            tr.setReportCompanyName(triPage.getReportCompanyName());
            tr.setReportProjectName(triPage.getReportProjectName());
            tr.setReportTestData(triPage.getReportTestData());
            tr.setReportRemark(triPage.getReportRemark());
            tr.setProjectParts(triPage.getProjectParts());
            tr.setSampleTestDate(triPage.getSampleTestDate());
            tr.setTemplateInfoId(triPage.getTemplateInfoId());
            testReportInfoDao.save(tr);

            List<SampleReport> sampleReport = sampleReportDao.findByProperty(
                    "reportId", triPage.getReportId());
            for (SampleReport sr : sampleReport) {
                sr.setReportId(uuid);
                sampleReportDao.update(sr);
            }

            // 报告数据
            TestReportInfo tri = testReportInfoDao.findById(triPage
                    .getReportId());
            tri.setReportStatus(triPage.getReportStatus());
            tri.setReportRemark(triPage.getReportRemark());
            tri.setUpdater(userId);
            tri.setSourcesReportId(uuid);
            List<TestReportInfo> info = testReportInfoDao.findByProperty(
                    "sourcesReportId", triPage.getReportId());
            for (Integer i = 1; i <= info.size() + 1; i++) {
                tri.setReportNo(triPage.getReportNo() + "-0" + i);
            }
            List<TestReportInfo> report = testReportInfoDao.findByProperty(
                    "sourcesReportId", triPage.getReportId());
            for (TestReportInfo testReportInfo : report) {
                testReportInfo.setSourcesReportId(uuid);
            }

            testReportInfoDao.update(tri);
        }

    }

    /**
     * 全部审核或者批准或者打印发放
     */
    @Override
    public void updateTestReport(TestReportInfoPage triPage, String userId) {

        if (CommonMethod.isNull(triPage.getReportId())) {
            throw new BusinessException("fail,报告ID不能为空", "");
        }
        // 报告数据
        TestReportInfo trInfo = testReportInfoDao.findById(triPage
                .getReportId());
        trInfo.setReportId(triPage.getReportId());
        trInfo.setReportNo(triPage.getReportNo());
        trInfo.setReportName(triPage.getReportName());
        trInfo.setUpdater(userId);
        trInfo.setReportProjectName(triPage.getReportProjectName());
        trInfo.setReportCompanyName(triPage.getReportCompanyName());
        trInfo.setReportTestData(triPage.getReportDate());
        trInfo.setReportDate(triPage.getReportDate());
        trInfo.setQcNumber(triPage.getQcNumber());
        trInfo.setTestResult(triPage.getTestResult());
        trInfo.setUpdateTime(CommonMethod.getDate());// 未打印
        trInfo.setReportConclusion(triPage.getReportConclusion());
        testReportInfoDao.update(trInfo);
    }

    /**
     * 全部审核或者批准或者打印发放
     */
    @Override
    public void saveAllReportApAndAu(Collection<TestReportInfoPage> coll,
                                     String userId) {
        for (TestReportInfoPage triPage : coll) {
            if (CommonMethod.isNull(triPage.getReportId())) {
                throw new BusinessException("fail,报告ID不能为空", "");
            }
            // 报告数据
            TestReportInfo tri = testReportInfoDao.findById(triPage
                    .getReportId());
            tri.setReportStatus(triPage.getReportStatus());
            if ("05".equals(triPage.getReportStatus())) {
                String reportNo = "";
                if (CommonMethod.isNull(tri.getReportNo())) {
                    // 报告数据
                    List<SampleReport> srList = sampleReportDao.findByProperty(
                            "reportId", tri.getReportId());
                    // 样品ID
                    String sampleId = srList.get(0).getSampleId();
                    // 样品信息
                    BaseSample bs = baseSampleDao.findById(sampleId);
                    reportNo = getNextReportSn(bs.getSnRule());
                } else {
                    reportNo = triPage.getReportNo();
                }
                tri.setReportNo(reportNo);
                tri.setApprover(userId);
                tri.setApproveTime(CommonMethod.getDate());
            } else if ("04".equals(triPage.getReportStatus())) {
                tri.setAuditor(userId);
                tri.setAuditTime(CommonMethod.getDate());
            } else if ("07".equals(triPage.getReportStatus())) {// 打印次数+1
                tri.setPrintNum(tri.getPrintNum() + 1);
                tri.setDistribute(userId);
                tri.setDistributeTime(CommonMethod.getDate());

                saveEntrustStatus(tri, userId);
            } else {
                if (!CommonMethod.isNull(triPage.getReportNo())) {
                    List<TestReportInfo> triList = testReportInfoDao
                            .findByProperty("reportNo", triPage.getReportNo());
                    if (triList != null && triList.size() > 0) {
                        throw new BusinessException("报告编号:"
                                + triPage.getReportNo() + "已经存在，请确认！", "");
                    }
                }
                tri.setReportNo(triPage.getReportNo());
                tri.setTester(triPage.getTester());// 检测人
                tri.setReportDate(triPage.getReportDate());// 报告日期
                tri.setTestResult(triPage.getTestResult());// 检测结果
                tri.setQcNumber(triPage.getQcNumber());// 质量监督号
                tri.setReportCompanyName(triPage.getReportCompanyName());// 报告单位名称
                tri.setReportProjectName(triPage.getReportProjectName());// 报告工程名称
            }

            testReportInfoDao.update(tri);

            List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId",
                    tri.getReportId());
            for (TwoDInfo td : tdList) {
                td.setReportDate(tri.getReportDate());
                td.setTestResult(tri.getTestResult());
                twoDInfoDao.update(td);
            }
            System.out.println("1");
        }
    }

    /**
     * 修改报告打印次数
     */
    @Override
    public void saveReportPrintNum(Collection<TestReportInfoPage> coll,
                                   String userId) {
        for (TestReportInfoPage triPage : coll) {
            if (CommonMethod.isNull(triPage.getReportId())) {
                throw new BusinessException("fail,报告ID不能为空", "");
            }
            // 报告数据
            TestReportInfo tri = testReportInfoDao.findById(triPage
                    .getReportId());
            // 打印次数+1
            tri.setPrintNum(tri.getPrintNum() + 1);
            tri.setDistribute(userId);
            tri.setDistributeTime(CommonMethod.getDate());
            testReportInfoDao.update(tri);
        }
    }

    /**
     * 根据报告发放情况修改委托记录状态
     *
     * @param tri
     * @param userId
     */
    private void saveEntrustStatus(TestReportInfo tri, String userId) {
        // 报告数据
        List<SampleReport> srList = sampleReportDao.findByProperty("reportId",
                tri.getReportId());
        String entrustId = srList.get(0).getEntrustId();
        // 委托ID下的所有报告数据
        DetachedCriteria dcSr = DetachedCriteria.forClass(SampleReport.class);
        dcSr.add(Property.forName("reportId").ne(tri.getReportId()));
        dcSr.add(Property.forName("entrustId").eq(entrustId));
        List<SampleReport> srEnturstList = sampleReportDao.findByCriteria(dcSr);

        boolean isAllPrint = false;

        List<String> reportIdList = new ArrayList<String>();
        boolean isAllHaveReport = true;
        if (srEnturstList != null && srEnturstList.size() > 0) {
            for (SampleReport srEnturst : srEnturstList) {
                if (CommonMethod.isNull(srEnturst.getReportId())) {
                    isAllHaveReport = false;
                    break;
                } else {
                    if (!reportIdList.contains(srEnturst.getReportId())) {
                        reportIdList.add(srEnturst.getReportId());
                    }
                }
            }
        } else {
            isAllPrint = true;
        }

        if (!isAllHaveReport) {
            return;
        }
        // //上委托明细
        // EntrustDetails ed =
        // entrustDetailDao.findById(srList.get(0).getEntrustDetailId());
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        // //上委托明细LIST
        // List<EntrustDetails> edList =
        // entrustDetailDao.findByProperty("entrustId", ed.getEntrustId());
        //

        // //上委托明细ID LIST
        // List<String> eDetailIdList = new ArrayList<String>();
        // for(EntrustDetails edTmp : edList){
        // eDetailIdList.add(edTmp.getEntrustDetailId());
        // }
        if (!isAllPrint) {
            // 查找委托中的报告是否全部打印
            DetachedCriteria dcTri = DetachedCriteria
                    .forClass(TestReportInfo.class);
            dcTri.add(Property.forName("reportId").in(reportIdList));
            dcTri.add(Property.forName("reportStatus").ne("07"));
            List<TestReportInfo> triList = testReportInfoDao
                    .findByCriteria(dcTri);
            if (triList != null && triList.size() > 0) {
            } else {
                isAllPrint = true;
            }
        }

        // 委托中所有报告已经打印
        if (isAllPrint) {
            if ("04".equals(ei.getEntrustStatus())) {// 该委托已收款
                ei.setEntrustStatus("05");// 该委托已完结
                ei.setUpdater(userId);
                ei.setUpdateTime(CommonMethod.getDate());
                entrustInfoDao.update(ei);

                EntrustOperationRecord eor = new EntrustOperationRecord();
                eor.setOperationRecordId(CommonMethod.getNewKey());
                eor.setEntrustId(ei.getEntrustId());
                eor.setOperation("06");// 委托已完结
                eor.setInputer(userId);
                eor.setInputeTime(CommonMethod.getDate());
                eOperationRecordDao.save(eor);
            } else {

                List<String> list = new ArrayList<String>();
                // 拿到明细ID 去查 对应的样品
                List<EntrustDetails> findByProperty = entrustDetailDao
                        .findByProperty("entrustId", entrustId);
                for (EntrustDetails entrustDetails : findByProperty) {
                    List<SampleReport> entrustDetailId = sampleReportDao
                            .findByProperty("entrustDetailId",
                                    entrustDetails.getEntrustDetailId());
                    for (SampleReport sampleReport : entrustDetailId) {
                        if (sampleReport.getReportId() == null
                                || sampleReport.getReportId() == "") {
                            list.add("");
                            break;
                        }
                        list.add(sampleReport.getReportId());
                    }

                }
                // 遍历所有的样品 没有发报告的 就返回true
                boolean st = false;
                for (String str : list) {
                    if (str == null || str == "") {
                        st = true;//
                        break;
                    }
                }
                // 存在没有发送报告的我们就 不更改委托的状态
                if (st) {
                } else {
                    ei.setEntrustStatus("03");// 该委托报告已全部发放
                }

                ei.setUpdater(userId);
                ei.setUpdateTime(CommonMethod.getDate());
                entrustInfoDao.update(ei);

                EntrustOperationRecord eor = new EntrustOperationRecord();
                eor.setOperationRecordId(CommonMethod.getNewKey());
                eor.setEntrustId(ei.getEntrustId());
                eor.setOperation("07");// 该委托报告已全部发放
                eor.setInputer(userId);
                eor.setInputeTime(CommonMethod.getDate());
                eOperationRecordDao.save(eor);
            }
        }
    }

    @Override
    public List<ReportTemplateInfoPage> findReportTitleInfo(String strReportId) {
        List<ReportTemplateInfoPage> triInfoPageist = new ArrayList<ReportTemplateInfoPage>();
        ReportTemplateInfoPage triInfoPage = new ReportTemplateInfoPage();
        // 报告数据
        TestReportInfo tri = testReportInfoDao.findById(strReportId);
        triInfoPage.setReportId(tri.getReportId());
        triInfoPage.setReportNo(tri.getReportNo());
        triInfoPage.setReportName(tri.getReportName());
        triInfoPage.setSampleTestDate(tri.getSampleTestDate());
        triInfoPage.setEntrustCompanyName(tri.getReportCompanyName());
        triInfoPage.setProjectName(tri.getReportProjectName());
        triInfoPage.setReportDate(tri.getReportDate());
        triInfoPage.setQcNumber(tri.getQcNumber());

        List<SampleReport> srList = sampleReportDao.findByProperty("reportId",
                tri.getReportId());
        SampleReport sr = srList.get(0);
        String entrustDetailId = sr.getEntrustDetailId();
        triInfoPage.setProjectParts(sr.getProjectPart());
        // 上委托明细
        EntrustDetails ed = entrustDetailDao.findById(entrustDetailId);
        // triInfoPage.setProjectParts(ed.getProjectPart());
        triInfoPage.setSampleSource(ed.getSampleSource());
        triInfoPage.setSampleSourceName(sysCodeService.findCodeName(
                "SAMPLE_SOURCES", ed.getSampleSource()));
        // triInfoPage.setSampleNo(ed.getSampleNo());
        // 样品信息
        BaseSample bs = baseSampleDao.findById(ed.getSampleId());
        triInfoPage.setSampleId(bs.getSampleId());
        triInfoPage.setSampleName(bs.getSampleName());
        triInfoPage.setSampleId(bs.getSampleId());
        triInfoPage.setSpecificationsModels(bs.getSpecificationsModels());
        triInfoPage.setStandards(bs.getStandards());
        triInfoPage.setTestResult(bs.getTestResult());// 检测结果
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(ed.getEntrustId());
        triInfoPage.setEntrustCompanyId(ei.getEntrustCompanyId());
        triInfoPage.setProjectId(ei.getProjectId());
        triInfoPage.setSupervisionUnitId(ei.getSupervisionUnitId());
        triInfoPage.setEntrustSn(ei.getEntrustSn());
        triInfoPage.setEntrustDate(ei.getEntrustDate());
        triInfoPage.setSampleFeeder(ei.getLinkMan());// 送样人=委托联系人
        triInfoPage.setWitnessMan(ei.getWitnessMan());
        // 见证人
        DetachedCriteria dcEmd = DetachedCriteria
                .forClass(EntrustMoneyDetails.class);
        dcEmd.add(Property.forName("entrustId").eq(ed.getEntrustId()));
        // dcEmd.add(Property.forName("departmentId").eq(ed.getDepartmentId()));
        // dcEmd.add(Property.forName("sampleId").eq(ed.getSampleId()));
        // dcEmd.add(Property.forName("testParameterId").eq(tri.getTestParameterId()));
        // dcEmd.add(Property.forName("sampleSource").eq(ed.getSampleSource()));
        // List<EntrustMoneyDetails> emdList =
        // entrustMoDetailDao.findByCriteria(dcEmd);
        // if(emdList!=null && emdList.size()>0){
        // triInfoPage.setWitnessMan(emdList.get(0).getWitnessMan());
        // }else{
        // triInfoPage.setWitnessMan("");
        // }
        // triInfoPage.setWitnessMan(ed.getWitnessMan());
        // 委托单位信息
        // EntrustCompany ec =
        // entrustCompanyDao.findById(ei.getEntrustCompanyId());
        // triInfoPage.setEntrustCompanyName(ec.getEntrustCompanyName());
        // 工程信息
        // ProjectInfo pInfo = projectInfoDao.findById(ei.getProjectId());
        // triInfoPage.setProjectName(pInfo.getProjectName());
        if (!CommonMethod.isNull(ei.getSupervisionUnitId())) {
            // 监理单位
            SupervisionUnit su = supervisionUnitDao.findById(ei
                    .getSupervisionUnitId());
            triInfoPage.setSupervisionUnitName(su.getSupervisionUnitName());
            // triInfoPage.setWitnessMan(su.getWitnesses());
        }
        // 委托金额明细
        String testParamter = "";
        List<EntrustMoneyDetails> emList = entrustMoDetailDao.findByProperty(
                "entrustDetailId", entrustDetailId);
        for (EntrustMoneyDetails em : emList) {
            TestParameter tp = testParameterDao.findById(em
                    .getTestParameterId());
            if (CommonMethod.isNull(testParamter)) {
                testParamter = tp.getTestParameterName();
            } else {
                testParamter = testParamter + "|" + tp.getTestParameterName();
            }
        }
        triInfoPage.setTestParameter(testParamter);
        triInfoPageist.add(triInfoPage);
        return triInfoPageist;
    }

    @Override
    public void saveSampleReportInfo(SampleReportRelationPage srrPage,
                                     String userId) {

        String entrustId = "";
        List<SampleReportPage> sampleReportRelationList = srrPage
                .getSampleReportRelationList();
        for (SampleReportPage sampleReportRelation : sampleReportRelationList) {
            SampleReport sr = sampleReportDao.findById(sampleReportRelation
                    .getSampleReportId());
            sr.setInvalidStatus(sampleReportRelation.getInvalidStatus());
            String str = sampleReportRelation.getProjectPart().replace("OO",
                    "#");
            str = str.replace("PLUS", "+");
            sr.setProjectPart(str);
            sr.setSampleLevel(sampleReportRelation.getSampleLevel());
            sr.setSampleAge(sampleReportRelation.getSampleAge());
            sr.setMoldingDate(sampleReportRelation.getMoldingDate());
            sr.setSampleType(sampleReportRelation.getSampleType());
            sr.setSampleTestDate(sampleReportRelation.getSampleTestDate());
            sr.setSampleState(sampleReportRelation.getSampleState());
            sampleReportDao.update(sr);

            entrustId = sr.getEntrustId();
        }

        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        if (ei.getEntrustStatus().equals("01")) {
            ei.setEntrustStatus("02");
            ei.setUpdater(userId);
            ei.setUpdateTime(CommonMethod.getDate());
            entrustInfoDao.update(ei);
        } else {
            ei.setUpdater(userId);
            ei.setUpdateTime(CommonMethod.getDate());
            entrustInfoDao.update(ei);
        }
        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(entrustId);
        eor.setOperation("04");// 录入上委托明细
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);

        // 报告明细
        List<TestReportInfoPage> testReportInfoPageList = srrPage
                .getTestReportInfoPageList();

        for (TestReportInfoPage triPage : testReportInfoPageList) {
            String reportId = CommonMethod.getNewKey();

            List<SampleReportPage> sampleReportList = triPage
                    .getSampleReportList();
            for (SampleReportPage srPage : sampleReportList) {
                SampleReport sr = sampleReportDao.findById(srPage
                        .getSampleReportId());
                sr.setReportId(reportId);
                sampleReportDao.update(sr);
            }
            SampleReportPage srPage = sampleReportList.get(0);
            TestReportInfo trInfo = new TestReportInfo();
            // 样品信息
            BaseSample bs = baseSampleDao.findById(srPage.getSampleId());

            if (CommonMethod.isNull(bs.getSnRule())) {
                throw new BusinessException(bs.getSampleName()
                        + "还没有设置报告编号规则，请确认！", "");
            }
            String reportSn = triPage.getReportNo();
            // reportSn = getNextReportSn(snRule);
            // 样品名称
            String ypName = bs.getSampleName();
            // 报告名称
            String reportName = ypName + "报告";
            trInfo.setReportId(reportId);
            trInfo.setReportNo(reportSn);
            trInfo.setReportName(reportName);
            trInfo.setReportStatus("01");// 报告未录入
            trInfo.setProjectParts(srPage.getProjectPart());
            trInfo.setProjectParts(srPage.getProjectPart());
            String str = trInfo.getProjectParts().replace("OO", "#");
            str = str.replace("PLUS", "+");
            trInfo.setProjectParts(str);
            trInfo.setPrintNum(0);// 未打印
            trInfo.setInputer(userId);
            trInfo.setInputeTime(CommonMethod.getDate());
            trInfo.setSampleTestDate(srPage.getSampleTestDate());
            String strname = triPage.getReportProjectName().replace("OO", "#");
            strname = strname.replace("PLUS", "+");
            trInfo.setReportProjectName(strname);
            trInfo.setReportCompanyName(triPage.getReportCompanyName());
            trInfo.setReportDate(triPage.getReportDate());
            trInfo.setQcNumber(triPage.getQcNumber());
            trInfo.setTestResult(triPage.getTestResult());
            trInfo.setReportConclusion(triPage.getReportConclusion());
            testReportInfoDao.save(trInfo);

            ReportOperationRecord roRecord = new ReportOperationRecord();
            roRecord.setOperationRecordId(CommonMethod.getNewKey());
            roRecord.setReportId(reportId);
            roRecord.setOperation("01");// 录入报告记录
            roRecord.setInputer(userId);
            roRecord.setInputeTime(CommonMethod.getDate());
            rOperationRecordDao.save(roRecord);
        }
    }

    @Override
    public void updateSampleReportByReportId(SampleReportRelationPage srrPage,
                                             String userId) {
        // 报告明细
        List<TestReportInfoPage> testReportInfoPageList = srrPage
                .getTestReportInfoPageList();

        for (TestReportInfoPage triPage : testReportInfoPageList) {
            List<SampleReportPage> sampleReportList = triPage
                    .getSampleReportList();
            if (sampleReportList.size() > 0) {
                List<SampleReport> emList = sampleReportDao.findByProperty(
                        "entrustDetailId", sampleReportList.get(0)
                                .getEntrustDetailId());
                // 查询出的报告明细大于传入的表示是删除关系
                if (emList.size() > sampleReportList.size()) {
                    for (int i = 0; i < emList.size(); i++) {
                        // 数据库的报告样品明细id是否在传入的数据当中，不在删除关系
                        if (!this.equalsSampleReport(emList.get(i)
                                .getSampleReportId(), sampleReportList)) {
                            SampleReport sr = sampleReportDao.findById(emList
                                    .get(i).getSampleReportId());
                            sr.setReportId("null");
                            sampleReportDao.update(sr);
                        }
                    }
                } else {
                    for (SampleReportPage srPage : sampleReportList) {
                        SampleReport sr = sampleReportDao.findById(srPage
                                .getSampleReportId());
                        sr.setReportId(triPage.getReportId());
                        sampleReportDao.update(sr);
                    }
                }
            }
        }
    }

    public void updateSampleReportInfo(SampleReportRelationPage srrPage,
                                       String userId) {
        // 样品报告关系表
        List<SampleReportPage> testReportInfoPageList = srrPage
                .getSampleReportRelationList();

        for (SampleReportPage sampPage : testReportInfoPageList) {
            DetachedCriteria dcEmd = DetachedCriteria
                    .forClass(SampleReport.class);
            dcEmd.add(Property.forName("sampleReportId").eq(
                    sampPage.getSampleReportId()));
            dcEmd.add(Property.forName("entrustId").eq(sampPage.getEntrustId()));

            List<SampleReport> sampList = sampleReportDao.findByCriteria(dcEmd);

            for (SampleReport sample : sampList) {
                sample.setEntrustDetailId(sampPage.getEntrustDetailId());
                sample.setEntrustId(sampPage.getEntrustId());
                sample.setInvalidStatus(sampPage.getInvalidStatus());
                sample.setMoldingDate(sampPage.getMoldingDate());
                sample.setProjectPart(sampPage.getProjectPart());
                sample.setReportId(sampPage.getReportId());
                sample.setSampleAge(sampPage.getSampleAge());
                sample.setSampleId(sampPage.getSampleId());
                sample.setSampleLevel(sampPage.getSampleLevel());
                sample.setSampleName(sampPage.getSampleName());
                sample.setSampleNo(sampPage.getSampleNo());
                sample.setSampleReportId(sampPage.getSampleReportId());
                sample.setSampleSource(sampPage.getSampleSource());
                sample.setSampleState(sampPage.getSampleState());
                sample.setSampleTestDate(sampPage.getSampleTestDate());
                sample.setSampleType(sampPage.getSampleType());
                sampleReportDao.update(sample);
            }
        }
    }

    public boolean equalsSampleReport(String samplrId,
                                      List<SampleReportPage> sampleReportList) {
        for (SampleReportPage srPage : sampleReportList) {
            if (srPage.getSampleReportId().equals(samplrId)) {
                return true;
            }
        }
        return false;
    }

    public String getNextReportSn(String rule) {
        String ruleU = rule.toUpperCase();
        int sPlace = ruleU.indexOf("-");
        String ruleNo = ruleU.substring(0, sPlace - 1);

        Long sn = runningNumService.getNextNum(ruleNo, 1);
        String sz = "";

        String year = CommonMethod.getCurrentYear();

        if (ruleU.contains("YYYY")) {
            ruleU = ruleU.replace("YYYY", year);
        }

        if (ruleU.contains("YY")) {
            ruleU = ruleU.replace("YY", year.substring(2, 4));
        }
        if (ruleU.contains("Y")) {
            ruleU = ruleU.replace("Y", year.substring(3, 4));
        }

        if (ruleU.contains("DDD")) {
            sz = CommonMethod.getOrderDate(CommonMethod.getTime(), 3);
            ruleU = ruleU.replace("DDD", sz);
        }

        int firstIndex = ruleU.indexOf("*");
        int lastIndex = ruleU.lastIndexOf("*");

        String strBeReplace = ruleU.substring(firstIndex, lastIndex + 1);

        int length = strBeReplace.length();
        String zeroStr = "";
        for (int i = 0; i < length - sn.toString().length(); i++) {
            zeroStr = zeroStr + "0";
        }
        zeroStr = zeroStr + sn.toString();

        ruleU = ruleU.replace(strBeReplace, zeroStr);
        return ruleU;
    }

    @Override
    public void saveReportPrintInfo(String reportId, String printNum,
                                    String distributeTime, String reportStatus, String userid) {
        // 报告数据
        TestReportInfo tri = testReportInfoDao.findById(reportId);
        tri.setPrintNum(Integer.valueOf(printNum));
        tri.setDistributeTime(distributeTime);
        tri.setReportStatus(reportStatus);
        tri.setDistribute(userid);
        testReportInfoDao.update(tri);
    }

    @Override
    public List<TestReportInfoPage> sampleReportList(TestReportInfoPage triPage) {

        List<TestReportInfoPage> list = new ArrayList<TestReportInfoPage>();
			/*//返回二维码信息
			List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId",
					triPage.getReportId());
			String twodInfoId = "";
			if (tdList != null && tdList.size() > 0) {
				twodInfoId = tdList.get(0).getTwodInfoId();
			}
			// 正式库地址
			// String twoDInfoUrl =
			// "http://171.221.206.40:8089/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;
			// 测试库地址
			// String twoDInfoUrl =
			// "http://218.6.168.103:9000/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;
			// String twoDInfoUrl = this.getTwoDInfoUrl(twodInfoId);
			String twoDInfoUrl = BookingConfig.getInstance().getValue(
					"twoDInfoUrl");
			triPage.setTwoDInfoUrl(twoDInfoUrl + twodInfoId);*/
        List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
        List<SampleReport> findByProperty = sampleReportDao.findByProperty(
                "reportId", triPage.getReportId());
        for (SampleReport sr : findByProperty) {
            SampleReportPage srPage = new SampleReportPage();
            srPage.setSampleReportId(sr.getSampleReportId());
            srPage.setReportId(sr.getReportId());
            srPage.setEntrustDetailId(sr.getEntrustDetailId());
            srPage.setEntrustId(sr.getEntrustId());
            srPage.setSampleNo(sr.getSampleNo());
            srPage.setInvalidStatus(sr.getInvalidStatus());// 00作废，01正常
            srPage.setProjectPart(sr.getProjectPart());
            srPage.setMoldingDate(sr.getMoldingDate());
            srPage.setSampleAge(sr.getSampleAge());
            srPage.setSampleId(sr.getSampleId());
            srPage.setSampleName(sr.getSampleName());
            srPage.setSampleLevel(sr.getSampleLevel());
            srPage.setSampleSource(sr.getSampleSource());
            srPage.setSampleType(sr.getSampleType());
            srPage.setSampleTestDate(sr.getSampleTestDate());
            srPage.setSampleState(sr.getSampleState());
            srPageList.add(srPage);
        }
        triPage.setSampleReportList(srPageList);
        list.add(triPage);
        return list;

    }

    @Override
    public void updTestReport(TestReportInfoPage triPage, String userId) {
        if (CommonMethod.isNull(triPage.getReportId())) {
            throw new BusinessException("fail,报告ID不能为空", "");
        }
        // 报告数据
        TestReportInfo trInfo = testReportInfoDao.findById(triPage
                .getReportId());

        trInfo.setReportId(triPage.getReportId());
        trInfo.setReportNo(triPage.getReportNo());
        trInfo.setReportDate(triPage.getReportDate());
        trInfo.setReportName(triPage.getReportName());
        trInfo.setReportPath(triPage.getReportPath());
        trInfo.setReportStatus(triPage.getReportPath());
        trInfo.setSampleNo(triPage.getSampleNo());
        trInfo.setProjectParts(triPage.getProjectParts());
        trInfo.setTestResult(triPage.getTestResult());
        trInfo.setReportConclusion(triPage.getReportConclusion());
        trInfo.setPrintNum(triPage.getPrintNum());
        trInfo.setInputer(triPage.getInputer());
        trInfo.setInputeTime(triPage.getInputeTime());
        trInfo.setUpdater(userId);
        trInfo.setUpdateTime(CommonMethod.getDate());
        trInfo.setAuditor(triPage.getAuditor());
        trInfo.setAuditTime(triPage.getAuditTime());
        trInfo.setApprover(triPage.getApprover());
        trInfo.setApproveTime(triPage.getApproveTime());
        trInfo.setDistribute(triPage.getDistribute());
        trInfo.setDistributeTime(triPage.getDistributeName());
        trInfo.setTester(triPage.getTester());
        trInfo.setSampleTestDate(triPage.getSampleTestDate());
        trInfo.setQcNumber(triPage.getQcNumber());
        trInfo.setReportCompanyName(triPage.getReportCompanyName());
        trInfo.setReportProjectName(triPage.getReportProjectName());
        trInfo.setReportTestData(triPage.getReportTestData());
        trInfo.setTemplateInfoId(triPage.getTemplateInfoId());
        trInfo.setReportRemark(triPage.getReportRemark());
        trInfo.setSourcesReportId(triPage.getSourcesReportId());
		
		/*trInfo.setReportId(triPage.getReportId());
		trInfo.setReportNo(triPage.getReportNo());
		trInfo.setReportName(triPage.getReportName());
		trInfo.setUpdater(userId);
		trInfo.setReportProjectName(triPage.getReportProjectName());
		trInfo.setReportCompanyName(triPage.getReportCompanyName());
		trInfo.setReportTestData(triPage.getReportDate());
		trInfo.setReportDate(triPage.getReportDate());
		trInfo.setQcNumber(triPage.getQcNumber());
		trInfo.setTestResult(triPage.getTestResult());
		trInfo.setUpdateTime(CommonMethod.getDate());// 未打印
		trInfo.setReportConclusion(triPage.getReportConclusion());*/
        testReportInfoDao.update(trInfo);
    }

    @SuppressWarnings("unused")
    @Override
    public List<TestReportInfoPage> saveTwoReportList(String reportId) {
        List<TestReportInfoPage> ti = new ArrayList<TestReportInfoPage>();
        List<TwoDInfo> twoDInfo = twoDInfoDao.findByProperty("reportId", reportId);
        List<TestReportInfo> testReportInfo = testReportInfoDao.findByProperty("reportId", reportId);

        if (twoDInfo.size() <= 0) {
            //List<SampleReportPage> sampleReportList = triPage.getSampleReportList();
            // 报告录入时，生成二维码数据
            // 委托报告明细信息
            TestReportInfo tri = testReportInfoDao.findById(reportId);
            TwoDInfo td = new TwoDInfo();
            td.setReportId(tri.getReportId());
            td.setReportDate(tri.getReportDate());
            td.setTestResult(tri.getTestResult());
            td.setReportConclusion(tri.getReportConclusion());
            List<SampleReport> findByProperty = sampleReportDao.findByProperty("reportId", reportId);
            EntrustDetails eDetails = entrustDetailDao.findById(findByProperty.get(0)
                    .getEntrustDetailId());
            EntrustSavePage esPage = new EntrustSavePage();
            esPage.setEntrustId(eDetails.getEntrustId());
            // List<EntrustSavePage> esPageList =
            // entrustInfoDao.findEntrustInfoListSQL(esPage);
            // EntrustSavePage esp = esPageList.get(0);
            td.setTwodInfoId(CommonMethod.getNewKey());
            td.setReportId(reportId);
            //超过。。。。时间之后就更改
            String thisDate = "2018-06-21 00:00";
            String entrustDate = null;
            List<SampleReport> sampleList = sampleReportDao.findByProperty("reportId", reportId);
            for (SampleReport sampleReport : sampleList) {
                EntrustInfo entrustInfo = entrustInfoDao.findById(sampleReport.getEntrustId());
                entrustDate = entrustInfo.getEntrustDate();
            }
            boolean compare_date = date.compare_date(entrustDate + " 00:01", thisDate);
            if (compare_date) {
                td.setTestAgencyName("四川省建业工程质量检测有限公司");
            } else {
                td.setTestAgencyName("四川省建业检验检测股份有限公司");
                //td.setTestAgencyName("四川省建业工程质量检测有限公司");
            }
            td.setTestCategories(eDetails.getSampleSource());
            // td.setProjectName(esp.getProjectName());
            // td.setProjectAddress(esp.getProjectAddress());
            td.setProjectParts(tri.getProjectParts().replace("OO", "#"));
            td.setProjectParts(tri.getProjectParts().replace("PLUS", "+"));
            td.setReportNo(tri.getReportNo());
            td.setStandards(eDetails.getStandards());
            // td.setEntrustCompanyName(esp.getEntrustCompanyName());
            td.setTestName(tri.getReportName());
            // td.setInspectionMan(esp.getInspectionMan());
            // td.setWitnessMan(esp.getWitnessMan());
            td.setIsNew("01");// 新系统
            twoDInfoDao.save(td);
            List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId", reportId);
            String twodInfoId = "";
            if (tdList != null && tdList.size() > 0) {
                twodInfoId = tdList.get(0).getTwodInfoId();
            }
            // 正式库地址
            // String twoDInfoUrl =
            // "http://171.221.206.40:8089/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;
            // 测试库地址
            // String twoDInfoUrl =
            // "http://218.6.168.103:9000/reportManage/twoDInfoAction!twoDInfo.action?strTwoDInfoId="+twodInfoId;
            // String twoDInfoUrl = this.getTwoDInfoUrl(twodInfoId);
            String twoDInfoUrl = BookingConfig.getInstance().getValue(
                    "twoDInfoUrl");
            ti.get(0).setTwoDInfoUrl(twoDInfoUrl + twodInfoId);
        }

        return ti;
    }

    @Override
    protected void initBaseDAO(BaseDao<TestReportInfo> baseDao) {

    }

    @Override
    public JsonPager<TestReportInfo> findJsonPageByCriteria(JsonPager<TestReportInfo> jp, TestReportInfo testReportInfo) {
        return null;
    }
}