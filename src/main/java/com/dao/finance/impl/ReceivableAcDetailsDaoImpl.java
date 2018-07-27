package com.dao.finance.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.finance.ReceivableAcDetailsDao;
import com.model.ReceivableAccountDetails;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("receivableAcDetailsDao")
public class ReceivableAcDetailsDaoImpl extends BaseDaoImpl<ReceivableAccountDetails> implements ReceivableAcDetailsDao {


    @Override
    public PaginationSupport<ReceivableAccountDetails> findPageByCriteria(PaginationSupport<ReceivableAccountDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<ReceivableAccountDetails> findJsonPageByCriteria(JsonPager<ReceivableAccountDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<ReceivableAccountDetails> findEasyPageByCriteria(EasyPager<ReceivableAccountDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}