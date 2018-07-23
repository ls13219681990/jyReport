package com.dao.finance.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.finance.ReceivableEnDetailsDao;
import com.model.ReceivableEntrustDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("receivableEnDetailsDao")
public class ReceivableEnDetailsDaoImpl extends BaseDaoImpl<ReceivableEntrustDetails> implements ReceivableEnDetailsDao {

    @Override
    public void deleteByInDetailId(final String strInvoiceDetailId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "delete ReceivableEntrustDetails s where s.invoiceDetailId = :invoiceDetailId";
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
                String hql = "delete ReceivableEntrustDetails s where s.accountDetailId = :accountDetailId";
                return session.createQuery(hql)
                        .setString("accountDetailId", strAccountDetailId)
                        .executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<ReceivableEntrustDetails> findPageByCriteria(PaginationSupport<ReceivableEntrustDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<ReceivableEntrustDetails> findJsonPageByCriteria(JsonPager<ReceivableEntrustDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<ReceivableEntrustDetails> findEasyPageByCriteria(EasyPager<ReceivableEntrustDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}