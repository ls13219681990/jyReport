package com.service.sys;

import com.common.service.BaseService;
import com.model.SysUserRole;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleService extends BaseService<SysUserRole> {
    List<SysUserRole> findRoleByUser(String strUserId);

    void saveSysUserRole(Collection<SysUserRole> coll, String strUserId);

    List<SysUserRole> findRoleUsed(String strRoleId);
}