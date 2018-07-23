package com.dao.sys.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
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