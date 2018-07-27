package com.dao.sys;

import com.dao.common.BaseDao;
import com.model.SysRoleFunction;

public interface SysRoleFunctionDao extends BaseDao<SysRoleFunction> {

    public void deleteByRole(String roleId);
}