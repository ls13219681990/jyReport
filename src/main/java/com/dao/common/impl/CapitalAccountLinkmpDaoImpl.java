package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.CapitalAccountLinkmpDao;
import com.model.CapitalAccountLinkmp;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("capitalAccountLinkmpDao")
public class CapitalAccountLinkmpDaoImpl extends BaseDaoImpl<CapitalAccountLinkmp> implements CapitalAccountLinkmpDao {


    @Override
    public PaginationSupport<CapitalAccountLinkmp> findPageByCriteria(PaginationSupport<CapitalAccountLinkmp> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<CapitalAccountLinkmp> findJsonPageByCriteria(JsonPager<CapitalAccountLinkmp> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<CapitalAccountLinkmp> findEasyPageByCriteria(EasyPager<CapitalAccountLinkmp> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}