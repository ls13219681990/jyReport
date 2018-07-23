package com.service.common;

import com.common.service.BaseService;
import com.model.BaseDepartment;

import java.util.Collection;
import java.util.List;

public interface BaseDepartmentService extends BaseService<BaseDepartment> {
    List<BaseDepartment> findDepartment(String strIsManagement);

    void saveDepartment(Collection<BaseDepartment> coll, String userId);

    void updateDepartment(Collection<BaseDepartment> coll, String userId);
}