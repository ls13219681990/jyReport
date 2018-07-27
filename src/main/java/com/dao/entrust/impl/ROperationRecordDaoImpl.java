package com.dao.entrust.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.entrust.ROperationRecordDao;
import com.model.ReportOperationRecord;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("rOperationRecordDao")
public class ROperationRecordDaoImpl extends BaseDaoImpl<ReportOperationRecord> implements ROperationRecordDao {


    @Override
    public PaginationSupport<ReportOperationRecord> findPageByCriteria(PaginationSupport<ReportOperationRecord> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<ReportOperationRecord> findJsonPageByCriteria(JsonPager<ReportOperationRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<ReportOperationRecord> findEasyPageByCriteria(EasyPager<ReportOperationRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}