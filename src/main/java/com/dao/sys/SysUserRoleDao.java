package com.dao.sys;

import com.common.dao.BaseDao;
import com.model.SysUserRole;

public interface SysUserRoleDao extends BaseDao<SysUserRole> {
    public void deleteByUser(String userId);
}