package com.dao.common.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.common.PaymentTypeDao;
import com.model.PaymentType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("paymentTypeDao")
public class PaymentTypeDaoImpl extends BaseDaoImpl<PaymentType> implements PaymentTypeDao {


    @Override
    public PaginationSupport<PaymentType> findPageByCriteria(PaginationSupport<PaymentType> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<PaymentType> findJsonPageByCriteria(JsonPager<PaymentType> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<PaymentType> findEasyPageByCriteria(EasyPager<PaymentType> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}