package com.dao.sys.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.sys.SysRoleFunctionDao;
import com.model.SysRoleFunction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("sysRoleFunctionDao")
public class SysRoleFunctionDaoImpl extends BaseDaoImpl<SysRoleFunction> implements SysRoleFunctionDao {

    @Override
    public void deleteByRole(final String roleId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "delete SysRoleFunction s where s.roleId = :roleId";
                return session.createQuery(hql)
                        .setString("roleId", roleId)
                        .executeUpdate();
            }
        });
    }

    @Override
    public PaginationSupport<SysRoleFunction> findPageByCriteria(PaginationSupport<SysRoleFunction> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysRoleFunction> findJsonPageByCriteria(JsonPager<SysRoleFunction> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysRoleFunction> findEasyPageByCriteria(EasyPager<SysRoleFunction> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}