package com.dao.common.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.common.BaseSampleDao;
import com.model.BaseSample;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("baseSampleDao")
public class BaseSampleDaoImpl extends BaseDaoImpl<BaseSample> implements BaseSampleDao {


    public PaginationSupport<BaseSample> findPageByCriteria(PaginationSupport<BaseSample> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    public JsonPager<BaseSample> findJsonPageByCriteria(JsonPager<BaseSample> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    public EasyPager<BaseSample> findEasyPageByCriteria(EasyPager<BaseSample> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}