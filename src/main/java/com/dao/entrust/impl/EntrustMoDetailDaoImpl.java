package com.dao.entrust.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.entrust.EntrustMoDetailDao;
import com.model.EntrustMoneyDetails;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository("entrustMoDetailDao")
public class EntrustMoDetailDaoImpl extends BaseDaoImpl<EntrustMoneyDetails> implements EntrustMoDetailDao {

    @Override
    public void deleteByEntrustDetailId(final String entrustDetailId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete EntrustMoneyDetails s where s.entrustDetailId = :entrustDetailId";
                Query query = session.createQuery(sql).setString("entrustDetailId", entrustDetailId);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<EntrustMoneyDetails> findPageByCriteria(PaginationSupport<EntrustMoneyDetails> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustMoneyDetails> findJsonPageByCriteria(JsonPager<EntrustMoneyDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustMoneyDetails> findEasyPageByCriteria(EasyPager<EntrustMoneyDetails> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}