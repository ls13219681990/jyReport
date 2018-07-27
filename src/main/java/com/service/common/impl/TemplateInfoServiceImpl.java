package com.service.common.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.common.TemplateInfoDao;
import com.dao.page.TemplateInfoPage;
import com.model.TemplateInfo;
import com.service.common.TemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("templateInfoService")
public class TemplateInfoServiceImpl extends BaseServiceImpl<TemplateInfo> implements
        TemplateInfoService {

    @Autowired
    private TemplateInfoDao templateInfoDao;

    @Override
    @Resource(name = "templateInfoDao")
    protected void initBaseDAO(BaseDao<TemplateInfo> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<TemplateInfo> findJsonPageByCriteria(JsonPager<TemplateInfo> jp,
                                                          TemplateInfo t) {
        return null;
    }

    @Override
    public List<TemplateInfoPage> findTInfoPageList(String strSampleId) {
        List<TemplateInfo> tInfoList = templateInfoDao.findByProperty("sampleId", strSampleId);
        List<TemplateInfoPage> tInfoPageList = new ArrayList<TemplateInfoPage>();
        for (TemplateInfo tInfo : tInfoList) {
            TemplateInfoPage tInfoPage = new TemplateInfoPage();
            tInfoPage.setTemplateInfoId(tInfo.getTemplateInfoId());
            tInfoPage.setSampleId(tInfo.getSampleId());
            tInfoPage.setTemplateName(tInfo.getTemplateName());
            tInfoPage.setTemplatePath(tInfo.getTemplatePath());
            tInfoPage.setTemplateViewName(tInfo.getTemplateViewName());
            tInfoPage.setSnRule(tInfo.getSnRule());
            tInfoPage.setInputer(tInfo.getInputer());
            tInfoPage.setInputeTime(tInfo.getInputeTime());
        }

        return tInfoPageList;
    }
}