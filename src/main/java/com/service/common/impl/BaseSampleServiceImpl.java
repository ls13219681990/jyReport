package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.BaseSampleDao;
import com.dao.common.TemplateInfoDao;
import com.dao.common.TemplateLocationDao;
import com.dao.common.TestDataInfoDao;
import com.dao.page.BaseSamplePage;
import com.dao.page.TemplateInfoPage;
import com.dao.page.TemplateLocationPage;
import com.dao.page.TestDataInfoPage;
import com.model.BaseSample;
import com.model.TemplateInfo;
import com.model.TemplateLocation;
import com.model.TestDataInfo;
import com.service.common.BaseSampleService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("baseSampleService")
public class BaseSampleServiceImpl extends BaseServiceImpl<BaseSample> implements
        BaseSampleService {

    @Autowired
    private BaseSampleDao baseSampleDao;
    @Autowired
    private TemplateLocationDao templateLocationDao;
    @Autowired
    private TestDataInfoDao testDataInfoDao;
    @Autowired
    private TemplateInfoDao templateInfoDao;
	
	
	/*@Override
	@Resource(name = "baseSampleDao")
	protected void initBaseDAO(BaseDao<BaseSample> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<BaseSample> findJsonPageByCriteria(JsonPager<BaseSample> jp,
			BaseSample t) {
		return null;
	}*/


    @Override
    public List<BaseSamplePage> findSample(String strDepartmentId) {
        DetachedCriteria dc = DetachedCriteria.forClass(BaseSample.class);
        if (!CommonMethod.isNull(strDepartmentId)) {
            dc.add(Property.forName("departmentId").eq(strDepartmentId));
        }
        List<BaseSample> bsList = baseSampleDao.findByCriteria(dc);
        List<BaseSamplePage> bsPageList = new ArrayList<BaseSamplePage>();
        for (BaseSample bs : bsList) {
            BaseSamplePage bspage = new BaseSamplePage();
            bspage.setSampleId(bs.getSampleId());
            bspage.setDepartmentId(bs.getDepartmentId());
            bspage.setSampleName(bs.getSampleName());
            bspage.setSpecificationsModels(bs.getSpecificationsModels());
            bspage.setStandards(bs.getStandards());
            bspage.setRemark(bs.getRemark());
            bspage.setSampleLevel(bs.getSampleLevel());
            bspage.setInputer(bs.getInputer());
            bspage.setInputeTime(bs.getInputeTime());
            bspage.setUpdater(bs.getUpdater());
            bspage.setUpdateTime(bs.getUpdateTime());
            bspage.setTestResult(bs.getTestResult());
            bspage.setSnRule(bs.getSnRule());
            bspage.setStatus(bs.getStatus());

            List<TemplateInfo> tInfoList = templateInfoDao.findByProperty("sampleId", bs.getSampleId());
            List<TemplateInfoPage> tInfoPageList = new ArrayList<TemplateInfoPage>();
            for (TemplateInfo tInfo : tInfoList) {
                TemplateInfoPage tInfoPage = new TemplateInfoPage();
                tInfoPage.setTemplateInfoId(tInfo.getTemplateInfoId());
                tInfoPage.setSampleId(tInfo.getSampleId());
                tInfoPage.setTemplateName(tInfo.getTemplateName());
                tInfoPage.setTemplatePath(tInfo.getTemplatePath());
                tInfoPage.setInputer(tInfo.getInputer());
                tInfoPage.setTemplateViewName(tInfo.getTemplateViewName());
                tInfoPage.setInputeTime(tInfo.getInputeTime());
                tInfoPage.setUpdateFlag(tInfo.getUpdateFlag());
                tInfoPageList.add(tInfoPage);
            }

            bspage.settInfoPage(tInfoPageList);
            bsPageList.add(bspage);
        }
        return bsPageList;
    }

    @Override
    public void saveSample(Collection<BaseSample> coll, String userId) {
        for (BaseSample baseSample : coll) {
            List<BaseSample> bsList = baseSampleDao.findByProperty("sampleName", baseSample.getSampleName());
            if (bsList != null && bsList.size() > 0) {
                throw new BusinessException("该样品名称已经存在！", "");
            }
            if (CommonMethod.isNull(baseSample.getDepartmentId())) {
                throw new BusinessException("部门ID不能为空！", "");
            }
            baseSample.setSampleId(CommonMethod.getNewKey());
            baseSample.setInputeTime(CommonMethod.getDate());
            baseSample.setInputer(userId);
            baseSample.setStatus("01");
            baseSampleDao.save(baseSample);
        }
    }

    @Override
    public void updateSample(BaseSample baseSample, TemplateLocationPage tLocationPage, String userId, String updateFlag) {
        if ("1".equals(updateFlag)) {
            BaseSample bSample = baseSampleDao.findById(baseSample.getSampleId());
            if (bSample == null) {
                throw new BusinessException("需要更新的样品不存在，请确认！", "");
            }
            if (CommonMethod.isNull(baseSample.getDepartmentId())) {
                throw new BusinessException("部门ID不能为空！", "");
            }
            DetachedCriteria dc = DetachedCriteria.forClass(BaseSample.class);
            dc.add(Property.forName("sampleId").ne(baseSample.getSampleId()));
            dc.add(Property.forName("sampleName").eq(baseSample.getSampleName()));
            List<BaseSample> bsList = baseSampleDao.findByCriteria(dc);
            if (bsList != null && bsList.size() > 0) {
                throw new BusinessException("该样品名称已经存在！", "");
            }
            bSample.setDepartmentId(baseSample.getDepartmentId());
            bSample.setSampleName(baseSample.getSampleName());
            bSample.setSpecificationsModels(baseSample.getSpecificationsModels());
            bSample.setStandards(baseSample.getStandards());
            bSample.setRemark(baseSample.getRemark());
            bSample.setSampleLevel(baseSample.getSampleLevel());
            bSample.setUpdater(userId);
            bSample.setUpdateTime(CommonMethod.getDate());
            bSample.setTestResult(baseSample.getTestResult());
            bSample.setSnRule(baseSample.getSnRule());
            if (!"".equals(baseSample.getStatus()) && baseSample.getStatus() != null) {
                bSample.setStatus(baseSample.getStatus());
            }
            baseSampleDao.update(bSample);
        }
    }

    @Override
    public String saveTemplateInfo(TemplateInfoPage esPage, String userId) {
        if (CommonMethod.isNull(esPage.getSampleId())) {
            throw new BusinessException("样品ID不能为空！", "");
        }
        String templateInfoId = CommonMethod.getNewKey();
        TemplateInfo tInfo = new TemplateInfo();
        tInfo.setTemplateInfoId(templateInfoId);
        tInfo.setSampleId(esPage.getSampleId());
        tInfo.setTemplateViewName(esPage.getTemplateViewName());
        tInfo.setSnRule(esPage.getSnRule());
        tInfo.setInputeTime(CommonMethod.getDate());
        tInfo.setInputer(userId);
        tInfo.setUpdateFlag("1");
        //后期补上
        tInfo.setTemplateName(esPage.getTemplateName());
        tInfo.setTemplatePath(esPage.getTemplatePath());
        templateInfoDao.save(tInfo);

        return templateInfoId;
    }

    @Override
    public void updateTemplateInfo(TemplateInfoPage esPage, String userId, TemplateLocationPage tLocationPage, String updateFlag) {

        //修改模板信息表
        if ("1".equals(updateFlag)) {
            if (CommonMethod.isNull(esPage.getSampleId())) {
                throw new BusinessException("样品ID不能为空！", "");
            }
            TemplateInfo tInfo = templateInfoDao.findById(esPage.getTemplateInfoId());
            tInfo.setTemplateViewName(esPage.getTemplateViewName());
            tInfo.setSnRule(esPage.getSnRule());
            tInfo.setInputeTime(CommonMethod.getDate());
            tInfo.setInputer(userId);
            if (esPage.getUpdateFlag() != null) {
                tInfo.setUpdateFlag(esPage.getUpdateFlag());
            } else {
                if (tInfo.getUpdateFlag() != null) {
                    tInfo.setInputer(tInfo.getUpdateFlag());
                }
            }
            templateInfoDao.save(tInfo);

        }
        //修改模板关键字表
        if ("0".equals(updateFlag)) {
            List<TemplateLocation> tlList = templateLocationDao.findByProperty("templateInfoId", tLocationPage.getTemplateInfoId());
            if (tlList != null && tlList.size() > 0) {
                TemplateLocation tl = tlList.get(0);
                tl.setReportNo(tLocationPage.getReportNo());
                tl.setQrCode(tLocationPage.getQrCode());
                tl.setReportName(tLocationPage.getReportName());
                tl.setEntrustSn(tLocationPage.getEntrustSn());
                tl.setEntrustCompanyName(tLocationPage.getEntrustCompanyName());
                tl.setProjectName(tLocationPage.getProjectName());
                tl.setEntrustDate(tLocationPage.getEntrustDate());
                tl.setProjectParts(tLocationPage.getProjectParts());
                tl.setTestDate(tLocationPage.getTestDate());
                tl.setSupervisionUnitName(tLocationPage.getSupervisionUnitName());
                tl.setWitnessMan(tLocationPage.getWitnessMan());
                tl.setParameterName(tLocationPage.getParameterName());
                tl.setSampleName(tLocationPage.getSampleName());
                tl.setSampleSourceName(tLocationPage.getSampleSourceName());
                tl.setReportDate(tLocationPage.getReportDate());
                tl.setStandards(tLocationPage.getStandards());
                tl.setSampleNo(tLocationPage.getSampleNo());
                tl.setSampleState(tLocationPage.getSampleState());
                tl.setEntrustLinkMan(tLocationPage.getEntrustLinkMan());
                tl.setMoldingDate(tLocationPage.getMoldingDate());
                tl.setTestAge(tLocationPage.getTestAge());
                tl.setSmapleLevel(tLocationPage.getSmapleLevel());
                tl.setTestResult(tLocationPage.getTestResult());
                tl.setTester(tLocationPage.getTester());
                tl.setAuditor(tLocationPage.getAuditor());
                tl.setApprover(tLocationPage.getApprover());
                tl.setTestData(tLocationPage.getTestData());
                tl.setQcNumber(tLocationPage.getQcNumber());
                tl.setMultiSample(tLocationPage.getMultiSample());
                tl.setCellSpan(tLocationPage.getCellSpan());
                tl.setQrCellSpan(tLocationPage.getQrCellSpan());
                tl.setTestResultArrayMode(tLocationPage.getTestResultArrayMode());
                tl.setExcludeConfig(tLocationPage.getExcludeConfig());
                tl.setPageRowCount(tLocationPage.getPageRowCount());
                templateLocationDao.update(tl);

                testDataInfoDao.deleteByLocationId(tl.getLocationId());
                List<TestDataInfoPage> tDataInfoList = tLocationPage.getTestDataInfoList();
                for (TestDataInfoPage tDataInfoPage : tDataInfoList) {
                    TestDataInfo tDataInfo = new TestDataInfo();
                    tDataInfo.setTestDataId(CommonMethod.getNewKey());
                    tDataInfo.setLocationId(tl.getLocationId());
                    tDataInfo.setTestLocation(tDataInfoPage.getTestLocation());
                    tDataInfo.setTestName(tDataInfoPage.getTestName());
                    tDataInfo.setTestUnit(tDataInfoPage.getTestUnit());
                    tDataInfo.setArrayMode(tDataInfoPage.getArrayMode());
                    tDataInfo.setRedundancy(tDataInfoPage.getRedundancy());
                    testDataInfoDao.save(tDataInfo);
                }
                TemplateInfo tInfo = templateInfoDao.findById(tLocationPage.getTemplateInfoId());
                if (tLocationPage.getUpdateFlag() != null) {
                    tInfo.setUpdateFlag(tLocationPage.getUpdateFlag());
                } else {
                    if (tInfo.getUpdateFlag() != null) {
                        tInfo.setInputer(tInfo.getUpdateFlag());
                    }
                }
                templateInfoDao.save(tInfo);
				/*TemplateInfo tInfo = templateInfoDao.findById(tLocationPage.getTemplateInfoId());
				BaseSample bSample = baseSampleDao.findById(tInfo.getSampleId());
				bSample.setUpdateFlag("1");
				baseSampleDao.update(bSample);*/
            } else {
                TemplateLocation tl = new TemplateLocation();
                tl.setLocationId(CommonMethod.getNewKey());
                tl.setTemplateInfoId(tLocationPage.getTemplateInfoId());
                tl.setReportNo(tLocationPage.getReportNo());
                tl.setQrCode(tLocationPage.getQrCode());
                tl.setReportName(tLocationPage.getReportName());
                tl.setEntrustSn(tLocationPage.getEntrustSn());
                tl.setEntrustCompanyName(tLocationPage.getEntrustCompanyName());
                tl.setProjectName(tLocationPage.getProjectName());
                tl.setEntrustDate(tLocationPage.getEntrustDate());
                tl.setProjectParts(tLocationPage.getProjectParts());
                tl.setTestDate(tLocationPage.getTestDate());
                tl.setSupervisionUnitName(tLocationPage.getSupervisionUnitName());
                tl.setWitnessMan(tLocationPage.getWitnessMan());
                tl.setParameterName(tLocationPage.getParameterName());
                tl.setSampleName(tLocationPage.getSampleName());
                tl.setSampleSourceName(tLocationPage.getSampleSourceName());
                tl.setReportDate(tLocationPage.getReportDate());
                tl.setStandards(tLocationPage.getStandards());
                tl.setSampleNo(tLocationPage.getSampleNo());
                tl.setSampleState(tLocationPage.getSampleState());
                tl.setEntrustLinkMan(tLocationPage.getEntrustLinkMan());
                tl.setMoldingDate(tLocationPage.getMoldingDate());
                tl.setTestAge(tLocationPage.getTestAge());
                tl.setSmapleLevel(tLocationPage.getSmapleLevel());
                tl.setTestResult(tLocationPage.getTestResult());
                tl.setTester(tLocationPage.getTester());
                tl.setAuditor(tLocationPage.getAuditor());
                tl.setApprover(tLocationPage.getApprover());
                tl.setTestData(tLocationPage.getTestData());
                tl.setQcNumber(tLocationPage.getQcNumber());
                tl.setMultiSample(tLocationPage.getMultiSample());
                tl.setCellSpan(tLocationPage.getCellSpan());
                tl.setQrCellSpan(tLocationPage.getQrCellSpan());
                tl.setTestResultArrayMode(tLocationPage.getTestResultArrayMode());
                tl.setPageRowCount(tLocationPage.getPageRowCount());
                templateLocationDao.save(tl);
                TemplateInfo tInfo = templateInfoDao.findById(tLocationPage.getTemplateInfoId());
                if (tLocationPage.getUpdateFlag() != null) {
                    tInfo.setUpdateFlag(tLocationPage.getUpdateFlag());
                } else {
                    if (tInfo.getUpdateFlag() != null) {
                        tInfo.setUpdateFlag(tInfo.getUpdateFlag());
                    }
                }
                templateInfoDao.update(tInfo);
            }
        }
    }

    @Override
    public void deleteTemplateInfo(TemplateInfoPage esPage, String userId) {
        if (CommonMethod.isNull(esPage.getTemplateInfoId())) {
            throw new BusinessException("模板对象ID不能为空！", "");
        }
        TemplateInfo tInfo = new TemplateInfo();
        tInfo.setTemplateInfoId(esPage.getTemplateInfoId());
        templateInfoDao.delete(tInfo);
    }

    @Override
    protected void initBaseDAO(BaseDao<BaseSample> baseDao) {

    }

    @Override
    public JsonPager<BaseSample> findJsonPageByCriteria(JsonPager<BaseSample> jp, BaseSample baseSample) {
        return null;
    }
}