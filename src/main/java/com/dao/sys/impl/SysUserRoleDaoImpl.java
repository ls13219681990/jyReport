package com.dao.sys.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.sys.SysUserRoleDao;
import com.model.SysUserRole;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole> implements SysUserRoleDao {
    @Override
    public void deleteByUser(final String userId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "delete SysUserRole s where s.sysUserId = :userId";
                return session.createQuery(hql)
                        .setString("userId", userId)
                        .executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<SysUserRole> findPageByCriteria(PaginationSupport<SysUserRole> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysUserRole> findJsonPageByCriteria(JsonPager<SysUserRole> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysUserRole> findEasyPageByCriteria(EasyPager<SysUserRole> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}