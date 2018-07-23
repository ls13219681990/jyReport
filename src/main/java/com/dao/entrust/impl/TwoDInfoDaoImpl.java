package com.dao.entrust.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
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