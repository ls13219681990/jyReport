package com.service.common.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.TemplateRecord;
import com.service.common.TemplateRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("templateRecordService")
public class TemplateRecordServiceImpl extends BaseServiceImpl<TemplateRecord> implements
        TemplateRecordService {

    @Override
    @Resource(name = "templateRecordDao")
    protected void initBaseDAO(BaseDao<TemplateRecord> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<TemplateRecord> findJsonPageByCriteria(JsonPager<TemplateRecord> jp,
                                                            TemplateRecord t) {
        return null;
    }

}