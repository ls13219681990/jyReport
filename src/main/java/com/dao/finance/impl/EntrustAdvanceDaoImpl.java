package com.dao.finance.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.finance.EntrustAdvanceDao;
import com.model.EntrustAdvance;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("entrustAdvanceDao")
public class EntrustAdvanceDaoImpl extends BaseDaoImpl<EntrustAdvance> implements EntrustAdvanceDao {


    @Override
    public PaginationSupport<EntrustAdvance> findPageByCriteria(PaginationSupport<EntrustAdvance> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustAdvance> findJsonPageByCriteria(JsonPager<EntrustAdvance> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustAdvance> findEasyPageByCriteria(EasyPager<EntrustAdvance> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}