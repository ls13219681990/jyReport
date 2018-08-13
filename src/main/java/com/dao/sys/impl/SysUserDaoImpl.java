package com.dao.sys.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.sys.SysUserDao;
import com.model.SysUser;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {

    @Override
    public void updatePword(final SysUser sysUser) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "update SYS_USER set USER_PASSWORD = '" + sysUser.getUserPassword() + "',updater = '" + sysUser.getUpdater() + "',update_time = '" + sysUser.getUpdateTime() + "' where SYS_USER_ID = '" + sysUser.getSysUserId() + "'";
                Query query = session.createQuery(sql);
                return query.executeUpdate();
            }
        });
//		getHibernateTemplate().update(sysUser);
    }

    @Override
    public PaginationSupport<SysUser> findPageByCriteria(PaginationSupport<SysUser> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysUser> findJsonPageByCriteria(JsonPager<SysUser> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysUser> findEasyPageByCriteria(EasyPager<SysUser> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}