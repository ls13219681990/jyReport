package com.service.sys;

import com.common.service.BaseService;
import com.model.SysFunction;

import java.util.Collection;
import java.util.List;

public interface SysFunctionService extends BaseService<SysFunction> {

    List<SysFunction> findFunction();

    void saveFunction(Collection<SysFunction> coll);

    void updateFunction(Collection<SysFunction> coll);

    List<SysFunction> findFunctionValid();
}