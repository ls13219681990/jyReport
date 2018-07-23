package com.dao.common.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.common.TemplateRecordDao;
import com.model.TemplateRecord;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("templateRecordDao")
public class TemplateRecordDaoImpl extends BaseDaoImpl<TemplateRecord> implements TemplateRecordDao {


    @Override
    public PaginationSupport<TemplateRecord> findPageByCriteria(PaginationSupport<TemplateRecord> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TemplateRecord> findJsonPageByCriteria(JsonPager<TemplateRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TemplateRecord> findEasyPageByCriteria(EasyPager<TemplateRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}