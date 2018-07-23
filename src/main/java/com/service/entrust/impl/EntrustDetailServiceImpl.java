package com.service.entrust.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.BaseSampleDao;
import com.dao.common.TestParameterDao;
import com.dao.entrust.*;
import com.dao.page.EntrustDetailReportPage;
import com.dao.page.EntrustSavePage;
import com.model.*;
import com.service.common.RunningNumService;
import com.service.entrust.EntrustDetailService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("entrustDetailService")
public class EntrustDetailServiceImpl extends BaseServiceImpl<EntrustDetails> implements
        EntrustDetailService {

    @Autowired
    private EntrustDetailDao entrustDetailDao;

    @Autowired
    private EntrustInfoDao entrustInfoDao;

    @Autowired
    private EntrustMoDetailDao entrustMoDetailDao;

    @Autowired
    private ROperationRecordDao rOperationRecordDao;

    @Autowired
    private EOperationRecordDao eOperationRecordDao;

    @Autowired
    private TestParameterDao testParameterDao;

    @Autowired
    private BaseSampleDao baseSampleDao;

    @Autowired
    private TestReportInfoDao testReportInfoDao;

    @Autowired
    private RunningNumService runningNumService;

    @Autowired
    private SampleReportDao sampleReportDao;
	
	/*@Override
	@Resource(name = "entrustDetailDao")
	protected void initBaseDAO(BaseDao<EntrustDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<EntrustDetails> findJsonPageByCriteria(JsonPager<EntrustDetails> jp,
			EntrustDetails t) {
		return null;
	}*/

    @Override
    public List<EntrustDetails> findIsEntrust(String strDepartmentId, String strSampleId, String strTParameterId) {
        DetachedCriteria dc = DetachedCriteria.forClass(EntrustDetails.class);
        if (!CommonMethod.isNull(strDepartmentId)) {
            dc.add(Property.forName("departmentId").eq(strDepartmentId));//部门
        }
        if (!CommonMethod.isNull(strSampleId)) {
            dc.add(Property.forName("sampleId").eq(strSampleId));//样品
        }
//		if(!CommonMethod.isNull(strTParameterId)){
//			dc.add(Property.forName("testParameterId").eq(strTParameterId));//参数
//		}
        return entrustDetailDao.findByCriteria(dc);
    }

    @Override
    public void saveEDetailReport(EntrustSavePage esPage, String userId) {

        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能办理上委托业务，请确认！", "");
        }
        //委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        int sampleNum = 1;
        String maxSampleNo = "";
        List<String> sampleNoList = entrustInfoDao.findMaxSampleNo(entrustId);
        if (sampleNoList != null && sampleNoList.size() > 0) {
            for (String sampleNo : sampleNoList) {
                int lastPlace = sampleNo.lastIndexOf("-");
                String numTmp = sampleNo.substring(lastPlace + 1);
                if (numTmp.compareTo(maxSampleNo) > 0) {
                    maxSampleNo = numTmp;
                }
            }
        }
        if (!CommonMethod.isNull(maxSampleNo)) {
            sampleNum = Integer.valueOf(maxSampleNo) + 1;
        }
        //委托报告明细
        List<EntrustDetailReportPage> edsPageList = esPage.getEdReport();
        for (EntrustDetailReportPage edrPage : edsPageList) {
            EntrustDetails ed = new EntrustDetails();
            //样品编号
            String sampleNo = ei.getEntrustSn() + "-" + sampleNum;

            String strTestParameterId = "";
            //样品信息
            BaseSample bs = baseSampleDao.findById(edrPage.getSampleId());
            String eDetailId = CommonMethod.getNewKey();
//			//一个样品中包含多个检测参数
//			List<EntrustParameterInfoPage> parPageList = edrPage.getEnPaInfoPageList();
//			for(EntrustParameterInfoPage eParPage :parPageList){
//				//委托金额明细中相同的部门样品检测参数是否存在
//				DetachedCriteria dcEmd = DetachedCriteria.forClass(EntrustMoneyDetails.class);
//				dcEmd.add(Property.forName("entrustId").eq(entrustId));
//				dcEmd.add(Property.forName("departmentId").eq(edrPage.getDepartmentId()));
//				dcEmd.add(Property.forName("sampleId").eq(edrPage.getSampleId()));
//				dcEmd.add(Property.forName("testParameterId").eq(eParPage.getTestParameterId()));
//				List<EntrustMoneyDetails> emDetailList = entrustMoDetailDao.findByCriteria(dcEmd);
//				if(emDetailList!=null && emDetailList.size()>0){
//				}else{
//					throw new BusinessException("上委托参数与办委托参数不符，不能进行上委托业务，请确认！","");
//				}
//				//参数信息
//				TestParameter tp = testParameterDao.findById(eParPage.getTestParameterId());
//				//参数ID
//				if(CommonMethod.isNull(strTestParameterId)){
//					strTestParameterId = strTestParameterId +eParPage.getTestParameterId();
//				}else{
//					strTestParameterId = strTestParameterId+"|" +eParPage.getTestParameterId();
//				}
//				for(int i=0;i<edrPage.getReportNum();i++){
//					TestReportInfo trInfo = new TestReportInfo();
//					String reportSn = "";
//					if("00".equals(ei.getIsComplementally())){//非补报告
//						reportSn = getNextReportSn(tp.getSnRule());
//					}else{//补报告
//						reportSn = getNextReportSn(tp.getSnRule());
//					}
//					//样品名称
//					String ypName = bs.getSampleName();
//					//检测参数名称
//					String csName = tp.getTestParameterName();
//					//报告名称
//					String reportName = ypName+"_"+csName+"检测报告";
//					String ReportId = CommonMethod.getNewKey();
//					trInfo.setReportId(ReportId);
//					trInfo.setTestParameterId(eParPage.getTestParameterId());
//					trInfo.setEntrustDetailId(eDetailId);
//					trInfo.setReportNo(reportSn);
//					trInfo.setReportName(reportName);
//					trInfo.setReportStatus("01");//报告未录入
//					trInfo.setSampleNo(sampleNo);
//					trInfo.setProjectParts(edrPage.getProjectPart());
//					trInfo.setPrintNum(0);//未打印
//					trInfo.setInputer(userId);
//					trInfo.setInputeTime(CommonMethod.getDate());
//					testReportInfoDao.save(trInfo);
//					
//					ReportOperationRecord roRecord = new ReportOperationRecord();
//					roRecord.setOperationRecordId(CommonMethod.getNewKey());
//					roRecord.setReportId(ReportId);
//					roRecord.setOperation("01");//录入报告记录
//					roRecord.setInputer(userId);
//					roRecord.setInputeTime(CommonMethod.getDate());
//					rOperationRecordDao.save(roRecord);
//				}
//			}
//			//报告数据
//			ed.setEntrustDetailId(eDetailId);
//			ed.setEntrustId(entrustId);
//			ed.setDepartmentId(edrPage.getDepartmentId());
//			ed.setSampleId(edrPage.getSampleId());
//			ed.setTestParameterId(strTestParameterId);
//			ed.setSampleNo(sampleNo);
//			ed.setSampleSource(edrPage.getSampleSource());
//			ed.setProjectPart(edrPage.getProjectPart());
//			ed.setMoldingDate(edrPage.getMoldingDate());
//			ed.setSampleAge(edrPage.getSampleAge());
//			ed.setReportNum(edrPage.getReportNum());
//			ed.setProcessStatus("01");//上委托
//			ed.setRemark(edrPage.getRemark());
//			ed.setInputer(userId);
//			ed.setInputeTime(CommonMethod.getDate());
//			ed.setStandards(edrPage.getStandards());
//			ed.setWitnessMan(edrPage.getWitness());
//			ed.setSampleLevel(bs.getSampleLevel());
//			entrustDetailDao.save(ed);
//			sampleNum++;
        }

        //修改委托状态为上委托
        ei.setEntrustStatus("02");
        ei.setUpdater(userId);
        ei.setUpdateTime(CommonMethod.getDate());
        entrustInfoDao.update(ei);

        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(entrustId);
        eor.setOperation("04");//录入上委托明细
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);
    }

    @Override
    public void updateEDetailReport(EntrustSavePage esPage, String userId) {
        String entrustId = esPage.getEntrustId();
        if (CommonMethod.isNull(entrustId)) {
            throw new BusinessException("委托ID为空，不能修改上委托业务，请确认！", "");
        }
        //委托信息
        EntrustInfo ei = entrustInfoDao.findById(entrustId);
        int sampleNum = 1;
        String maxSampleNo = "";
        List<String> sampleNoList = entrustInfoDao.findMaxSampleNo(entrustId);
        if (sampleNoList != null && sampleNoList.size() > 0) {
            for (String sampleNo : sampleNoList) {
                int lastPlace = sampleNo.lastIndexOf("-");
                String numTmp = sampleNo.substring(lastPlace + 1);
                if (numTmp.compareTo(maxSampleNo) > 0) {
                    maxSampleNo = numTmp;
                }
            }
        }
        if (!CommonMethod.isNull(maxSampleNo)) {
            sampleNum = Integer.valueOf(maxSampleNo) + 1;
        }
        //委托报告明细
        List<EntrustDetailReportPage> edsPageList = esPage.getEdReport();
        for (EntrustDetailReportPage edrPage : edsPageList) {
            String strTestParameterId = "";
            //一个样品中包含多个检测参数
//			List<EntrustParameterInfoPage> parPageList = edrPage.getEnPaInfoPageList();
//			for(EntrustParameterInfoPage eParPage :parPageList){
//				//委托金额明细中相同的部门样品检测参数是否存在
//				DetachedCriteria dcEmd = DetachedCriteria.forClass(EntrustMoneyDetails.class);
//				dcEmd.add(Property.forName("entrustId").eq(entrustId));
//				dcEmd.add(Property.forName("departmentId").eq(edrPage.getDepartmentId()));
//				dcEmd.add(Property.forName("sampleId").eq(edrPage.getSampleId()));
//				dcEmd.add(Property.forName("testParameterId").eq(eParPage.getTestParameterId()));
//				List<EntrustMoneyDetails> emDetailList = entrustMoDetailDao.findByCriteria(dcEmd);
//				if(emDetailList!=null && emDetailList.size()>0){
//				}else{
//					throw new BusinessException("上委托参数与办委托参数不符，不能修改上委托业务，请确认！","");
//				}
//				//参数ID
//				if(CommonMethod.isNull(strTestParameterId)){
//					strTestParameterId = strTestParameterId +eParPage.getTestParameterId();
//				}else{
//					strTestParameterId = strTestParameterId+"|" +eParPage.getTestParameterId();
//				}
//			}

            if (CommonMethod.isNull(edrPage.getEntrustDetailId())) {
                EntrustDetails ed = new EntrustDetails();
                //样品编号
                String sampleNo = ei.getEntrustSn() + "-" + sampleNum;

                //样品信息
                BaseSample bs = baseSampleDao.findById(edrPage.getSampleId());
                String eDetailId = CommonMethod.getNewKey();
                //一个样品中包含多个检测参数
//				List<EntrustParameterInfoPage> enParPageList = edrPage.getEnPaInfoPageList();
//				for(EntrustParameterInfoPage eParPage :enParPageList){
//					//委托金额明细中相同的部门样品检测参数是否存在
//					DetachedCriteria dcEmd = DetachedCriteria.forClass(EntrustMoneyDetails.class);
//					dcEmd.add(Property.forName("entrustId").eq(entrustId));
//					dcEmd.add(Property.forName("departmentId").eq(edrPage.getDepartmentId()));
//					dcEmd.add(Property.forName("sampleId").eq(edrPage.getSampleId()));
//					dcEmd.add(Property.forName("testParameterId").eq(eParPage.getTestParameterId()));
//					List<EntrustMoneyDetails> emDetailList = entrustMoDetailDao.findByCriteria(dcEmd);
//					if(emDetailList!=null && emDetailList.size()>0){
//					}else{
//						throw new BusinessException("上委托参数与办委托参数不符，不能进行上委托业务，请确认！","");
//					}
//					//参数信息
//					TestParameter tp = testParameterDao.findById(eParPage.getTestParameterId());
//					//参数ID
//					if(CommonMethod.isNull(strTestParameterId)){
//						strTestParameterId = strTestParameterId +eParPage.getTestParameterId();
//					}else{
//						strTestParameterId = strTestParameterId+"|" +eParPage.getTestParameterId();
//					}
//					for(int i=0;i<edrPage.getReportNum();i++){
//						TestReportInfo trInfo = new TestReportInfo();
//						String reportSn = "";
//						if("00".equals(ei.getIsComplementally())){//非补报告
//							reportSn = getNextReportSn(tp.getSnRule());
//						}else{//补报告
//							reportSn = getNextReportSn_Complementally(tp.getSnRule());
//						}
//						//样品名称
//						String ypName = bs.getSampleName();
//						//检测参数名称
//						String csName = tp.getTestParameterName();
//						//报告名称
//						String reportName = ypName+"_"+csName+"检测报告";
//						String ReportId = CommonMethod.getNewKey();
//						trInfo.setReportId(ReportId);
//						trInfo.setEntrustDetailId(eDetailId);
//						trInfo.setReportNo(reportSn);
//						trInfo.setReportName(reportName);
//						trInfo.setReportStatus("01");//报告未录入
//						trInfo.setSampleNo(sampleNo);
//						trInfo.setProjectParts(edrPage.getProjectPart());
//						trInfo.setPrintNum(0);//未打印
//						trInfo.setInputer(userId);
//						trInfo.setInputeTime(CommonMethod.getDate());
//						testReportInfoDao.save(trInfo);
//						
//						ReportOperationRecord roRecord = new ReportOperationRecord();
//						roRecord.setOperationRecordId(CommonMethod.getNewKey());
//						roRecord.setReportId(ReportId);
//						roRecord.setOperation("01");//录入报告记录
//						roRecord.setInputer(userId);
//						roRecord.setInputeTime(CommonMethod.getDate());
//						rOperationRecordDao.save(roRecord);
//					}
//				}
//				//报告数据
//				ed.setEntrustDetailId(eDetailId);
//				ed.setEntrustId(entrustId);
//				ed.setDepartmentId(edrPage.getDepartmentId());
//				ed.setSampleId(edrPage.getSampleId());
//				ed.setTestParameterId(strTestParameterId);
//				ed.setSampleNo(sampleNo);
//				ed.setSampleSource(edrPage.getSampleSource());
//				ed.setProjectPart(edrPage.getProjectPart());
//				ed.setMoldingDate(edrPage.getMoldingDate());
//				ed.setSampleAge(edrPage.getSampleAge());
//				ed.setReportNum(edrPage.getReportNum());
//				ed.setProcessStatus("01");//上委托
//				ed.setRemark(edrPage.getRemark());
//				ed.setInputer(userId);
//				ed.setInputeTime(CommonMethod.getDate());
//				ed.setStandards(edrPage.getStandards());
//				ed.setWitnessMan(edrPage.getWitness());
//				entrustDetailDao.save(ed);
//				sampleNum++;
//			}else{
//				EntrustDetails ed = new EntrustDetails();
//				//委托明细
//				DetachedCriteria dcEd = DetachedCriteria.forClass(EntrustDetails.class);
//				dcEd.add(Property.forName("entrustId").eq(entrustId));
//				dcEd.add(Property.forName("departmentId").eq(edrPage.getDepartmentId()));
//				dcEd.add(Property.forName("sampleId").eq(edrPage.getSampleId()));
//				dcEd.add(Property.forName("testParameterId").eq(strTestParameterId));
//				List<EntrustDetails> eDetailList = entrustDetailDao.findByCriteria(dcEd);
//				if(eDetailList!=null && eDetailList.size()>0){
//					ed = eDetailList.get(0);
//				}
//				if(ed.getReportNum()>edrPage.getReportNum()){//报告份数减少
//					//报告明细
//					DetachedCriteria dcTri = DetachedCriteria.forClass(TestReportInfo.class);
//					dcTri.add(Property.forName("entrustDetailId").eq(entrustId));
//					dcTri.add(Property.forName("reportStatus").eq("01"));//报告未录入
//					dcTri.add(Property.forName("reportPath").isNull());
//					List<TestReportInfo> triList =testReportInfoDao.findByCriteria(dcTri);
//					if(triList!=null && triList.size()>0){
//						int lessSum = ed.getReportNum()-edrPage.getReportNum();
//						if(lessSum>triList.size()){//报告减少份数>未生成报告记录数
//							throw new BusinessException("该委托的报告减少份数："+lessSum+"份，大于未生成报告记录数"+triList.size()+"份，不能减少报告份数，请确认！","");
//						}else{
//							for(int i=0;i<lessSum;i++){
//								testReportInfoDao.delete(triList.get(i));
//							}
//						}
//					}else{
//						throw new BusinessException("该委托的报告已全部生成，不能减少报告份数，请确认！","");
//					}
//				}else if(ed.getReportNum()<edrPage.getReportNum()){//报告份数增加
//					int addSum = edrPage.getReportNum()-ed.getReportNum();
//					//检测参数
//					TestParameter tp = testParameterDao.findById(edrPage.getTestParameterId());
//					//样品
//					BaseSample bs = baseSampleDao.findById(edrPage.getSampleId());
//					//报告数据
//					for(int i=0;i<addSum;i++){
//						TestReportInfo trInfo = new TestReportInfo();
//						String reportSn = "";
//						if("00".equals(ei.getIsComplementally())){//非补报告
//							reportSn = getNextReportSn(tp.getSnRule());
//						}else{//补报告
//							reportSn = getNextReportSn_Complementally(tp.getSnRule());
//						}
//						//样品名称
//						String ypName = bs.getSampleName();
//						//检测参数名称
//						String csName = tp.getTestParameterName();
//						//报告名称
//						String reportName = ypName+"_"+csName+"检测报告";
//						String ReportId = CommonMethod.getNewKey();
//						trInfo.setReportId(ReportId);
//						trInfo.setEntrustDetailId(ed.getEntrustDetailId());
//						trInfo.setReportNo(reportSn);
//						trInfo.setReportName(reportName);
//						trInfo.setReportStatus("01");//报告未录入
//						trInfo.setSampleNo(ed.getSampleNo());
//						trInfo.setProjectParts(edrPage.getProjectPart());
//						trInfo.setPrintNum(0);//未打印
//						trInfo.setInputer(userId);
//						trInfo.setInputeTime(CommonMethod.getDate());
//						testReportInfoDao.save(trInfo);
//						
//						ReportOperationRecord roRecord = new ReportOperationRecord();
//						roRecord.setOperationRecordId(CommonMethod.getNewKey());
//						roRecord.setReportId(ReportId);
//						roRecord.setOperation("01");//录入报告记录
//						roRecord.setInputer(userId);
//						roRecord.setInputeTime(CommonMethod.getDate());
//						rOperationRecordDao.save(roRecord);
//					}
//				}
//				ed.setSampleSource(edrPage.getSampleSource());
//				ed.setProjectPart(edrPage.getProjectPart());
//				ed.setMoldingDate(edrPage.getMoldingDate());
//				ed.setSampleAge(edrPage.getSampleAge());
//				ed.setReportNum(edrPage.getReportNum());
//				ed.setProcessStatus("01");//上委托
//				ed.setRemark(edrPage.getRemark());
//				ed.setUpdater(userId);
//				ed.setUpdateTime(CommonMethod.getDate());
//				ed.setStandards(edrPage.getStandards());
//				ed.setWitnessMan(edrPage.getWitness());
//				entrustDetailDao.update(ed);
            }
        }
        EntrustOperationRecord eor = new EntrustOperationRecord();
        eor.setOperationRecordId(CommonMethod.getNewKey());
        eor.setEntrustId(entrustId);
        eor.setOperation("05");//修改上委托明细
        eor.setInputer(userId);
        eor.setInputeTime(CommonMethod.getDate());
        eOperationRecordDao.save(eor);
    }

    private String getNextReportSn_Complementally(String rule) {

        String reportSn = getNextReportSn(rule);

        reportSn = reportSn + "_" + runningNumService.getNextNum(reportSn, 1);

        return reportSn;
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
    public List<SampleReport> EntrustDetailsInfo(String EntrustDetailsId) {
        EntrustDetails edId = entrustDetailDao.findById(EntrustDetailsId);
        List<SampleReport> SampleReportlist = sampleReportDao.findByProperty("entrustDetailId", edId.getEntrustDetailId());
        return SampleReportlist;
    }

    @Override
    protected void initBaseDAO(BaseDao<EntrustDetails> baseDao) {

    }

    @Override
    public JsonPager<EntrustDetails> findJsonPageByCriteria(JsonPager<EntrustDetails> jp, EntrustDetails entrustDetails) {
        return null;
    }
}