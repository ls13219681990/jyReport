package com.dao.sys;

import com.common.dao.BaseDao;
import com.model.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {
    void updatePword(SysUser sysUser);
}