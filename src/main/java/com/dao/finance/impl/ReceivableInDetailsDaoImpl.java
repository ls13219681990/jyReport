package com.dao.finance.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.finance.ReceivableInDetailsDao;
import com.model.ReceivableInvoiceDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("receivableInDetailsDao")
public class ReceivableInDetailsDaoImpl extends BaseDaoImpl<ReceivableInvoiceDetails> implements ReceivableInDetailsDao {

    @Override
    public void deleteByInDetailId(final String strInvoiceDetailId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "delete ReceivableInvoiceDetails s where s.invoiceDetailId = :invoiceDetailId";
                return session.createQuery(hql)
                        .setString("invoiceDetailId", strInvoiceDetailId)
                        .executeUpdate();
            }
        });
    }

    @Override
    public void deleteByAcDetailId(final String strAccountDetailId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "delete ReceivableInvoiceDetails s where s.accountDetailId = :accountDetailId";
                return session.createQuery(hql)
                        .setString("accountDetailId", strAccountDetailId)
                        .executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<ReceivableInvoiceDetails> findPageByCriteria(PaginationSupport<ReceivableInvoiceDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<ReceivableInvoiceDetails> findJsonPageByCriteria(JsonPager<ReceivableInvoiceDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<ReceivableInvoiceDetails> findEasyPageByCriteria(EasyPager<ReceivableInvoiceDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}