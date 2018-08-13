package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.TestDataInfoDao;
import com.model.TestDataInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository("testDataInfoDao")
public class TestDataInfoDaoImpl extends BaseDaoImpl<TestDataInfo> implements TestDataInfoDao {

    @Override
    public void deleteByLocationId(final String locationId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete TestDataInfo s where s.locationId = :locationId";
                Query query = session.createQuery(sql).setString("locationId", locationId);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<TestDataInfo> findPageByCriteria(PaginationSupport<TestDataInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TestDataInfo> findJsonPageByCriteria(JsonPager<TestDataInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TestDataInfo> findEasyPageByCriteria(EasyPager<TestDataInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}