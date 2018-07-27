package com.dao.sys;

import com.dao.common.BaseDao;
import com.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {
    void updatePword(SysUser sysUser);
}