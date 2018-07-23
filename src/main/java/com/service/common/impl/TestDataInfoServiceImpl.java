package com.service.common.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.TestDataInfo;
import com.service.common.TestDataInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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