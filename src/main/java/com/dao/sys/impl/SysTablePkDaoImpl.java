package com.dao.sys.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.sys.SysTablePkDao;
import com.model.SysTablePk;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("sysTablePkDao")
public class SysTablePkDaoImpl extends BaseDaoImpl<SysTablePk> implements SysTablePkDao {
    /**
     * 获取主键值
     *
     * @param tablename 表名
     * @param count     本次获取主键值个数
     * @return
     */
    public Long getNextKey(String tablename, int count) {
        SysTablePk sysTablepk = new SysTablePk();
        sysTablepk = (SysTablePk) getHibernateTemplate().get(SysTablePk.class, tablename);
        int returnstr;
        if (sysTablepk == null || sysTablepk.getTableName() == null) {
            sysTablepk = new SysTablePk();
            sysTablepk.setTableName(tablename);
            sysTablepk.setCurrentPk(String.valueOf(100000l));
            getHibernateTemplate().save(sysTablepk);
            return 100000l;
        } else {
            long res = Integer.parseInt(sysTablepk.getCurrentPk()) + 1;
            returnstr = Integer.parseInt(sysTablepk.getCurrentPk()) + count;
            sysTablepk.setCurrentPk(String.valueOf(returnstr));
            sysTablepk.setTableName(tablename);
            getHibernateTemplate().update(sysTablepk);
            return res;
        }
    }

    @Override
    public PaginationSupport<SysTablePk> findPageByCriteria(PaginationSupport<SysTablePk> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysTablePk> findJsonPageByCriteria(JsonPager<SysTablePk> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysTablePk> findEasyPageByCriteria(EasyPager<SysTablePk> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}
