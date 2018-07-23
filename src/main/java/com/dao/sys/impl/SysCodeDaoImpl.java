package com.dao.sys.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.sys.SysCodeDao;
import com.model.SysCode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("sysCodeDao")
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao {


    @Override
    public PaginationSupport<SysCode> findPageByCriteria(PaginationSupport<SysCode> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SysCode> findJsonPageByCriteria(JsonPager<SysCode> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SysCode> findEasyPageByCriteria(EasyPager<SysCode> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}