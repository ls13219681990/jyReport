package com.dao.common;

import com.common.dao.BaseDao;
import com.model.TestDataInfo;

public interface TestDataInfoDao extends BaseDao<TestDataInfo> {

    void deleteByLocationId(String locationId);
}