package com.dao.sys;

import com.common.dao.BaseDao;
import com.model.SysRoleFunction;

public interface SysRoleFunctionDao extends BaseDao<SysRoleFunction> {

    public void deleteByRole(String roleId);
}