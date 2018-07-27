package com.dao.common;

import com.model.TestDataInfo;

public interface TestDataInfoDao extends BaseDao<TestDataInfo> {

    void deleteByLocationId(String locationId);
}