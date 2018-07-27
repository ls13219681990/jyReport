package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.TestParameterDao;
import com.model.TestParameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("testParameterDao")
public class TestParameterDaoImpl extends BaseDaoImpl<TestParameter> implements TestParameterDao {


    @Override
    public PaginationSupport<TestParameter> findPageByCriteria(PaginationSupport<TestParameter> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TestParameter> findJsonPageByCriteria(JsonPager<TestParameter> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TestParameter> findEasyPageByCriteria(EasyPager<TestParameter> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}