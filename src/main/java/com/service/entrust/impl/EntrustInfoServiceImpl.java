package com.service.entrust.impl;

import com.common.BookingConfig;
import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.BaseDepartmentDao;
import com.dao.common.BaseSampleDao;
import com.dao.common.TestParameterDao;
import com.dao.entrust.*;
import com.dao.finance.ReceivableEnDetailsDao;
import com.dao.page.*;
import com.dao.sys.SysUserDao;
import com.model.*;
import com.service.common.RunningNumService;
import com.service.entrust.EntrustInfoService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("entrustInfoService")
public class EntrustInfoServiceImpl extends BaseServiceImpl<EntrustInfo>
        implements EntrustInfoService {

    private static Logger sampleNumber = LoggerFactory.getLogger(EntrustInfoServiceImpl.class);


    @Autowired
    private EntrustInfoDao entrustInfoDao;

    @Autowired
    private EntrustMoDetailDao entrustMoDetailDao;
    @Autowired
    private ROperationRecordDao rOperationRecordDao;

    @Autowired
    private EOperationRecordDao eOperationRecordDao;

    @Autowired
    private TwoDInfoDao twoDInfoDao;

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BaseDepartmentDao baseDepartmentDao;
    @Autowired
    private BaseSampleDao baseSampleDao;
    @Autowired
    private TestParameterDao testParameterDao;
    @Autowired
    private TestReportInfoDao testReportInfoDao;
    @Autowired
    private EntrustDetailDao entrustDetailDao;
    @Autowired
    private RunningNumService runningNumService;
    @Autowired
    private ReceivableEnDetailsDao receivableEnDetailsDao;
    @Autowired
    private SampleReportDao sampleReportDao;

	/*@Override
	@Resource(name = "entrustInfoDao")
	protected void initBaseDAO(BaseDao<EntrustInfo> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<EntrustInfo> findJsonPageByCriteria(
			JsonPager<EntrustInfo> jp, EntrustInfo t) {
		return null;
	}*/

    @Override
    public List<EntrustInfo> findIsEntrust(String strECompanyId,
                                           String strProjectId, String strSUnitId) {
        DetachedCriteria dc = DetachedCriteria.forClass(EntrustInfo.class);
        if (!CommonMethod.isNull(strECompanyId)) {
            dc.add(Property.forName("entrustCompanyId").eq(strECompanyId));
        }
        if (!CommonMethod.isNull(strProjectId)) {
            dc.add(Property.forName("projectId").eq(strProjectId));
        }
        if (!CommonMethod.isNull(strSUnitId)) {
            dc.add(Property.forName("supervisionUnitId").eq(strSUnitId));
        }
        return entrustInfoDao.findByCriteria(dc);
    }

    @Override
    public EntrustInfo findEntrust(String entrustDetailId) {
        DetachedCriteria dc = DetachedCriteria.forClass(EntrustInfo.class);
        if (!CommonMethod.isNull(entrustDetailId)) {
            dc.add(Property.forName("entrustDetailId").eq(entrustDetailId));
        }
        List<EntrustInfo> eslist = entrustInfoDao.findByCriteria(dc);
        EntrustInfo info = new EntrustInfo();
        for (EntrustInfo es : eslist) {
            info = es;
        }
        return info;
    }

    @Override
    public String saveEntrustInfo(EntrustSavePage esPage, String userId) {
        String entrustSn = "";// 委托编号
        // 委托明细
        List<EntrustDetailReportPage> edsPageList = esPage.getEdReport();
        // 部门ID
        String departmentId = edsPageList.get(0).getDepartmentId();
        // 部门信息
        BaseDepartment bd = baseDepartmentDao.findById(departmentId);
        int sampsize = 0;
        // 市政部门
        if ("01".equals(bd.getSpecialRule())) {
            if (CommonMethod.isNull(esPage.getEntrustDate())) {// 委托日期必须录入
                throw new BusinessException("委托日期必须录入，请确认！", "");
            } else {
                // 获取当前天数
                String orderDay = CommonMethod.getOrderDate(
                        CommonMethod.string2Time1(esPage.getEntrustDate()), 3);
                // 委托年份
                String entrustYear = esPage.getEntrustDate().substring(0, 4);
                String ruleU = "";
                if ("00".equals(esPage.getIsComplementally())) {// 正办委托
                    ruleU = "zb" + entrustYear + orderDay;
                } else {// 补办委托
                    ruleU = "bb" + entrustYear + orderDay;
                }
                Long sn = runningNumService.getNextNum(ruleU, 1);
                if (sn >= 100) {
                    entrustSn = entrustYear + "-" + orderDay + sn;
                } else {
                    entrustSn = entrustYear + "-" + orderDay
                            + String.format("%0" + 3 + "d", sn);
                }
            }
        } else {// 市政部门以外其他部门
            if ("00".equals(esPage.getIsComplementally())) {// 正办委托
                if (CommonMethod.isNull(esPage.getEntrustSn())) {// 委托编号没有录入
                    String ruleU = "entrustSn";
                    Long sn = runningNumService.getNextNum(ruleU, 1);
                    if (sn >= 1000) {
                        entrustSn = CommonMethod.getCurrentYear() + "-" + sn;
                    } else {
                        entrustSn = CommonMethod.getCurrentYear() + "-"
                                + String.format("%0" + 4 + "d", sn);
                    }
                } else {
                    String[] a = {"00"};
                    entrustSn = esPage.getEntrustSn();
                    // 查询录入的委托编号是否存在
                    DetachedCriteria eiDc = DetachedCriteria
                            .forClass(EntrustInfo.class);
                    eiDc.add(Property.forName("entrustSn").eq(entrustSn));
                    eiDc.add(Property.forName("isComplementally").eq(
                            esPage.getIsComplementally()));
                    eiDc.add(Restrictions.not(Restrictions.in("entrustStatus", a)));
                    List<EntrustInfo> eiList = entrustInfoDao
                            .findByCriteria(eiDc);
                    if (eiList != null && eiList.size() > 0) {
                        throw new BusinessException("委托编号："
                                + esPage.getEntrustSn() + "已经存在，不能保存，请确认！", "");
                    }
                }
            } else {// 补办委托时，委托编号必须录入
                if (CommonMethod.isNull(esPage.getEntrustSn())) {// 委托编号没有录入
                    throw new BusinessException("委托编号不能为空，请确认！", "");
                } else {
                    entrustSn = esPage.getEntrustSn();

                    // //查询录入的委托编号是否存在
                    // DetachedCriteria eiDc =
                    // DetachedCriteria.forClass(EntrustInfo.class);
                    // eiDc.add(Property.forName("entrustSn").eq(entrustSn));
                    // eiDc.add(Property.forName("isComplementally").eq(esPage.getIsComplementally()));
                    // List<EntrustInfo> eiList =
                    // entrustInfoDao.findByCriteria(eiDc);
                    // if(eiList!=null && eiList.size()>0){
                    // throw new
                    // BusinessException("委托编号："+esPage.getEntrustSn()+"已经存在，不能保存，请确认！","");
                    // }
                }
            }
        }

        double rMoney = 0d;
        String entrustId = CommonMethod.getNewKey();

        int sampleNum = 1;
        String maxSampleNo = "0";
        List<String> sampleNoList = entrustInfoDao.findMaxSampleNo(entrustId);
        if (sampleNoList != null && sampleNoList.size() > 0) {
            for (String sampleNo : sampleNoList) {
                int lastPlace = sampleNo.lastIndexOf("-");
                String numTmp = sampleNo.substring(lastPlace + 1);
                if (Integer.valueOf(numTmp) > Integer.valueOf(maxSampleNo)) {
                    maxSampleNo = numTmp;
                }
            }
        }
        if (!CommonMethod.isNull(maxSampleNo)) {
            sampleNum = Integer.valueOf(maxSampleNo) + 1;
        }
        String entrustSnTmp = entrustSn.replace("-", "").substring(2);

        // 委托明细
        // List<EntrustDetailReportPage> edsPageList = esPage.getEdReport();
        for (EntrustDetailReportPage edsPage : edsPageList) {

            rMoney = rMoney + edsPage.getRealTotalPrice();
            EntrustDetails ed = new EntrustDetails();
            ed.setEntrustDetailId(CommonMethod.getNewKey());
            // 委托金额明细
            List<EntrustDetailSavePage> edSavePageList = edsPage
                    .getEnPaInfoPageList();
            for (EntrustDetailSavePage edSavePage : edSavePageList) {
                EntrustMoneyDetails emd = new EntrustMoneyDetails();
                emd.setEntrustMoneyDetailId(CommonMethod.getNewKey());
                emd.setEntrustDetailId(ed.getEntrustDetailId());
                emd.setTestParameterId(edSavePage.getTestParameterId());
                emd.setRealUnitPrice(Double.valueOf(edSavePage.getUnitPrice()));
                emd.setSampleSource(edSavePage.getSampleSource());
                emd.setRemark(edSavePage.getRemark());
                emd.setInputer(userId);
                emd.setInputeTime(CommonMethod.getDate());
                emd.setTestCount(edSavePage.getTestCount());
                entrustMoDetailDao.save(emd);
            }

            if ("01".equals(esPage.getIsComplementally())) {// 01,补办委托
                // 查询委托信息
                DetachedCriteria eiDc = DetachedCriteria
                        .forClass(EntrustInfo.class);
                eiDc.add(Property.forName("entrustSn").eq(entrustSn));
                eiDc.add(Property.forName("entrustCompanyId").eq(
                        esPage.getEntrustCompanyId()));
                eiDc.add(Property.forName("projectId")
                        .eq(esPage.getProjectId()));
                eiDc.add(Property.forName("entrustDate").eq(
                        esPage.getEntrustDate()));
                eiDc.add(Property.forName("isComplementally").eq("01"));
                List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);
                // 委托id
                List<String> entrustIdList = new ArrayList<String>();
                for (EntrustInfo srEnturst : eiList) {
                    entrustIdList.add(srEnturst.getEntrustId());
                }
                if (entrustIdList.size() > 0) {
                    // 委托样品关联表,查询关联已经存在，编号顺延
                    DetachedCriteria dcTri = DetachedCriteria
                            .forClass(SampleReport.class);
                    dcTri.add(Property.forName("entrustId").in(entrustIdList));
                    // dcTri.add(Property.forName("reportId").isNotNull());
                    // dcTri.add(Property.forName("invalidStatus").eq("01"));//00作废，01正常
                    // dcTri.add(Property.forName("entrustId").asc());
                    List<SampleReport> sampList = sampleReportDao
                            .findByCriteria(dcTri);
                    sampleNumber.info(sampList.size() + "查询样品条数");
                    if (sampsize == 0) {
                        if (sampList != null && sampList.size() > 0) {
                            sampsize = sampList.size() + 1;
                        }
                    }
                    // 对已经关联的委托进行检查，00作废并且合同ID为null，编号减一不顺延
                    for (SampleReport samp : sampList) {

                        DetachedCriteria eiDc1 = DetachedCriteria
                                .forClass(EntrustInfo.class);
                        eiDc1.add(Property.forName("entrustId").eq(
                                samp.getEntrustId()));
                        eiDc1.add(Property.forName("entrustStatus").eq("00"));
                        List<EntrustInfo> eiList1 = entrustInfoDao
                                .findByCriteria(eiDc1);
                        if (samp.getReportId() == null
                                || "".equals(samp.getReportId())) {
                            if (eiList1.size() > 0) {
                                sampsize -= 1;
                            }
                        }
                    }
                    sampleNum = sampsize;
                }
            }

            for (int i = 0; i < edsPage.getReportNum(); i++) {
                sampleNumber.info(edsPage.getReportNum() + "循环几次");
                // 样品编号
                String sampleNo = entrustSnTmp + "-"
                        + String.format("%0" + 3 + "d", sampleNum);
                // 样品信息
                BaseSample bs = baseSampleDao.findById(edsPage.getSampleId());
                SampleReport sr = new SampleReport();
                sr.setSampleReportId(CommonMethod.getNewKey());
                sr.setEntrustDetailId(ed.getEntrustDetailId());
                sr.setEntrustId(entrustId);
                sr.setSampleNo(sampleNo);
                sampleNumber.info(sampleNo + "样品编号");
                sr.setInvalidStatus("01");// 00作废，01正常
                sr.setSampleId(edsPage.getSampleId());
                sr.setSampleName(bs.getSampleName());
                sr.setSampleLevel(bs.getSampleLevel());
                sr.setSampleSource(edsPage.getSampleSource());
                sr.setSampleType(edsPage.getSampleType());
                sampleReportDao.save(sr);
                sampleNum++;
            }
            sampsize = sampleNum;
            ed.setEntrustId(entrustId);
            ed.setDepartmentId(edsPage.getDepartmentId());
            ed.setSampleId(edsPage.getSampleId());
            ed.setSampleSource(edsPage.getSampleSource());
            ed.setReportNum(edsPage.getReportNum());
            ed.setProcessStatus("01");// 办委托
            ed.setRemark(edsPage.getRemark());
            ed.setInputer(userId);
            ed.setInputeTime(CommonMethod.getDate());
            ed.setStandards(edsPage.getStandards());
            ed.setWitnessMan(edsPage.getWitness());
            ed.setRealTotalPrice(edsPage.getRealTotalPrice());
            ed.setSampleType(edsPage.getSampleType());
            entrustDetailDao.save(ed);
        }
        // 委托信息
        EntrustInfo ei = new EntrustInfo();
        ei.setEntrustId(entrustId);
        ei.setEntrustSn(entrustSn);
        ei.setEntrustCompanyId(esPage.getEntrustCompanyId());
        ei.setProjectId(esPage.getProjectId());
        ei.setProjectRemark(esPage.getProjectRemark());
        ei.setEntrustDate(esPage.getEntrustDate());
        ei.setSupervisionUnitId(esPage.getSupervisionUnitId());
        ei.setProjectAddress(esPage.getProjectAddress());
        ei.setLinkPhone(esPage.getLinkPhone());
        ei.setInspectionMan(esPage.getInspectionMan());
        ei.setWitnessMan(esPage.getWitnessMan());
        ei.setLinkMan(esPage.getLinkMan());
        ei.setAccountType(esPage.getAccountType());
        ei.setAccountKinds(esPage.getAccountKinds());
        ei.setContractCode(esPage.getContractCode());
        ei.setAccountValue(rMoney);
        ei.setManagementDepartmentId(esPage.getManagementDepartmentId());
        ei.setIsComplementally(esPage.getIsComplementally());
        ei.setEntrustStatus("01");// 办委托
        ei.setPrintNum(esPage.getPrintNum() == null ? 0 : Integer
                .valueOf(esPage.getPrintNum()));// 打印次数
        ei.setRemark(esPage.getRemark());
        ei.setInputer(userId);
        ei.setInputeTime(CommonMethod.getDate());
        ei.setEntrustType(esPage.getEntrustType());
        ei.setCapitalAccountId(esPage.getCapitalAccountId());
        ei.setContractId(esPage.getContractId());
        ei.setSpecialRule(esPage.getSpecialRule());
        entrustInfoDao.save(ei);

        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(entrustId);
        eor.setOperation("01");// 录入委托
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);

        return entrustId;
    }

    @Override
    public void updateEntrustInfoBySn(EntrustSavePage esPage, String userId) {
        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能修改，请确认！", "");
        }
        String entrustSn = esPage.getEntrustSn();
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        ei.setEntrustSn(entrustSn);
        if (esPage.getInvalid().equals("True")) {// True为作废
            ei.setProjectAddress(ei.getEntrustStatus());
            ei.setEntrustStatus("00");// 办委托
        } else {
            ei.setEntrustStatus(ei.getProjectAddress());// 办委托
        }
        ei.setUpdater(userId);
        entrustInfoDao.update(ei);

        // 重新生成样品编号
        String entrustSnTmp = ei.getEntrustSn().replace("-", "").substring(2);
        /*
         * //查询委托信息 DetachedCriteria eiDc =
         * DetachedCriteria.forClass(EntrustInfo.class);
         * eiDc.add(Property.forName("entrustSn").eq(ei.getEntrustSn()));
         * eiDc.add
         * (Property.forName("entrustCompanyId").eq(ei.getEntrustCompanyId()));
         * eiDc.add(Property.forName("projectId").eq(ei.getProjectId()));
         * eiDc.add(Property.forName("entrustDate").eq(ei.getEntrustDate()));
         * eiDc.add(Property.forName("isComplementally").eq("01"));
         * List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);
         * //委托id List<String> entrustIdList = new ArrayList<String>();
         * for(EntrustInfo srEnturst:eiList){
         * entrustIdList.add(srEnturst.getEntrustId()); }
         *
         * //委托样品关联表,查询关联已经存在，编号顺延 DetachedCriteria dcTri =
         * DetachedCriteria.forClass(SampleReport.class);
         * dcTri.add(Property.forName("entrustId").in(entrustIdList));
         * List<SampleReport> sampList = sampleReportDao.findByCriteria(dcTri);
         *
         * int sampsize = 0; for(SampleReport samp:sampList){
         * if(samp.getEntrustId().equals(ei.getEntrustId()) &&
         * ei.getEntrustStatus().equals("00")){ }else{ DetachedCriteria eiDc1 =
         * DetachedCriteria.forClass(EntrustInfo.class);
         * eiDc1.add(Property.forName("entrustId").eq(samp.getEntrustId()));
         * eiDc1.add(Property.forName("entrustStatus").eq("00"));
         * List<EntrustInfo> eiList1 = entrustInfoDao.findByCriteria(eiDc1);
         *
         * if(eiList1.size() < 1){ //样品编号 sampsize += 1; }else{
         * if(samp.getReportId() != null || !"".equals(samp.getReportId()) ){
         * //样品编号 sampsize += 1; } } String sampleNo1 =
         * entrustSnTmp+"-"+String.format("%0"+3+"d",sampsize);
         * samp.setSampleNo(sampleNo1); sampleReportDao.save(samp); } }
         */
        this.updateSampleBySampleNo(ei, entrustSnTmp);
    }

    public void updateSampleBySampleNo(EntrustInfo ei, String entrustSnTmp) {
        // 重新生成样品编号
        // 查询委托信息
        DetachedCriteria eiDc = DetachedCriteria.forClass(EntrustInfo.class);
        eiDc.add(Property.forName("entrustSn").eq(ei.getEntrustSn()));
        eiDc.add(Property.forName("entrustCompanyId").eq(
                ei.getEntrustCompanyId()));
        eiDc.add(Property.forName("projectId").eq(ei.getProjectId()));
        eiDc.add(Property.forName("entrustDate").eq(ei.getEntrustDate()));
        eiDc.add(Property.forName("isComplementally").eq("01"));
        List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);

        // 委托id
        List<String> entrustIdList = new ArrayList<String>();
        for (EntrustInfo srEnturst : eiList) {
            entrustIdList.add(srEnturst.getEntrustId());
        }
        if (entrustIdList.size() > 0) {
            // 委托样品关联表,查询关联已经存在，编号顺延
            List<SampleReport> sampList = sampleReportDao
                    .showEntrustIdByOrder(entrustIdList);
            // 记录所有不重新排序的编号
            List<String> sampStrId = new ArrayList<String>();// 委托样品关系id
            List<String> sampStrList = new ArrayList<String>();// 编号
            for (SampleReport samp : sampList) {
                // 报告已经录入不可重排样品编号
                if (samp.getReportId() != null
                        && !"".equals(samp.getReportId())) {
                    sampleNumber.info(samp.getSampleReportId() + "该样品已经发送报告不能排序编号");
                    // 报告除了未录入外都不可重排样品编号
                    DetachedCriteria testDc = DetachedCriteria
                            .forClass(TestReportInfo.class);
                    testDc.add(Property.forName("reportId").eq(
                            samp.getReportId()));
                    testDc.add(Property.forName("reportStatus").ne("01"));// 不能用等于01操作,因为报告id有可能不存在
                    List<TestReportInfo> testList = testReportInfoDao
                            .findByCriteria(testDc);
                    if (testList.size() > 0) {
                        sampStrList.add(samp.getSampleNo());
                        sampStrId.add(samp.getSampleReportId());
                    }
                }
            }
            // 样品编号
            int sampsize = 0;
            for (SampleReport samp : sampList) {

                DetachedCriteria eiDc1 = DetachedCriteria
                        .forClass(EntrustInfo.class);
                eiDc1.add(Property.forName("entrustId").eq(samp.getEntrustId()));
                eiDc1.add(Property.forName("entrustStatus").eq("00"));// 00作废
                List<EntrustInfo> eiList1 = entrustInfoDao
                        .findByCriteria(eiDc1);

                // 委托样品关系id相同,记录编号
                if (sampStrId.contains(samp.getSampleReportId())) {
                    continue;
                }
                // 委托是作废状态，并且没有录入报告，不操作排序编号
                if (eiList1.size() > 0) {
                    if (samp.getReportId() == null
                            || "".equals(samp.getReportId())) {
                        sampleNumber.info(samp.getSampleReportId() + "该样品已经作废不能排序编号");
                        continue;
                    }
                }
                sampsize += 1;
                String sampleNo1 = entrustSnTmp + "-"
                        + String.format("%0" + 3 + "d", sampsize);
                // 编号相同加1
                if (sampStrList.contains(sampleNo1)) {
                    sampsize = this.getSampleNo(sampStrList, sampleNo1,
                            entrustSnTmp, sampsize);
                    sampleNo1 = entrustSnTmp + "-"
                            + String.format("%0" + 3 + "d", sampsize);
                }
                sampleNumber.info(samp.getSampleReportId() + "样品报告中间表ID" + "新增后查询所有修改" + sampleNo1);
                samp.setSampleNo(sampleNo1);
                sampleReportDao.save(samp);
            }
        }
    }

    @Override
    public void updateEntrustInfo(EntrustSavePage esPage, String userId) {
        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能修改，请确认！", "");
        }

        String entrustSnTmp = esPage.getEntrustSn().replace("-", "").substring(2);
        EntrustInfo entrust = entrustInfoDao.findById(esPage.getEntrustId());
        if (!entrust.getEntrustSn().equals(esPage.getEntrustSn())) {
            int number = 1;
            List<SampleReport> findByProperty = sampleReportDao.findByProperty("entrustId", entrust.getEntrustId());
            for (SampleReport sampleReport : findByProperty) {
                sampleReport.setSampleNo(entrustSnTmp + "-"
                        + String.format("%0" + 3 + "d", number));
                number++;
                sampleReportDao.update(sampleReport);
            }
        }


        String entrustSn = "";// 委托编号
        if ("00".equals(esPage.getIsComplementally())) {// 正办委托

            if (CommonMethod.isNull(esPage.getEntrustSn())) {// 委托编号没有录入
                String ruleU = "entrustSn";
                Long sn = runningNumService.getNextNum(ruleU, 1);
                if (sn >= 1000) {
                    entrustSn = CommonMethod.getCurrentYear() + "-" + sn;
                } else {
                    entrustSn = CommonMethod.getCurrentYear() + "-"
                            + String.format("%0" + 4 + "d", sn);
                }
            } else {
                /*
                 * entrustSn = esPage.getEntrustSn(); // 查询录入的委托编号是否存在
                 * DetachedCriteria eiDc = DetachedCriteria
                 * .forClass(EntrustInfo.class);
                 * eiDc.add(Property.forName("entrustId")
                 * .ne(esPage.getEntrustId()));
                 * eiDc.add(Property.forName("entrustSn").eq(entrustSn));
                 * eiDc.add(Property.forName("isComplementally").eq(
                 * esPage.getIsComplementally())); List<EntrustInfo> eiList =
                 * entrustInfoDao.findByCriteria(eiDc); if (eiList != null &&
                 * eiList.size() > 0) { throw new BusinessException("委托编号：" +
                 * esPage.getEntrustSn() + "已经存在，不能保存，请确认！", "");
                 */
                entrustSn = esPage.getEntrustSn();
                // 查询录入的委托编号是否存在
                DetachedCriteria eiDc = DetachedCriteria
                        .forClass(EntrustInfo.class);
                eiDc.add(Property.forName("entrustSn").eq(entrustSn));
                eiDc.add(Property.forName("isComplementally").eq(
                        esPage.getIsComplementally()));
                eiDc.add(Property.forName("entrustStatus").ne(
                        "00"));
                List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);
                if (eiList != null && eiList.size() > 0) {
                    for (EntrustInfo entrustInfo : eiList) {
                        if (entrustInfo.getEntrustId().equals(esPage.getEntrustId())) {
                        } else {
                            throw new BusinessException("委托编号："
                                    + esPage.getEntrustSn() + "已经存在，不能保存，请确认！",
                                    "");
                        }
                    }
                }
            }
        } else {// 补办委托时，委托编号必须录入
            if (CommonMethod.isNull(esPage.getEntrustSn())) {// 委托编号没有录入
                throw new BusinessException("委托编号不能为空，请确认！", "");
            } else {
                entrustSn = esPage.getEntrustSn();
            }

            // 查询委托信息新否有相同的已经存在
            DetachedCriteria eiDc = DetachedCriteria
                    .forClass(EntrustInfo.class);
            eiDc.add(Property.forName("entrustId").ne(esPage.getEntrustId()));
            eiDc.add(Property.forName("entrustSn").eq(entrustSn));
            eiDc.add(Property.forName("entrustCompanyId").eq(
                    esPage.getEntrustCompanyId()));
            eiDc.add(Property.forName("projectId").eq(esPage.getProjectId()));
            eiDc.add(Property.forName("entrustDate")
                    .eq(esPage.getEntrustDate()));
            eiDc.add(Property.forName("isComplementally").eq("01"));
            List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);
            if (eiList.size() > 0) {
                // 有相同委托信息存在对比修改是否涉及这四个字段，涉及不允许修改
                DetachedCriteria eiinDc = DetachedCriteria
                        .forClass(EntrustInfo.class);
                eiinDc.add(Property.forName("entrustId").eq(
                        esPage.getEntrustId()));
                List<EntrustInfo> eiinList = entrustInfoDao
                        .findByCriteria(eiinDc);
                EntrustInfo ent = eiinList.get(0);
                if (!esPage.getEntrustDate().equals(ent.getEntrustDate())
                        || !entrustSn.equals(ent.getEntrustSn())
                        || !esPage.getProjectId().equals(ent.getProjectId())
                        || !esPage.getEntrustCompanyId().equals(
                        ent.getEntrustCompanyId())) {
                    throw new BusinessException("修改失败！委托编号（" + entrustSn
                            + "）、委托日期、工程、单位信息已存在。请确认检查修改信息，或者将此委托作废后重新录入。", "");
                }
            }
        }
        // 市政部门
        /*
         * if("01".equals(esPage.getSpecialRule())){
         * if(CommonMethod.isNull(esPage.getEntrustDate())){//委托日期必须录入 throw new
         * BusinessException("委托日期必须录入，请确认！",""); }else{ //获取当前天数 String
         * orderDay =
         * CommonMethod.getOrderDate(CommonMethod.string2Time1(esPage.
         * getEntrustDate()), 3); //委托年份 String entrustYear =
         * esPage.getEntrustDate().substring(0,4); String ruleU = "";
         * if("00".equals(esPage.getIsComplementally())){//正办委托 ruleU =
         * "zb"+entrustYear+orderDay; }else{//补办委托 ruleU =
         * "bb"+entrustYear+orderDay; } Long sn =
         * runningNumService.getNextNum(ruleU, 1); if(sn>=100){ entrustSn =
         * entrustYear+"-"+orderDay + sn; }else{ entrustSn =
         * entrustYear+"-"+orderDay + String.format("%0"+3+"d",sn); } }
         * }else{//市政部门以外其他部门
         * if("00".equals(esPage.getIsComplementally())){//正办委托
         * if(CommonMethod.isNull(esPage.getEntrustSn())){//委托编号没有录入 String
         * ruleU = "entrustSn"; Long sn = runningNumService.getNextNum(ruleU,
         * 1); if(sn>=1000){ entrustSn = CommonMethod.getCurrentYear()+"-" + sn;
         * }else{ entrustSn = CommonMethod.getCurrentYear()+"-" +
         * String.format("%0"+4+"d",sn); } }else{ entrustSn =
         * esPage.getEntrustSn(); //查询录入的委托编号是否存在 DetachedCriteria eiDc =
         * DetachedCriteria.forClass(EntrustInfo.class);
         * eiDc.add(Property.forName("entrustId").ne(esPage.getEntrustId()));
         * eiDc.add(Property.forName("entrustSn").eq(entrustSn));
         * eiDc.add(Property
         * .forName("isComplementally").eq(esPage.getIsComplementally()));
         * List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc);
         * if(eiList!=null && eiList.size()>0){ throw new
         * BusinessException("委托编号："+esPage.getEntrustSn()+"已经存在，不能保存，请确认！","");
         * } } }else{//补办委托时，委托编号必须录入
         * if(CommonMethod.isNull(esPage.getEntrustSn())){//委托编号没有录入 throw new
         * BusinessException("委托编号不能为空，请确认！",""); }else{ entrustSn =
         * esPage.getEntrustSn(); // //查询录入的委托编号是否存在 // DetachedCriteria eiDc =
         * DetachedCriteria.forClass(EntrustInfo.class); //
         * eiDc.add(Property.forName("entrustId").ne(esPage.getEntrustId())); //
         * eiDc.add(Property.forName("entrustSn").eq(entrustSn)); //
         * eiDc.add(Property
         * .forName("isComplementally").eq(esPage.getIsComplementally())); //
         * List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(eiDc); //
         * if(eiList!=null && eiList.size()>0){ // throw new
         * BusinessException("委托编号："+esPage.getEntrustSn()+"已经存在，不能保存，请确认！","");
         * // } } } }
         */
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        ei.setEntrustSn(entrustSn);
        ei.setEntrustCompanyId(esPage.getEntrustCompanyId());
        ei.setProjectId(esPage.getProjectId());
        ei.setProjectRemark(esPage.getProjectRemark());
        ei.setEntrustDate(esPage.getEntrustDate());
        ei.setSupervisionUnitId(esPage.getSupervisionUnitId());
        ei.setProjectAddress(esPage.getProjectAddress());
        ei.setLinkPhone(esPage.getLinkPhone());
        ei.setInspectionMan(esPage.getInspectionMan());
        ei.setWitnessMan(esPage.getWitnessMan());
        ei.setLinkMan(esPage.getLinkMan());
        ei.setAccountType(esPage.getAccountType());
        ei.setAccountKinds(esPage.getAccountKinds());
        ei.setContractCode(esPage.getContractCode());
        // ei.setAccountValue(rMoney);
        ei.setManagementDepartmentId(esPage.getManagementDepartmentId());
        ei.setIsComplementally(esPage.getIsComplementally());
        // ei.setEntrustStatus("01");//办委托
        ei.setPrintNum(esPage.getPrintNum() == null ? 0 : Integer
                .valueOf(esPage.getPrintNum()));// 打印次数
        ei.setRemark(esPage.getRemark());
        ei.setUpdater(userId);
        ei.setUpdateTime(CommonMethod.getDate());
        ei.setEntrustType(esPage.getEntrustType());
        ei.setCapitalAccountId(esPage.getCapitalAccountId());
        ei.setContractId(esPage.getContractId());
        ei.setSpecialRule(esPage.getSpecialRule());
        entrustInfoDao.update(ei);

        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(entrustId);
        eor.setOperation("02");// 修改委托
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);
    }

    @Override
    public void updateEntrustMDetail(EntrustSavePage esPage, String userId) {

        double rMoney = 0d;
        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能修改，请确认！", "");
        }
        List<SampleReport> srList = sampleReportDao.findByProperty("entrustId",
                entrustId);
        // 获取委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        // 根据委托ID删除样品报告关系
        sampleReportDao.deleteByEntrustId(entrustId);

        int sampleNum = 1;
        String maxSampleNo = "0";
        List<String> sampleNoList = entrustInfoDao.findMaxSampleNo(entrustId);

        if (sampleNoList != null && sampleNoList.size() > 0) {
            for (String sampleNo : sampleNoList) {
                int lastPlace = sampleNo.lastIndexOf("-");
                String numTmp = sampleNo.substring(lastPlace + 1);
                if (Integer.valueOf(numTmp) > Integer.valueOf(maxSampleNo)) {
                    maxSampleNo = numTmp;
                }
            }
        }
        if (!CommonMethod.isNull(maxSampleNo)) {
            sampleNum = Integer.valueOf(maxSampleNo) + 1;
        }
        String entrustSnTmp = ei.getEntrustSn().replace("-", "").substring(2);
        // 委托明细
        List<EntrustDetailReportPage> edrPageList = esPage.getEdReport();
        for (EntrustDetailReportPage edrPage : edrPageList) {

            String strEntrustDetailId = edrPage.getEntrustDetailId();
            if (CommonMethod.isNull(strEntrustDetailId)) {
                strEntrustDetailId = CommonMethod.getNewKey();
            }
            List<SampleReport> srTmpList = new ArrayList<SampleReport>();
            if (!CommonMethod.isNull(edrPage.getEntrustDetailId())) {
                for (SampleReport sr : srList) {
                    if (sr.getEntrustDetailId().equals(
                            edrPage.getEntrustDetailId())) {
                        srTmpList.add(sr);
                    }
                }
            }
            // 删除委托参数金额明细
            entrustMoDetailDao.deleteByEntrustDetailId(strEntrustDetailId);
            // 删除委托明细
            if ("01".equals(edrPage.getDeleteFlag())) {
                entrustDetailDao.deleteByEntrustDetailId(strEntrustDetailId);
            } else {
                rMoney = rMoney + edrPage.getRealTotalPrice();

                // 委托金额明细
                List<EntrustDetailSavePage> edSavePageList = edrPage
                        .getEnPaInfoPageList();
                for (EntrustDetailSavePage edSavePage : edSavePageList) {
                    EntrustMoneyDetails emd = new EntrustMoneyDetails();
                    emd.setEntrustMoneyDetailId(CommonMethod.getNewKey());
                    emd.setEntrustDetailId(strEntrustDetailId);
                    emd.setTestParameterId(edSavePage.getTestParameterId());
                    emd.setRealUnitPrice(Double.valueOf(edSavePage
                            .getUnitPrice()));
                    emd.setSampleSource(edSavePage.getSampleSource());
                    emd.setRemark(edSavePage.getRemark());
                    emd.setInputer(userId);
                    emd.setInputeTime(CommonMethod.getDate());
                    emd.setTestCount(edSavePage.getTestCount());
                    entrustMoDetailDao.save(emd);
                }

                for (int i = 0; i < edrPage.getReportNum(); i++) {
                    // 样品编号
                    String sampleNo = entrustSnTmp + "-"
                            + String.format("%0" + 3 + "d", sampleNum);

                    sampleNumber.info("update方法" + sampleNo + "样品编号");

                    // 样品信息
                    BaseSample bs = baseSampleDao.findById(edrPage
                            .getSampleId());
                    SampleReport sr = new SampleReport();
                    sr.setSampleReportId(CommonMethod.getNewKey());
                    sr.setEntrustDetailId(strEntrustDetailId);
                    sr.setEntrustId(entrustId);
                    sr.setSampleNo(sampleNo);
                    sr.setInvalidStatus("01");// 00作废，01正常
                    if (!CommonMethod.isNull(edrPage.getEntrustDetailId())) {
                        if (i < srTmpList.size()) {
                            SampleReport sr2 = srTmpList.get(i);
                            sr.setReportId(sr2.getReportId());
                            sr.setProjectPart(sr2.getProjectPart());
                            sr.setMoldingDate(sr2.getMoldingDate());
                            sr.setSampleLevel(sr2.getSampleLevel());
                            sr.setSampleAge(sr2.getSampleAge());
                            sr.setSampleId(edrPage.getSampleId());
                            sr.setSampleName(bs.getSampleName());
                            sr.setSampleSource(sr2.getSampleSource());
                            sr.setSampleType(sr2.getSampleType());
                            sr.setSampleState(sr2.getSampleState());
                            sr.setSampleTestDate(sr2.getSampleTestDate());
                        } else {
                            sr.setSampleId(edrPage.getSampleId());
                            sr.setSampleName(bs.getSampleName());
                            sr.setSampleLevel(bs.getSampleLevel());
                            sr.setSampleSource(edrPage.getSampleSource());
                        }
                    } else {
                        sr.setSampleId(edrPage.getSampleId());
                        sr.setSampleName(bs.getSampleName());
                        sr.setSampleLevel(bs.getSampleLevel());
                        sr.setSampleSource(edrPage.getSampleSource());
                        sr.setSampleType(edrPage.getSampleType());

                    }
                    sampleReportDao.save(sr);
                    sampleNum++;
                }
                if (CommonMethod.isNull(edrPage.getEntrustDetailId())) {// 添加新的明细
                    EntrustDetails ed = new EntrustDetails();
                    ed.setEntrustDetailId(strEntrustDetailId);
                    ed.setEntrustId(entrustId);
                    ed.setDepartmentId(edrPage.getDepartmentId());
                    ed.setSampleId(edrPage.getSampleId());
                    ed.setSampleSource(edrPage.getSampleSource());
                    ed.setReportNum(edrPage.getReportNum());
                    ed.setProcessStatus("01");// 办委托
                    ed.setRemark(edrPage.getRemark());
                    ed.setInputer(userId);
                    ed.setInputeTime(CommonMethod.getDate());
                    ed.setStandards(edrPage.getStandards());
                    ed.setWitnessMan(edrPage.getWitness());
                    ed.setRealTotalPrice(edrPage.getRealTotalPrice());
                    ed.setSampleType(edrPage.getSampleType());
                    entrustDetailDao.save(ed);
                } else {// 修改已有的明细
                    EntrustDetails ed = entrustDetailDao
                            .findById(strEntrustDetailId);
                    ed.setSampleSource(edrPage.getSampleSource());
                    ed.setReportNum(edrPage.getReportNum());
                    ed.setRemark(edrPage.getRemark());
                    ed.setUpdater(userId);
                    ed.setUpdateTime(CommonMethod.getDate());
                    ed.setStandards(edrPage.getStandards());
                    ed.setWitnessMan(edrPage.getWitness());
                    ed.setRealTotalPrice(edrPage.getRealTotalPrice());
                    ed.setSampleType(edrPage.getSampleType());
                    entrustDetailDao.update(ed);
                }
            }
        }
        ei.setAccountValue(rMoney);
        ei.setUpdater(userId);
        ei.setUpdateTime(CommonMethod.getDate());
        entrustInfoDao.update(ei);

        this.updateSampleBySampleNo(ei, entrustSnTmp);

    }

    /**
     * @param sampStrList  //不可改变编号集合
     * @param entrustSnTmp 编号前缀
     * @param sampsize     编号
     * @return
     */
    public int getSampleNo(List<String> sampStrList, String sampleNo1,
                           String entrustSnTmp, int sampsize) {
        String sampleNo = "";
        if (sampStrList.contains(sampleNo1)) {
            sampsize += 1;
            sampleNo = entrustSnTmp + "-"
                    + String.format("%0" + 3 + "d", sampsize);
            this.getSampleNo(sampStrList, sampleNo, entrustSnTmp, sampsize);
        }
        return sampsize;

    }

    @Override
    public void saveEntrustMDetail(EntrustSavePage esPage, String userId) {
        double rMoney = 0d;
        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能修改，请确认！", "");
        }
        // 获取委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);

        int sampleNum = 1;
        String maxSampleNo = "0";
        List<String> sampleNoList = entrustInfoDao.findMaxSampleNo(entrustId);
        if (sampleNoList != null && sampleNoList.size() > 0) {
            for (String sampleNo : sampleNoList) {
                int lastPlace = sampleNo.lastIndexOf("-");
                String numTmp = sampleNo.substring(lastPlace + 1);
                if (Integer.valueOf(numTmp) > Integer.valueOf(maxSampleNo)) {
                    maxSampleNo = numTmp;
                }
            }
        }
        if (!CommonMethod.isNull(maxSampleNo)) {
            sampleNum = Integer.valueOf(maxSampleNo) + 1;
        }
        String entrustSnTmp = ei.getEntrustSn().replace("-", "").substring(2);
        // 委托明细
        List<EntrustDetailReportPage> edrPageList = esPage.getEdReport();
        for (EntrustDetailReportPage edrPage : edrPageList) {
            rMoney = rMoney + edrPage.getRealTotalPrice();
            EntrustDetails ed = new EntrustDetails();
            ed.setEntrustDetailId(CommonMethod.getNewKey());
            // 委托金额明细
            List<EntrustDetailSavePage> edSavePageList = edrPage
                    .getEnPaInfoPageList();
            for (EntrustDetailSavePage edSavePage : edSavePageList) {
                EntrustMoneyDetails emd = new EntrustMoneyDetails();
                emd.setEntrustMoneyDetailId(CommonMethod.getNewKey());
                emd.setEntrustDetailId(ed.getEntrustDetailId());
                emd.setTestParameterId(edSavePage.getTestParameterId());
                emd.setRealUnitPrice(Double.valueOf(edSavePage.getUnitPrice()));
                emd.setSampleSource(edSavePage.getSampleSource());
                emd.setRemark(edSavePage.getRemark());
                emd.setInputer(userId);
                emd.setInputeTime(CommonMethod.getDate());
                entrustMoDetailDao.save(emd);
            }

            for (int i = 0; i < edrPage.getReportNum(); i++) {
                // 样品编号
                String sampleNo = entrustSnTmp + "-"
                        + String.format("%0" + 3 + "d", sampleNum);
                // 样品信息
                BaseSample bs = baseSampleDao.findById(edrPage.getSampleId());
                SampleReport sr = new SampleReport();
                sr.setSampleReportId(CommonMethod.getNewKey());
                sr.setEntrustDetailId(ed.getEntrustDetailId());
                sr.setEntrustId(entrustId);
                sr.setSampleNo(sampleNo);
                sr.setInvalidStatus("01");// 00作废，01正常
                sr.setSampleId(edrPage.getSampleId());
                sr.setSampleName(bs.getSampleName());
                sr.setSampleLevel(bs.getSampleLevel());
                sr.setSampleSource(edrPage.getSampleSource());
                sr.setSampleType(edrPage.getSampleType());
                sampleReportDao.save(sr);
                sampleNum++;
            }

            ed.setEntrustId(entrustId);
            ed.setDepartmentId(edrPage.getDepartmentId());
            ed.setSampleId(edrPage.getSampleId());
            ed.setSampleSource(edrPage.getSampleSource());
            ed.setReportNum(edrPage.getReportNum());
            ed.setProcessStatus("01");// 办委托
            ed.setRemark(edrPage.getRemark());
            ed.setInputer(userId);
            ed.setInputeTime(CommonMethod.getDate());
            ed.setStandards(edrPage.getStandards());
            ed.setWitnessMan(edrPage.getWitness());
            ed.setRealTotalPrice(edrPage.getRealTotalPrice());
            ed.setSampleType(edrPage.getSampleType());
            entrustDetailDao.save(ed);
        }
        ei.setAccountValue(ei.getAccountValue() + rMoney);
        ei.setUpdater(userId);
        ei.setUpdateTime(CommonMethod.getDate());
        entrustInfoDao.update(ei);
    }

    @Override
    public List<EntrustSavePage> findEntrustInfoByCondition(
            EntrustSavePage esPage) {
        List<EntrustSavePage> esPageList = entrustInfoDao
                .findEntrustInfoListSQL(esPage);
        esPageList = getEsPageList(esPageList);
        return esPageList;
    }

    @Override
    public List<EntrustSavePage> findEntrustInfoByConditionByAll(
            EntrustSavePage esPage) {
        List<EntrustSavePage> esPageList = null;
        List<EntrustSavePage> esPageListDetail = null;
        // 工程ID不为空
        if (CommonMethod.isNull(esPage.getProjectId())
                && CommonMethod.isNull(esPage.getProjectName())) {
            if (!CommonMethod.isNull(esPage.getEntrustSn())) {
                DetachedCriteria dc = DetachedCriteria
                        .forClass(EntrustInfo.class);
                dc.add(Property.forName("entrustSn").like(
                        "%" + esPage.getEntrustSn() + "%"));
                List<EntrustInfo> eslist = entrustInfoDao.findByCriteria(dc);
                if (eslist.size() > 0) {
                    return esPageListDetail = findEntrustDetailInfoById(eslist
                            .get(0).getEntrustId());
                } else {
                    return esPageListDetail;
                }
            } else {
                throw new BusinessException("参数为空，请确认！", "");
            }
        } else {
            // 根据工程ID和名称查询委托
            esPageList = entrustInfoDao.findEntrustInfoListSQLByAll(esPage);
        }
        StringBuffer strEntrustId = new StringBuffer();
        for (EntrustSavePage esPageTmp : esPageList) {
            strEntrustId.append(esPageTmp.getEntrustId() + "','");
        }
        // 查询委托明细
        esPageListDetail = this.findEntrustDetailInfoById(strEntrustId
                .toString());
        return esPageListDetail;
    }

    public List<EntrustSavePage> getEsPageList(List<EntrustSavePage> esPageList) {
        for (EntrustSavePage esPageTmp : esPageList) {
            DetachedCriteria dcIn = DetachedCriteria
                    .forClass(ReceivableEntrustDetails.class);
            dcIn.add(Property.forName("entrustId").eq(esPageTmp.getEntrustId()));
            if (CommonMethod.isNull(esPageTmp.getInputerName())) {
                dcIn.add(Property.forName("inputerName").like(
                        esPageTmp.getInputerName()));
            }
            dcIn.add(Property.forName("invoiceDetailId").isNotNull());
            List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao
                    .findByCriteria(dcIn);
            if (riDetailsListTmpIn != null && riDetailsListTmpIn.size() > 0) {
                esPageTmp.setIsInvoice("01");
            } else {
                esPageTmp.setIsInvoice("00");
            }

            DetachedCriteria dcAc = DetachedCriteria
                    .forClass(ReceivableEntrustDetails.class);
            dcAc.add(Property.forName("entrustId").eq(esPageTmp.getEntrustId()));
            if (CommonMethod.isNull(esPageTmp.getInputerName())) {
                dcAc.add(Property.forName("inputerName").like(
                        esPageTmp.getInputerName()));
            }
            dcAc.add(Property.forName("accountDetailId").isNotNull());
            List<ReceivableEntrustDetails> riDetailsListTmpAc = receivableEnDetailsDao
                    .findByCriteria(dcAc);
            ;
            if (riDetailsListTmpAc != null && riDetailsListTmpAc.size() > 0) {
                esPageTmp.setIsCollections("01");
            } else {
                esPageTmp.setIsCollections("00");
            }
        }
        return esPageList;
    }

    @Override
    public List<EntrustSavePage> findEntrustDetailInfoById(String strEntrustId) {
        List<EntrustSavePage> esPageList = entrustInfoDao
                .findEMDInfoListSQL(strEntrustId);
        for (EntrustSavePage esPage : esPageList) {
            List<EntrustDetailReportPage> edReportPageList = esPage
                    .getEdReport();
            for (EntrustDetailReportPage edReportPage : edReportPageList) {
                // 查询参数金额明细
                List<EntrustDetailSavePage> edSavePageList = new ArrayList<EntrustDetailSavePage>();
                List<EntrustMoneyDetails> emdList = entrustMoDetailDao
                        .findByProperty("entrustDetailId",
                                edReportPage.getEntrustDetailId());
                for (EntrustMoneyDetails emd : emdList) {
                    EntrustDetailSavePage edSavePage = new EntrustDetailSavePage();
                    edSavePage.setEntrustMDetailId(emd
                            .getEntrustMoneyDetailId());
                    edSavePage.setEntrustDetailId(emd.getEntrustDetailId());
                    edSavePage.setTestParameterId(emd.getTestParameterId());
                    TestParameter tp = testParameterDao.findById(emd
                            .getTestParameterId());
                    edSavePage.setTestParameterName(tp.getTestParameterName());
                    edSavePage.setUnitPrice(emd.getRealUnitPrice().toString());
                    edSavePage.setSampleSource(emd.getSampleSource());
                    edSavePage.setRemark(emd.getRemark());
                    edSavePage.setInputer(emd.getInputer());
                    edSavePage.setInputeTime(emd.getInputeTime());
                    edSavePage.setUpdater(emd.getUpdater());
                    edSavePage.setUpdateTime(emd.getUpdateTime());
                    edSavePage.setTestCount(emd.getTestCount());
                    edSavePageList.add(edSavePage);
                }
                edReportPage.setEnPaInfoPageList(edSavePageList);
                // 查询样品报告关系
                List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
                List<SampleReport> srList = sampleReportDao.findByProperty(
                        "entrustDetailId", edReportPage.getEntrustDetailId());
                Collections.sort(srList, new Str2IntComparator(false));
                for (SampleReport sr : srList) {
                    SampleReportPage srPage = new SampleReportPage();
                    srPage.setSampleReportId(sr.getSampleReportId());
                    srPage.setReportId(sr.getReportId());
                    srPage.setEntrustId(sr.getEntrustId());
                    srPage.setEntrustDetailId(sr.getEntrustDetailId());
                    srPage.setSampleReportId(sr.getSampleReportId());
                    srPage.setSampleNo(sr.getSampleNo());
                    srPage.setInvalidStatus(sr.getInvalidStatus());
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
                edReportPage.setSrPageList(srPageList);
            }
        }
        return esPageList;
    }

    @Override
    protected void initBaseDAO(BaseDao<EntrustInfo> baseDao) {

    }

    @Override
    public JsonPager<EntrustInfo> findJsonPageByCriteria(JsonPager<EntrustInfo> jp, EntrustInfo entrustInfo) {
        return null;
    }

    // 字符串按照整型排序比较器
    static class Str2IntComparator implements Comparator<SampleReport> {
        private boolean reverseOrder; // 是否倒序

        public Str2IntComparator(boolean reverseOrder) {
            this.reverseOrder = reverseOrder;
        }

        public int compare(SampleReport samp0, SampleReport samp1) {
            String[] number0 = samp0.getSampleNo().split("-");
            String arg0 = number0[1].toString();

            String[] number1 = samp1.getSampleNo().split("-");
            String arg1 = number1[1].toString();
            if (reverseOrder)
                return Integer.parseInt(arg1) - Integer.parseInt(arg0);
            else
                return Integer.parseInt(arg0) - Integer.parseInt(arg1);
        }
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：委托室用)
     */
    @Override
    public List<EntrustSavePage> findERDetailInfoById(EntrustSavePage esPage) {
        List<EntrustSavePage> esPageList = entrustInfoDao
                .findERDInfoListSQL(esPage);
        // for(EntrustSavePage esavePage :esPageList){
        // List<EntrustDetailReportPage> edReportList = esavePage.getEdReport();
        // for(EntrustDetailReportPage edrPage :edReportList){
        // List<EntrustParameterInfoPage> eParPageList = new
        // ArrayList<EntrustParameterInfoPage>();
        //
        // // if(edrPage.getTestParameterId().contains("|")){
        // // String[] strIdList = edrPage.getTestParameterId().split("\\|");
        // // for(int i=0;i<strIdList.length;i++){
        // // EntrustParameterInfoPage eParInfoPage = new
        // EntrustParameterInfoPage();
        // // eParInfoPage.setTestParameterId(strIdList[i]);
        // // TestParameter tp = testParameterDao.findById(strIdList[i]);
        // // eParInfoPage.setTestParameterName(tp.getTestParameterName());
        // // eParPageList.add(eParInfoPage);
        // // }
        // // }else{
        // // EntrustParameterInfoPage eParInfoPage = new
        // EntrustParameterInfoPage();
        // // eParInfoPage.setTestParameterId(edrPage.getTestParameterId());
        // // TestParameter tp =
        // testParameterDao.findById(edrPage.getTestParameterId());
        // // eParInfoPage.setTestParameterName(tp.getTestParameterName());
        // // eParPageList.add(eParInfoPage);
        // // }
        // // edrPage.setEnPaInfoPageList(eParPageList);
        // }
        // }
        return esPageList;
    }

    /**
     * 根据委托信息，查询委托明细信息(报告：检测科室用)
     */
    @Override
    public List<EntrustReportPage> findERDetailInfo(EntrustReportPage erPage) {
        List<EntrustReportPage> erPageList = entrustInfoDao
                .findERDetailInfoListSQL(erPage);
        for (EntrustReportPage erPageTemp : erPageList) {
            List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
            List<SampleReport> srList = sampleReportDao.findByProperty(
                    "entrustDetailId", erPageTemp.getEntrustDetailId());
            for (SampleReport sr : srList) {
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
            erPageTemp.setSrPageList(srPageList);
        }
        return erPageList;
    }

    @Override
    public List<EntrustTfSheetPage> findEntrustEfSheet(String strEntrustId) {
        List<EntrustTfSheetPage> eTfSheetList = new ArrayList<EntrustTfSheetPage>();
        List<EntrustTfSheetDetailPage> eTfSheetDetailList = new ArrayList<EntrustTfSheetDetailPage>();
        Double totalPrice = 0d;// 收费金额
        Integer totalAmount = 0;// 试件数量
        String smpleName = "";// 试件名称
        String departmentName = "";// 检测部门名称
        // 设置参数委托ID
        EntrustSavePage esPage = new EntrustSavePage();
        esPage.setEntrustId(strEntrustId);
        // 委托金额明细信息
        // List<EntrustSavePage> emdPageList =
        // entrustInfoDao.findEMDInfoListSQL(esPage.getEntrustId());
        // List<EntrustDetailSavePage> edsPageList =
        // emdPageList.get(0).getEdSave();
        // for(EntrustDetailSavePage edsPage:edsPageList){
        // EntrustTfSheetDetailPage eTfSheetDetail = new
        // EntrustTfSheetDetailPage();
        // eTfSheetDetail.setTestParameterId(edsPage.getTestParameterId());
        // eTfSheetDetail.setTestParameterName(edsPage.getTestParameterName());
        // eTfSheetDetail.setUnitPrice(edsPage.getUnitPrice());
        // eTfSheetDetail.setAmount(edsPage.getAmount());
        // eTfSheetDetail.setRealTotalPrice(edsPage.getRealTotalPrice());
        // totalPrice = totalPrice + edsPage.getRealTotalPrice();
        // totalAmount = totalAmount + edsPage.getAmount();
        // if(CommonMethod.isNull(smpleName)){
        // smpleName = smpleName+edsPage.getSampleName();
        // }else{
        // smpleName = smpleName+"、"+edsPage.getSampleName();
        // }
        // if(CommonMethod.isNull(departmentName)){
        // departmentName = departmentName+edsPage.getDepartmentName();
        // }else{
        // departmentName = departmentName+"、"+edsPage.getDepartmentName();
        // }
        // eTfSheetDetailList.add(eTfSheetDetail);
        // }
        // 委托信息
        List<EntrustSavePage> esPageList = entrustInfoDao
                .findEntrustInfoListSQL(esPage);
        EntrustSavePage eSavePage = esPageList.get(0);
        EntrustTfSheetPage eTfSheet = new EntrustTfSheetPage();
        eTfSheet.seteTfSheetList(eTfSheetDetailList);
        eTfSheet.setEntrustCompanyId(eSavePage.getEntrustCompanyId());
        eTfSheet.setEntrustCompanyName(eSavePage.getEntrustCompanyName());
        eTfSheet.setEntrustDate(eSavePage.getEntrustDate());
        eTfSheet.setProjectName(eSavePage.getProjectName());
        eTfSheet.setSampleName(smpleName);
        eTfSheet.setTestNum(totalAmount.toString());
        eTfSheet.setAccountValue(totalPrice.toString());
        eTfSheet.setLinkMan(eSavePage.getLinkMan());
        eTfSheet.setLinkPhone(eSavePage.getLinkPhone());
        eTfSheet.setAccountKinds(eSavePage.getAccountKinds());
        eTfSheet.setAccountKindsName(eSavePage.getAccountKindsName());
        eTfSheet.setAccountType(eSavePage.getAccountType());
        eTfSheet.setAccountTypeName(eSavePage.getAccountTypeName());
        eTfSheet.setAccountValue(eSavePage.getAccountValue().toString());
        eTfSheet.setTestDepartment(departmentName);
        eTfSheet.setManagementDepartmentId(eSavePage
                .getManagementDepartmentId());
        eTfSheet.setManagementDepartmentName(eSavePage
                .getManagementDepartmentName());
        eTfSheetList.add(eTfSheet);
        return eTfSheetList;
    }

    @Override
    public List<TestTfSheetPage> findTestEfSheet(String strEntrustId) {
        List<TestTfSheetPage> tTfSheetList = new ArrayList<TestTfSheetPage>();
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(strEntrustId);
        // 用户信息
        SysUser su = sysUserDao.findById(ei.getInputer());
        // 设置参数委托ID
        EntrustSavePage esPage = new EntrustSavePage();
        esPage.setEntrustId(strEntrustId);
        // 委托报告明细信息
        List<EntrustSavePage> emdPageList = entrustInfoDao
                .findERDInfoListSQL(esPage);
        List<EntrustDetailReportPage> edrPageList = emdPageList.get(0)
                .getEdReport();
        for (EntrustDetailReportPage edrPage : edrPageList) {
            TestTfSheetPage tTfSheet = new TestTfSheetPage();
            BaseSample bs = baseSampleDao.findById(edrPage.getSampleId());
            tTfSheet.setEntrustId(strEntrustId);
            tTfSheet.setEntrustDate(ei.getEntrustDate());
            tTfSheet.setEntrustSn(ei.getEntrustSn());
            tTfSheet.setSampleId(edrPage.getSampleId());
            tTfSheet.setSampleName(edrPage.getSampleName());
            // tTfSheet.setSampleNo(edrPage.getSampleNo());

            // tTfSheet.setMoldingDate(edrPage.getMoldingDate());
            tTfSheet.setSampleSource(edrPage.getSampleSource());
            tTfSheet.setSpecificationsModels(bs.getSpecificationsModels());
            tTfSheet.setStandards(bs.getStandards());
            tTfSheet.setInputer(su.getUserName());
            // tTfSheet.setTestParameterId(edrPage.getTestParameterId());
            // tTfSheet.setTestParameterName(edrPage.getTestParameterName());
            // tTfSheet.setSampleAge(edrPage.getSampleAge());
            tTfSheetList.add(tTfSheet);
        }
        return tTfSheetList;
    }

    @Override
    public void updateEntrustStatus(String strEntrustId,
                                    String strEntrustStatus, String userId) {
        if (!("00".equals(strEntrustStatus) || "04".equals(strEntrustStatus))) {
            throw new BusinessException("委托状态为已作废和已收款以外的状态不能进行本操作！", "");
        }
        String eOperation = "";// 操作
        // 委托信息
        EntrustInfo ei = entrustInfoDao.findById(strEntrustId);
        if ("00".equals(strEntrustStatus)) {// 已作废
            ei.setEntrustStatus(strEntrustStatus);
            eOperation = "09";// 已作废
        } else {// 已收款
            if ("03".equals(ei.getEntrustStatus())) {// 报告已全部发放
                ei.setEntrustStatus("05");// 已完结
                eOperation = "10";// 已完结
            } else {
                ei.setEntrustStatus(strEntrustStatus);
                eOperation = "08";// 已收款
            }
        }
        ei.setUpdater(userId);
        ei.setUpdateTime(CommonMethod.getDate());
        entrustInfoDao.update(ei);

        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(ei.getEntrustId());
        eor.setOperation(eOperation);// 该委托已完结
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);
    }

    @Override
    public List<ProjectMoneyPage> findProjectMoney(ProjectMoneyPage pMoneyPage) {
        return entrustInfoDao.findProjectMoney(pMoneyPage);
    }

    @Override
    public List<ProjectTestMoneyPage> findProjectTestMoney(String strProjectId) {
        // 工程检测费用统计
        List<ProjectTestMoneyPage> ptmPageList = new ArrayList<ProjectTestMoneyPage>();
        // 工程检测费用明细
        List<ProjectTestMoneyDetailPage> ptmDetailPageList = new ArrayList<ProjectTestMoneyDetailPage>();
        // 工程检测费用统计对象
        ProjectTestMoneyPage ptm = new ProjectTestMoneyPage();
        ptm = entrustInfoDao.findProjectTestMoney(strProjectId);
        ptmDetailPageList = entrustInfoDao.findPTMDetailList(strProjectId);
        ptm.setPtmDetailPageList(ptmDetailPageList);
        ptmPageList.add(ptm);
        return ptmPageList;
    }

    /**
     * 根据委托信息，查询委托明细信息(报告：各科室审核批准用)
     */
    @Override
    public List<EntrustReportPage> findDepartmentDetailInfo(
            EntrustReportPage erPage, String userId) {
        SysUser sUser = sysUserDao.findById(userId);

        BaseDepartment bd = baseDepartmentDao.findById(sUser.getDepartmentId());
        List<BaseDepartment> wtDepartmentId = new ArrayList<BaseDepartment>();
        //00非检测科室
        if (bd.getIsTestDept().equals("00")) {
            wtDepartmentId = baseDepartmentDao.findByProperty("parentDepartmentId", bd.getDepartmentId());

        }
        List<EntrustReportPage> erPageList = entrustInfoDao
                .findDepartmentDetailInfo(erPage, wtDepartmentId);
        for (EntrustReportPage erPageTemp : erPageList) {
            List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
            List<SampleReport> srList = sampleReportDao.findByProperty(
                    "entrustDetailId", erPageTemp.getEntrustDetailId());
            for (SampleReport sr : srList) {
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
            erPageTemp.setSrPageList(srPageList);
        }
        return erPageList;
    }

    /**
     * 根据委托信息，查询委托明细信息(报告：各科室审核批准用)
     */
    @Override
    public List<EntrustReportPage> findDepartmentDetailInputInfo(
            EntrustReportPage erPage, String userId) {
        SysUser sUser = sysUserDao.findById(userId);
        BaseDepartment bd = baseDepartmentDao.findById(sUser.getDepartmentId());
        List<BaseDepartment> wtDepartmentId = new ArrayList<BaseDepartment>();
        //00非检测科室
        if (bd.getIsTestDept().equals("00")) {
            wtDepartmentId = baseDepartmentDao.findByProperty("parentDepartmentId", bd.getDepartmentId());

        }
//		if (!CommonMethod.isNull(bd.getParentDepartmentId())) {
//			wtDepartmentId = bd.getDepartmentId();
//		}
        List<EntrustReportPage> erPageList = entrustInfoDao
                .findDepartmentDetailInputInfo(erPage, wtDepartmentId);
	/*	for (EntrustReportPage erPageTemp : erPageList) {
			List<SampleReportPage> srPageList = new ArrayList<SampleReportPage>();
			List<SampleReport> srList = sampleReportDao.findByProperty(
					"entrustDetailId", erPageTemp.getEntrustDetailId());
			for (SampleReport sr : srList) {
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
			erPageTemp.setSrPageList(srPageList);
		}*/
        return erPageList;
    }

    @Override
    public List<String> ifAllReportStatus(Collection<EntrustReportPage> coll) {
        List<String> map = new ArrayList<String>();
        for (EntrustReportPage erPage : coll) {
            //
            List<SampleReport> srList = sampleReportDao.findByProperty(
                    "entrustDetailId", erPage.getEntrustDetailId());
            List<String> reportIdList = new ArrayList<String>();
            for (SampleReport sr : srList) {
                if (!CommonMethod.isNull(sr.getReportId())) {
                    reportIdList.add(sr.getReportId());
                }
            }
            for (String reportId : reportIdList) {
                // 委托ID下的所有报告数据
                DetachedCriteria dcSr = DetachedCriteria
                        .forClass(TestReportInfo.class);
                dcSr.add(Property.forName("reportId").eq(reportId));
                String status = "03";
                if (erPage.getMenuStatus().equals("03")) {
                    status = "06";
                }
                dcSr.add(Property.forName("reportStatus").eq(status));
                List<TestReportInfo> tri = testReportInfoDao
                        .findByCriteria(dcSr);
                for (TestReportInfo temp : tri) {
                    map.add(erPage.getEntrustSn() + " " + temp.getReportName());
                }
            }
        }
        return map;
    }

    @Override
    public void saveAllReportStatus(Collection<EntrustReportPage> coll,
                                    String userId) {
        for (EntrustReportPage erPage : coll) {
            //
            List<SampleReport> srList = sampleReportDao.findByProperty(
                    "entrustDetailId", erPage.getEntrustDetailId());
            List<String> reportIdList = new ArrayList<String>();
            for (SampleReport sr : srList) {
                if (!CommonMethod.isNull(sr.getReportId())
                        && !"null".equals(sr.getReportId())) {
                    reportIdList.add(sr.getReportId());
                }
            }
            for (String reportId : reportIdList) {
                // 报告数据
                TestReportInfo tri = testReportInfoDao.findById(reportId);
                if ("01".equals(erPage.getMenuStatus())) {
                    if ("01".equals(tri.getReportStatus())) {
                        tri.setReportStatus("02");
                        tri.setUpdater(userId);
                        tri.setUpdateTime(CommonMethod.getDate());
                    }
                } else if ("02".equals(erPage.getMenuStatus())) {
                    if ("02".equals(tri.getReportStatus())
                            || "03".equals(tri.getReportStatus())) {
                        tri.setReportStatus("04");
                        tri.setAuditor(userId);
                        tri.setAuditTime(CommonMethod.getDate());
                    }
                } else if ("03".equals(erPage.getMenuStatus())) {
                    if ("04".equals(tri.getReportStatus())
                            || "06".equals(tri.getReportStatus())) {
                        String reportNo = "";
                        if (CommonMethod.isNull(tri.getReportNo())) {
                            // 报告数据
                            List<SampleReport> srList1 = sampleReportDao
                                    .findByProperty("reportId",
                                            tri.getReportId());
                            // 样品ID
                            String sampleId = srList1.get(0).getSampleId();
                            // 样品信息
                            BaseSample bs = baseSampleDao.findById(sampleId);
                            reportNo = getNextReportSn(bs.getSnRule());
                            tri.setReportNo(reportNo);
                        }

                        tri.setReportStatus("05");
                        tri.setApprover(userId);
                        tri.setApproveTime(CommonMethod.getDate());
                    }
                } else if ("04".equals(erPage.getMenuStatus())) {
                    if ("05".equals(tri.getReportStatus())) {
                        tri.setReportStatus("07");
                        tri.setDistribute(userId);
                        tri.setDistributeTime(CommonMethod.getDate());

                        saveEntrustStatus(tri, userId);
                    }
                }
                testReportInfoDao.update(tri);
                ReportOperationRecord roRecord = new ReportOperationRecord();
                roRecord.setOperationRecordId(CommonMethod.getNewKey());
                roRecord.setReportId(tri.getReportId());
                roRecord.setOperation(tri.getReportStatus());// 报告操作状态
                roRecord.setInputer(userId);
                roRecord.setInputeTime(CommonMethod.getDate());
                rOperationRecordDao.save(roRecord);
            }
        }
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

    /**
     * 查询各科室产值统计信息
     */
    @Override
    public List<OutputValueCountPage> findOutputValueCount(
            String strOutputValueYear) {
        return entrustInfoDao.findOutputValueCount(strOutputValueYear);
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

        List<String> reportIdList = new ArrayList<String>();
        boolean isAllHaveReport = true;
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
        boolean isAllPrint = false;
        // //上委托明细ID LIST
        // List<String> eDetailIdList = new ArrayList<String>();
        // for(EntrustDetails edTmp : edList){
        // eDetailIdList.add(edTmp.getEntrustDetailId());
        // }

        // 查找委托中的报告是否全部打印
        DetachedCriteria dcTri = DetachedCriteria
                .forClass(TestReportInfo.class);
        dcTri.add(Property.forName("reportId").in(reportIdList));
        dcTri.add(Property.forName("reportStatus").ne("07"));
        List<TestReportInfo> triList = testReportInfoDao.findByCriteria(dcTri);
        if (triList != null && triList.size() > 0) {
        } else {
            isAllPrint = true;
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

                ei.setEntrustStatus("03");// 该委托报告已全部发放
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
    public List<TestReportInfoPage> findDepartmentReportInfo(Integer pageNo,
                                                             Integer pageSize, String departmentId, String userId,
                                                             String entrustSn, String entrustCompanyName, String projectName,
                                                             String startDate, String endDate, String inputTime,
                                                             String entrustDate, String projectPart, String sampleAge,
                                                             String reportStatus, String reportNo, String inputerName,
                                                             String auditorName, String apprvoerName, String printUserName) {
        List<TestReportInfoPage> findDepartmentReportInfo = entrustInfoDao
                .findDepartmentReportInfo(pageNo, pageSize, departmentId,
                        entrustSn, entrustCompanyName, projectName, startDate,
                        endDate, inputTime, entrustDate, projectPart,
                        sampleAge, reportStatus, reportNo, inputerName,
                        auditorName, apprvoerName, printUserName);
        for (TestReportInfoPage testReportInfoPage : findDepartmentReportInfo) {
            List<TwoDInfo> tdList = twoDInfoDao.findByProperty("reportId",
                    testReportInfoPage.getReportId());
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
            testReportInfoPage.setTwoDInfoUrl(twoDInfoUrl + twodInfoId);
        }
        return findDepartmentReportInfo;
    }

    @Override
    public List<AgreementPage> findReceivablesStatistics(Integer pageNo,
                                                         Integer pageSize, String accountType, String contractNo,
                                                         String entrustCompanyName, String projectName) {
        List<AgreementPage> findReceivablesStatistics = entrustInfoDao
                .findReceivablesStatistics(pageNo, pageSize, accountType,
                        contractNo, entrustCompanyName, projectName);
        return findReceivablesStatistics;
    }

    @Override
    public List<WaterAccountStatisticsPage> findentrustWaterAccountStatistics(
            Integer pageNo, Integer pageSize, String entrustSn,
            String startDate, String endDate, String entrustDate,
            String inputTime, String capitalAccountNo, String inputerName,
            String entrustCompanyName, String projectName,
            String entrustStatus, String accountKinds, String testDepartment,
            String managmentDepartment, String acceptanceMan, String sampleName, String accountType) {
        List<WaterAccountStatisticsPage> entrustWaterAccountStatisticsSql = entrustInfoDao
                .entrustWaterAccountStatisticsSql(pageNo, pageSize, entrustSn,
                        startDate, endDate, entrustDate, inputTime,
                        capitalAccountNo, inputerName, entrustCompanyName,
                        projectName, entrustStatus, accountKinds,
                        testDepartment, managmentDepartment, acceptanceMan,
                        sampleName, accountType);
        return entrustWaterAccountStatisticsSql;
    }

}