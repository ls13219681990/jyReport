package com.dao.sys.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.sys.SysFunctionDao;
import com.model.SysFunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("sysFunctionDao")
public class SysFunctionDaoImpl extends BaseDaoImpl<SysFunction> implements SysFunctionDao {


    @Override
    public PaginationSupport<SysFunction> findPageByCriteria(PaginationSupport<SysFunction> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysFunction> findJsonPageByCriteria(JsonPager<SysFunction> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysFunction> findEasyPageByCriteria(EasyPager<SysFunction> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}