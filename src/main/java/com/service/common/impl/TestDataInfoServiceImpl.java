package com.service.common.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.TestDataInfo;
import com.service.common.TestDataInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("testDataInfoService")
public class TestDataInfoServiceImpl extends BaseServiceImpl<TestDataInfo> implements
        TestDataInfoService {

    @Override
    @Resource(name = "testDataInfoDao")
    protected void initBaseDAO(BaseDao<TestDataInfo> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<TestDataInfo> findJsonPageByCriteria(JsonPager<TestDataInfo> jp,
                                                          TestDataInfo t) {
        return null;
    }

}