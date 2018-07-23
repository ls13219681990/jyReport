package com.service.common;

import com.common.service.BaseService;
import com.dao.page.BaseSamplePage;
import com.dao.page.TemplateInfoPage;
import com.dao.page.TemplateLocationPage;
import com.model.BaseSample;

import java.util.Collection;
import java.util.List;

public interface BaseSampleService extends BaseService<BaseSample> {

    List<BaseSamplePage> findSample(String strDepartmentId);

    void saveSample(Collection<BaseSample> coll, String userId);

    void updateSample(BaseSample bs, TemplateLocationPage tLocation, String userId, String updateFlag);

    String saveTemplateInfo(TemplateInfoPage esPage, String userId);

    void updateTemplateInfo(TemplateInfoPage esPage, String userId, TemplateLocationPage tLocationPage, String updateFlag);

    void deleteTemplateInfo(TemplateInfoPage esPage, String userId);
}