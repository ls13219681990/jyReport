package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.ProjectInfoDao;
import com.model.ProjectInfo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("projectInfoDao")
public class ProjectInfoDaoImpl extends BaseDaoImpl<ProjectInfo> implements ProjectInfoDao {


    @Override
    public PaginationSupport<ProjectInfo> findPageByCriteria(PaginationSupport<ProjectInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<ProjectInfo> findJsonPageByCriteria(JsonPager<ProjectInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<ProjectInfo> findEasyPageByCriteria(EasyPager<ProjectInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}