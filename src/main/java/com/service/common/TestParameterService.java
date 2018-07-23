package com.service.common;

import com.common.service.BaseService;
import com.model.TestParameter;

import java.util.Collection;
import java.util.List;

public interface TestParameterService extends BaseService<TestParameter> {
    List<TestParameter> findTeParameter(String strBaseSampleId);

    void saveTeParameter(Collection<TestParameter> coll, String userId);

    void updateTeParameter(Collection<TestParameter> coll, String userId);

    void updateReportSn(String strBaseSampleId, String strReportSn, String userId);

    public List<TestParameter> findAllParameter(String strDepartmentId);
}