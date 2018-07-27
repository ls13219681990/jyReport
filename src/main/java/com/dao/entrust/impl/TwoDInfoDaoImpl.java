package com.dao.entrust.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.entrust.TwoDInfoDao;
import com.model.TwoDInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("twoDInfoDao")
public class TwoDInfoDaoImpl extends BaseDaoImpl<TwoDInfo> implements TwoDInfoDao {


    @Override
    public PaginationSupport<TwoDInfo> findPageByCriteria(PaginationSupport<TwoDInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TwoDInfo> findJsonPageByCriteria(JsonPager<TwoDInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TwoDInfo> findEasyPageByCriteria(EasyPager<TwoDInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}