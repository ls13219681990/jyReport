package com.service.sys;

import com.service.common.BaseService;
import com.model.SysRole;

import java.util.Collection;
import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    List<SysRole> findSysRole();

    void saveSysRole(Collection<SysRole> coll, String userId);

    void updateSysRole(Collection<SysRole> coll);

    List<SysRole> findSysRoleValid();
}