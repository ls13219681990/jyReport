package com.dao.common.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
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