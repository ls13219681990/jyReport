package com.dao.common.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.common.TemplateLocationDao;
import com.model.TemplateLocation;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("templateLocationDao")
public class TemplateLocationDaoImpl extends BaseDaoImpl<TemplateLocation> implements TemplateLocationDao {


    @Override
    public PaginationSupport<TemplateLocation> findPageByCriteria(PaginationSupport<TemplateLocation> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TemplateLocation> findJsonPageByCriteria(JsonPager<TemplateLocation> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TemplateLocation> findEasyPageByCriteria(EasyPager<TemplateLocation> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}