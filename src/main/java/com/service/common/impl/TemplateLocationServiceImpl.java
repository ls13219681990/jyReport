package com.service.common.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.common.TemplateLocationDao;
import com.dao.common.TestDataInfoDao;
import com.dao.page.TemplateLocationPage;
import com.dao.page.TestDataInfoPage;
import com.model.TemplateLocation;
import com.model.TestDataInfo;
import com.service.common.TemplateLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("templateLocationService")
public class TemplateLocationServiceImpl extends BaseServiceImpl<TemplateLocation> implements
        TemplateLocationService {

    @Autowired
    private TemplateLocationDao templateLocationDao;
    @Autowired
    private TestDataInfoDao testDataInfoDao;

    @Override
    @Resource(name = "templateLocationDao")
    protected void initBaseDAO(BaseDao<TemplateLocation> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<TemplateLocation> findJsonPageByCriteria(JsonPager<TemplateLocation> jp,
                                                              TemplateLocation t) {
        return null;
    }

    @Override
    public List<TemplateLocationPage> findTLocationPageList(String strTemplateInfoId) {
        List<TemplateLocation> tLocationList = templateLocationDao.findByProperty("templateInfoId", strTemplateInfoId);
        List<TemplateLocationPage> tLocationPageList = new ArrayList<TemplateLocationPage>();
        for (TemplateLocation tLocation : tLocationList) {
            TemplateLocationPage tl = new TemplateLocationPage();
            tl.setLocationId(tLocation.getLocationId());
            tl.setReportNo(tLocation.getReportNo());
            tl.setTemplateInfoId(tLocation.getTemplateInfoId());
            tl.setQrCode(tLocation.getQrCode());
            tl.setReportName(tLocation.getReportName());
            tl.setEntrustSn(tLocation.getEntrustSn());
            tl.setEntrustCompanyName(tLocation.getEntrustCompanyName());
            tl.setProjectName(tLocation.getProjectName());
            tl.setEntrustDate(tLocation.getEntrustDate());
            tl.setProjectParts(tLocation.getProjectParts());
            tl.setTestDate(tLocation.getTestDate());
            tl.setSupervisionUnitName(tLocation.getSupervisionUnitName());
            tl.setWitnessMan(tLocation.getWitnessMan());
            tl.setParameterName(tLocation.getParameterName());
            tl.setSampleName(tLocation.getSampleName());
            tl.setSampleSourceName(tLocation.getSampleSourceName());
            tl.setReportDate(tLocation.getReportDate());
            tl.setStandards(tLocation.getStandards());
            tl.setSampleNo(tLocation.getSampleNo());
            tl.setSampleState(tLocation.getSampleState());
            tl.setEntrustLinkMan(tLocation.getEntrustLinkMan());
            tl.setMoldingDate(tLocation.getMoldingDate());
            tl.setTestAge(tLocation.getTestAge());
            tl.setSmapleLevel(tLocation.getSmapleLevel());
            tl.setTestResult(tLocation.getTestResult());
            tl.setTester(tLocation.getTester());
            tl.setAuditor(tLocation.getAuditor());
            tl.setApprover(tLocation.getApprover());
            tl.setTestData(tLocation.getTestData());
            tl.setQcNumber(tLocation.getQcNumber());
            tl.setMultiSample(tLocation.getMultiSample());
            tl.setCellSpan(tLocation.getCellSpan());
            tl.setQrCellSpan(tLocation.getQrCellSpan());
            tl.setTestResultArrayMode(tLocation.getTestResultArrayMode());
            tl.setExcludeConfig(tLocation.getExcludeConfig());
            tl.setPageRowCount(tLocation.getPageRowCount());

            List<TestDataInfo> tDataInfoList = testDataInfoDao.findByProperty("locationId", tLocation.getLocationId());
            List<TestDataInfoPage> tDataInfoPageList = new ArrayList<TestDataInfoPage>();
            for (TestDataInfo tDataInfo : tDataInfoList) {
                TestDataInfoPage tDataInfoPage = new TestDataInfoPage();
                tDataInfoPage.setTestDataId(tDataInfo.getTestDataId());
                tDataInfoPage.setLocationId(tDataInfo.getLocationId());
                tDataInfoPage.setTestLocation(tDataInfo.getTestLocation());
                tDataInfoPage.setTestName(tDataInfo.getTestName());
                tDataInfoPage.setTestUnit(tDataInfo.getTestUnit());
                tDataInfoPage.setArrayMode(tDataInfo.getArrayMode());
                tDataInfoPage.setRedundancy(tDataInfo.getRedundancy());
                tDataInfoPageList.add(tDataInfoPage);
            }
            tl.setTestDataInfoList(tDataInfoPageList);
            tLocationPageList.add(tl);
        }


        return tLocationPageList;
    }
}