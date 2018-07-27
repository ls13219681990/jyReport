package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.SignNameDao;
import com.model.SignName;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("signNameDao")
public class SignNameDaoImpl extends BaseDaoImpl<SignName> implements SignNameDao {


    @Override
    public PaginationSupport<SignName> findPageByCriteria(PaginationSupport<SignName> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SignName> findJsonPageByCriteria(JsonPager<SignName> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SignName> findEasyPageByCriteria(EasyPager<SignName> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}