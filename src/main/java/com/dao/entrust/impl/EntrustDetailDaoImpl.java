package com.dao.entrust.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.entrust.EntrustDetailDao;
import com.model.EntrustDetails;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("entrustDetailDao")
public class EntrustDetailDaoImpl extends BaseDaoImpl<EntrustDetails> implements EntrustDetailDao {

    @Override
    public void deleteByEntrustDetailId(final String entrustDetailId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String sql = "delete EntrustDetails s where s.entrustDetailId = :entrustDetailId";
                Query query = session.createQuery(sql).setString("entrustDetailId", entrustDetailId);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<EntrustDetails> findPageByCriteria(PaginationSupport<EntrustDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustDetails> findJsonPageByCriteria(JsonPager<EntrustDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustDetails> findEasyPageByCriteria(EasyPager<EntrustDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}