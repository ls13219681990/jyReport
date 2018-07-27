package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.RunningNumDao;
import com.model.RunningNum;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("runningNumDao")
public class RunningNumDaoImpl extends BaseDaoImpl<RunningNum> implements RunningNumDao {


    @Override
    public PaginationSupport<RunningNum> findPageByCriteria(PaginationSupport<RunningNum> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<RunningNum> findJsonPageByCriteria(JsonPager<RunningNum> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<RunningNum> findEasyPageByCriteria(EasyPager<RunningNum> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}