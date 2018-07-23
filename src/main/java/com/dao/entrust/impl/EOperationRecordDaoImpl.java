package com.dao.entrust.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
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