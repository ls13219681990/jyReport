package com.dao.finance.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.finance.InvoiceDetailsDao;
import com.model.InvoiceDetails;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("invoiceDetailsDao")
public class InvoiceDetailsDaoImpl extends BaseDaoImpl<InvoiceDetails> implements InvoiceDetailsDao {


    @Override
    public PaginationSupport<InvoiceDetails> findPageByCriteria(PaginationSupport<InvoiceDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<InvoiceDetails> findJsonPageByCriteria(JsonPager<InvoiceDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<InvoiceDetails> findEasyPageByCriteria(EasyPager<InvoiceDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}