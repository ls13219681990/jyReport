package com.dao.entrust.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.entrust.TestReportInfoDao;
import com.model.TestReportInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("testReportInfoDao")
public class TestReportInfoDaoImpl extends BaseDaoImpl<TestReportInfo> implements TestReportInfoDao {


    @Override
    public PaginationSupport<TestReportInfo> findPageByCriteria(PaginationSupport<TestReportInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<TestReportInfo> findJsonPageByCriteria(JsonPager<TestReportInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<TestReportInfo> findEasyPageByCriteria(EasyPager<TestReportInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}