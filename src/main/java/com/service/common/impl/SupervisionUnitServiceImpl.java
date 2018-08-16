package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.common.SupervisionUnitDao;
import com.model.SupervisionUnit;
import com.service.common.SupervisionUnitService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Transactional
@Service("supervisionUnitService")
public class SupervisionUnitServiceImpl extends BaseServiceImpl<SupervisionUnit> implements
        SupervisionUnitService {

    @Autowired
    private SupervisionUnitDao supervisionUnitDao;

    @Override
    @Resource(name = "supervisionUnitDao")
    protected void initBaseDAO(BaseDao<SupervisionUnit> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<SupervisionUnit> findJsonPageByCriteria(JsonPager<SupervisionUnit> jp,
                                                             SupervisionUnit t) {
        return null;
    }

    @Override
    public List<SupervisionUnit> findSupervisionUnit(String strSupervisionUnitId, String strSupervisionUnitName, String strWitnesses) {
        DetachedCriteria dc = DetachedCriteria.forClass(SupervisionUnit.class);
        if (!CommonMethod.isNull(strSupervisionUnitId)) {
            dc.add(Property.forName("supervisionUnitId").eq(strSupervisionUnitId));
        }
        if (!CommonMethod.isNull(strSupervisionUnitName)) {
            strSupervisionUnitName = strSupervisionUnitName.replace("OO", "#");
            dc.add(Property.forName("supervisionUnitName").like(strSupervisionUnitName, MatchMode.ANYWHERE));
        }
        if (!CommonMethod.isNull(strWitnesses)) {
            dc.add(Property.forName("witnesses").like(strWitnesses, MatchMode.ANYWHERE));
        }
        dc.addOrder(Order.desc("inputeTime"));
        return supervisionUnitDao.findByCriteria(dc);
    }

    @Override
    public List<SupervisionUnit> findSupervisionUnitName(String strSupervisionUnitId) {
        SupervisionUnit su = supervisionUnitDao.findById(strSupervisionUnitId);

        DetachedCriteria dc = DetachedCriteria.forClass(SupervisionUnit.class);
        dc.add(Property.forName("supervisionUnitName").eq(su.getSupervisionUnitName()));
        dc.addOrder(Order.desc("inputeTime"));
        return supervisionUnitDao.findByCriteria(dc);
    }

    @Override
    public String saveSupervisionUnit(Collection<SupervisionUnit> coll, String userId) {
        String supervisionUnitId = "";
        for (SupervisionUnit su : coll) {
            DetachedCriteria dc = DetachedCriteria.forClass(SupervisionUnit.class);
            dc.add(Property.forName("supervisionUnitName").eq(su.getSupervisionUnitName()));
            //dc.add(Property.forName("witnesses").eq(su.getWitnesses()));
            List<SupervisionUnit> suList = supervisionUnitDao.findByCriteria(dc);
            if (suList != null && suList.size() > 0) {
                throw new BusinessException("该监理单位名称已经存在，不能添加 ，请重试！", "");
            }
            su.setSupervisionUnitId(CommonMethod.getNewKey());
            su.setInputer(userId);
            su.setInputeTime(CommonMethod.getDate());
            supervisionUnitDao.save(su);

            supervisionUnitId = su.getSupervisionUnitId();
        }

        return supervisionUnitId;
    }

    @Override
    public void updateSupervisionUnit(Collection<SupervisionUnit> coll, String userId) {
        for (SupervisionUnit su : coll) {
            DetachedCriteria dc = DetachedCriteria.forClass(SupervisionUnit.class);
            dc.add(Property.forName("supervisionUnitId").ne(su.getSupervisionUnitId()));
            dc.add(Property.forName("supervisionUnitName").eq(su.getSupervisionUnitName()));
            dc.add(Property.forName("witnesses").eq(su.getWitnesses()));
            List<SupervisionUnit> suList = supervisionUnitDao.findByCriteria(dc);
            if (suList != null && suList.size() > 0) {
                throw new BusinessException("该监理单位与见证人已经存在，不能修改！", "");
            }
            SupervisionUnit sUnit = supervisionUnitDao.findById(su.getSupervisionUnitId());
            sUnit.setSupervisionUnitName(su.getSupervisionUnitName());
            sUnit.setWitnesses(su.getWitnesses());
            sUnit.setRemark(su.getRemark());
            sUnit.setUpdater(userId);
            sUnit.setUpdateTime(CommonMethod.getDate());
            supervisionUnitDao.update(sUnit);
        }
    }

}