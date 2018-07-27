package com.service.sys.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.sys.SysRoleDao;
import com.model.SysRole;
import com.service.sys.SysRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements
        SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    /*@Override
    @Resource(name = "sysRoleDao")
    protected void initBaseDAO(BaseDao<SysRole> baseDao) {
        setBaseDao(baseDao);
    }
*/
    @Override
    public PaginationSupport<SysRole> findPageByCriteria(
            PaginationSupport<SysRole> ps, SysRole t) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
        return sysRoleDao.findPageByCriteria(ps, Order.desc("sysRoleId"), dc);
    }

    @Override
    public JsonPager<SysRole> findJsonPageByCriteria(JsonPager<SysRole> jp,
                                                     SysRole t) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
        return sysRoleDao.findJsonPageByCriteria(jp, dc);

    }

    @Override
    public List<SysRole> findSysRole() {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
        return sysRoleDao.findByCriteria(dc);
    }

    @Override
    public void saveSysRole(Collection<SysRole> coll, String userId) {
        for (SysRole sysRole : coll) {
            sysRole.setRoleId(CommonMethod.getNewKey());
            sysRole.setInputTime(CommonMethod.getDate());
            sysRole.setInputer(userId);
            sysRoleDao.save(sysRole);
        }
    }

    @Override
    public void updateSysRole(Collection<SysRole> coll) {
        for (SysRole sysRole : coll) {
            sysRoleDao.update(sysRole);
        }
    }

    @Override
    public List<SysRole> findSysRoleValid() {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
        dc.add(Property.forName("roleStatus").eq("01"));
        return sysRoleDao.findByCriteria(dc);
    }

    @Override
    protected void initBaseDAO(BaseDao<SysRole> baseDao) {

    }


}