package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
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