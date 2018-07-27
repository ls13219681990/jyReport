package com.dao.entrust.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.entrust.EOperationRecordDao;
import com.model.EntrustOperationRecord;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("eOperationRecordDao")
public class EOperationRecordDaoImpl extends BaseDaoImpl<EntrustOperationRecord> implements EOperationRecordDao {


    @Override
    public PaginationSupport<EntrustOperationRecord> findPageByCriteria(PaginationSupport<EntrustOperationRecord> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustOperationRecord> findJsonPageByCriteria(JsonPager<EntrustOperationRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustOperationRecord> findEasyPageByCriteria(EasyPager<EntrustOperationRecord> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}