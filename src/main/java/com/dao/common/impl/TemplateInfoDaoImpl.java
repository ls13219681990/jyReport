package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.TemplateInfoDao;
import com.model.TemplateInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("templateInfoDao")
public class TemplateInfoDaoImpl extends BaseDaoImpl<TemplateInfo> implements TemplateInfoDao {


    @Override
    public PaginationSupport<TemplateInfo> findPageByCriteria(PaginationSupport<TemplateInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TemplateInfo> findJsonPageByCriteria(JsonPager<TemplateInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TemplateInfo> findEasyPageByCriteria(EasyPager<TemplateInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}