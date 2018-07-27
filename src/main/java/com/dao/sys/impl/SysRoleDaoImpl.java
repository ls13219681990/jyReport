package com.dao.sys.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.sys.SysRoleDao;
import com.model.SysRole;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao {

    @Override
    public PaginationSupport<SysRole> findPageByCriteria(PaginationSupport<SysRole> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysRole> findJsonPageByCriteria(JsonPager<SysRole> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysRole> findEasyPageByCriteria(EasyPager<SysRole> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}