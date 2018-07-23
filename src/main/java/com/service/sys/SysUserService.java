package com.service.sys;

import com.common.service.BaseService;
import com.dao.page.UserRolePage;
import com.model.SysUser;

import java.util.Collection;
import java.util.List;

public interface SysUserService extends BaseService<SysUser> {
    List<UserRolePage> findSysUser();

    void saveSysUser(Collection<SysUser> coll, String userId);

    void updateSysUser(Collection<SysUser> coll, String userId);

    void updateSysPassword(String oldPassword, String newPassword, String userId);

    void resetSysPassword(String userId);

    List<UserRolePage> findSysUserValid();

    UserRolePage findUserRole(SysUser su);
}