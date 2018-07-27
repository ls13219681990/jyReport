package com.dao.sys.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
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