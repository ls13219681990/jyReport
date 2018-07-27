package com.service.sys;

import com.service.common.BaseService;
import com.model.SysRoleFunction;

import java.util.Collection;
import java.util.List;

public interface SysRoleFunctionService extends BaseService<SysRoleFunction> {
    List<SysRoleFunction> findFunctionByRole(String strRoleId);

    void saveSysRoleFunction(Collection<SysRoleFunction> coll, String strRoleId);

    List<SysRoleFunction> findFuncitonUsed(String strFunctionId);
}