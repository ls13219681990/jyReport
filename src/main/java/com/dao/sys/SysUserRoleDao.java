package com.dao.sys;

import com.dao.common.BaseDao;
import com.model.SysUserRole;

public interface SysUserRoleDao extends BaseDao<SysUserRole> {
    public void deleteByUser(String userId);
}