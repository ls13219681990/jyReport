package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
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