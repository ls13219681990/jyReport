package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
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