package com.service.common;

import com.dao.page.TemplateLocationPage;
import com.model.TemplateLocation;

import java.util.List;

public interface TemplateLocationService extends BaseService<TemplateLocation> {

    List<TemplateLocationPage> findTLocationPageList(String strSampleId);
}