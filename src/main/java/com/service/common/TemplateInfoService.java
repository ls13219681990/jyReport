package com.service.common;

import com.common.service.BaseService;
import com.dao.page.TemplateInfoPage;
import com.model.TemplateInfo;

import java.util.List;

public interface TemplateInfoService extends BaseService<TemplateInfo> {

    List<TemplateInfoPage> findTInfoPageList(String strSampleId);
}