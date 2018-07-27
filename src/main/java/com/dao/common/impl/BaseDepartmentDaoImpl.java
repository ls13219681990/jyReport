package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.BaseDepartmentDao;
import com.model.BaseDepartment;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;


@Repository("baseDepartmentDao")
public class BaseDepartmentDaoImpl extends BaseDaoImpl<BaseDepartment> implements BaseDepartmentDao {


    public PaginationSupport<BaseDepartment> findPageByCriteria(PaginationSupport<BaseDepartment> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    public JsonPager<BaseDepartment> findJsonPageByCriteria(JsonPager<BaseDepartment> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    public EasyPager<BaseDepartment> findEasyPageByCriteria(EasyPager<BaseDepartment> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}